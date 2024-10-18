# rest id가 같다.
# 서울에 위치한 식당들의 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수
select info.rest_id, info.rest_name, info.food_type, info.favorites, info.address,round(avg(review.review_score),2) as score
from rest_info as info inner join rest_review as review 
on info.rest_id = review.rest_id
where info.address like "서울%"
group by rest_id
order by score desc, info.favorites desc
