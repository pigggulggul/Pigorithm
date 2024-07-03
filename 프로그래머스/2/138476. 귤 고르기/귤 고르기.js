let map = new Map();
let arr= [];
let deleteCount;
function solution(k, tangerine) {
    var answer = 0;
    for(let i = 0 ; i < tangerine.length ; i++){
        if(map.has(tangerine[i])){
            map.set(tangerine[i],map.get(tangerine[i])+1);
        }else{
            map.set(tangerine[i],1);
        }
    }
    deleteCount = tangerine.length - k;
    
    for([key,value] of map){
        arr.push([key,value]);
    }
    arr.sort((a,b)=>a[1]-b[1]);
    deleteTan();
    for(let i = 0 ; i < arr.length ; i++){
        if(arr[i][1] !==0){
            answer++;
        }
    }
    return answer;
}
function deleteTan(){
    //지울 개수 있으니까 리스트를 돌면서 value 값이 0이 될 떄 까지 빼기
    for(let i = 0 ; i < arr.length; i++){
        if(deleteCount===0){
            return;
        }
        if(deleteCount >= arr[i][1]){
            deleteCount -= arr[i][1];
            arr[i][1]=0;
        }else{
            arr[i][1] -= deleteCount;
            deleteCount=0;
        }
    }
}