function solution(participant, completion) {
    let answer="";
    participant.sort((a,b)=>{
        if(a>b) return 1;
        else return -1;
    });
    completion.sort((a,b)=>{
        if(a>b) return 1;
        else return -1;
    });
    for(var i = 0 ; i < completion.length ; i++){
        if(completion[i] !== participant[i]){
            return participant[i];
        }
    }
    return participant[participant.length-1];
}