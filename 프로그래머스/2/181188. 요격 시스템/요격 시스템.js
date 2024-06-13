let point=0;
function solution(targets) {
    var answer = 0;
    
    targets.sort((a,b)=>{
        return a[1]-b[1];
    })
    for(let i = 0 ; i < targets.length ; i++){
        const [start,end] = targets[i];
        if(start < point){
            continue;
        }else{
            point=end;
            answer++;
        }
        
    }
    return answer;
}