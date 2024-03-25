let newArr = [];
let result = 0;
function solution(board, moves) {
    for(let i = 0 ; i < moves.length ; i++){
        pushDoll(moves[i],board,moves);
    }
    rotateDoll();
    var answer = result;
    return answer;
}

function pushDoll(num,board,moves){
    for(let i = 0 ; i < board.length ; i++){
        if(board[i][num-1] != 0){
            newArr.push(board[i][num-1]);
            board[i][num-1]=0;
            return;
        }
    }
}
function rotateDoll(){
    for(let i = 0 ; i < newArr.length-1 ; i++){
        if(newArr[i]===newArr[i+1]){
            result+=2;
            console.log(i);
            console.log(newArr);
            newArr.splice(i,2);
            i=-1;
            console.log(newArr);
        }
    }
}