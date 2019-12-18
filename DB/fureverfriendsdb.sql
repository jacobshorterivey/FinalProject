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
  `image_id` INT NOT NULL,
  PRIMARY KEY (`shelter_id`, `image_id`),
  INDEX `fk_shelter_has_images_images1_idx` (`image_id` ASC),
  INDEX `fk_shelter_has_images_shelter1_idx` (`shelter_id` ASC),
  CONSTRAINT `fk_shelter_has_images_shelter1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `shelter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shelter_has_images_images1`
    FOREIGN KEY (`image_id`)
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
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `image` (`id`, `image_url`) VALUES (1, 'https://www.westparkanimalhospital.com/wp-content/uploads/2019/05/WestPark_iStock-600994082-1024x706-1.webp');
INSERT INTO `image` (`id`, `image_url`) VALUES (2, 'https://www.thehappycatsite.com/wp-content/uploads/2018/02/Maine-Coon-Cats-HC-long-1024x555.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpWMHkB5cJ4BXvDQOSCEC6DnY8fm588FZnJw6AJemp95zdBALhHA&s');
INSERT INTO `image` (`id`, `image_url`) VALUES (4, 'https://journalism.missouri.edu/wp-content/uploads/2019/07/douglas-wilbur-2015-768x1024-300x400.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (5, 'https://www.animalleague.org/wp-content/uploads/2019/02/logo-humane-society-south-platte.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (6, 'https://d3926qxcw0e1bh.cloudfront.net/post_photos/7e/88/7e88f84df638febec7e91529dd0446be.jpg.max578.jpg');

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
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (1, 'Shiba Inu', 0, 'Double-coat short', 'The Shiba Inu is a Japanese breed of hunting dog. A small-to-medium breed, it is the smallest of the six original and distinct spitz breeds of dog native to Japan.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (2, 'Siberian Huskey', 0, 'Double-coat Medium', 'The Siberian Husky is a medium-sized working dog breed. The breed belongs to the spitz genetic family. It is recognizable by its thickly furred double coat, erect triangular ears, and distinctive markings, and is smaller than a very similar-looking dog, the Alaskan Malamute.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (3, 'Maine Coon', 0, 'Long', 'The Maine Coon is the largest domesticated cat breed. It has a distinctive physical appearance and valuable hunting skills. It is one of the oldest natural breeds in North America, specifically native to the state of Maine, where it is the official state cat.', 2, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (4, 'Yorkshire Terrier', 1, 'Long', 'Beneath the dainty, glossy, floor-length coat of a Yorkshire Terrier beats the heart of a feisty, old-time terrier. Yorkies earned their living as ratters in mines and mills long before they became the beribboned lapdogs of Victorian ladies.', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (5, 'Airedale Terrier', 1, 'Short', 'His size, strength, and unflagging spirit have earned the Airedale Terrier the nickname “The King of Terriers.” The Airedale stands among the world’s most versatile dog breeds and has distinguished himself as hunter, athlete, and companion.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (6, 'Akita', 0, 'Double-coat short', 'Akitas are muscular, double-coated dogs of ancient Japanese lineage famous for their dignity, courage, and loyalty. In their native land, they are venerated as family protectors and symbols of good health, happiness, and long life.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (7, 'Alaskan Malamute', 0, 'Double-coat long', 'An immensely strong, heavy-duty worker of spitz type, the Alaskan Malamute is an affectionate, loyal, and playful but dignified dog recognizable by his well-furred plumed tail carried over the back, erect ears, and substantial bone.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (8, 'American Bulldog ', 0, 'Short', 'Kind but courageous, friendly but dignified, the Bulldog is a thick-set, low-slung, well-muscled bruiser whose “sourmug” face is the universal symbol of courage and tenacity. These docile, loyal companions adapt well to town or country.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (9, 'American Bully (Standard)', 0, 'Short', 'The American Bully should give the impression of great strength for its size. It is a compact and medium/large size dog with a muscular body and blocky head. The American Bully should have the appearance of heavy bone structure with a bulky build and look.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (10, 'American Eskimo Dog (Standard)', 0, 'Double-coat long', 'The American Eskimo Dog combines striking good looks with a quick and clever mind in a total brains-and-beauty package. Neither shy nor aggressive, Eskies are always alert and friendly, though a bit conservative when making new friends.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (11, 'Am. Staffordshire Terrier', 0, 'Short', 'The American Staffordshire Terrier, known to their fans as AmStaffs, are smart, confident, good-natured companions. Their courage is proverbial. A responsibly bred, well-socialized AmStaff is a loyal, trustworthy friend to the end.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (12, 'American Pit Bull Terrier', 0, 'Short', 'The American Pit Bull Terrier is a companion and family dog breed. Originally bred to “bait” bulls, the breed evolved into all-around farm dogs, and later moved into the house to become “nanny dogs” because they were so gentle around children. Their tenacity, gameness, and courage make them popular competitors in the sports of weight pulling, agility, and obedience competition.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (13, 'Australian Cattle Dog (Heeler)', 0, 'Double-coat short', 'The compact but muscular Australian Cattle Dog, also called Blue Heeler or Queensland Heeler, is related to Australia’s famous wild dog, the Dingo. These resilient herders are intelligent enough to routinely outsmart their owners.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (14, 'Australian Kelpie', 0, 'Short', 'The Australian Kelpie is a lithe, active dog, capable of untiring work. He is extremely intelligent, alert, and eager with unlimited energy.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (15, 'Australian Shepherd', 0, 'Medium', 'The Australian Shepherd, a lean, tough ranch dog, is one of those “only in America” stories: a European breed perfected in California by way of Australia. Fixtures on the rodeo circuit, they are closely associated with the cowboy life.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (17, 'Austrialian Terrier', 0, 'Medium', 'The diminutive Australian Terrier is plucky, spirited, and smart—how did they fit so much dog into such a bitty package? Upbeat and lively, the self-assured Aussie approaches life with plenty of the old-time terrier curiosity and grit.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (18, 'Barbet', 0, 'Long', 'An archetypic water dog of France, the Barbet is a rustic breed of medium size and balanced proportions who appears in artwork as early as the 16th century. In profile, the Barbet is slightly rectangular with a substantial head and long, sweeping tail. He has a long, dense covering of curly hair and a distinctive beard. An agile athlete, the Barbet has been used primarily to locate, flush, and retrieve birds. He has a cheerful disposition and is very social and loyal.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (19, 'Basenji', 1, 'Short', 'The Basenji, Africa’s “Barkless Dog,” is a compact, sweet-faced hunter of intelligence and poise. They are unique and beguiling pets, best for owners who can meet their exercise needs and the challenge of training this catlike canine.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (20, 'Basset Hound', 0, 'Short', 'Among the most appealing of the AKC breeds, the endearing and instantly recognizable Basset Hound is a perennial favorite of dog lovers all over the world. This low-slung and low-key hound can be sometimes stubborn, but is always charming.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (21, 'Beagle', 0, 'Short', 'Not only is the Beagle an excellent hunting dog and loyal companion, it is also happy-go-lucky, funny, and—thanks to its pleading expression—cute. They were bred to hunt in packs, so they enjoy company and are generally easygoing.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (22, 'Beauceron', 0, 'Short', 'The Beauceron is imposing and powerful, but also remarkably smart, spirited, and a versatile herder—imagine a Border Collie’s brain in a 100-pound body. They are especially beloved by women as a dashing but sensitive companion and protector.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (23, 'Bedlington Terrier', 1, 'Short', 'Graceful terriers in sheep’s clothing, Bedlington Terriers, named for the English mining shire where they were first bred, are genial housemates, alert watchdogs, versatile athletes, and irresistibly cuddly TV-time companions.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (24, 'Belgian Malinois', 0, 'Short', 'The smart, confident, and versatile Belgian Malinois is a world-class worker who forges an unbreakable bond with his human partner. To deny a Mal activity and the pleasure of your company is to deprive him of his very reasons for being.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (25, 'Belgian Tervuren', 0, 'Long', 'The elegant, agile Belgian Tervuren is a bright and self-assured herding dog of medium size, known to be affectionate and possessive with loved ones. Lots of hard work and challenging play is heaven for this tireless, do-it-all dog.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (26, 'Bernese Mountain Dog', 0, 'Double-coat Long', 'Big, powerful, and built for hard work, the Bernese Mountain Dog is also strikingly beautiful and blessed with a sweet, affectionate nature. Berners are generally placid but are always up for a romp with the owner, whom they live to please.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (27, 'Bichon Frise', 1, 'Double-coat medium', 'The small but sturdy and resilient Bichon Frise stands among the world’s great “personality dogs.” Since antiquity, these irresistible canine comedians have relied on charm, beauty, and intelligence to weather history’s ups and downs.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (28, 'Black and Tan Coonhound', 0, 'Short', 'Large, athletic hunters who work nights, Black and Tan Coonhounds are friendly, easygoing hounds who love company. They are snoozy by the fireside but tenacious when on the trail of the wily raccoon. The B&T is a real American original.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (29, 'Bloodhound', 0, 'Short', 'The world famous “Sleuth Hound” does one thing better than any creature on earth: find people who are lost or hiding. An off-duty Bloodhound is among the canine kingdom’s most docile citizens, but he’s relentless and stubborn on scent.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (30, 'Bluetick Coonhound', 0, 'Short', 'The sleekly beautiful Bluetick Coonhound is a sweet and affectionate charmer who might enjoy snoozing in the shade, but in pursuit of quarry he is relentless, bold, and single-minded. His off-the-charts prey drive must be channeled.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (31, 'Boerboel', 0, 'Short', 'Boerboels are intimidating but discerning guardians of home and family who learned their trade while protecting remote South African homesteads from ferocious predators. They are dominant and confident, also bright and eager to learn.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (32, 'Border Collie', 0, 'Double-coat medium', 'A remarkably bright workaholic, the Border Collie is an amazing dog—maybe a bit too amazing for owners without the time, energy, or means to keep it occupied. These energetic dogs will settle down for cuddle time when the workday is done.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (33, 'Border Terrier', 0, 'Double-coat short', 'Admirers of the upbeat and agile Border Terrier cherish their breed’s reputation as a tough, no-frills working terrier. These plucky, happy, and affectionate dogs are popular pets in town and country. The wiry coat is an easy keeper.', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (34, 'Boston Terrier', 0, 'Short', 'The Boston Terrier is a lively little companion recognized by his tight tuxedo jacket, sporty but compact body, and the friendly glow in his big, round eyes. His impeccable manners have earned him the nickname “The American Gentleman.”', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (35, 'Bouvier des Flandres', 0, 'Double-coat medium', 'They don’t build ’em like this anymore. Burly and barrel-chested, the tousle-coated dog of Flandres is from a time and place where a dog had to work like … well, a dog. These smart and steady all-purpose workers make excellent watchdogs.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (36, 'Boxer', 0, 'Short', 'Loyalty, affection, intelligence, work ethic, and good looks: Boxers are the whole doggy package. Bright and alert, sometimes silly, but always courageous, the Boxer has been among America’s most popular dog breeds for a very long time.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (37, 'Boykin Spaniel', 0, 'Double-coat medium', 'A medium-sized flushing and retrieving dog known for its rich brown coat, the Boykin Spaniel is avid, eager, merry, and trainable. This mellow housedog and tenacious bird dog was once South Carolina’s best-kept secret.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (38, 'Bracco Italiano ', 0, 'Short', 'The Bracco Italiano is one of the oldest of the pointing breeds. It was introduced into the United States in the 1990s and has remained true to its heritage as a versatile gun dog. It is gentle in the home and tireless in the field.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (39, 'Briard', 0, 'Long', 'The Briard packs so much loyalty, love, and spirit into its ample frame that it\'s often described as a \"heart wrapped in fur.\" The dashing good looks of these muscular Frenchmen radiate a distinct aura of Gallic romance and elegance.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (40, 'Brittany', 0, 'Medium', 'Sportsmen on both sides of the Atlantic cherish the agile, energetic Brittany as a stylish and versatile gundog. Bright and eager at home, and tireless afield, Brittanys require a lot of exercises, preferably with their favorite humans.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (41, 'Bull Terrier (Standard)', 0, 'Short', 'Among the most comical and mischievous citizens of dogdom, the Bull Terrier is playful and endearing, sometimes stubborn, but always devoted. These unique “eggheads” are exuberant, muscular companions who thrive on affection and exercise.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (42, 'Bull Terrier (Miniature)', 0, 'Short', 'In most every way the Miniature Bull Terrier is a Bull Terrier, only smaller. These upbeat, mischievous dogs come equipped with terrier fire and fearlessness. If ever a dog could claim the title “Clown Prince of Dogdom,” it’s the Mini.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (43, 'Bulldog', 0, 'Short', 'Kind but courageous, friendly but dignified, the Bulldog is a thick-set, low-slung, well-muscled bruiser whose “sourmug” face is the universal symbol of courage and tenacity. These docile, loyal companions adapt well to town or country.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (44, 'Bullmastiff', 0, 'Short', 'Fearless at work, docile at home, the Bullmastiff is a large, muscular guarder who pursued and held poachers in Merry Old England—merry, we suppose, for everyone but poachers. Bullmastiffs are the result of Bulldog and Mastiff crosses.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (45, 'Cairn Terrier', 1, 'Double-coat medium', 'Cairn Terriers are happy, busy little earthdogs originally bred to fearlessly root out foxes and other small, furred prey in the rocky Scottish countryside. Curious and alert, Cairns like having a place where they can explore and dig.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (46, 'Cane Corso', 0, 'Short', 'Smart, trainable, and of noble bearing, the assertive and confident Cane Corso is a peerless protector. The Corso’s lineage goes back to ancient Roman times, and the breed’s name roughly translates from the Latin as “bodyguard dog.”', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (47, 'Cardigan Welsh Corgi', 0, 'Double-coat short', 'The Cardigan Welsh Corgi is a masterpiece of the breeder’s art: Every aspect of its makeup is perfectly suited to moving cattle, and yet it is so congenial and sweet-faced that it would be a cherished companion even if it never did a day’s work.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (48, 'Catahoula Leopard Dog', 0, 'Short', 'The Catahoula Leopard Dog is a multi-purpose working dog that is well-muscled and powerful, but with a sense of agility and endurance. They are serious while working and playful at home.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (49, 'Caucasian Shepherd (Ovcharka)', 0, 'Double-coat long', 'The Caucasian Shepherd dog is a serious guardian breed and should never be taken lightly. The Caucasian is bold, fearless, self-confident and fierce when a threat is present, but he is soft, devoted, kind and endearing to his family, including other family pets.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (50, 'Cavalier King Charles Spaniel', 0, 'Medium', 'The Cavalier King Charles Spaniel wears his connection to British history in his breed’s name. Cavaliers are the best of two worlds, combining the gentle attentiveness of a toy breed with the verve and athleticism of a sporting spaniel.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (51, 'Chesapeake Bay Retriever', 0, 'Double-coat medium', 'The Chesapeake Bay Retriever, peerless duck dog of the Mid-Atlantic, is an American original who embodies the classic traits of a good retriever: loyal, upbeat, affectionate, and tireless. The Chessie is famous for his waterproof coat.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (52, 'Chihuahua (Long hair)', 0, 'Long', 'The Chihuahua is a tiny dog with a huge personality. A national symbol of Mexico, these alert and amusing \"purse dogs\" stand among the oldest breeds of the Americas, with a lineage going back to the ancient kingdoms of pre-Columbian times.', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (53, 'Chihuahua (Smooth)', 0, 'Short', 'The Chihuahua is a tiny dog with a huge personality. A national symbol of Mexico, these alert and amusing \"purse dogs\" stand among the oldest breeds of the Americas, with a lineage going back to the ancient kingdoms of pre-Columbian times.', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (54, 'Chinese Crested', 1, 'Short', 'With their spotted pink skin, spiky “crested” hairdo, furry socks and feathery tail, you can’t mistake the sweet and slender Chinese Crested for any other breed. This frolicsome, ultra-affectionate companion dog is truly a breed apart.', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (55, 'Chinese Shar-Pei', 0, 'Short', 'This fascinating but challenging breed of ancient pedigree is steadfastly loyal to family, but standoffish with strangers. The Chinese Shar-Pei has physical characteristics that make him a one-of-a-kind companion and guardian dog.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (56, 'Chinook', 0, 'Double-coat medium', 'The pride of Wonalancet, New Hampshire, the Chinook is a rugged working dog and devoted family pet known for intelligence, patience, and eagerness to please. Once on the verge of extinction, the Chinook is among the scarcest AKC breeds.\nThe pride of Wonalancet, New Hampshire, the Chinook is a rugged working dog and devoted family pet known for intelligence, patience, and eagerness to please. Once on the verge of extinction, the Chinook is among the scarcest AKC breeds.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (57, 'Chow Chow', 0, 'Double-coat long', 'The Chow Chow, an all-purpose dog of ancient China, presents the picture of a muscular, deep-chested aristocrat with an air of inscrutable timelessness. Dignified, serious-minded, and aloof, the Chow Chow is a breed of unique delights.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (58, 'Clumber Spaniel', 0, 'Double-coat medium', 'A dignified and mellow hunting companion of kings, the Clumber Spaniel is the largest of the AKC flushing spaniels. For those who can handle some shedding and drooling, the amiable Clumber is an amusing best friend and a gentlemanly housemate.', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (59, 'Cocker Spaniel (American)', 0, 'Long', 'The merry and frolicsome Cocker Spaniel, with his big, dreamy eyes and impish personality, is one of the world’s best-loved breeds. They were developed as hunting dogs, but Cockers gained their wide popularity as all-around companions.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (60, 'Cocker Spaniel (English)', 0, 'Medium', 'English Cocker Spaniel lovers often use the word “merry” to describe their breed. Upbeat in the field and mellow at home, this compact, silky-coated bird dog is widely admired for his delightful personality and irresistible good looks.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (61, 'Collie (Smooth)', 0, 'Double-coat short', 'The majestic Collie, thanks to a hundred years as a pop-culture star, is among the world’s most recognizable and beloved dog breeds. The full-coated “rough” Collie is the more familiar variety, but there is also a sleek “smooth” Collie.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (62, 'Collie (Rough)', 0, 'Double-coat medium', 'The majestic Collie, thanks to a hundred years as a pop-culture star, is among the world’s most recognizable and beloved dog breeds. The full-coated “rough” Collie is the more familiar variety, but there is also a sleek “smooth” Collie.', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (63, 'Coton De Tulear', 1, 'Medium', 'The Coton de Tulear, “Royal Dog of Madagascar,” is a bright, happy-go-lucky companion dog whose favorite activities include clowning, cavorting, and following their special human around the house. The Coton is small but robustly sturdy.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (64, 'Dachshund (smooth)', 0, 'Short', 'The famously long, low silhouette, ever-alert expression, and bold, vivacious personality of the Dachshund have made him a superstar of the canine kingdom. Dachshunds come in two sizes and in three coat types of various colors and patterns.', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (65, 'Dachshund (wire/long haired)', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (66, 'Dalmatian', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (67, 'Doberman Pinscher', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (68, 'Dogo Argentino', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (69, 'Dutch Shepherd', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (70, 'English Setter', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (71, 'English Shepherd', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (72, 'English Springer Spaniel', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (73, 'English Toy Spaniel', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (74, 'English Toy Terrier', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (75, 'Eurasier', 0, 'Double-coat medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (76, 'Field Spaniel', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (77, 'Finnish Lapphund', 0, 'Double-coat Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (78, 'Finnish Spitz', 0, 'Double-coat short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (79, 'Flat Coat Retriever', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (80, 'French Bulldog', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (81, 'German Pinscher', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (82, 'German Shepherd Dog', 0, 'Double-coat medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (83, 'German Shorthaired Pointer', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (84, 'Giant Schnauzer', 1, 'Double-coat medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (85, 'Glen of Imaal Terrier', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (86, 'Golden Retriever', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (87, 'Gordon Setter', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (88, 'Great Dane', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (89, 'Great Pyrenees', 0, 'Double-coat long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (90, 'Greyhound', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (91, 'Harrier', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (92, 'Havanese', 1, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (93, 'Irish Setter', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (94, 'Irish Terrier', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (95, 'Irish Wolfhound', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (96, 'Italian Greyhound', 1, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (97, 'Japanese Chin', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (98, 'Japanese Spitz', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (99, 'Keeshond', 0, 'Double-coat long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (100, 'Komondor', 1, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (101, 'Kooikerhondje', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (102, 'Kuvasz', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (103, 'Labrador Retriever', 0, 'Double-coat short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (104, 'Lagotto Romagnolo', 1, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (105, 'Leonberger', 0, 'Double-coat medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (106, 'Lhasa Apso', 1, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (107, 'Maltese', 1, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (108, 'Miniature American Shepherd', 0, 'Double-coat medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (109, 'Miniature Pinscher', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (110, 'Miniature Schnauzer', 1, 'Double-coat long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (111, 'Newfoundland', 0, 'Double-coat long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (112, 'Norfolk Terrier', 0, 'Medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (113, 'Norwich Terrier', 0, 'Double-coat long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (114, 'Nova Scotia Duck Tolling Retriever', 0, 'Double-coat medium', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (115, 'Olde English Bulldogge', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (116, 'Old English Sheepdog', 0, 'Double-coat long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (117, 'Papillon', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (118, 'Parson Russell Terrier', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (119, 'Patterdale Terrier (Smooth or Broken)', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (120, 'Patterdale Terrier (Rough)', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (121, 'Pekingese', 0, 'Long', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (122, 'Pembroke Welsh Corgi', 0, 'Double-coat short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (123, 'Pharaoh Hound', 0, 'Short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (124, 'Plott', 0, 'Double-coat short', '', 1, '');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (125, 'Pointer (English)', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (126, 'Pomeranian', 0, 'Double-coat long', '', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (127, 'Poodle (Miniature)', 1, 'Medium', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (128, 'Poodle (Standard)', 1, 'Medium', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (129, 'Poodle (Toy)', 1, 'Medium', '', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (130, 'Portugese Water Dog', 1, 'Long', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (131, 'Presa Canario', 0, 'Short', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (132, 'Pug', 0, 'Short', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (133, 'Puli', 0, 'Long', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (134, 'Pumi', 0, 'Medium', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (135, 'Rat Terrier', 0, 'Short', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (136, 'Redbone Coonhound', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (137, 'Rhodesian Ridgeback', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (138, 'Rottweiler', 0, 'Double-coat medium', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (139, 'Russian Toy', 0, 'Short', '', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (140, 'Saluki', 0, 'Long', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (141, 'Samoyed', 0, 'Double-coat long', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (142, 'Schipperke', 0, 'Double-coat medium', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (143, 'Scottish Deerhound', 0, 'Long', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (144, 'Scottish Terrier', 1, 'Double-coat long', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (145, 'Shetland Sheepdog (Sheltie)', 0, 'Double-coat long', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (146, 'Shih Tzu', 1, 'Double-coat long', '', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (147, 'Shiloh Shepherd', 0, 'Double-coat long', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (148, 'Silky Terrier', 0, 'Long', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (149, 'Smooth Fox Terrier', 0, 'Medium', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (150, 'Soft Coated Wheaten Terrier', 1, 'Long', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (151, 'Spanish Water Dog', 0, 'Long', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (152, 'Spinone Italiano', 0, 'Medium', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (153, 'St. Bernard', 0, 'Short', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (154, 'Staffordshire Bull Terrier', 0, 'Short', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (155, 'Standard Schnauzer', 0, 'Double-coat long', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (156, 'Swedish Vallhund ', 0, 'Double-coat medium', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (157, 'Thai Ridgeback', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (158, 'Tibetan Mastiff', 0, 'Double-coat long', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (159, 'Tibetan Spaniel', 0, 'Double-coat long', '', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (160, 'Tibetan Terrier', 0, 'Long', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (161, 'Toy Fox Terrier', 0, 'Short', '', 1, 'Toy');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (162, 'Treeing Walker Coonhound', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (163, 'Vizsla', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (164, 'Weimaraner', 0, 'Short', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (165, 'Welsh Springer Spaniel', 0, 'Medium', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (166, 'West Highland White Terrier', 1, 'Double-coat long', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (167, 'Whippet', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (168, 'White Shepherd ', 0, 'Double-coat long', '', 1, 'Large');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (169, 'Wire Fox Terrier', 0, 'Long', '', 1, 'Small');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (170, 'Wirehaired Pointing Griffon', 0, 'Double-coat long', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (171, 'Xoloitzcuintli', 0, 'Short', '', 1, 'Medium');
INSERT INTO `breed` (`id`, `name`, `hypoallergenic`, `hair_type`, `description`, `species_id`, `size`) VALUES (172, 'Afghan Hound', 1, 'Long', '', 1, 'Medium');

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
-- Data for table `pet_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `pet_image` (`image_id`, `pet_id`) VALUES (1, 3);
INSERT INTO `pet_image` (`image_id`, `pet_id`) VALUES (2, 3);

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
INSERT INTO `foster_pet` (`foster_id`, `pet_id`, `id`, `date_requested`, `active`, `notes`, `date_completed`) VALUES (1, 1, 1, NULL, false, NULL, NULL);

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
-- Data for table `shelter_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `shelter_image` (`shelter_id`, `image_id`) VALUES (1, 5);
INSERT INTO `shelter_image` (`shelter_id`, `image_id`) VALUES (1, 6);

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
INSERT INTO `pet_adoption` (`id`, `date_requested`, `accepted_date`, `meet_date`, `meet_req_date`, `meet_notes`, `accepted`, `reason_denied`, `user_id`, `pet_id`) VALUES (1, '2019-12-17', NULL, '2019-12-21', '2019-12-17', NULL, false, NULL, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_shelter`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `user_shelter` (`shelter_id`, `user_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `fureverfriendsdb`;
INSERT INTO `user_image` (`user_id`, `image_id`) VALUES (1, 3);
INSERT INTO `user_image` (`user_id`, `image_id`) VALUES (2, 4);

COMMIT;

