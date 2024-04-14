var plate= Array.from(Array(103),()=>new Array(103).fill(0));
var dx = [0,1,0,-1];
var dy = [-1,0,1,0];
function solution(rectangle, characterX, characterY, itemX, itemY) {
    var answer = 0;
    characterX *=2;
    characterY *=2;
    itemX*=2;
    itemY*=2;
    let doubleRect = rectangle.map(rec => rec.map (point => point*2));
    for(let i = 0 ; i < doubleRect.length ; i++){
        //좌측하단 x,y 우측상단 x,y
        makeRect(doubleRect[i][0],doubleRect[i][1],doubleRect[i][2],doubleRect[i][3]);
    }
    for(let i = 0 ; i < rectangle.length ; i++){
        //좌측하단 x,y 우측상단 x,y
        clearRect(doubleRect[i][0]+1,doubleRect[i][1]+1,doubleRect[i][2]-1,doubleRect[i][3]-1);
    }
    searchBFS(characterX,characterY,itemX,itemY);
    plate[characterX][characterY] = 1;
    return Math.floor(plate[itemX][itemY]/2);
}
function makeRect(x1,y1,x2,y2){
    for(let i = x1 ; i <= x2 ; i++){
        for(let j = y1 ; j <= y2 ; j++){
            plate[i][j]=1;
        }
    }
}
function clearRect(x1,y1,x2,y2){
    for(let i = x1 ; i <= x2 ; i++){
        for(let j = y1 ; j <= y2 ; j++){
            plate[i][j]=0;
        }
    }
}
function searchBFS(chaX,chaY,itemX,itemY){
    let queue = [];
    queue.push([chaX,chaY]);
    while(queue.length != 0){
        let info = queue.shift();
        if(info[0]==itemX && info[1]==itemY){
            return;
        }
        for(let d = 0 ; d < 4 ; d++){
            let nx = info[0]+dx[d];
            let ny = info[1]+dy[d];
            if(!checkWall(nx,ny)){
                continue;
            }
            if(plate[nx][ny]==1){
                queue.push([nx,ny]);
                plate[nx][ny]=plate[info[0]][info[1]]+1;
            }
        }
    }
}
function checkWall(x,y){
    return x>=0 && x<plate.length && y>=0 && y<plate[0].length;
}