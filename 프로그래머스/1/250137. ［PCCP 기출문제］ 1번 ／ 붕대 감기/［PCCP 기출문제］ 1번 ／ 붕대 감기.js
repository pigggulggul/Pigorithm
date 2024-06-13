let fullHealth;
let healCount=0;
let attackCount=0;
let time=1;
function solution(bandage, health, attacks) {
    var answer = 0;
    fullHealth=health;
    while(attackCount < attacks.length){
        if(attacks[attackCount][0]===time){
            health = actionAttack(health,healCount,attacks[attackCount][1]);
            attackCount++;
            healCount=0;
            if(health <= 0){
                return -1;
            }
        }else{
            health = actionHealing(health,bandage);
        }
        time++;
    }
    return health;
}
//bandage [x시간, 초당 회복량, 추가 회복량]
//health 체력
//attacks [공격 시간, 피해량]
//죽으면 -1 리턴, 공격 당하면 기술으 취소됨
//공격잉 끝나고 남은 체력 리턴

function actionAttack(health,healCount,damage){
    health -= damage;
    healCount=0;
    return health;
}
function actionHealing(health,bandage){
    // 회복
    health+=bandage[1];
    // 추가회복
    healCount++;
    if(healCount>= bandage[0]){
        health+=bandage[2];
        healCount=0;
    }
    // 상한선
    if(health > fullHealth){
        health=fullHealth;
    }
    return health;
}