create table tab_101 (
id number,
text varchar2(20)
);

select text, id+2
from (
    select id, <caret> from tab_101
    )

--id
--id (tab_101)
--text (tab_101)
--IntellijIdeaRulezzz
