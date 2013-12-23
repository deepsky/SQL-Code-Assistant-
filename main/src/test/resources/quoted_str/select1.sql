select 'hello'
from dual

select 'hello' || '456'
from dual

select 'hello
    world'
from dual

select q'[7484]'
from dual

select 'hello' || 1 || q'(7484)'
from dual

select q'[7484
    LLL o UUU
    NMB]' || 'eee'
from dual
