var dx = [-1,0,1,0];
var dy = [0,1,0,-1];
var n;
var m;
var queue;
var answer;
function solution(maps) {
    n=maps.length;
    m=maps[0].length;
    queue = [[0,0,1]];
    maps[0][0]=0;
    answer = 0;
    
    var bfs = ()=>{
        while(queue.length > 0 ){
        let [x,y,dis] = queue.shift();
        if(x==n-1 && y==m-1){
            return dis;
        }
        for(let d = 0 ; d < 4 ; d++){
            let nx = x+dx[d];
            let ny = y+dy[d];
            if(checkWall(nx,ny) && maps[nx][ny]==1){
               queue.push([nx,ny,dis+1]);
                maps[nx][ny]=0;
             }
            
        }
    }
        return -1
    }
    answer=bfs();
    return answer;
}
function checkWall(x,y){
    return x>=0 && x<n && y>=0 && y<m;
}