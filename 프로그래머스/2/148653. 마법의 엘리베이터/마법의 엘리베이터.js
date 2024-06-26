// 특정 층에서 엘리베이터 버튼을 최저 버튼을 눌러 0층에 도달하기
// +할 때랑 -할 때 구해서 최저를 넣기
// 뒤에 몇 번을 하던 앞에는 1개만 영향받음
// 맨 뒤에서부터 탐색을 진행해서
// 2553 일때 5보다 작으면 -로 5보다 크면 +한다
// 이 떄 해당 자리가 5면 + 할 때랑 - 할 때랑 나눠서 함수를 넣는다
// 그래서 값이 0이 됐을 때 max 작은쪽으로 넣기
let max = -1;
function solution(storey) {
    let result = 0;
    while(storey){
        console.log(storey);
        let cur = storey % 10;
        let next = (storey / 10) % 10;
        
        if (cur > 5) { // 5보다 큰 경우
          result += 10 - cur; // 결과값에 + 10-cur
          storey += 10; // 다음 자리수 +1
        } else if (cur === 5) { // 5와 같은 경우
          result += 5;
          storey += next >= 5 ? 10 : 0; // 다음 자리수가 5보다 크면 +1
        } else { // 5보다 작은 경우
          result += cur;
        }

    storey = parseInt(storey / 10); // 자리수를 변경하여 탐색
    }
    return result;
}
