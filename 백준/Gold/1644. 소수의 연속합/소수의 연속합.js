// 자연수가 주어졌을때 소수의 연속합으로 표현되는 수의 경우가 몇 개인지

// N이 주어졌을 때 N까지의 소수 구하기
// 소수만을 배열에 담기
// 투포인트 배열사용

// 41일 때

// start = [0]
// end = [0]
// 2

// 수가 부족하므로 end 추가
// 2,3
// 추가 추가추가
// 2,3,5
// 2,3,5,7
// 2,3,5,7,11
// 2,3,5,7,11,13 = 41, answer++
// 오른쪽 값이 같거나 작으면 start++ (값이 줄어든다)
// 3,5,7,11,13 = 39, 추가
// 3,5,7,11,13,17 = 56 오른쪽값이 41이니까 start++;
// 11,13,17 = 41 answer++
// 11,13,17,23 = 64 start++;
// 13,16,23 =52 start++;
// 16,23 = 39
// 16,23,29 이런식으로 계속하면
// 41 자기 수에 도달하면 종료.
// answer값 추출
let input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let N = Number(input);
let sosu = new Array();
let visited = new Array(N + 1).fill(false);
let start = 0;
let end = 0;
let answer = 0;
makeSosu();
let value = sosu[start];

// console.log(sosu);
while (start <= end) {
  if (value < N) {
    end++;
    value += sosu[end];
  } else if (value === N) {
    answer++;
    value -= sosu[start];
    start++;
  } else if (value > N) {
    value -= sosu[start];
    start++;
  } else {
    break;
  }
}
console.log(answer);

//에라스토테네스의 체. 소수만들기
function makeSosu() {
  for (let i = 2; i <= N; i++) {
    if (visited[i] === true) continue;
    sosu.push(i);
    for (let j = 2; i * j <= N; j++) {
      if (!visited[i * j]) visited[i * j] = true;
    }
  }
}