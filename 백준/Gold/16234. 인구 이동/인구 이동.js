// N min max
// 배열안의 숫자들

const dx = [-1, 0, 1, 0];
const dy = [0, 1, 0, -1];
const [first, ...arrInput] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [N, min, max] = first.split(" ").map(Number);
let flag = true;
let day = 0;
let visited = new Array(N);
for (let i = 0; i < N; i++) {
  visited[i] = new Array(N).fill(0);
}
let arr = new Array(N);
for (let i = 0; i < arrInput.length; i++) {
  arr[i] = arrInput[i].split(" ").map(Number);
}

// 인접국가의 인구 수가 min 이상 max 이하면 국경선이 열린다. 열리면서 visited 1로 방문표시를 한다.
while (flag === true) {
  flag = false;
  visited = new Array(N);
  for (let i = 0; i < N; i++) {
    visited[i] = new Array(N).fill(0);
  }
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (visited[i][j] === 0) {
        BFS(i, j);
      }
    }
  }
}

// for (let i = 0; i < arrInput.length; i++) {
//   console.log(arr[i]);
// }
console.log(day);
// 국경선이 열리면 열린 국가들끼리 더해서 수만큼 나눈다. for문을 2차원으로 돌면서 visited가 있으면
// visited로 연결된 국가들의 수를 더하면서 총 값과 수를 정한다. 이 때 visited 2로 표시.
// visited가 2인 곳들을 다 총값/수로 지정하고 visited를 -1로 바꾼다.
// 이 때 flag===false고 값이 다르면 falg=true;
// 이 과정을 반복하여 인구이동이 멈출때까지 반복하고 인구이동 day를 리턴

function BFS(startX, startY) {
  let queue = [[startX, startY]];
  let idx = 0;
  let totalPop = 0;
  let totalCount = 0;
  while (queue.length > idx) {
    let [x, y] = queue[idx];
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny)) {
        continue;
      }
      let pop = Math.abs(arr[nx][ny] - arr[x][y]);
      if (visited[nx][ny] === 0 && min <= pop && pop <= max) {
        visited[nx][ny] = 1;
        totalPop += arr[nx][ny];
        totalCount++;
        queue.push([nx, ny]);
      }
    }
    idx++;
  }
  if (totalCount !== 0) {
    // console.log(totalPop, totalCount, Math.floor(totalPop / totalCount));
    changeValue(startX, startY, Math.floor(totalPop / totalCount));
  }
}
function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < N;
}
function changeValue(startX, startY, value) {
  let queue = [[startX, startY]];
  let idx = 0;
  while (queue.length > idx) {
    let [x, y] = queue[idx];
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny)) {
        continue;
      }
      if (visited[nx][ny] === 1) {
        visited[nx][ny] = -1;
        if (flag === false && value !== arr[nx][ny]) {
          flag = true;
          day++;
        }
        arr[nx][ny] = value;
        queue.push([nx, ny]);
      }
    }
    idx++;
  }
}