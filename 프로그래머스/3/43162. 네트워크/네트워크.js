let isvisited;
let com;
function solution(n, computers) {
    var answer = 0;
    com = computers;
    isvisited=new Array(n).fill(false);
    console.log(isvisited);
    for(let i = 0 ; i < computers.length ; i++){
        if(!isvisited[i]){
            answer++;
            checker(i);
        }
    }
    return answer;
}
function checker(num){
    com[num].map((item,index)=>{
        if(index !== num && item === 1 && !isvisited[index]){
            isvisited[index]=true;
            checker(index);
        }
    })
}