CREATE TABLE `fear_index` (
  `id`              INT(11)   NOT NULL AUTO_INCREMENT,
  `index`           INT(11)   NOT NULL,
  `status`          VARCHAR(255) NOT NULL,
  `date`            CHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8;

CREATE TABLE `coin_price` (
  `id`              INT(11)   NOT NULL AUTO_INCREMENT,
  `price`           INT(11)   NOT NULL,
  `symbol`          VARCHAR(10) NOT NULL,
  `date`            CHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8;