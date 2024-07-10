// N M 배열길이 활성상태 바이러스 수
// 2의 위치들을 queue에 담기. perm을 만들어서 length중 M개만큼 뽑기
// BFS로 시작
// min값 저장
let dx = [-1, 0, 1, 0];
let dy = [0, 1, 0, -1];
let storeTwo = [];
let p = 0;
let max = Infinity;
const [first, ...second] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [N, M] = first.split(" ").map(Number);

let arr = new Array(N);

for (let i = 0; i < N; i++) {
  arr[i] = second[i].split(" ").map(Number);
}
// for (let i = 0; i < N; i++) {
//   console.log(arr[i]);
// }

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (arr[i][j] === 2) {
      storeTwo.push([i, j]);
    }
  }
}
p = storeTwo.length;

let pOrder = [];
let visited = new Array(p).fill(false);
perm(pOrder, visited, 0, 0);

if (max === Infinity) {
  console.log(-1);
} else {
  console.log(max);
}
//p에서 M개 뽑기
function perm(numArr, visit, start, cnt) {
  if (cnt === M) {
    let perNum = BFS(numArr);
    if (perNum !== -1 && max > perNum) {
      max = perNum;
    }
  } else {
    for (let i = start; i < p; i++) {
      if (visit[i]) {
        continue;
      }
      visit[i] = true;
      pOrder.push(i);
      perm(numArr, visit, i, cnt + 1);
      pOrder.pop();
      visit[i] = false;
    }
  }
}

//numArr을 받고 BFS 시작
function BFS(bfsArr) {
  //bfsArr은 start위치를 가질 storeTow의 index가 들어있다.
  let queue = [];
  let idx = 0;
  let copyArr = arr.map((v) => [...v]);
  let maxCount = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (copyArr[i][j] === 2) {
        copyArr[i][j] = -1;
      }
    }
  }
  for (let i = 0; i < bfsArr.length; i++) {
    let [startX, startY] = storeTwo[bfsArr[i]];
    copyArr[startX][startY] = 2;
    queue.push([startX, startY, 0]);
  }
  while (queue.length > idx) {
    let [x, y, count] = queue[idx];

    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (!checkWall(nx, ny)) {
        continue;
      }
      if (copyArr[nx][ny] === -1) {
        copyArr[nx][ny] = 2;
        queue.push([nx, ny, count + 1]);
      } else if (copyArr[nx][ny] === 0) {
        if (maxCount < count + 1) {
          maxCount = count + 1;
        }
        copyArr[nx][ny] = count + 1;
        queue.push([nx, ny, count + 1]);
      }
    }
    idx++;
  }
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (copyArr[i][j] === 0) {
        return -1;
      }
    }
  }
  return maxCount;
}

function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < N;
}