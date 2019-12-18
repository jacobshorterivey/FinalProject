-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fureverfriendsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fureverfriendsdb` ;

-- -----------------------------------------------------
-- Schema fureverfriendsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fureverfriendsdb` DEFAULT CHARACTER SET utf8 ;
USE `fureverfriendsdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) NOT NULL,
  `street2` VARCHAR(100) NULL,
  `city` VARCHAR(100) NULL,
  `state_abbr` VARCHAR(2) NULL,
  `zip` INT NOT NULL DEFAULT 00000,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shelter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shelter` ;

CREATE TABLE IF NOT EXISTS `shelter` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(25) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `website_url` TEXT NULL,
  `email` VARCHAR(100) NULL,
  `active` TINYINT NOT NULL DEFAULT 0,
  `address_id` INT NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shelter_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_shelter_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `fname` VARCHAR(50) NOT NULL,
  `lname` VARCHAR(50) NOT NULL,
  `age` INT NULL,
  `phone` VARCHAR(25) NULL,
  `active` TINYINT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trait`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trait` ;

CREATE TABLE IF NOT EXISTS `trait` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet` ;

CREATE TABLE IF NOT EXISTS `pet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `size` VARCHAR(45) NULL,
  `weight` INT NULL,
  `age` INT NULL,
  `color` VARCHAR(45) NULL,
  `fixed` TINYINT NULL,
  `special_conditions` TEXT NULL,
  `adopted` TINYINT NULL,
  `shelter_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pet_shelter1_idx` (`shelter_id` ASC),
  CONSTRAINT `fk_pet_shelter1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `shelter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `image` ;

CREATE TABLE IF NOT EXISTS `image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `species`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `species` ;

CREATE TABLE IF NOT EXISTS `species` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `breed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `breed` ;

CREATE TABLE IF NOT EXISTS `breed` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `hypoallergenic` TINYINT NOT NULL DEFAULT 0,
  `hair_type` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `species_id` INT NOT NULL,
  `size` VARCHAR(25) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_breed_species1_idx` (`species_id` ASC),
  CONSTRAINT `fk_breed_species1`
    FOREIGN KEY (`species_id`)
    REFERENCES `species` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_trait`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_trait` ;

CREATE TABLE IF NOT EXISTS `pet_trait` (
  `pet_id` INT NOT NULL,
  `trait_id` INT NOT NULL,
  PRIMARY KEY (`pet_id`, `trait_id`),
  INDEX `fk_pet_has_traits_traits1_idx` (`trait_id` ASC),
  INDEX `fk_pet_has_traits_pet_idx` (`pet_id` ASC),
  CONSTRAINT `fk_pet_has_traits_pet`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_has_traits_traits1`
    FOREIGN KEY (`trait_id`)
    REFERENCES `trait` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_breed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_breed` ;

CREATE TABLE IF NOT EXISTS `pet_breed` (
  `breed_id` INT NOT NULL,
  `pet_id` INT NOT NULL,
  PRIMARY KEY (`breed_id`, `pet_id`),
  INDEX `fk_breed_has_pet_pet1_idx` (`pet_id` ASC),
  INDEX `fk_breed_has_pet_breed1_idx` (`breed_id` ASC),
  CONSTRAINT `fk_breed_has_pet_breed1`
    FOREIGN KEY (`breed_id`)
    REFERENCES `breed` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_breed_has_pet_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_image` ;

