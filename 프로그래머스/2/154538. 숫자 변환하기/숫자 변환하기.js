let dp = [];
let target;
let result=-1;
function solution(x, y, n) {
    var answer = 0;
    target=x;
    dp = new Array(1000001).fill(Infinity);
    // y부터 시작
    dp[x]=0;
    for(let i = x ; i <= y ; i++){
        if(dp[i]===Infinity){
            continue;
        }
        if(dp[i+n] === Infinity){
            dp[i+n]= dp[i]+1;
        }else{
            dp[i+n]= dp[i+n] > dp[i]+1 ? dp[i]+1 : dp[i+n];
        }
        if(dp[i*2] === Infinity){
            dp[i*2]= dp[i]+1;
        }else{
            dp[i*2]= dp[i*2] > dp[i]+1 ? dp[i]+1 : dp[i*2];
        }
        if(dp[i*3] === Infinity){
            dp[i*3]= dp[i]+1;
        }else{
            dp[i*3]= dp[i*3] > dp[i]+1 ? dp[i]+1 : dp[i*3];
        }
    }
    // console.log(result);
    return dp[y] == Infinity ? -1 : dp[y];
}