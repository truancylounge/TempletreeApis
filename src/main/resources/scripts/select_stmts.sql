select * from templetree.items;

update templetree.items set category = "Test Box" where id = 1;

	

select * from templetree.invoices;

select * from templetree.invoicesItems;

SET SQL_SAFE_UPDATES = 0;
delete  from templetree.invoicesItems;
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
delete  from templetree.invoices;
SET SQL_SAFE_UPDATES = 1;



SET SQL_SAFE_UPDATES = 0;
delete  from templetree.items;
SET SQL_SAFE_UPDATES = 1;