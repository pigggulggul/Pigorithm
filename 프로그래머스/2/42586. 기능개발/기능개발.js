function solution(progresses, speeds) {
    var answer = [];
    for(let i = 0 ; i < progresses.length ; i++){
        let a = 100-progresses[i];
        let b = Math.ceil(a/speeds[i]);
        console.log(i); //7일 
        
        for(let j = i ; j < progresses.length ; j++){
            progresses[j] += speeds[j]*b;
        }
        let result = 0;
        for(let j = i ; j < progresses.length ; j++){
            if(progresses[j]>=100){
                result++;
            }else{
                break;
            }
        }
        answer.push(result);
        i+=result;
        i--;
        console.log(progresses);
    }
    return answer;
}