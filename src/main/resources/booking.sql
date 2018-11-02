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
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `middleName` VARCHAR(50) NULL,
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
-- Table `kirova`.`requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`requests` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `roomCapacity` INT NOT NULL,
  `checkinDate` BIGINT(20) NOT NULL,
  `checkoutDate` BIGINT(20) NOT NULL,
  `roomClass` VARCHAR(80) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `usersUsername` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`, `usersUsername`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_requests_users1_idx` (`usersUsername` ASC),
  CONSTRAINT `fk_requests_users1`
    FOREIGN KEY (`usersUsername`)
    REFERENCES `kirova`.`users` (`username`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
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
  `roomClassesId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `roomClassesId`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_rooms_room_classes1_idx` (`roomClassesId` ASC),
  CONSTRAINT `fk_rooms_room_classes1`
    FOREIGN KEY (`roomClassesId`)
    REFERENCES `kirova`.`room_classes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`reservations` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `reservationDate` BIGINT(20) NOT NULL,
  `checkinDate` BIGINT(20) NOT NULL,
  `checkoutDate` BIGINT(20) NOT NULL,
  `totalCost` DOUBLE NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `requestsId` BIGINT(20) NOT NULL,
  `requestsUsersUsername` VARCHAR(50) NOT NULL,
  `roomsId` BIGINT(20) NOT NULL,
  `roomsRoomClassesId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `roomsId`, `roomsRoomClassesId`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_reservations_requests1_idx` (`requestsId` ASC, `requestsUsersUsername` ASC),
  INDEX `fk_reservations_rooms1_idx` (`roomsId` ASC, `roomsRoomClassesId` ASC),
  CONSTRAINT `fk_reservations_requests1`
    FOREIGN KEY (`requestsId` , `requestsUsersUsername`)
    REFERENCES `kirova`.`requests` (`id` , `usersUsername`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservations_rooms1`
    FOREIGN KEY (`roomsId` , `roomsRoomClassesId`)
    REFERENCES `kirova`.`rooms` (`id` , `roomClassesId`)
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
  `roomsId` BIGINT(20) NOT NULL,
  `facilitiesId` BIGINT(20) NOT NULL,
  `count` INT NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`roomsId`, `facilitiesId`),
  INDEX `fk_rooms_has_facilities_facilities1_idx` (`facilitiesId` ASC),
  INDEX `fk_rooms_has_facilities_rooms1_idx` (`roomsId` ASC),
  CONSTRAINT `fk_rooms_has_facilities_rooms1`
    FOREIGN KEY (`roomsId`)
    REFERENCES `kirova`.`rooms` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rooms_has_facilities_facilities1`
    FOREIGN KEY (`facilitiesId`)
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
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `firstName`, `lastName`, `middleName`, `enabled`) VALUES ('vasia', 'vasia@gmail.com', '923c9d8ac13b5c8c71089e1d321ed605ee36d68df3b12095def054fa48189e4e', 'Василий', 'Каримов', 'Петрович', true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `firstName`, `lastName`, `middleName`, `enabled`) VALUES ('gromov', 'k.gromov@tut.by', 'b8c0435e852a909d4273cb94361407aef4f545dc6bdfd07fbfa81a398f0df00d', 'Константин', 'Громов', 'Леонидович', true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `firstName`, `lastName`, `middleName`, `enabled`) VALUES ('jsmith', 'j.smith@outlook.com', '3e4f201d4e20fcf7dc29d977339dfbb54a93b43d9f11e7e526034b0681ec6bf6', 'John', 'Smith', 'K.', true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `firstName`, `lastName`, `middleName`, `enabled`) VALUES ('jdoe', 'j.doe@gmail.com', '4f1aea101019e2bee28bd5cb8a71dc47e2a033f908f36d5728978ec4d681d6d1', 'Jane', 'Doe', NULL, true);
INSERT INTO `kirova`.`users` (`username`, `email`, `password`, `firstName`, `lastName`, `middleName`, `enabled`) VALUES ('admin', 'admin@admin.com', '85ba22d78544c72b1c0c6c31d4cb414f0667414db42379e831be7fdba7e4adcc', 'Admin', 'Admin', NULL, true);

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`requests`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`requests` (`id`, `roomCapacity`, `checkinDate`, `checkoutDate`, `roomClass`, `enabled`, `usersUsername`) VALUES (1, 8, 1539118800000, 1539550800000, 'president', true, 'vasia');
INSERT INTO `kirova`.`requests` (`id`, `roomCapacity`, `checkinDate`, `checkoutDate`, `roomClass`, `enabled`, `usersUsername`) VALUES (2, 2, 1539118800000, 1539550800000, 'luxe', true, 'jdoe');
INSERT INTO `kirova`.`requests` (`id`, `roomCapacity`, `checkinDate`, `checkoutDate`, `roomClass`, `enabled`, `usersUsername`) VALUES (3, 1, 1539723600000, 1540155600000, 'luxe', true, 'vasia');
INSERT INTO `kirova`.`requests` (`id`, `roomCapacity`, `checkinDate`, `checkoutDate`, `roomClass`, `enabled`, `usersUsername`) VALUES (4, 2, 1540069200000, 1540674000000, 'standard', true, 'jsmith');
INSERT INTO `kirova`.`requests` (`id`, `roomCapacity`, `checkinDate`, `checkoutDate`, `roomClass`, `enabled`, `usersUsername`) VALUES (5, 3, 1539550800000, 1539723600000, 'luxe', true, 'jsmith');
INSERT INTO `kirova`.`requests` (`id`, `roomCapacity`, `checkinDate`, `checkoutDate`, `roomClass`, `enabled`, `usersUsername`) VALUES (6, 2, 1543611600000, 1544648400000, 'luxe', true, 'vasia');

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`room_classes`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`room_classes` (`id`, `name`, `enabled`) VALUES (1, 'luxe', true);
INSERT INTO `kirova`.`room_classes` (`id`, `name`, `enabled`) VALUES (2, 'standard', true);
INSERT INTO `kirova`.`room_classes` (`id`, `name`, `enabled`) VALUES (3, 'president', true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`rooms`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (1, 'двуместный номер с терассой', '202', 2, 100.0, true, 1);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (2, 'двуместный номер с видом на океан', '201', 2, 120.0, true, 1);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (3, 'одноместный номер', '101', 1, 50.0, true, 2);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (4, 'двуместный номер с 2 кроватями', '102', 2, 70.0, true, 2);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (5, 'четырехместный номер', '301', 4, 110.0, true, 2);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (6, 'двуместный номер с диваном', '203', 3, 85.00, true, 1);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (7, 'одноместный номер', '302', 1, 60.0, true, 1);
INSERT INTO `kirova`.`rooms` (`id`, `name`, `number`, `capacity`, `cost`, `enabled`, `roomClassesId`) VALUES (8, 'президентский номер с видом на океан', '401', 5, 200.0, true, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kirova`.`reservations`
-- -----------------------------------------------------
START TRANSACTION;
USE `kirova`;
INSERT INTO `kirova`.`reservations` (`id`, `reservationDate`, `checkinDate`, `checkoutDate`, `totalCost`, `enabled`, `requestsId`, `requestsUsersUsername`, `roomsId`, `roomsRoomClassesId`) VALUES (1, 1539118800000, 1539118800000, 1539550800000, 500.00, true, 2, 'jdoe', 1, 1);
INSERT INTO `kirova`.`reservations` (`id`, `reservationDate`, `checkinDate`, `checkoutDate`, `totalCost`, `enabled`, `requestsId`, `requestsUsersUsername`, `roomsId`, `roomsRoomClassesId`) VALUES (2, 1539723600000, 1539723600000, 1540155600000, 300.00, true, 3, 'vasia', 7, 1);
INSERT INTO `kirova`.`reservations` (`id`, `reservationDate`, `checkinDate`, `checkoutDate`, `totalCost`, `enabled`, `requestsId`, `requestsUsersUsername`, `roomsId`, `roomsRoomClassesId`) VALUES (3, 1540069200000, 1540069200000, 1540674000000, 490.00, true, 4, 'jsmith', 4, 2);
INSERT INTO `kirova`.`reservations` (`id`, `reservationDate`, `checkinDate`, `checkoutDate`, `totalCost`, `enabled`, `requestsId`, `requestsUsersUsername`, `roomsId`, `roomsRoomClassesId`) VALUES (4, 1539550800000, 1539550800000, 1539723600000, 130.00, true, 5, 'jsmith', 6, 1);

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
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (1, 1, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (1, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (1, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (2, 1, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (2, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (2, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (3, 2, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (3, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (4, 2, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (4, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (5, 1, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (5, 2, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (5, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (6, 1, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (6, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (6, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (6, 5, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (7, 2, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (7, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (7, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (8, 1, 2, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (8, 3, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (8, 4, 1, true);
INSERT INTO `kirova`.`rooms_has_facilities` (`roomsId`, `facilitiesId`, `count`, `enabled`) VALUES (8, 5, 1, true);

COMMIT;

