function solution(arr)
{
    var answer = [];
    if(arr.length > 0){
        answer.push(arr[0]);
        for(let i = 1 ; i < arr.length ; i++){
            if(arr[i] !== arr[i-1]){
                answer.push(arr[i]);
            }
        }
    }
    console.log(answer);
    
    return answer;
}