CREATE TABLE IF NOT EXISTS `pet_image` (
  `image_id` INT NOT NULL,
  `pet_id` INT NOT NULL,
  PRIMARY KEY (`image_id`, `pet_id`),
  INDEX `fk_pet_images_has_pet_pet1_idx` (`pet_id` ASC),
  INDEX `fk_pet_images_has_pet_pet_images1_idx` (`image_id` ASC),
  CONSTRAINT `fk_pet_images_has_pet_pet_images1`
    FOREIGN KEY (`image_id`)
    REFERENCES `image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_images_has_pet_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `foster` ;

CREATE TABLE IF NOT EXISTS `foster` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `max_foster` INT NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_foster_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_foster_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foster_breed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `foster_breed` ;

CREATE TABLE IF NOT EXISTS `foster_breed` (
  `foster_id` INT NOT NULL,
  `breed_id` INT NOT NULL,
  PRIMARY KEY (`foster_id`, `breed_id`),
  INDEX `fk_foster_has_breed_breed1_idx` (`breed_id` ASC),
  INDEX `fk_foster_has_breed_foster1_idx` (`foster_id` ASC),
  CONSTRAINT `fk_foster_has_breed_foster1`
    FOREIGN KEY (`foster_id`)
    REFERENCES `foster` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foster_has_breed_breed1`
    FOREIGN KEY (`breed_id`)
    REFERENCES `breed` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foster_trait`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `foster_trait` ;

CREATE TABLE IF NOT EXISTS `foster_trait` (
  `trait_id` INT NOT NULL,
  `foster_id` INT NOT NULL,
  PRIMARY KEY (`trait_id`, `foster_id`),
  INDEX `fk_traits_has_foster_foster1_idx` (`foster_id` ASC),
  INDEX `fk_traits_has_foster_traits1_idx` (`trait_id` ASC),
  CONSTRAINT `fk_traits_has_foster_traits1`
    FOREIGN KEY (`trait_id`)
    REFERENCES `trait` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_traits_has_foster_foster1`
    FOREIGN KEY (`foster_id`)
    REFERENCES `foster` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foster_pet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `foster_pet` ;

CREATE TABLE IF NOT EXISTS `foster_pet` (
  `foster_id` INT NOT NULL,
  `pet_id` INT NOT NULL,
  `id` INT NOT NULL,
  `date_requested` DATETIME NULL,
  `active` TINYINT NULL,
  `notes` TEXT NULL,
  `date_completed` DATETIME NULL,
  INDEX `fk_foster_has_pet_pet1_idx` (`pet_id` ASC),
  INDEX `fk_foster_has_pet_foster1_idx` (`foster_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_foster_has_pet_foster1`
    FOREIGN KEY (`foster_id`)
    REFERENCES `foster` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foster_has_pet_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fost_reputation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fost_reputation` ;

CREATE TABLE IF NOT EXISTS `fost_reputation` (
  `id` INT NOT NULL,
  `foster_id` INT NOT NULL,
  `content` TEXT NULL,
  `rating` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reputation_foster1_idx` (`foster_id` ASC),
  CONSTRAINT `fk_reputation_foster1`
    FOREIGN KEY (`foster_id`)
    REFERENCES `foster` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill` ;

CREATE TABLE IF NOT EXISTS `skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `volunteer_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `volunteer_skill` ;

CREATE TABLE IF NOT EXISTS `volunteer_skill` (
  `skills_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`skills_id`, `user_id`),
  INDEX `fk_volunteer_has_skills_skills1_idx` (`skills_id` ASC),
  INDEX `fk_volunteer_skill_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_volunteer_has_skills_skills1`
    FOREIGN KEY (`skills_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_volunteer_skill_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shelter_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shelter_image` ;

CREATE TABLE IF NOT EXISTS `shelter_image` (
  `shelter_id` INT NOT NULL,
  `images_id` INT NOT NULL,
  PRIMARY KEY (`shelter_id`, `images_id`),
  INDEX `fk_shelter_has_images_images1_idx` (`images_id` ASC),
  INDEX `fk_shelter_has_images_shelter1_idx` (`shelter_id` ASC),
  CONSTRAINT `fk_shelter_has_images_shelter1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `shelter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shelter_has_images_images1`
    FOREIGN KEY (`images_id`)
    REFERENCES `image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `species_foster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `species_foster` ;

CREATE TABLE IF NOT EXISTS `species_foster` (
  `species_id` INT NOT NULL,
  `foster_id` INT NOT NULL,
  PRIMARY KEY (`species_id`, `foster_id`),
  INDEX `fk_species_has_foster_foster1_idx` (`foster_id` ASC),
  INDEX `fk_species_has_foster_species1_idx` (`species_id` ASC),
  CONSTRAINT `fk_species_has_foster_species1`
    FOREIGN KEY (`species_id`)
    REFERENCES `species` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_species_has_foster_foster1`
    FOREIGN KEY (`foster_id`)
    REFERENCES `foster` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_adoption`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_adoption` ;

CREATE TABLE IF NOT EXISTS `pet_adoption` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_requested` DATETIME NULL,
  `accepted_date` DATETIME NULL,
  `meet_date` DATETIME NULL,
  `meet_req_date` DATETIME NULL,
  `meet_notes` TEXT NULL,
  `accepted` TINYINT NULL,
  `reason_denied` TEXT NULL,
  `user_id` INT NOT NULL,
  `pet_id` INT NOT NULL,
  INDEX `fk_pet_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_pet_has_user_pet1_idx` (`pet_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_pet_has_user_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_shelter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_shelter` ;

CREATE TABLE IF NOT EXISTS `user_shelter` (
  `shelter_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`shelter_id`, `user_id`),
  INDEX `fk_shelter_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_shelter_has_user_shelter1_idx` (`shelter_id` ASC),
  CONSTRAINT `fk_shelter_has_user_shelter1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `shelter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shelter_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_image` ;

CREATE TABLE IF NOT EXISTS `user_image` (
  `user_id` INT NOT NULL,
  `image_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `image_id`),
  INDEX `fk_user_has_image_image1_idx` (`image_id` ASC),
  INDEX `fk_user_has_image_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_image_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_image_image1`
    FOREIGN KEY (`image_id`)
    REFERENCES `image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS fureverfriend@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'fureverfriend'@'localhost' IDENTIFIED BY 'fureverfriend';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'fureverfriend'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state_abbr`, `zip`) VALUES (1, '13883 E. 104th place', NULL, 'Commerce City', 'CO', 80022);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state_abbr`, `zip`) VALUES (2, '2129 W Chenango Ave', 'Unit A', 'Littleton', 'CO', 80120);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state_abbr`, `zip`) VALUES (3, '1543 Euclid Cir', NULL, 'Lafayette', 'CO', 80026);

