-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fooddb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fooddb` ;

-- -----------------------------------------------------
-- Schema fooddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fooddb` DEFAULT CHARACTER SET utf8 ;
USE `fooddb` ;

-- -----------------------------------------------------
-- Table `food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food` ;

CREATE TABLE IF NOT EXISTS `food` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS owner@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'owner'@'localhost' IDENTIFIED BY 'owner';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'owner'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `food`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `food` (`id`, `name`, `category`, `price`) VALUES (1, 'wings', 'appetizers', 8.99);

COMMIT;

