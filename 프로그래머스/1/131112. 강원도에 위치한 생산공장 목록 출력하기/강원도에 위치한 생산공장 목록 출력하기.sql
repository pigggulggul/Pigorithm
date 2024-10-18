# 공장 id, 이름, 주소
select factory_id, factory_name, address
from food_factory
where address like '%강원도%'
order by factory_id