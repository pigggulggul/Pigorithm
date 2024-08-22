// 14
// 2 4 2 1 8 6 2 3 1 6 5 6

//결국 그 숫자에 도달했을때 이 숫자까지 도달하는데 최대가 몇이냐가 중요.

const [first, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const N = Number(first);
let arr = new Array(input.length);
let maxNum = -1;

arr = input.split(" ").map(Number);
// console.log(arr);
maxNum = 1;
let store = new Array(1001).fill(0);
for (let i = 0; i < N; i++) {
  let num = arr[i];
  let compareMax = 0;
  for (let j = 1; j < num; j++) {
    if (store[j] === 0) continue;
    if (compareMax < store[j]) {
      compareMax = store[j];
    }
  }
  store[num] = compareMax + 1;
  if (maxNum < store[num]) {
    maxNum = store[num];
  }
}
console.log(maxNum);