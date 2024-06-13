// DFS를 이용한 문제.
// DFS를 면적의 크기를 구하고 크기 구할 때 minLen과 maxLen도 구하고
// {index:number, minLen:number, maxLen:number 3개 구하기, value:number}
// Array에 넣기.
// 그렇게 모든 곳에 대한 정보를 저장한다음에
// 시추관을 뚫을 때 Array의 map을 돌면서 해당 시추관pos가 minLen~maxLen이면 cal에 value를 더해주기 
class hole{
    constructor(index,minLen,maxLen,value){
        this.index=index;
        this.minLen=minLen;
        this.maxLen=maxLen;
        this.value=value;
    }
}
let visited;
let size=1;
let min=999999999;
let max=-1;
let index=0;
let dx = [-1,0,1,0];
let dy = [0,1,0,-1];
function solution(land) {
    var answer = -1;
    visited = new Array(land.length).fill(false);
    for(let i = 0 ; i < visited.length ; i++){
        visited[i] = new Array(land[0].length).fill(false);
    }
    //탐색
    let arrMap=new Array(0);
    for(let i = 0 ; i < land.length ; i++){
        for(let j = 0 ; j < land[i].length ; j++){
            if(land[i][j]===1 && visited[i][j]===false){
                initialValue();
                DFS(i,j,land);
                if(size!==1){
                    size-=1;
                }
                arrMap.push(new hole(index,min,max,size));
            }
        }
    }
    for(let i = 0 ; i < land[0].length ; i++){
        let sichu = 0;
        arrMap.map((item,key)=>{
            if(i >= item.minLen && i <= item.maxLen){
                sichu += item.value;
            }
        })
        if(sichu > answer){
            answer=sichu;
        }
    }
    // console.log(arrMap);
    return answer;
}
function initialValue(){
    size=1;
    min=999999;
    max=-1;
    index++;
}
function DFS(startX,startY,land){

    const queue = [[startX,startY]];
    
    while(queue.length > 0 ){
        const [x,y] = queue.shift();
        checkLength(y);
        for(let d = 0 ; d < 4 ; d++){
            let nx = x+dx[d];
            let ny = y+dy[d];
        
            if(!checkWall(nx,ny)){
                continue;
            }
            if(visited[nx][ny]===true){
                continue;
            }
            if(visited[nx][ny]===false && land[nx][ny]===1){
                visited[nx][ny]=true;
                land[nx][ny]=size;
                size++;
                checkLength(ny);
                queue.push([nx,ny]);
            }
        }
    }
    
};
function checkWall(x,y){
    return x >= 0 && x < visited.length && y>=0 && y < visited[0].length;
}
function checkLength(y){
    if(y>max){
        max=y;
    }
    if(y<min){
        min=y;
    }
}