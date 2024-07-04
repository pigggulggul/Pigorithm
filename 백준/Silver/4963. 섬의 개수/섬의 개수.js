let input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let dx = [-1, -1, 0, 1, 1, 1, 0, -1];
let dy = [0, 1, 1, 1, 0, -1, -1, -1];
let arrSize = input.shift().split(" ").map(Number);
let [N, M] = [arrSize[1], arrSize[0]];
let arr;
let answer;
let landcount;
let queue;
while (N !== 0 && M !== 0) {
  answer = 0;
  landcount = 2;
  queue = [];
  arr = [];
  arr = new Array(N);
  for (let i = 0; i < N; i++) {
    arr[i] = new Array(M);
  }
  for (let i = 0; i < N; i++) {
    let inputArr = input.shift().split(" ").map(Number);
    for (let j = 0; j < M; j++) {
      arr[i][j] = inputArr[j];
    }
  }

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (arr[i][j] == 1) {
        BFS(i, j);
        landcount++;
        answer++;
      }
    }
  }

  console.log(answer);
  arrSize = input.shift().split(" ").map(Number);
  [N, M] = [arrSize[1], arrSize[0]];
}
function BFS(startX, startY) {
  queue = [[startX, startY]];
  arr[startX][startY] = landcount;
  let idx = 0;
  while (queue.length != idx) {
    let [x, y] = queue[idx];
    for (let d = 0; d < 8; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny)) {
        continue;
      }
      if (arr[nx][ny] === 1) {
        arr[nx][ny] = landcount;
        queue.push([nx, ny]);
      }
    }
    idx++;
  }
}
function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < M;
}