COMMIT;


-- -----------------------------------------------------
-- Data for table `shelter`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `shelter` (`id`, `phone`, `name`, `website_url`, `email`, `active`, `address_id`, `username`, `password`, `role`) VALUES (1, '3037032938', 'Humane Society Of The South Platte Valley', 'hsspv.org', 'info@hsspv.org', 1, 2, 'testShelter', 'test', 'shelter');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `user` (`id`, `email`, `username`, `password`, `role`, `fname`, `lname`, `age`, `phone`, `active`, `address_id`) VALUES (1, 'test.email@gmail.com', 'testUser', 'test', 'user', 'bob', 'boberson', 35, '3033598941', 1, 1);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `role`, `fname`, `lname`, `age`, `phone`, `active`, `address_id`) VALUES (2, 'test2.email@gmail.com', 'secondUser', 'test', 'Admin', 'Liam', 'Wilbur', 24, '3037562190', 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trait`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `trait` (`id`, `description`) VALUES (1, 'Intellegent');
INSERT INTO `trait` (`id`, `description`) VALUES (2, 'Playful');
INSERT INTO `trait` (`id`, `description`) VALUES (3, 'Gentle');
INSERT INTO `trait` (`id`, `description`) VALUES (4, 'Alert');
INSERT INTO `trait` (`id`, `description`) VALUES (5, 'Confident');
INSERT INTO `trait` (`id`, `description`) VALUES (6, 'Faithful');
INSERT INTO `trait` (`id`, `description`) VALUES (7, 'Outgoing');
INSERT INTO `trait` (`id`, `description`) VALUES (8, 'Aggressive');
INSERT INTO `trait` (`id`, `description`) VALUES (9, 'Reserved');
INSERT INTO `trait` (`id`, `description`) VALUES (10, 'Affectionate');

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `pet` (`id`, `name`, `size`, `weight`, `age`, `color`, `fixed`, `special_conditions`, `adopted`, `shelter_id`) VALUES (1, 'Leeloo', 'average', 20, 4, 'Golden', 1, 'None', 0, 1);
INSERT INTO `pet` (`id`, `name`, `size`, `weight`, `age`, `color`, `fixed`, `special_conditions`, `adopted`, `shelter_id`) VALUES (2, 'Yuki', 'large', 50, 8, 'Red and white', 1, 'Needs heart worm pills', 0, 1);
INSERT INTO `pet` (`id`, `name`, `size`, `weight`, `age`, `color`, `fixed`, `special_conditions`, `adopted`, `shelter_id`) VALUES (3, 'Meeko', 'average', 15, 2, 'Black and brown', 0, 'None', 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `species`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `species` (`id`, `name`) VALUES (1, 'Dog');
INSERT INTO `species` (`id`, `name`) VALUES (2, 'Cat');

COMMIT;


-- -----------------------------------------------------
-- Data for table `breed`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (1, 'Shiba Inu', 0, 'Double-coat short', 'The Shiba Inu is a Japanese breed of hunting dog. A small-to-medium breed, it is the smallest of the six original and distinct spitz breeds of dog native to Japan.', 1, 'small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (2, 'Siberian Huskey', 0, 'Double-coat short', 'The Siberian Husky is a medium-sized working dog breed. The breed belongs to the spitz genetic family. It is recognizable by its thickly furred double coat, erect triangular ears, and distinctive markings, and is smaller than a very similar-looking dog, the Alaskan Malamute.', 1, 'medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (3, 'Maine Coon', 0, 'Long', 'The Maine Coon is the largest domesticated cat breed. It has a distinctive physical appearance and valuable hunting skills. It is one of the oldest natural breeds in North America, specifically native to the state of Maine, where it is the official state cat.', 2, 'large');

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_trait`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (1, 4);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (1, 10);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (1, 2);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (2, 9);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (2, 1);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (2, 6);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (3, 2);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (3, 10);
INSERT INTO `pet_trait` (`pet_id`, `trait_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_breed`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `pet_breed` (`breed_id`, `pet_id`) VALUES (1, 1);
INSERT INTO `pet_breed` (`breed_id`, `pet_id`) VALUES (2, 2);
INSERT INTO `pet_breed` (`breed_id`, `pet_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `foster`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `foster` (`id`, `max_foster`, `user_id`) VALUES (1, 4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `foster_breed`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `foster_breed` (`foster_id`, `breed_id`) VALUES (1, 1);
INSERT INTO `foster_breed` (`foster_id`, `breed_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `foster_trait`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `foster_trait` (`trait_id`, `foster_id`) VALUES (2, 1);
INSERT INTO `foster_trait` (`trait_id`, `foster_id`) VALUES (10, 1);
INSERT INTO `foster_trait` (`trait_id`, `foster_id`) VALUES (3, 1);
INSERT INTO `foster_trait` (`trait_id`, `foster_id`) VALUES (7, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `foster_pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `foster_pet` (`foster_id`, `pet_id`, `id`, `date_requested`, `active`, `notes`, `date_completed`) VALUES (1, 1, 1, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fost_reputation`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `fost_reputation` (`id`, `foster_id`, `content`, `rating`) VALUES (1, 1, 'SOOOOOO GOOOD!!', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `skill` (`id`, `name`) VALUES (1, 'Grooming');
INSERT INTO `skill` (`id`, `name`) VALUES (2, 'Training');

COMMIT;


-- -----------------------------------------------------
-- Data for table `volunteer_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `volunteer_skill` (`skills_id`, `user_id`) VALUES (1, 2);
INSERT INTO `volunteer_skill` (`skills_id`, `user_id`) VALUES (2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `species_foster`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `species_foster` (`species_id`, `foster_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_adoption`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `pet_adoption` (`id`, `date_requested`, `accepted_date`, `meet_date`, `meet_req_date`, `meet_notes`, `accepted`, `reason_denied`, `user_id`, `pet_id`) VALUES (1, '2019-12-17', NULL, '2019-12-21', '2019-12-17', NULL, NULL, NULL, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_shelter`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `user_shelter` (`shelter_id`, `user_id`) VALUES (1, 2);

COMMIT;

