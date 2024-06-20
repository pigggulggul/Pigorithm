let maze=[];
let visited=[];
let dx = [-1,0,1,0];
let dy = [0,1,0,-1];
let lever = [0,0,0];
function solution(maps) {
    var answer = 0;
    maze = new Array(maps.length).fill().map(()=>[]);
    visited = new Array(maps.length).fill().map(()=>[]);
    for(let i = 0 ; i < maps.length;i++){
        for(let j = 0 ; j < maps[i].length;j++){
            maze[i][j]=maps[i][j];
            visited[i][j]=0;
        }
    }
    for(let i = 0 ; i < maze.length;i++){
        for(let j = 0 ; j < maze[i].length;j++){
            if(maze[i][j]==='S'){
                lever = leverBFS(i,j,0,'L');
            }
        }
    }
    if(lever[0]===-1 && lever[1]===-1){
        return -1;
    }
    for(let i = 0 ; i < maps.length;i++){
        for(let j = 0 ; j < maps[i].length;j++){
            visited[i][j]=0;
        }
    }
    answer = leverBFS(lever[0],lever[1],lever[2],'E');
    // console.log(maze);
    // console.log(visited);
    return answer[2];
}
function leverBFS(startX,startY,startCount,alpha){
    let queue =[];
    queue.push([startX,startY,startCount]);
    while(queue.length != 0){
        let [x,y,count] = queue.shift();
        for(let d = 0 ; d < 4 ; d++){
            let nx = x+dx[d];
            let ny = y+dy[d];
            
            if(!checkWall(nx,ny)){
                continue;
            }
            if(visited[nx][ny]==0 && maze[nx][ny]!='X'){
                visited[nx][ny]= count+1;
                queue.push([nx,ny,count+1]);
            }
            if(maze[nx][ny]==alpha){
                return [nx,ny,count+1];
            }
        }
    }
    return [-1,-1,-1];
}
function checkWall(x,y){
    return x>=0 && x<maze.length && y>=0 && y<maze[0].length ;
}
// 미로탈출. 통로나 벽. 통로칸(O)만 이동가능 벽(X)는 이동 불가 
// 출구는1개. 레버를 당겨야함. 레버는 다른곳에 존재
// 출발지점 S -> 레버 L -> 출구칸 E
// 레버 안 당겨도 출구칸 지날 수 있다.
// 1칸당 1초. 가장 빠른 길 구하기
// S에서 시작해서 L까지 가고 E를 가야한다.
// S에서 BFS로 L까지가고 L에서 E까지 BFS로 간다.