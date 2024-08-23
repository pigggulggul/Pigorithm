// N개의 물건 K의 하중
// W의 무게와 V의 가치
// 넣을 수 있는 물건의 최댓값

// 4 8

// W V
// 6 13
// 1 5
// 2 8
// 4 8
// 3 6
// 5 12

// K에 가장 가까우면서 가치가 높아야한다.
// 최대한 높은 가치를 넣어야함
// dp를 만들어서 val이 0이 아닌곳의 kg에 본인의 kg를

let [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, K] = first.split(" ").map(Number);

let backpack = Array.from({ length: N }, () => Array());
let maxVal = 0;

let dp = new Array(K + 1).fill(0);
for (let i = 0; i < N; i++) {
  backpack[i] = input[i].split(" ").map(Number);
}
backpack.sort((a, b) => {
  if (b[1] / b[0] === a[1] / a[0]) {
    return a[0] - b[0];
  }
  return b[1] / b[0] - a[1] / a[0];
});
for (let i = 0; i < N; i++) {
  let kg = backpack[i][0];
  let val = backpack[i][1];
  //   console.log(kg, val);

  let queue = checkIdx();
  //   console.log(queue);
  let newDp = JSON.parse(JSON.stringify(dp));
  for (let j = 0; j < queue.length; j++) {
    let idx = queue[j];
    if (idx + kg <= K && dp[idx + kg] < dp[idx] + val) {
      //   console.log("idx 값: ", idx, dp[idx]);
      //   console.log(dp[idx] + val);
      newDp[idx + kg] = dp[idx] + val;
    }
  }

  if (dp[kg] < val) {
    newDp[kg] = val;
  }
  dp = JSON.parse(JSON.stringify(newDp));
  //   console.log(dp);
}
for (let i = 0; i < dp.length; i++) {
  if (maxVal < dp[i]) maxVal = dp[i];
}
console.log(maxVal);

function checkIdx() {
  let queue = [];
  for (let i = 0; i <= K; i++) {
    if (dp[i] !== 0) queue.push(i);
  }
  return queue;
}