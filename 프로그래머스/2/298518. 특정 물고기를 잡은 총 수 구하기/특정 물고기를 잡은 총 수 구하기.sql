-- 코드를 작성해주세요
select count(*) as FISH_COUNT
from fish_info as i inner join fish_name_info as n 
on i.fish_type = n.fish_type
where fish_name='BASS' or fish_name='SNAPPER'