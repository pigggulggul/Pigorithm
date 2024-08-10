const [first, second] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, K] = first.split(" ").map(Number);
const plusArr = second.split(" ").map(Number);

let result = 0;
let map = new Map();
let currentSum = 0;

// 초기 조건: 부분합 0을 미리 추가해둔다.
map.set(0, 1);

for (let i = 0; i < N; i++) {
  currentSum += plusArr[i];

  // 현재 부분합에서 K를 뺀 값이 map에 존재하면 그 값만큼 result를 증가시킨다.
  if (map.has(currentSum - K)) {
    result += map.get(currentSum - K);
  }

  // 현재 부분합을 map에 추가하거나 빈도수를 증가시킨다.
  if (map.has(currentSum)) {
    map.set(currentSum, map.get(currentSum) + 1);
  } else {
    map.set(currentSum, 1);
  }
}

console.log(result);