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
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`invoiceName`)
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
  FOREIGN KEY (`invoiceId`) references templetree.invoices(`id`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
 
 CREATE TABLE templetree.billingInvoices (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `totalAmount` DECIMAL(10,2),
  `cash` DECIMAL(10,2),
  `credit` DECIMAL(10,2),  
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
  CREATE TABLE templetree.billingInvoicesItems (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `billingInvoiceId` int(10) unsigned NOT NULL,
  `itemName` varchar(255),
  `barcode` varchar(255),
  `quantity` int(10),
  `purchasePrice` DECIMAL(10,2),
  `total` DECIMAL(10,2),
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`billingInvoiceId`) references templetree.billingInvoices(`id`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
  
  CREATE TABLE templetree.customers (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255),
  `telephoneNo` varchar(255),  
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`, `email`, `telephoneNo`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
  
  
  
  