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
  `username` VARCHAR(50) NULL,
  `password` VARCHAR(50) NULL,
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
  `username` VARCHAR(50) NULL,
  `password` VARCHAR(50) NOT NULL,
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
  `traits_id` INT NOT NULL,
  PRIMARY KEY (`pet_id`, `traits_id`),
  INDEX `fk_pet_has_traits_traits1_idx` (`traits_id` ASC),
  INDEX `fk_pet_has_traits_pet_idx` (`pet_id` ASC),
  CONSTRAINT `fk_pet_has_traits_pet`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_has_traits_traits1`
    FOREIGN KEY (`traits_id`)
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
  `traits_id` INT NOT NULL,
  `foster_id` INT NOT NULL,
  PRIMARY KEY (`traits_id`, `foster_id`),
  INDEX `fk_traits_has_foster_foster1_idx` (`foster_id` ASC),
  INDEX `fk_traits_has_foster_traits1_idx` (`traits_id` ASC),
  CONSTRAINT `fk_traits_has_foster_traits1`
    FOREIGN KEY (`traits_id`)
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
  PRIMARY KEY (`id`, `foster_id`),
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
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `user` (`id`, `email`, `username`, `password`, `fname`, `lname`, `age`, `phone`, `active`, `address_id`) VALUES (1, 'test.email@gmail.com', 'testUser', 'test', 'bob', 'boberson', 35, '3033598941', 1, 1);

COMMIT;

