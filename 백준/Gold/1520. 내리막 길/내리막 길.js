const [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [N, M] = first.split(" ").map(Number);

const arr = input.map((line) => line.split(" ").map(Number));
const dp = Array.from({ length: N }, () => Array(M).fill(-1));

const dx = [-1, 0, 1, 0];
const dy = [0, 1, 0, -1];

function isInside(x, y) {
  return x >= 0 && x < N && y >= 0 && y < M;
}

function dfs(x, y) {
  if (x === N - 1 && y === M - 1) return 1; // 도착 지점
  if (dp[x][y] !== -1) return dp[x][y]; // 이미 계산된 경로 수 사용

  dp[x][y] = 0; // 초기화
  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i];
    const ny = y + dy[i];

    if (isInside(nx, ny) && arr[nx][ny] < arr[x][y]) {
      dp[x][y] += dfs(nx, ny);
    }
  }

  return dp[x][y];
}

console.log(dfs(0, 0)); // 시작 지점에서 DFS 시작