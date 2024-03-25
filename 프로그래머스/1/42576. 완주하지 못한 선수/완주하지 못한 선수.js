function solution(participant, completion) {
    let answer="";
    participant.sort((a,b)=>{
        if(a > b){
            return 1
        }else {
            return -1
        }
    });
    completion.sort((a,b)=>{if(a>b){return 1} else {return -1}})
    for(let i = 0 ; i < participant.length ; i++){
        if(participant[i] !== completion[i]){
            answer=participant[i];
            break;
        }
    }
    return answer;
}