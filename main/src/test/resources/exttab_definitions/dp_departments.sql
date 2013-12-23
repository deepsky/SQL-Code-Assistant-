create table dp_departments
   organization external(
           type oracle_datapump
           default directory EXTTABDIR
           access parameters
           (
                   version '10.2.0'
           )
           location ('dp_departments.dmp')
   )
as
   select * from departments;