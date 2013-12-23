CREATE TABLE ORDERS(
    order_id integer primary key,
    order_date date not null,
    customer_sid integer references CUSTOMER(SID),
    amount number
);