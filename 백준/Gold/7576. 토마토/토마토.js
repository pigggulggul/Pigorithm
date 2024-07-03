const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let result = 0;
let ground;
let queue = [];
let dx = [0, 1, 0, -1];
let dy = [-1, 0, 1, 0];
const [M, N] = input.shift().split(" ").map(Number);
ground = new Array(N);
for (let i = 0; i < N; i++) {
  ground[i] = new Array(M);
}
for (let i = 0; i < N; i++) {
  let num = input.shift().split(" ").map(Number);
  for (let j = 0; j < M; j++) {
    ground[i][j] = num[j];
    if (num[j] === 1) {
      queue.push([i, j, 1]);
    }
  }
}
if (findZero() === false) {
  console.log(0);
  return;
}
BFS();
result = checkDay();
console.log(result);

function BFS() {
  let idx = 0;
  while (queue.length != idx) {
    let [x, y, count] = queue[idx];
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny)) {
        continue;
      }
      if (ground[nx][ny] === 0) {
        ground[nx][ny] = count;
        queue.push([nx, ny, count + 1]);
      }
    }
    idx++;
  }
}
function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < M;
}

function checkDay() {
  let max = -1;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (ground[i][j] === 0) {
        return -1;
      }
      if (ground[i][j] > max) {
        max = ground[i][j];
      }
    }
  }
  return max;
}
function findZero() {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (ground[i][j] === 0) {
        return true;
      }
    }
  }
  return false;
}