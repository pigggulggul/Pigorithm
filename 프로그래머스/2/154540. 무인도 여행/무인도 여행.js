// X는 바다. 숫자는 무인도
// 무인도의 숫자는 식량을 나타냄
// 연결되는 칸에 적힌 숫자를 모두 합한 값은 
// 무인도에서 최대 며칠동안 머물 수 있는지를 나타냄.
// 최대 며칠 씩 머물 수 있는지 오름차순
// bfs 한 번 방문하고 나온 숫자 push로 넣기 
let land = [];
let visited = [];
let dx = [-1,0,1,0];
let dy = [0,1,0,-1];
let landCount=[];
function solution(maps) {
    var answer = [];
    land = new Array(maps.length).fill().map(()=>[]);
    visited = new Array(maps.length).fill().map(()=>[]);
    for(let i = 0 ; i < maps.length ; i++){
        for(let j = 0 ; j < maps[i].length ; j++){
            land[i][j] = maps[i][j];
            visited[i][j]=false;
        }
    }
    for(let i = 0 ; i < maps.length ; i++){
        for(let j = 0 ; j < maps[i].length ; j++){
            if(land[i][j] !== 'X' && visited[i][j] ===false){
                landCount.push(BFS(i,j));
            }
        }
    }
    if(landCount.length == 0){
        return [-1];
    }else{
        landCount.sort((a,b)=> a-b);
        return landCount;
    }
}
function BFS(startX,startY){
    let queue =[];
    let count = parseInt(land[startX][startY]);
    visited[startX][startY]=true;
    queue.push([startX,startY]);
    while(queue.length != 0){
        let [x,y] = queue.shift();
        for(let d = 0 ; d < 4 ; d++){
            let nx = x+dx[d];
            let ny = y+dy[d];
            
            if(!checkWall(nx,ny)){
                continue;
            }
            if(land[nx][ny] != 'X' && visited[nx][ny] == false ){
                visited[nx][ny]=true;
                queue.push([nx,ny]);
                count+=parseInt(land[nx][ny]);
            }
        }
    }
    return count;
}
function checkWall(x,y){
    return x>=0 && x<land.length && y>=0 && y<land[0].length;
}