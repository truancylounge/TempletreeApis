

CREATE SCHEMA `templetree` ;


CREATE TABLE templetree.items (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) NOT NULL,
  `category` varchar(255),
  `itemName` varchar(255),
  `salesPrice` DECIMAL(10,2),
  `purchasePrice` DECIMAL(10,2),
  `createdDate` timestamp NULL DEFAULT NULL,
  `quantity` int(10),
  `updatedDate` timestamp NULL DEFAULT NULL,
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
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`invoiceName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE templetree.invoices ADD COLUMN `invoiceNumber` varchar(255);  
ALTER TABLE templetree.invoices ADD COLUMN `invoiceDate` timestamp NULL DEFAULT NULL; 

CREATE TABLE templetree.invoicesItems (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `invoiceId` int(10) unsigned NOT NULL,
  `itemName` varchar(255),
  `barcode` varchar(255),
  `quantity` int(10),
  `purchasePrice` DECIMAL(10,2),
  `total` DECIMAL(10,2),
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`invoiceId`) references templetree.invoices(`id`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
CREATE TABLE templetree.customers (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255),
  `telephoneNo` varchar(255),  
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`, `email`, `telephoneNo`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;  

 
 CREATE TABLE templetree.billingInvoices (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `invoiceName` varchar(255),
  `customerId` int(10) unsigned,
  `totalAmount` DECIMAL(10,2),
  `cash` DECIMAL(10,2),
  `credit` DECIMAL(10,2),  
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customerId`) references templetree.customers(`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
  CREATE TABLE templetree.billingInvoicesItems (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `billingInvoiceId` int(10) unsigned NOT NULL,
  `itemName` varchar(255),
  `barcode` varchar(255),
  `quantity` int(10),
  `purchasePrice` DECIMAL(10,2),
  `total` DECIMAL(10,2),
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`billingInvoiceId`) references templetree.billingInvoices(`id`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
CREATE TABLE templetree.users (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255),
  `role` varchar(255),
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
CREATE TABLE templetree.attributes(
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(45) NOT NULL,
  `tkey` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


  

-- ROLES
INSERT INTO  `templetree`.`attributes`(`id`,`type`,`tkey`,`value`,`createdDate`,`updatedDate`) VALUES (null,'roles','ADMIN', 'ADMIN',now(), null);
INSERT INTO  `templetree`.`attributes`(`id`,`type`,`tkey`,`value`,`createdDate`,`updatedDate`) VALUES (null,'roles','GUEST', 'GUEST',now(), null);
INSERT INTO  `templetree`.`attributes`(`id`,`type`,`tkey`,`value`,`createdDate`,`updatedDate`) VALUES (null,'roles','EDITOR', 'EDITOR',now(), null);
-- Guest user creds
INSERT INTO `templetree`.`users`(`username`, `password`, `role`, `createdDate`,`updatedDate` )
VALUES ('guest','n4bQgYhMfWWaL+qgxVrQFaO/TxsrC4Is0V1sFbDwCgg=', 'ADMIN',now(), now());

  
  
  