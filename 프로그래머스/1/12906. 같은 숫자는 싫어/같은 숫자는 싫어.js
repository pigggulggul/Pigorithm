function solution(arr)
{
    var answer = [];
    if(arr.length>0){
        answer.push(arr[0]);
        for(let i = 0 ; i < arr.length ; i++){
            if(answer[answer.length-1]!==arr[i]){
                answer.push(arr[i]);
            }
        }   
    }
    console.log(answer);
    
    return answer;
}