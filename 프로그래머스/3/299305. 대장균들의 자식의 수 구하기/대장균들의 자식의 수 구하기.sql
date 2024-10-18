select ID, IFNULL((
    select count(*) from ecoli_data
    group by parent_id
    having parent_id = id),0
) AS CHILD_COUNT
from ecoli_data
order by ID;