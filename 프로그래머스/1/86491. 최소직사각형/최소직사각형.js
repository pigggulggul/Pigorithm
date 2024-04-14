var size1=0;
var size2=0;
function solution(sizes) {
    var answer = 0;
    // 지갑 만들기
    // 존재하는 모든 명함을 수납해야한다
    // 가장 작은 다 들어가는 지갑 만들기
    // 가로에 큰 값들 배열로 세로에 작은값들 넣기
    for(let i = 0 ; i < sizes.length ; i++){
        if(sizes[i][0] < sizes[i][1]){
            [sizes[i][0],sizes[i][1]]=[sizes[i][1],sizes[i][0]];
        }
        if(size1 < sizes[i][0]){
            size1=sizes[i][0];
        }
        if(size2 < sizes[i][1]){
            size2=sizes[i][1];
        }
        
    }
    return size1*size2;
}