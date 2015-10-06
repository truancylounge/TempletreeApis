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

CREATE TABLE templetree.invoices (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `invoiceName` varchar(255) NOT NULL,
  `subTotal` DECIMAL(10,2),
  `shipping` DECIMAL(10,2),
  `packing` DECIMAL(10,2),
  `grandTotal` DECIMAL(10,2),
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE templetree.invoicesItems (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `invoiceId` int(10) unsigned NOT NULL,
  `itemName` varchar(255),
  `barcode` varchar(255),
  `quantity` int(10),
  `purchasePrice` DECIMAL(10,2),
  `total` DECIMAL(10,2),
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`invoiceId`) references templetree.invoices(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;