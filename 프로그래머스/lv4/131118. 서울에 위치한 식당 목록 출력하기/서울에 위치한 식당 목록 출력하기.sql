-- 코드를 입력하세요
 select info.REST_ID, info.REST_NAME, info.FOOD_TYPE, info.FAVORITES, info.ADDRESS, round(sum(review.REVIEW_SCORE)/count(review.REVIEW_SCORE),2)
 from REST_INFO info, REST_REVIEW review
 where info.REST_ID = review.REST_ID
 group by REST_ID
 having info.address like '서울%'
 order by round(sum(review.REVIEW_SCORE)/count(review.REVIEW_SCORE),2) desc, FAVORITES desc
