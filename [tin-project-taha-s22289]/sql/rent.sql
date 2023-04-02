CREATE SCHEMA IF NOT EXISTS `tin`;
use `tin`;
CREATE TABLE IF NOT EXISTS `tin`.`car`
(
    `_id` INT NOT NULL AUTO_INCREMENT,
    `model` VARCHAR(45) NOT NULL,
    `year` DATE NOT NULL,
    `colar` VARCHAR(45) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`_id`),
    UNIQUE KEY `car_id_UNIQUE` (`_id` ASC)
) ENGINE=InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `tin`.`customer`
(
    `_id` INT NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `age` INT NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `number` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`_id`),
    UNIQUE KEY `customer_id_UNIQUE` (`_id` ASC)
) ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `tin`.`rent`
(
    `_id` INT NOT NULL AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `car_id` INT NOT NULL,
    `location` VARCHAR(45) NOT NULL,
    `rentdate` DATE NOT NULL,
    `returndate` DATE NOT NULL,
    `price` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`_id`),
    UNIQUE KEY `tin_rent_id_UNIQUE` (`_id` ASC),
    CONSTRAINT `customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `tin`. `customer` (`_id`),
	CONSTRAINT `car_fk` FOREIGN KEY (`car_id`) REFERENCES `tin`. `car` (`_id`)
) ENGINE=InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;


INSERT IGNORE INTO `tin`.`customer` (`_id`, `firstname`, `surname`, `address`, `age`, `email`,`number`) VALUES
(1, 'jim', 'Carrey', 'tatranska',42,'carreya@pjwstk.edu.pl','123456789'),
(2, 'Anna', 'Kowalska','dubois',38,'anna.kowalska@pjwstk.edu.pl', '987654321'),
(3, 'ali', 'baran','ankara',23,'alibaran@pjwstk.edu.pl','145234678'),
(4, 'Rumeysa', 'Atesoglu','kayseri',45,'rumos@pjwstk.edu.pl','876453231');

INSERT IGNORE INTO `tin`.`car` (`_id`, `model`, `year`, `colar`, `status`) VALUES
(1, 'CHEVROLET CAMARO','2018-12-12', 'Black', 'rear window defective'),
(2, 'MERCEDES C63S AMG W205','2011-07-05', 'White', 'full care'),
(3, 'BMW M3 EVO E30','2019-07-02', 'Red', 'petrol full'),
(4, 'AUDI RS5 CABRIO GT','2023-01-01', 'Blue', 'full care');

INSERT IGNORE INTO `tin`.`rent` (`_id`, `customer_id`, `car_id`, `location`, `rentdate`,`returndate`,`price`) VALUES
(1, 1, 1, 'warsawa', '2019-01-01','2019-02-01','500'),
(2, 1, 2, 'warsawa', '2019-03-04','2019-04-05','450'),
(3, 1, 3, 'warsawa', '2019-09-25','2019-10-11','950'),
(4, 1, 4, 'warsawa', '2020-01-01','2020-02-01-','400'),
(5, 2, 1, 'ankara', '2020-07-15','2020-08-01','250'),
(6, 2, 2, 'ankara', '2021-08-11','2021-09-10','1000'),
(7, 2, 3, 'ankara', '2022-12-12','2023-01-12','1500'),
(8, 2, 4, 'ankara', '2022-05-06','2022-07-02','745'),
(9, 3, 1, 'gdansk', '2018-04-27','2018-05-14','500'),
(10, 3, 2, 'warsawa', '2019-01-02','2019-02-02','500'),
(11, 3, 3, 'gdanks', '2019-04-12','2019-05-12','256'),
(12, 3, 4, 'gdanks', '2019-06-24','2019-07-17','951'),
(13, 4, 1, 'krakow', '2019-08-04','2019-08-17','500'),
(14, 4, 2, 'lodz', '2022-09-18','2022-11-02','747'),
(15, 4, 3, 'wroclaw', '2022-12-28','2023-01-02','500'),
(16, 4, 4, 'strogatgdansk', '2023-02-03','2023-03-11','125');