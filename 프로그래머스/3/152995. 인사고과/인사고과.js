function solution(scores) {
    var answer = 1;
    let newScores=[];
    let wanho = scores[0];
    let num = scores[0][0]+scores[0][1];
    let maxScore = 0;
    scores.sort((a,b) => a[0] === b[0] ? a[1] - b[1] : b[0] - a[0]);
    for(let i = 0 ; i < scores.length; i++){
        if(scores[i][1] < maxScore) {
            // 탈락대상
            if(scores[i] === wanho) return -1;
        } else {
            // 인센대상
            maxScore = Math.max(maxScore, scores[i][1]);
            if(scores[i][0] + scores[i][1] > num){
                answer++;
            }
        }
    }
    
    return answer;
}