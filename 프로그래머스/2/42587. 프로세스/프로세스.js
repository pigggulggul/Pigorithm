let num = 1;
let newArr;
function solution(priorities, location) {
    var answer = 0;
    // 숫자 num=1;
    // 똑같은 배열 만들기
    // for priorities의 length만큼 반복
    // 1. 현재 위치 기록
    // 2. 우선순위 가장 높은거 찾기
    // 3. 우선순위 가장 높은수에 도착했을 때 그 위치의 새 배열에 num 넣고 num++;
    newArr = new Array(priorities.length).fill(0);
    let pos=0;
    for(let i = 0 ; i < priorities.length ; i++){
        let pri = checkPrior(priorities);
        for(let j = 0 ; j < priorities.length ; j++){
            if(priorities[pos]===pri){
                newArr[pos]=num;
                num++;
                priorities[pos]=-1;
                break;
            }
            pos++;
            if(pos >= priorities.length){
                pos -= priorities.length;
            }
        }
    }
    console.log(newArr);
    return newArr[location];
}
function checkPrior(priorities){
    let max = -1;
    for(let i = 0 ; i < priorities.length ; i++){
        if(priorities[i] >max){
            max=priorities[i];
        }
    }
    return max;
}