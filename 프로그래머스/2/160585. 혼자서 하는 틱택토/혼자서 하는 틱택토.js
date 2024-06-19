// O가 틱택토를 완성했지만 X와 개수가 같으면 안된다.
// X가 틱택토를 완성했을때는 O와 개수가 같아야 한다.
// X가 O보다 많으면 안된다.
let newBoard=[[],[],[]];
let count = [0,0];
function solution(board) {
    var answer = -1;
    for(let i = 0 ; i < board.length ; i++){
        for(let j = 0 ; j < board[i].length ; j++){
            newBoard[i].push(board[i][j]);
            if(board[i][j]=='O'){ count[0]+=1}
            else if(board[i][j]=='X'){count[1]+=1}
        }
    }
    let [numO,numX] = count;
    if(numO-numX >1 || numO-numX < 0){
        return 0;
    }
    if(checkTicTacToe('O') && numO-numX != 1){
        console.log("0확인");
        return 0;
    }
    if(checkTicTacToe('X') && numO-numX != 0){
        console.log("X확인");
        return 0;
    }
    
    return 1;
}
function checkTicTacToe(str){
    if(checkGaro(str) || checkSero(str) || checkDaegak(str)){
        return true;
    }else {
        return false;
    }
}
function checkGaro(str){
    for(let i = 0 ; i < newBoard.length ; i++){
        let garoCount = 0;
        for(let j = 0 ; j < newBoard[i].length ; j++){
            if(newBoard[i][j]==str){
                garoCount++;
            }
        }
        if(garoCount==3){
            return true;
        }
    }
    return false;
}
function checkSero(str){
    for(let i = 0 ; i < newBoard.length ; i++){
        let seroCount = 0;
        for(let j = 0 ; j < newBoard[i].length ; j++){
            if(newBoard[j][i]==str){
                seroCount++;
            }
        }
        if(seroCount==3){
            return true;
        }
    }
    return false;
}
function checkDaegak(str){
    let daegakCount = 0 ;
    if(newBoard[0][0] == str &&newBoard[1][1] == str && newBoard[2][2] == str ){
        return true;
    }
    if(newBoard[0][2] == str &&newBoard[1][1] == str && newBoard[2][0] == str ){
        return true;
    }
    return false;
}