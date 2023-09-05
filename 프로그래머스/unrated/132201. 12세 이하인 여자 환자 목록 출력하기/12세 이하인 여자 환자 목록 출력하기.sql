-- 코드를 입력하세요
SELECT PT_NAME,PT_NO,GEND_CD,AGE,IFNULL(TLNO,"NONE") from PATIENT where age < 13 AND GEND_CD='W' order by age desc, PT_NAME