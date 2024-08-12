// dp를 이용한 문제
// 각 동전으로 만들 수 있는 경우의 수를 구해야함
// 동전을 돌면서 기존 금액의 경우의수(dp[i]) + 코인이 들어가서 바뀔 경우의 수(dp[i-coin])
// 이때 dp[i-coin]===0이면 dp[i]++한다

let [T, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
T = Number(T);

let idx = 0;
for (let t = 0; t < T; t++) {
  let coinNum = Number(input[idx++]);
  let coins = input[idx++].split(" ").map(Number);
  let goal = Number(input[idx++]);
  //   console.log(coinNum, coins, goal);

  let dp = new Array(goal + 1).fill(0);
  dp[0] = 1;
  coins.forEach((coin) => {
    for (let i = coin; i <= goal; i++) {
      dp[i] += dp[i - coin];
    }
  });
  console.log(dp[goal]);
}