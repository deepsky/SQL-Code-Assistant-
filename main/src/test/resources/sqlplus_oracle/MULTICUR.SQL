create or replace package Customers
as  
        cursor ord1 is  select ordid,orderdate,shipdate,total   from ord; 
        type ordCur is ref cursor return ord1%rowtype;  

        cursor sales1 is  select prodname,prodid,amount   from sales; 
        type salesCur is ref cursor return sales1%rowtype;  
  
	procedure GetCutomerSalesOrder(inName IN CHAR , ordCursor in out ordCur ,SalesCursor in out salesCur);

                                
end Customers;  
/  
show errors  
 
create or replace package body Customers as 
procedure GetCutomerSalesOrder(inName IN CHAR ,ordCursor in out ordCur ,SalesCursor in out salesCur) is 
incustid NUMBER(6);
begin 
   		select custid into incustid from customer where Name = inName;
		
                open ordCursor for  select ordid,orderdate,shipdate,total   from ord where custid = incustid;

            	open salesCursor for select prodname,prodid,amount   from sales where custid = incustid;

        
end GetCutomerSalesOrder; 

end Customers;
/

show errors 


declare
bur number;
btr VARCHAR2(23);
nsdr BLOB;
begin
 bur := 388;
 btr := 'we';
 jgshs := iwi2929 + 727/562;
end;
 
 
 
