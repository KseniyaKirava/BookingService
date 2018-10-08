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
  `password` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `middle_name` VARCHAR(50) NULL,
  `enable` TINYINT(1) NOT NULL,
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
  `id` LONG NOT NULL AUTO_INCREMENT,
  `room_capacity` INT NOT NULL,
  `checkin_date` LONG NOT NULL,
  `checkout_date` LONG NOT NULL,
  `room_class` VARCHAR(80) NOT NULL,
  `users_username` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`, `users_username`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_requests_users1_idx` (`users_username` ASC),
  CONSTRAINT `fk_requests_users1`
    FOREIGN KEY (`users_username`)
    REFERENCES `kirova`.`users` (`username`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`room_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`room_classes` (
  `id` LONG NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`rooms` (
  `id` LONG NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `number` VARCHAR(50) NOT NULL,
  `capacity` INT NOT NULL,
  `cost` DOUBLE NOT NULL,
  `room_classes_id` LONG NOT NULL,
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
  `id` LONG NOT NULL AUTO_INCREMENT,
  `reservation_date` LONG NOT NULL,
  `checkin_date` LONG NOT NULL,
  `checkout_date` LONG NOT NULL,
  `total_cost` DOUBLE NOT NULL,
  `requests_id` LONG NOT NULL,
  `requests_users_username` VARCHAR(50) NOT NULL,
  `rooms_id` LONG NOT NULL,
  `rooms_room_classes_id` LONG NOT NULL,
  PRIMARY KEY (`id`, `rooms_id`, `rooms_room_classes_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_reservations_requests1_idx` (`requests_id` ASC, `requests_users_username` ASC),
  INDEX `fk_reservations_rooms1_idx` (`rooms_id` ASC, `rooms_room_classes_id` ASC),
  CONSTRAINT `fk_reservations_requests1`
    FOREIGN KEY (`requests_id` , `requests_users_username`)
    REFERENCES `kirova`.`requests` (`id` , `users_username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservations_rooms1`
    FOREIGN KEY (`rooms_id` , `rooms_room_classes_id`)
    REFERENCES `kirova`.`rooms` (`id` , `room_classes_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`facilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`facilities` (
  `id` LONG NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kirova`.`rooms_has_facilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kirova`.`rooms_has_facilities` (
  `rooms_id` LONG NOT NULL,
  `facilities_id` LONG NOT NULL,
  `count` INT NOT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
