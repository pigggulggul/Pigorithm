// N 행성개수
// M 행성 필요속도. 최대 10억이다.
// 제시된 속도와 같거나 배수일경우에만 지날 수 있다.
// 속도는 줄이기만 가능하다.
// 결국 처음 수가 1배부터 계속 올려서 함수를 돌린다.
// 300 -> 400이 더 크니까 break;
// 600 -> 400 -> 500이 더 크니까 break;
// 900 -> 800 -> 500 -> 400 -> 300 출력.

//배수로 진행. 주어진 수보다 작은 최대한의 배수로 만든다. 그 수로 만들기.다음 값으로
//numArr = [300,400,500,400,300];
//multify = 1;
// remainNum;
// 일반적으로 풀면 시간초과가 나온다.
// 반대로 접근해보자
// 맨 끝에서부터 하면 배수만 정해주면 된다.

let [N, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
N = Number(N);
const numArr = input.split(" ").map(Number);

// 맨 마지막 행성 속도
let speed = numArr[N - 1];

for (let i = N - 2; i >= 0; i--) {
  // 점점 내려간다. 앞에 숫자가 더 크면 갱신
  if (speed < numArr[i]) {
    speed = numArr[i];
  } else if (speed > numArr[i] && speed % numArr[i] != 0) {
    speed = Math.floor(speed / numArr[i] + 1) * numArr[i];
  }
}

console.log(speed);