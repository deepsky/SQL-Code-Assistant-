CREATE TABLE Supplier (
     SupplierNumber  INTEGER NOT NULL,
     Name            VARCHAR(20) NOT NULL,
     Address         VARCHAR(50) NOT NULL,
     Type            VARCHAR(10),
     CONSTRAINT supplier_pk PRIMARY KEY(SupplierNumber)
--     CONSTRAINT number_value CHECK (SupplierNumber > 0)
)

CREATE TABLE Invoices (
     InvoiceNumber   INTEGER NOT NULL,
     SupplierNumber  INTEGER NOT NULL,
     Text            VARCHAR(4096),
     CONSTRAINT invoice_pk PRIMARY KEY(InvoiceNumber),
--     CONSTRAINT inumber_value CHECK (InvoiceNumber > 0),
     CONSTRAINT supplier_fk FOREIGN KEY(SupplierNumber)
        REFERENCES Supplier(SupplierNumber)
        ON UPDATE CASCADE ON DELETE RESTRICT
)


CREATE TABLE products
( 	product_id 	numeric(10) 	not null,
	supplier_id 	numeric(10) 	not null,
	CONSTRAINT fk_supplier
	  FOREIGN KEY (supplier_id)
	  REFERENCES supplier(supplier_id)
);