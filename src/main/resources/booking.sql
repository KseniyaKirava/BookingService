-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema kirova
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kirova
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kirova` DEFAULT CHARACTER SET utf8 ;
USE `kirova` ;

-- -----------------------------------------------------
-- Table `kirova`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `middle_name` VARCHAR(50) NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `id_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`authorities` (
  `authority` VARCHAR(50) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  INDEX `fk_authorities_users1_idx` (`username` ASC),
  CONSTRAINT `fk_authorities_users1`
    FOREIGN KEY (`username`)
    REFERENCES `kirova`.`users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`room_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`room_classes` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`rooms` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `number` VARCHAR(50) NOT NULL,
  `capacity` INT NOT NULL,
  `cost` DOUBLE NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `room_classes_id` BIGINT(20) NOT NULL,
  `average_assessment` DOUBLE NULL,
  PRIMARY KEY (`id`, `room_classes_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_rooms_room_classes1_idx` (`room_classes_id` ASC),
  CONSTRAINT `fk_rooms_room_classes1`
    FOREIGN KEY (`room_classes_id`)
    REFERENCES `kirova`.`room_classes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`reservations` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `reservation_date` BIGINT(20) NOT NULL,
  `checkin_date` BIGINT(20) NOT NULL,
  `checkout_date` BIGINT(20) NOT NULL,
  `total_cost` DOUBLE NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `users_username` VARCHAR(50) NOT NULL,
  `rooms_id` BIGINT(20) NOT NULL,
  `assessment` TINYINT(1) NULL,
  PRIMARY KEY (`id`, `users_username`, `rooms_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_reservations_rooms1_idx` (`rooms_id` ASC),
  INDEX `fk_reservations_users1_idx` (`users_username` ASC),
  CONSTRAINT `fk_reservations_rooms1`
    FOREIGN KEY (`rooms_id`)
    REFERENCES `kirova`.`rooms` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservations_users1`
    FOREIGN KEY (`users_username`)
    REFERENCES `kirova`.`users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`facilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`facilities` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`rooms_has_facilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`rooms_has_facilities` (
  `rooms_id` BIGINT(20) NOT NULL,
  `facilities_id` BIGINT(20) NOT NULL,
  `count` INT NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`rooms_id`, `facilities_id`),
  INDEX `fk_rooms_has_facilities_facilities1_idx` (`facilities_id` ASC),
  INDEX `fk_rooms_has_facilities_rooms1_idx` (`rooms_id` ASC),
  CONSTRAINT `fk_rooms_has_facilities_rooms1`
    FOREIGN KEY (`rooms_id`)
    REFERENCES `kirova`.`rooms` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rooms_has_facilities_facilities1`
    FOREIGN KEY (`facilities_id`)
    REFERENCES `kirova`.`facilities` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `kirova` ;

-- -----------------------------------------------------
-- Placeholder table for view `kirova`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `kirova`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kirova`.`view1`;
USE `kirova`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `kirova`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `first_name`, `last_name`, `middle_name`, `enabled`) VALUES ('vasia', 'vasia@gmail.com', '923c9d8ac13b5c8c71089e1d321ed605ee36d68df3b12095def054fa48189e4e', 'Василий', 'Каримов', 'Петрович', true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `first_name`, `last_name`, `middle_name`, `enabled`) VALUES ('gromov', 'k.gromov@tut.by', 'b8c0435e852a909d4273cb94361407aef4f545dc6bdfd07fbfa81a398f0df00d', 'Константин', 'Громов', 'Леонидович', true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `first_name`, `last_name`, `middle_name`, `enabled`) VALUES ('jsmith', 'j.smith@outlook.com', '3e4f201d4e20fcf7dc29d977339dfbb54a93b43d9f11e7e526034b0681ec6bf6', 'John', 'Smith', 'K.', true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `first_name`, `last_name`, `middle_name`, `enabled`) VALUES ('jdoe', 'j.doe@gmail.com', '4f1aea101019e2bee28bd5cb8a71dc47e2a033f908f36d5728978ec4d681d6d1', 'Jane', 'Doe', NULL, true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `first_name`, `last_name`, `middle_name`, `enabled`) VALUES ('admin', 'admin@admin.com', '85ba22d78544c72b1c0c6c31d4cb414f0667414db42379e831be7fdba7e4adcc', 'Admin', 'Admin', NULL, true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `first_name`, `last_name`, `middle_name`, `enabled`) VALUES ('mcdonald', 'm.sally@gmail.com', 'e41005aef647ab855fa4b63c297b3719177e99b7f6326de8685194f3ea00f835', 'Sally', 'McDonald', NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`authorities`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`authorities` (`authority`, `username`, `enabled`) VALUES ('admin', 'admin', true);
INSERT INTO `kirova`.`authorities` (`authority`, `username`, `enabled`) VALUES ('user', 'vasia', true);
INSERT INTO `kirova`.`authorities` (`authority`, `username`, `enabled`) VALUES ('user', 'gromov', true);
INSERT INTO `kirova`.`authorities` (`authority`, `username`, `enabled`) VALUES ('user', 'jsmith', true);
INSERT INTO `kirova`.`authorities` (`authority`, `username`, `enabled`) VALUES ('user', 'jdoe', true);
INSERT INTO `kirova`.`authorities` (`authority`, `username`, `enabled`) VALUES ('manager', 'mcdonald', true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`room_classes`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`room_classes` (`id`, `name`, `enabled`) VALUES (1, 'люкс', true);
INSERT INTO `kirova`.`room_classes` (`id`, `name`, `enabled`) VALUES (2, 'стандартный', true);
INSERT INTO `kirova`.`room_classes` (`id`, `name`, `enabled`) VALUES (3, 'президентский', true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`rooms`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (1, 'двухместный номер с террасой', '202', 2, 100.0, true, 1, NULL);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (2, 'двухместный номер с видом на океан', '201', 2, 120.0, true, 1, NULL);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (3, 'одноместный номер', '101', 1, 50.0, true, 2, NULL);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (4, 'двухместный номер с 2 кроватями', '102', 2, 70.0, true, 2, NULL);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (5, 'четырехместный номер', '301', 4, 110.0, true, 2, NULL);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (6, 'двухместный номер с диваном', '203', 3, 85.00, true, 1, NULL);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (7, 'одноместный номер', '302', 1, 60.0, true, 1, NULL);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `room_classes_id`, `average_assessment`) VALUES (8, 'президентский номер с видом на океан', '401', 5, 200.0, true, 3, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`reservations`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`reservations` (`id`, `reservation_date`, `checkin_date`, `checkout_date`, `total_cost`, `enabled`, `users_username`, `rooms_id`, `assessment`) VALUES (1, 1539118800000, 1539118800000, 1539550800000, 500.00, true, 'vasia', 1, 5);
INSERT INTO `kirova`.`reservations` (`id`, `reservation_date`, `checkin_date`, `checkout_date`, `total_cost`, `enabled`, `users_username`, `rooms_id`, `assessment`) VALUES (2, 1539723600000, 1539723600000, 1540155600000, 300.00, true, 'gromov', 7, 5);
INSERT INTO `kirova`.`reservations` (`id`, `reservation_date`, `checkin_date`, `checkout_date`, `total_cost`, `enabled`, `users_username`, `rooms_id`, `assessment`) VALUES (3, 1540069200000, 1540069200000, 1540674000000, 490.00, true, 'jdoe', 4, 4);
INSERT INTO `kirova`.`reservations` (`id`, `reservation_date`, `checkin_date`, `checkout_date`, `total_cost`, `enabled`, `users_username`, `rooms_id`, `assessment`) VALUES (4, 1539550800000, 1539550800000, 1539723600000, 130.00, true, 'vasia', 6, 3);
INSERT INTO `kirova`.`reservations` (`id`, `reservation_date`, `checkin_date`, `checkout_date`, `total_cost`, `enabled`, `users_username`, `rooms_id`, `assessment`) VALUES (5, 1544562000000, 1552338000000, 1553202000000, 2000, true, 'vasia', 8, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`facilities`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`facilities` (`id`, `name`, `enabled`) VALUES (1, 'двуспальная кровать', true);
INSERT INTO `kirova`.`facilities` (`id`, `name`, `enabled`) VALUES (2, 'односпальная кровать', true);
INSERT INTO `kirova`.`facilities` (`id`, `name`, `enabled`) VALUES (3, 'фен', true);
INSERT INTO `kirova`.`facilities` (`id`, `name`, `enabled`) VALUES (4, 'утюг', true);
INSERT INTO `kirova`.`facilities` (`id`, `name`, `enabled`) VALUES (5, 'диван', true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`rooms_has_facilities`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (1, 1, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (1, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (1, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (2, 1, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (2, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (2, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (3, 2, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (3, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (4, 2, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (4, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (5, 1, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (5, 2, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (5, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (6, 1, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (6, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (6, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (6, 5, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (7, 2, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (7, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (7, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (8, 1, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (8, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (8, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`rooms_id`, `facilities_id`, `count`, `enabled`) VALUES (8, 5, 1, true);

COMMIT;

