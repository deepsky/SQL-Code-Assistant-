select *
from left lefttable, lright, (select * from join) jjon
where j<caret> -- should complete "left" as a table correlation name
