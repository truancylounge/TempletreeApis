CREATE SCHEMA `templetree` ;


CREATE TABLE templetree.items (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) NOT NULL,
  `category` varchar(255),
  `itemName` varchar(255),
  `salesPrice` DECIMAL(10,2),
  `purchasePrice` DECIMAL(10,2),
  `createdDate` date DEFAULT NULL,
  `quantity` int(10),
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;