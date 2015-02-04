SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `music` DEFAULT CHARACTER SET latin1 COLLATE latin1_german2_ci ;
USE `music` ;

-- -----------------------------------------------------
-- Table `music`.`songs`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `music`.`songs` (
  `songid` INT NOT NULL AUTO_INCREMENT ,
  `songname` VARCHAR(45) NOT NULL ,
  `songdescr` VARCHAR(45) NOT NULL ,
  `songimage` VARCHAR(45) NOT NULL ,
  `songrelease` TIMESTAMP NOT NULL ,
  `songfile` VARCHAR(45) NULL ,
  PRIMARY KEY (`songid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `music`.`albums`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `music`.`albums` (
  `albumid` INT NOT NULL ,
  `albumname` VARCHAR(45) NOT NULL ,
  `albumdescr` VARCHAR(45) NOT NULL ,
  `albumimage` VARCHAR(45) NOT NULL ,
  `albumrelease` TIMESTAMP NOT NULL ,
  PRIMARY KEY (`albumid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `music`.`albums_has_songs`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `music`.`albums_has_songs` (
  `albums_albumid` INT NOT NULL ,
  `songs_songid` INT NOT NULL ,
  PRIMARY KEY (`albums_albumid`, `songs_songid`) ,
  INDEX `fk_albums_has_songs_songs1` (`songs_songid` ASC) ,
  INDEX `fk_albums_has_songs_albums` (`albums_albumid` ASC) ,
  CONSTRAINT `fk_albums_has_songs_albums`
    FOREIGN KEY (`albums_albumid` )
    REFERENCES `music`.`albums` (`albumid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albums_has_songs_songs1`
    FOREIGN KEY (`songs_songid` )
    REFERENCES `music`.`songs` (`songid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;