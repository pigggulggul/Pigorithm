-- 코드를 입력하세요
SELECT member_id,member_name,gender,date_format(date_of_birth,'%Y-%m-%d') as date_of_birth
from member_profile
where gender='w' and tlno is not null and month(date_of_birth) = '3'
group by member_id