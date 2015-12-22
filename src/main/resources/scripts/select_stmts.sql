select * from templetree.items;

update templetree.items set category = "Test Box" where id = 1;

delete from templetree.invoices where id = 21;
	
select * from templetree.invoices;

select * from templetree.invoicesItems;

select * from templetree.customers;

select * from templetree.billingInvoices;

select * from templetree.attributes;

insert into templetree.billingInvoices
values(1, 200, 100, 100, now(), now());

select * from templetree.billingInvoicesItems;

select * from templetree.users;

insert into templetree.users
values(3, 'lal', 'axdbsdfs', 'guest', now(), now());



SET SQL_SAFE_UPDATES = 0;
delete  from templetree.invoicesItems;
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
delete  from templetree.invoices;
SET SQL_SAFE_UPDATES = 1;

delete  from templetree.invoices where id in (34, 35, 36, 37, 38, 39);


SET SQL_SAFE_UPDATES = 0;
delete  from templetree.items;
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
delete  from templetree.customers;
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
delete  from templetree.billingInvoices;
SET SQL_SAFE_UPDATES = 1;



