// 10x10 판이 있다.
// 블록은 3가지가 있고 원하는 곳에 넣으면 x축과 y축으로 각각 쭉 내려간다
// 내려가는 도중 다른 블록을 만나면 멈춘다.
// 세로로 내려간 블록은 4개의 행을 만나면 지워지고
// 가로로 내려간 블록은 4개의 열을 만나면 지워진다
// 연한 블록에 블록이 있으면 세로로 내려가는 공간에선 연한 블록이 있는 행의 수만큼 아래 행이 지워짐.
// 가로로 내려가는 공간에선 연한 블록이 있는 열의 수만큼 열이 지워짐.
// 지워질 때는 열 처리 후에 연한 블록 처리를 한다.

// DFS로 특정 방향으로 move()
// 멈추면 열 처리 delete()
// 정렬 sort()
// 연한블록 확인 checkLightGreen() checkLightBlue();
// 정렬 sort()

// 점수와 남아있는 타일의 개수 확인
const dx = [1, 0];
const dy = [0, 1];
const [N, ...block] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let tile = [
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
  [0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
  [0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
  [0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
  [0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
  [0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
];
let answer = 0;
for (let i = 0; i < Number(N); i++) {
  const [t, x, y] = block[i].split(" ").map(Number);
  move(x, y, t);
  deleteSero();
  deleteGaro();
  checkLightGreen();
  checkLightBlue();
}
// console.log(tile);
let remainTile = 0;
for (let i = 0; i < 10; i++) {
  for (let j = 0; j < 10; j++) {
    if (tile[i][j] === 1) {
      remainTile++;
    }
  }
}
console.log(answer);
console.log(remainTile);

function move(x, y, t) {
  if (t === 1) {
    //x y 내려가기
    let nx = x + dx[0];
    let ny = y + dy[0];
    while (true) {
      if (!checkWall(nx, ny)) {
        nx -= dx[0];
        ny -= dy[0];
        break;
      }
      if (tile[nx][ny] !== 0) {
        nx -= dx[0];
        ny -= dy[0];
        break;
      }
      nx += dx[0];
      ny += dy[0];
    }
    tile[nx][ny] = 1;

    let nnx = x + dx[1];
    let nny = y + dy[1];
    while (true) {
      if (!checkWall(nnx, nny)) {
        nnx -= dx[1];
        nny -= dy[1];
        break;
      }
      if (tile[nnx][nny] !== 0) {
        nnx -= dx[1];
        nny -= dy[1];
        break;
      }
      nnx += dx[1];
      nny += dy[1];
    }
    tile[nnx][nny] = 1;
  } else if (t === 2) {
    let nx = x + dx[0];
    let ny = y + dy[0];
    while (true) {
      if (!checkWall(nx, ny) || !checkWall(nx, ny + 1)) {
        nx -= dx[0];
        ny -= dy[0];
        break;
      }
      if (tile[nx][ny] !== 0 || tile[nx][ny + 1] !== 0) {
        nx -= dx[0];
        ny -= dy[0];
        break;
      }
      nx += dx[0];
      ny += dy[0];
    }
    tile[nx][ny] = 1;
    tile[nx][ny + 1] = 1;

    let nnx = x + dx[1];
    let nny = y + dy[1];
    while (true) {
      if (!checkWall(nnx, nny) || !checkWall(nnx, nny + 1)) {
        nnx -= dx[1];
        nny -= dy[1];
        break;
      }
      if (tile[nnx][nny] !== 0 || tile[nnx][nny + 1] !== 0) {
        nnx -= dx[1];
        nny -= dy[1];
        break;
      }
      nnx += dx[1];
      nny += dy[1];
    }
    tile[nnx][nny] = 1;
    tile[nnx][nny + 1] = 1;
  } else {
    let nx = x + dx[0];
    let ny = y + dy[0];
    while (true) {
      if (!checkWall(nx, ny) || !checkWall(nx + 1, ny)) {
        nx -= dx[0];
        ny -= dy[0];
        break;
      }
      if (tile[nx][ny] !== 0 || tile[nx + 1][ny] !== 0) {
        nx -= dx[0];
        ny -= dy[0];
        break;
      }
      nx += dx[0];
      ny += dy[0];
    }
    tile[nx][ny] = 1;
    tile[nx + 1][ny] = 1;

    let nnx = x + dx[1];
    let nny = y + dy[1];
    while (true) {
      if (!checkWall(nnx, nny) || !checkWall(nnx + 1, nny)) {
        nnx -= dx[1];
        nny -= dy[1];
        break;
      }
      if (tile[nnx][nny] !== 0 || tile[nnx + 1][nny] !== 0) {
        nnx -= dx[1];
        nny -= dy[1];
        break;
      }
      nnx += dx[1];
      nny += dy[1];
    }
    tile[nnx][nny] = 1;
    tile[nnx + 1][nny] = 1;
  }
}
function checkWall(x, y) {
  return x >= 0 && x < 10 && y >= 0 && y < 10;
}
function deleteSero() {
  for (let i = 9; i >= 4; i--) {
    if (
      tile[i][0] === 1 &&
      tile[i][1] === 1 &&
      tile[i][2] === 1 &&
      tile[i][3] === 1
    ) {
      tile[i][0] = 0;
      tile[i][1] = 0;
      tile[i][2] = 0;
      tile[i][3] = 0;
      answer++;
      sortSero(i);
      i++;
    }
  }
}
function deleteGaro() {
  for (let i = 9; i >= 4; i--) {
    if (
      tile[0][i] === 1 &&
      tile[1][i] === 1 &&
      tile[2][i] === 1 &&
      tile[3][i] === 1
    ) {
      tile[0][i] = 0;
      tile[1][i] = 0;
      tile[2][i] = 0;
      tile[3][i] = 0;
      answer++;
      sortGaro(i);
      i++;
    }
  }
}
function sortSero(num) {
  for (let i = num; i > 4; i--) {
    //한칸씩 땡겨오기
    tile[i][0] = tile[i - 1][0];
    tile[i][1] = tile[i - 1][1];
    tile[i][2] = tile[i - 1][2];
    tile[i][3] = tile[i - 1][3];
    tile[i - 1][0] = 0;
    tile[i - 1][1] = 0;
    tile[i - 1][2] = 0;
    tile[i - 1][3] = 0;
  }
}
function sortGaro(num) {
  for (let i = num; i > 4; i--) {
    //한칸씩 땡겨오기
    tile[0][i] = tile[0][i - 1];
    tile[1][i] = tile[1][i - 1];
    tile[2][i] = tile[2][i - 1];
    tile[3][i] = tile[3][i - 1];
    tile[0][i - 1] = 0;
    tile[1][i - 1] = 0;
    tile[2][i - 1] = 0;
    tile[3][i - 1] = 0;
  }
}
function checkLightGreen() {
  let count = 0;
  if (
    tile[4][0] === 1 ||
    tile[4][1] === 1 ||
    tile[4][2] === 1 ||
    tile[4][3] === 1
  ) {
    count++;
  }
  if (
    tile[5][0] === 1 ||
    tile[5][1] === 1 ||
    tile[5][2] === 1 ||
    tile[5][3] === 1
  ) {
    count++;
  }
  if (count === 1) {
    tile[9][0] = 0;
    tile[9][1] = 0;
    tile[9][2] = 0;
    tile[9][3] = 0;
    sortSero(9);
  } else if (count === 2) {
    tile[9][0] = 0;
    tile[9][1] = 0;
    tile[9][2] = 0;
    tile[9][3] = 0;
    sortSero(9);
    tile[9][0] = 0;
    tile[9][1] = 0;
    tile[9][2] = 0;
    tile[9][3] = 0;
    sortSero(9);
  }
}
function checkLightBlue() {
  let count = 0;
  if (
    tile[0][4] === 1 ||
    tile[1][4] === 1 ||
    tile[2][4] === 1 ||
    tile[3][4] === 1
  ) {
    count++;
  }
  if (
    tile[0][5] === 1 ||
    tile[1][5] === 1 ||
    tile[2][5] === 1 ||
    tile[3][5] === 1
  ) {
    count++;
  }
  if (count === 1) {
    tile[0][9] = 0;
    tile[1][9] = 0;
    tile[2][9] = 0;
    tile[3][9] = 0;
    sortGaro(9);
  } else if (count === 2) {
    tile[0][9] = 0;
    tile[1][9] = 0;
    tile[2][9] = 0;
    tile[3][9] = 0;
    sortGaro(9);
    tile[0][9] = 0;
    tile[1][9] = 0;
    tile[2][9] = 0;
    tile[3][9] = 0;
    sortGaro(9);
  }
}