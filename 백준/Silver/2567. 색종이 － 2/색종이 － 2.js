const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const N = Number(input.shift());
let arr = new Array(101).fill(0);
for (let i = 0; i < arr.length; i++) {
  arr[i] = new Array(101).fill(0);
}

for (let i = 0; i < N; i++) {
  const [x, y] = input.shift().split(" ").map(Number);
  //x하고 y부터 x+10 y+10까지 범위 색 채우기

  fillPaper(x, y);
}

let count = 0;
for (let i = 0; i < 101; i++) {
  for (let j = 0; j < 100; j++) {
    if (
      (arr[i][j] === 0 && arr[i][j + 1] === 1) ||
      (arr[i][j] === 1 && arr[i][j + 1] === 0)
    ) {
      count++;
    }
  }
}
for (let i = 0; i < 100; i++) {
  for (let j = 0; j < 101; j++) {
    if (
      (arr[i][j] === 0 && arr[i + 1][j] === 1) ||
      (arr[i][j] === 1 && arr[i + 1][j] === 0)
    ) {
      count++;
    }
  }
}
console.log(count);

function fillPaper(x, y) {
  for (let i = x; i < x + 10; i++) {
    for (let j = y; j < y + 10; j++) {
      if (i > 100 || j > 100) {
        continue;
      }
      arr[i][j] = 1;
    }
  }
}