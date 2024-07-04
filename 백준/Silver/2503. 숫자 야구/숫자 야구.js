const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let N = Number(input.shift());
let target = [];
let answer = 0;
for (let i = 0; i < N; i++) {
  let ballList = input.shift().split(" ").map(Number);
  target.push([ballList[0], ballList[1], ballList[2]]);
}
for (let i = 1; i <= 9; i++) {
  for (let j = 1; j <= 9; j++) {
    for (let k = 1; k <= 9; k++) {
      if (i === j || j === k || k === i) {
        continue;
      }
      number = "" + i + j + k;
      checkNumber(number);
    }
  }
}
function checkNumber(num) {
  let result = 0;
  for (let i = 0; i < N; i++) {
    let strike = 0;
    let ball = 0;
    let compareNum = "" + target[i][0];
    for (let j = 0; j < 3; j++) {
      if (compareNum.charAt(j) === num.charAt(j)) {
        strike++;
      }
      if (
        compareNum.includes(num.charAt(j)) &&
        compareNum.charAt(j) !== num.charAt(j)
      ) {
        ball++;
      }
    }
    if (strike == target[i][1] && ball == target[i][2]) {
      result++;
    }
  }
  if (result == N) {
    // console.log(num);
    answer++;
  }
}
console.log(answer);