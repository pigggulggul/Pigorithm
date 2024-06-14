let PI = Math.PI;
function solution(r1, r2) {
    var answer = 0;
    console.log(r1);
    let rc1 = Math.pow(r1,2);
    let rc2 = Math.pow(r2,2);
    for(let i = 1 ; i <r2 ; i++){
        let y1 = 1;
        if(r1>i){
            y1 =Math.ceil(Math.sqrt(rc1-Math.pow(i,2)));   
        }
        let y2 = Math.floor(Math.sqrt(rc2-Math.pow(i,2)));

        answer += y2;
        answer -= y1-1;
    }
    answer *= 4;
    answer += (r2 - r1 + 1) * 4;
    return answer;
}