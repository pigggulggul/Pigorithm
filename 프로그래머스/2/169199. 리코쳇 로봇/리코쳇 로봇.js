// R에서 G로가기
// 모든 방향에 대해서 bfs 돌리기 조건에 맞으면 1칸이 아니라 쭉 직진하고 그 다음에 넣기
// 큐를 다 돌아도 해결이 안되면 -1 리턴
let dx = [0,1,0,-1];
let dy = [-1,0,1,0];
let newBoard;
let result =0;
function solution(board) {
    var answer = 0;
    
    newBoard = new Array(board.length).fill().map(()=>[]);
    
    for(let i = 0 ; i < board.length; i++){
        for(let j = 0 ; j < board[i].length ; j++){
            newBoard[i].push(board[i][j]);
        }
    }
    for(let i = 0 ; i < newBoard.length;i++){
        for(let j = 0 ; j < newBoard[i].length;j++){
            if(newBoard[i][j]=='R'){
                BFS(i,j);
            }
        }
    }
    return result != 0 ? result : -1;
}
function BFS(startX,startY){
    newBoard[startX][startY]='0';
    let queue = [];
    queue.push([startX,startY,0]);
    while(queue.length !== 0){
        // console.log(newBoard);
        let [x,y,count] = queue.shift();
        for(let d = 0 ; d < 4 ; d++){
            let nx = x+dx[d];
            let ny = y+dy[d];
            if(!checkWall(nx,ny)){
                continue;
            }
            
            // 끝까지 가기. 벽에 부딪히거나 장애물(D)에 부딪힐떄까지
            while(checkWall(nx,ny) && newBoard[nx][ny] !== 'D'){
                nx+=dx[d];
                ny+=dy[d];
            }
            // 벽에 부딪히기 직전의 좌표를 뽑기
            nx-=dx[d];
            ny-=dy[d];
            
            if(newBoard[nx][ny]=='G'){
                result = count+1;
                return;
            }else if (newBoard[nx][ny] !== '0'){
                newBoard[nx][ny]='0';
                queue.push([nx,ny,count+1]);
            }
        }
    }
    
}
function checkWall(x,y){
    return x>=0 && x < newBoard.length && y>=0 && y < newBoard[0].length;
}