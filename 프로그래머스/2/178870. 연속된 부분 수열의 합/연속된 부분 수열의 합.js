//부분수열 합이 k인 부분 찾기.
//여러개 인 경우 길이가 가장 짧은 수열 찾기
//가장 짧은 수열이 여러개일 경우 가장 앞쪽에 나오는 수열 찾기
// 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아
// return
// start, end, count 3개 만들고
// for문 돌면서 k값이 될 때의 start,count를 구하기. 일단 count가 가장 짧아야함.
// 그리고 count가 가장 짧을때 start가 가장 앞에 있어야함.
let start;
let end;
let count=999999999;
let result;
let cal=0;
function solution(sequence, k) {
    var answer = [];
    start = 0;
    end = 0;
    if(sequence[start] === k ){
        return [start,end];
    }
    else{
        end++;
    }
    cal=sequence[0]+sequence[1];
    while(end <= sequence.length-1){
        if(start>end){
            break;
        }
        if(cal < k){
        end++;
        cal+=sequence[end];
        }else if(cal > k){
            cal-= sequence[start];
            start++;
        }else{
            if(count > end-start+1){
                count = end-start+1;
                answer = [start,end];
            }
            end++;
            cal+=sequence[end];
        }
    }
    
    return answer;
}