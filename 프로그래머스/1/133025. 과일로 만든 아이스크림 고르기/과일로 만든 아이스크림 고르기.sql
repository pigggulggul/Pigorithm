select f.flavor
from first_half as F left join icecream_info as I on f.flavor=i.flavor
where f.total_order >3000 and i.ingredient_type = 'fruit_based'
order by f.total_order desc