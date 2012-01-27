select object_type
from user_objects

select
parsing_time, l.id
from load_ref_stats l,
load_time_stats t
where l.id = t.id

insert into kip (id) values (3);

select i--extract(SECOND FROM sysdate)
from kip

alter table load_time_stats add  nm


