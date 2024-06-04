let answer = [];
function solution(tickets) {
    for(let i = 0 ; i < tickets.length ; i++){
        if(tickets[i][0] === 'ICN'){
            var newArr = [];
            var isVisited = new Array(tickets.length).fill(false);
            newArr = [];
            isVisited[i]=true;
            newArr.push('ICN');
            check(isVisited,tickets[i][1],tickets,newArr);
            isVisited[i]=false;
        }
    }
    return answer;
}
function check(isVisited,startWord,tickets,newArr){
    for(let i = 0 ; i < isVisited.length ; i++){
        if(tickets[i][0] === startWord && !isVisited[i]){
            isVisited[i]=true;
            newArr.push(startWord);
            if(newArr && newArr.length === isVisited.length){
                newArr.push(tickets[i][1]);
                if(newArr.length === answer.length){
                    if(answer > newArr){
                        answer=newArr.slice();
                    }
                }else{
                    answer=newArr.slice();                    
                }
                
                return;
            }
            var anotherArr = newArr.slice();
            var newVisited = isVisited.slice();
            check(newVisited,tickets[i][1],tickets,anotherArr);
            newArr.pop();
            isVisited[i]=false;
        }
    }
}