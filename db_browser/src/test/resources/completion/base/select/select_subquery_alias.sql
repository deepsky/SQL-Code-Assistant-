create table tab_101 (
id number,
text varchar2(20)
);

insert into tab_101 (id, text) select tab_101.id, <caret> from tab_101, (select text, id from tab_101) a;

--id
--id (tab_101)
--text (tab_101)

