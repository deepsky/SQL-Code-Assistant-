CREATE TABLE suppliers
( supplier_id number(10) not null,
  supplier_name varchar2(50) not null,
  contact_name varchar2(50),
    CONSTRAINT suppliers_pk PRIMARY KEY (supplier_id)
);

CREATE TABLE orders
( supplier_id number(10) not null,
  order_name varchar2(50) not null,
  order_date date
);

SELECT suppliers.supplier_id, suppliers.supplier_name, orders.order_date
FROM suppliers
    <caret>
