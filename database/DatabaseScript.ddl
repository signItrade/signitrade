DROP TABLE IF EXISTS `stratos`.`users`;
CREATE TABLE  `stratos`.`users` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `login_name` varchar(45) NOT NULL,
  `login_password` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `stratos`.`customer`;
CREATE TABLE  `stratos`.`customer` (
  `CUSTOMER_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;



CREATE  TABLE IF NOT EXISTS `stratos`.`T_CUSTOMER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `dob` VARCHAR(45) NOT NULL ,
  `emailId` VARCHAR(45) NOT NULL ,
  `address 1` VARCHAR(45) NOT NULL ,
  `address2` VARCHAR(45) NULL ,
  `city` VARCHAR(45) NOT NULL ,
  `postCode` VARCHAR(45) NOT NULL ,
  `phoneNumber` VARCHAR(45) NOT NULL ,
  `mobileNumber` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `emailId_UNIQUE` (`emailId` ASC) )
ENGINE = InnoDB


CREATE  TABLE IF NOT EXISTS `stratos`.`T_CUSTOMER_ACCESS_REF` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `stratos`.`T_CUSTOMER_ACCESS_MAPPING` (
  `id` INT NOT NULL ,
  `custId` INT NOT NULL ,
  `acessId` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `customer_fk` (`custId` ASC) ,
  INDEX `access_fk` (`acessId` ASC) ,
  CONSTRAINT `customer_fk`
    FOREIGN KEY (`custId` )
    REFERENCES `stratos`.`T_CUSTOMER` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `access_fk`
    FOREIGN KEY (`acessId` )
    REFERENCES `stratos`.`T_CUSTOMER_ACCESS_REF` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `stratos`.`T_INDICES_INFO_REF` (
  `id` INT NOT NULL ,
  `code` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) )
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `stratos`.`T_SECTOR_INFO_REF` (
  `id` INT NOT NULL ,
  `code` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `stratos`.`T_STOCK_EXCHANGE_DEFN_REF` (
  `id` INT NOT NULL ,
  `code` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `stratos`.`T_WATCHLIST` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `customerId` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `customerId_fk` (`customerId` ASC) ,
  CONSTRAINT `customerId_fk`
    FOREIGN KEY (`customerId` )
    REFERENCES `stratos`.`T_CUSTOMER` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `stratos`.`T_WATCHLIST_SECURITY_MAPPING` (
  `id` INT NOT NULL ,
  `securityId` VARCHAR(45) NULL ,
  `highAlarm` VARCHAR(45) NULL ,
  `lowAlarm` VARCHAR(45) NULL ,
  `watchListId` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `securityId_fk` (`securityId` ASC) ,
  INDEX `watchList_fk` (`watchListId` ASC) ,
  CONSTRAINT `securityId_fk`
    FOREIGN KEY (`securityId` )
    REFERENCES `stratos`.`T_SECTOR_INFO_REF` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `watchList_fk`
    FOREIGN KEY (`watchListId` )
    REFERENCES `stratos`.`T_WATCHLIST` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB


CREATE  TABLE IF NOT EXISTS `stratos`.`T_SECURITY_DEFN_REF` (
  `id` INT NOT NULL ,
  `code` VARCHAR(45) NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `sector` VARCHAR(45) NULL ,
  `exchangeCode` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) ,
  INDEX `stockexchange_fk` (`exchangeCode` ASC) ,
  INDEX `sector_fk` (`sector` ASC) ,
  CONSTRAINT `stockexchange_fk`
    FOREIGN KEY (`exchangeCode` )
    REFERENCES `stratos`.`T_STOCK_EXCHANGE_DEFN_REF` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sector_fk`
    FOREIGN KEY (`sector` )
    REFERENCES `stratos`.`T_SECTOR_INFO_REF` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB



CREATE  TABLE IF NOT EXISTS `stratos`.`T_SECURITY_DETAILS_EOD` (
  `id` INT NOT NULL ,
  `securityCode` VARCHAR(45) NOT NULL ,
  `businessDate` DATETIME NULL ,
  `eodPrice` FLOAT NULL DEFAULT 0 ,
  `signal` VARCHAR(45) NULL ,
  `signalCount` VARCHAR(45) NULL ,
  `count` VARCHAR(45) NULL ,
  `ma` VARCHAR(45) NULL ,
  `macd` VARCHAR(45) NULL ,
  `sto` VARCHAR(45) NULL ,
  `cha` VARCHAR(45) NULL ,
  `cci` VARCHAR(45) NULL ,
  `pr` VARCHAR(45) NULL ,
  `rsi` VARCHAR(45) NULL ,
  `dmi` VARCHAR(45) NULL ,
  `fi` VARCHAR(45) NULL ,
  `90DayChange` FLOAT NULL ,
  `90DayAvgTradedAmount` FLOAT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `securityCode_fk` (`securityCode` ASC) ,
  CONSTRAINT `securityCode_fk`
    FOREIGN KEY (`securityCode` )
    REFERENCES `stratos`.`T_SECURITY_DEFN_REF` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
