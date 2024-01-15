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
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `menu` ;

CREATE TABLE IF NOT EXISTS `menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `description` TEXT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_menu_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_menu_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `seating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `seating` ;

CREATE TABLE IF NOT EXISTS `seating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `capacity` INT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer_order` ;

CREATE TABLE IF NOT EXISTS `customer_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `table_id` INT NOT NULL,
  `order_date` DATETIME NULL,
  `total_amount` DOUBLE NULL,
  `status` VARCHAR(45) NULL,
  `server_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_order_customer1_idx` (`customer_id` ASC),
  INDEX `fk_order_table1_idx` (`table_id` ASC),
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_table1`
    FOREIGN KEY (`table_id`)
    REFERENCES `seating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_item` ;

CREATE TABLE IF NOT EXISTS `order_item` (
  `id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  `quantity` INT NULL,
  `subtotal` DOUBLE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_item_order1_idx` (`order_id` ASC),
  INDEX `fk_order_item_menu1_idx` (`item_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_order_item_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `customer_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_item_menu1`
    FOREIGN KEY (`item_id`)
    REFERENCES `menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employee` ;

CREATE TABLE IF NOT EXISTS `employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation` ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `table_id` INT NOT NULL,
  `reservation_date` DATETIME NULL,
  `time` VARCHAR(45) NULL,
  `staus` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_reservation_customer1_idx` (`customer_id` ASC),
  INDEX `fk_reservation_table1_idx` (`table_id` ASC),
  CONSTRAINT `fk_reservation_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_table1`
    FOREIGN KEY (`table_id`)
    REFERENCES `seating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment` ;

CREATE TABLE IF NOT EXISTS `payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `payment_date` DATETIME NULL,
  `amount` DOUBLE NULL,
  `payment_method` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idpayment_UNIQUE` (`id` ASC),
  INDEX `fk_payment_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_payment_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `customer_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `rating` VARCHAR(45) NULL,
  `comment` VARCHAR(100) NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_review_customer1_idx` (`customer_id` ASC),
  INDEX `fk_review_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_review_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `customer_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Appetizer');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Meal');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Dessert');

COMMIT;


-- -----------------------------------------------------
-- Data for table `menu`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `menu` (`id`, `name`, `price`, `description`, `category_id`) VALUES (1, 'Wings', 8.99, 'Bone in or bone out', 1);
INSERT INTO `menu` (`id`, `name`, `price`, `description`, `category_id`) VALUES (2, 'Margherita Pizza', 10.99, 'Classic tomato and mozzarella', 2);
INSERT INTO `menu` (`id`, `name`, `price`, `description`, `category_id`) VALUES (3, 'Caesar Salad', 8.99, 'Romaine lettuce, croutons, Caesar', 1);
INSERT INTO `menu` (`id`, `name`, `price`, `description`, `category_id`) VALUES (4, 'Grilled Salmon', 16.99, 'Freshly grilled salmon fillet ', 2);
INSERT INTO `menu` (`id`, `name`, `price`, `description`, `category_id`) VALUES (5, 'Tiramisu', 6.99, 'Italian coffee-flavored dessert', 3);
INSERT INTO `menu` (`id`, `name`, `price`, `description`, `category_id`) VALUES (6, 'Spaghetti Bolognese', 12.99, 'Traditional Italian pasta dish', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES (101, 'John', 'Smith', 'john.smith@example.com', '1234567890');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES (102, 'Emily', 'Johnson', 'emily.j@example.com', '1987654321');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES (103, 'Michael', 'Davis', 'michael.d@example.com', '1122334455');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES (104, 'Sarah', 'Miller', 'sarah.m@example.com', '1567890123');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES (105, 'David', 'Wilson ', 'david.w@example.com', '1908765432');

COMMIT;


-- -----------------------------------------------------
-- Data for table `seating`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `seating` (`id`, `name`, `capacity`, `status`) VALUES (1, 'Table 1', 4, 'Vacant');
INSERT INTO `seating` (`id`, `name`, `capacity`, `status`) VALUES (2, 'Table 2', 6, 'Occupied    ');
INSERT INTO `seating` (`id`, `name`, `capacity`, `status`) VALUES (3, 'Table 3', 2, 'Reserved    ');
INSERT INTO `seating` (`id`, `name`, `capacity`, `status`) VALUES (4, 'Table 4', 8, 'Vacant      ');
INSERT INTO `seating` (`id`, `name`, `capacity`, `status`) VALUES (5, 'Table 5', 4, 'Reserved    ');

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer_order`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `customer_order` (`id`, `customer_id`, `table_id`, `order_date`, `total_amount`, `status`, `server_id`) VALUES (1, 101, 3, '2024-01-15 18:30:00', 69.96, 'Completed    ', 5);
INSERT INTO `customer_order` (`id`, `customer_id`, `table_id`, `order_date`, `total_amount`, `status`, `server_id`) VALUES (2, 102, 1, '2024-01-20 19:15:00', 10.99, 'In Progress', 3);
INSERT INTO `customer_order` (`id`, `customer_id`, `table_id`, `order_date`, `total_amount`, `status`, `server_id`) VALUES (3, 103, 2, '2024-02-05 17:45:00', 50.97, 'Completed    ', 1);
INSERT INTO `customer_order` (`id`, `customer_id`, `table_id`, `order_date`, `total_amount`, `status`, `server_id`) VALUES (4, 104, 5, '2024-02-10 20:30:00', 40.98, 'In Progress', 4);
INSERT INTO `customer_order` (`id`, `customer_id`, `table_id`, `order_date`, `total_amount`, `status`, `server_id`) VALUES (5, 105, 4, '2024-03-01 18:00:00', 28.97, 'Completed    ', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `order_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `order_item` (`id`, `order_id`, `item_id`, `quantity`, `subtotal`) VALUES (1, 1, 1, 2, 25.98);
INSERT INTO `order_item` (`id`, `order_id`, `item_id`, `quantity`, `subtotal`) VALUES (2, 1, 3, 1, 8.99);
INSERT INTO `order_item` (`id`, `order_id`, `item_id`, `quantity`, `subtotal`) VALUES (3, 2, 2, 1, 10.99);
INSERT INTO `order_item` (`id`, `order_id`, `item_id`, `quantity`, `subtotal`) VALUES (4, 3, 4, 3, 50.97);
INSERT INTO `order_item` (`id`, `order_id`, `item_id`, `quantity`, `subtotal`) VALUES (5, 4, 5, 2, 13.98);

COMMIT;


-- -----------------------------------------------------
-- Data for table `employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `position`) VALUES (1, 'R', 'Johnson', 'Waiter');
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `position`) VALUES (2, 'E', 'Davis', 'Waiter');
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `position`) VALUES (3, 'D', 'Smith', 'Waiter');
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `position`) VALUES (4, 'S', 'Miller', 'Waiter');
INSERT INTO `employee` (`id`, `first_name`, `last_name`, `position`) VALUES (5, 'M', 'Wilson', 'Waiter');

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `reservation` (`id`, `customer_id`, `table_id`, `reservation_date`, `time`, `staus`) VALUES (1, 101, 3, '2024-01-15', '18:00:00', 'Confirmed');
INSERT INTO `reservation` (`id`, `customer_id`, `table_id`, `reservation_date`, `time`, `staus`) VALUES (2, 102, 1, '2024-01-20', '19:30:00', 'Pending');
INSERT INTO `reservation` (`id`, `customer_id`, `table_id`, `reservation_date`, `time`, `staus`) VALUES (3, 103, 2, '2024-02-05', '17:00:00', 'Canceled');
INSERT INTO `reservation` (`id`, `customer_id`, `table_id`, `reservation_date`, `time`, `staus`) VALUES (4, 105, 5, '2024-02-10', '20:00:00', 'Confirmed');

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `payment` (`id`, `order_id`, `payment_date`, `amount`, `payment_method`) VALUES (1, 1, '2024-01-15 19:45:00', 50.00, 'Credit Card');
INSERT INTO `payment` (`id`, `order_id`, `payment_date`, `amount`, `payment_method`) VALUES (2, 2, '2024-01-20 20:15:00', 30.00, 'Cash');
INSERT INTO `payment` (`id`, `order_id`, `payment_date`, `amount`, `payment_method`) VALUES (3, 3, '2024-02-05 18:30:00', 25.00, 'Credit Card');
INSERT INTO `payment` (`id`, `order_id`, `payment_date`, `amount`, `payment_method`) VALUES (4, 4, '2024-02-10 21:00:00', 40.00, 'Debit Card');
INSERT INTO `payment` (`id`, `order_id`, `payment_date`, `amount`, `payment_method`) VALUES (5, 5, '2024-03-01 19:00:00', 15.00, 'Cash');

COMMIT;


-- -----------------------------------------------------
-- Data for table `review`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `review` (`id`, `customer_id`, `order_id`, `rating`, `comment`, `date`) VALUES (1, 101, 1, '4.5', 'Excellent service and food!', '2024-01-16 09:30:00');
INSERT INTO `review` (`id`, `customer_id`, `order_id`, `rating`, `comment`, `date`) VALUES (2, 102, 2, '3.0', 'Pizza was a bit too salty. ', '2024-01-21 10:45:00');
INSERT INTO `review` (`id`, `customer_id`, `order_id`, `rating`, `comment`, `date`) VALUES (3, 103, 3, '5.0', 'Amazing food! Will be back.', '2024-02-06 08:15:00');
INSERT INTO `review` (`id`, `customer_id`, `order_id`, `rating`, `comment`, `date`) VALUES (4, 104, 4, '4.0', 'Good atmosphere, friendly staff', '2024-02-11 12:00:00');
INSERT INTO `review` (`id`, `customer_id`, `order_id`, `rating`, `comment`, `date`) VALUES (5, 105, 5, '4.5', 'Desserts were fantastic.', '2024-03-02 11:00:00');

COMMIT;

