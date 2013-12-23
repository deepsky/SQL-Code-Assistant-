CREATE TABLE products
( 	product_id 	numeric(10) 	not null,
	supplier_id 	numeric(10) 	not null,
	supplier_name 	varchar2(50) 	not null,
	CONSTRAINT fk_supplier_comp
	  FOREIGN KEY (supplier_id, supplier_name)
	  REFERENCES supplier(supplier_id, supplier_name)
);