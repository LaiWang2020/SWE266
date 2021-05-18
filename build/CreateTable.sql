DROP DATABASE IF EXISTS bank;
CREATE SCHEMA bank DEFAULT CHARACTER SET utf8;
USE bank;

drop table if exists account;
CREATE TABLE account(
      `accid` INT NOT NULL auto_increment,
      `psw` VARCHAR(45) NULL DEFAULT NULL,
      `username` VARCHAR(45) NULL DEFAULT NULL,
      `deposit` VARCHAR(45) NULL DEFAULT NULL,
      PRIMARY KEY (`accid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

