select *
from bookingevents
where eventdatetime > systimestamp <caret>  --- completion variants is not correct : alter/and/comment/create/drop
order by eventdatetime desc