var reserveBool;
var lostNum=0;
var reserveNum=0;
function solution(n, lost, reserve) {
    var answer = 0;
    lost.sort((a,b)=> a-b);
    reserve.sort((a,b)=>a-b);
    lostNum=lost.length;
    reserveBool = new Array(reserve.length).fill(false);
    lostBool = new Array(lost.length).fill(false);
    for(let i = 0 ; i < lost.length ; i++){
        for(let j = 0 ; j < reserve.length ; j++){
            if(reserveBool[j])continue;
            if(lost[i] == reserve[j]){
                lostBool[i]=true;
                reserveBool[j]=true;
                lostNum-=1;
            }
        }
    }
    
    for(let i = 0 ; i < lost.length ; i++){
        if(lostBool[i]) continue;
        for(let j = 0 ; j < reserve.length ; j++){
            if(reserveBool[j])continue;
            if(reserve[j] == (lost[i]-1) || reserve[j] == (lost[i]+1)){
                reserveBool[j]=true;
                answer++;
                break;
            } 
        }
    }
    return n-lostNum+answer;
}