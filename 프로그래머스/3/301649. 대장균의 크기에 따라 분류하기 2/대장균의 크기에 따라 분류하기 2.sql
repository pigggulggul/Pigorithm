select A.ID, 
CASE
    WHEN A.PER <= 0.25 THEN 'CRITICAL'
    WHEN A.PER <= 0.50 THEN 'HIGH'
    WHEN A.PER <= 0.75 THEN 'MEDIUM'
    ELSE
        'LOW'
END AS COLONY_NAME
from (select ID, PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS PER
from ecoli_Data) as A
order by A.ID