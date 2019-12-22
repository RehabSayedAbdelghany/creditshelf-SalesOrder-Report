insert into company values(1,'Company 1');
insert into company values(2,'Company 2');

insert into product(id,product_id,name,price,currency,company_id) values(1,1,'Product 1 for company 1',18.15,'GBP',1);
insert into product(id,product_id,name,price,currency,company_id) values(2,2,'Product 2 for company 1',17.50,'GBP',1);


insert into product(id,product_id,name,price,currency,company_id) values(3,1,'Product 1 for company 2',72586.50,'JPY',2);
insert into product(id,product_id,name,price,currency,company_id) values(4,2,'Product 2 for company 2',60488749.09,'JPY',2);

insert into sales_order(order_id,order_date,order_number,company_id) values(1,PARSEDATETIME('1/15/2019','M/d/yyyy'),1,1);
insert into sales_order_details(id,currency_code,quantity,sale_price,order_id,product_id) values (1,'EUR',2,29.99,1,2);

insert into sales_order(order_id,order_date,order_number,company_id) values(2,PARSEDATETIME('6/25/2019','M/d/yyyy'),2,1);
insert into sales_order_details(id,currency_code,quantity,sale_price,order_id,product_id) values (2,'USD',3,32,2,1);

insert into sales_order(order_id,order_date,order_number,company_id) values(3,PARSEDATETIME('1/13/2019','M/d/yyyy'),1,2);
insert into sales_order_details(id,currency_code,quantity,sale_price,order_id,product_id) values (3,'USD',2,1002.67,3,1);
insert into sales_order_details(id,currency_code,quantity,sale_price,order_id,product_id) values (4,'USD',2,724153.15,3,2);


insert into sales_order(order_id,order_date,order_number,company_id) values(4,PARSEDATETIME('2/27/2019','M/d/yyyy'),2,2);
insert into sales_order_details(id,currency_code,quantity,sale_price,order_id,product_id) values (5,'JPY',3,724153.15,4,1);
