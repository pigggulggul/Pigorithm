// 뿌요뿌요 필드가 주어진다.
// 4개 이상 모이면 연쇄폭발.

// while(true)
// 필드에서 4개 이상 모인 필드 찾기 (아래에서 위로. 이때 알파벳이 없으면 그만 찾기)
// 있으면 터뜨리기. 없으면 while 종료
// 중력 받기

//4개 이상 모인 필드 찾을때는 queue에 {x,y}담기.
// 터뜨릴때 다시 bfs해서 그 부분.으로 바꾸기

const [...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let dx = [-1, 0, 1, 0];
let dy = [0, 1, 0, -1];

let gamePlate = Array.from({ length: 12 }, () => Array(6));
let visited;
for (let i = 0; i < 12; i++) {
  for (let j = 0; j < 6; j++) {
    gamePlate[i][j] = input[i][j];
  }
}

let result = 0;
// console.log(gamePlate);
let queue = [];
while (true) {
  visited = Array.from({ length: 12 }, () => Array(6).fill(false));
  queue = findPuyo();
  if (queue.length > 0) {
    popPuyo(queue);
  } else {
    break;
  }
  gravity();
  result++;
}
console.log(result);
function findPuyo() {
  let que = [];
  for (let i = 11; i >= 0; i--) {
    for (let j = 0; j < 6; j++) {
      if (!visited[i][j] && gamePlate[i][j] !== ".") {
        let count = bfs(i, j, gamePlate[i][j]);
        if (count >= 4) {
          que.push([i, j]);
        }
      }
    }
  }
  return que;
}
function popPuyo(queue) {
  for (let i = 0; i < queue.length; i++) {
    const [x, y] = queue[i];
    popBfs(x, y, gamePlate[x][y]);
  }
}
function gravity() {
  //중력 영향받게하기
  for (let i = 0; i < 6; i++) {
    let idx = -1;
    idx = findIdx(i);
    if (idx === 13) continue;
    for (let j = idx; j >= 0; j--) {
      if (idx === -1) {
        idx = findIdx(i);
        if (idx === 13) break;
      }
      if (idx !== -1 && gamePlate[j][i] !== ".") {
        gamePlate[idx][i] = gamePlate[j][i];
        gamePlate[j][i] = ".";
        idx = -1;
      }
    }
  }
}

function findIdx(y) {
  for (let i = 11; i >= 0; i--) {
    if (gamePlate[i][y] === ".") {
      return i;
    }
  }
  return 13;
}
function bfs(startX, startY, alphabet) {
  let que = [[startX, startY]];
  let idx = 0;
  let count = 1;
  visited[startX][startY] = true;
  while (que.length !== idx) {
    let [x, y] = que[idx];
    visited[x][y] = true;
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];
      if (!checkWall(nx, ny)) continue;

      if (!visited[nx][ny] && gamePlate[nx][ny] === alphabet) {
        visited[nx][ny] = true;
        que.push([nx, ny]);
        count++;
      }
    }
    idx++;
  }
  return count;
}
function popBfs(startX, startY, alphabet) {
  let que = [[startX, startY]];
  let idx = 0;
  gamePlate[startX][startY] = ".";
  while (que.length !== idx) {
    let [x, y] = que[idx];
    gamePlate[x][y] = ".";
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];
      if (!checkWall(nx, ny)) continue;

      if (gamePlate[nx][ny] === alphabet) {
        gamePlate[nx][ny] = ".";
        que.push([nx, ny]);
      }
    }
    idx++;
  }
}

function checkWall(x, y) {
  return x >= 0 && x < 12 && y >= 0 && y < 6;
}