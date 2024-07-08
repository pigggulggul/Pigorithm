const [N, ...inputArr] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// 각 회의 i에 대해 시작시간과 끝나는 시간이 주어져 있고
// 회의의 최대 개수 찾기
// 끝나는 시간이 필요
let arr = [];
for (let i = 0; i < Number(N); i++) {
  let [item1, item2] = inputArr[i].split(" ").map(Number);
  arr.push([item1, item2]);
}
arr.sort((a, b) => {
  if (a[1] === b[1]) {
    return a[0] - b[0];
  }
  return a[1] - b[1];
});

let time = 0;
let count = 0;
for (let i = 0; i < N; i++) {
  if (time <= arr[i][0]) {
    time = arr[i][1];
    count++;
  }
}
console.log(count);