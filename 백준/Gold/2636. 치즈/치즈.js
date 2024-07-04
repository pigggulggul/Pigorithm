const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, M] = input.shift().split(" ").map(Number);
const dx = [-1, 0, 1, 0];
const dy = [0, 1, 0, -1];
let queue;
let time = 0;
let cheesePlate = new Array(N).fill(0);
let maxCheese = 0;
for (let i = 0; i < N; i++) {
  cheesePlate[i] = new Array(M).fill(0);
}
for (let i = 0; i < N; i++) {
  item = input.shift().split(" ").map(Number);
  for (let j = 0; j < M; j++) {
    cheesePlate[i][j] = item[j];
  }
}
if (checkCheese() === false) {
  console.log(time);
  console.log(maxCheese);
  return;
}
//여기서 먼저 치즈가 있는지 검사해야함
while (true) {
  searchRange(0, 0);
  time++;
  deleteCheese();
  if (checkCheese() === false) {
    break;
  }
}
console.log(time);
console.log(maxCheese);

/*
while(true)
1. 사라지는 범위 찾기
2. 1시간 지나고 없애기
3. 남은 치즈 검사
*/
function searchRange(startX, startY) {
  queue = [[startX, startY]];
  cheesePlate[startX][startY] = 2;
  let idx = 0;
  while (queue.length != idx) {
    let [x, y] = queue[idx];
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny)) {
        continue;
      }
      if (cheesePlate[nx][ny] === 1) {
        cheesePlate[nx][ny] = 3;
      } else if (cheesePlate[nx][ny] === 0) {
        cheesePlate[nx][ny] = 2;
        queue.push([nx, ny]);
      }
    }
    idx++;
  }
}
function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < M;
}
function deleteCheese() {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (cheesePlate[i][j] === 2 || cheesePlate[i][j] === 3) {
        cheesePlate[i][j] = 0;
      }
    }
  }
}
function checkCheese() {
  let cheese = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (cheesePlate[i][j] === 1) {
        cheese++;
      }
    }
  }
  if (cheese > 0) {
    maxCheese = cheese;
    return true;
  }
  return false;
}