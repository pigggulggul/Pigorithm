const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let N = Number(input.shift());
let building = new Array(N);
let count = 2;
let countBuilding = [];
let dx = [-1, 0, 1, 0];
let dy = [0, 1, 0, -1];
for (let i = 0; i < N; i++) {
  building[i] = new Array(N);
}
for (let i = 0; i < N; i++) {
  let arr = input.shift();
  for (let j = 0; j < N; j++) {
    building[i][j] = Number(arr.charAt(j));
  }
}

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (building[i][j] === 1) {
      BFS(i, j);
      count++;
    }
  }
}
countBuilding.sort((a, b) => a - b);
console.log(countBuilding.length);
for (let i = 0; i < countBuilding.length; i++) {
  console.log(countBuilding[i]);
}

function BFS(startX, startY) {
  let queue = [[startX, startY]];
  building[startX][startY] = count;
  let idx = 0;
  let num = 1;
  while (queue.length != idx) {
    let [x, y] = queue[idx];
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny)) {
        continue;
      }

      if (building[nx][ny] == 1) {
        building[nx][ny] = count;
        num++;
        queue.push([nx, ny]);
      }
    }
    idx++;
  }
  countBuilding.push(num);
}
function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < N;
}