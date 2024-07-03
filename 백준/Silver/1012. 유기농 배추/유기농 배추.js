const fs = require("fs");
const { parse } = require("path");
// TODO: 제출 시 경로 변환 필수 ("/dev/stdin")

let dx = [0, 1, 0, -1];
let dy = [-1, 0, 1, 0];
let input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let T = Number(input.shift());

for (let t = 0; t < T; t++) {
  const [M, N, Crop] = input.shift().split(" ").map(Number);
  let ground = new Array(N).fill(0);
  for (let i = 0; i < ground.length; i++) {
    ground[i] = new Array(M).fill(0);
  }
  for (let i = 0; i < Crop; i++) {
    const [b, a] = input.shift().split(" ").map(Number);
    ground[a][b] = 1;
  }
  let result = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (ground[i][j] === 1) {
        BFS(i, j, ground);
        result++;
      }
    }
  }
  console.log(result);
}

function BFS(startX, startY, ground) {
  let queue = [[startX, startY]];
  ground[startX][startY] = 0;
  while (queue.length != 0) {
    let [x, y] = queue.shift();
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny, ground)) {
        continue;
      }
      if (ground[nx][ny] === 1) {
        ground[nx][ny] = 0;
        queue.push([nx, ny]);
      }
    }
  }
}

function checkWall(x, y, arr) {
  return x >= 0 && x < arr.length && y >= 0 && y < arr[0].length;
}