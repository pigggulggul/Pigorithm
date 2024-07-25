// 1.비어있는 칸 중 좋아하는 학생이 인접한 칸에 가장 많은 자리를 정한다.
// 2.1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
// 3.2를 만족하는 칸도 위로갈수록, 왼쪽으로 갈수록.

// NxN칸에 총 N^2의 자리가 존재함.
let [N, ...arr] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
N = Number(N);
let plate = new Array(N);
let maxCount = 0;
let maxArr = new Array();
let maxRemainCount = 0;
let maxRemainArr = new Array();
let dx = [-1, 0, 1, 0];
let dy = [0, 1, 0, -1];
let result = 0;
for (let i = 0; i < plate.length; i++) {
  plate[i] = new Array(N).fill(0);
}
for (let i = 0; i < arr.length; i++) {
  maxCount = 0;
  maxRemainCount = 0;
  maxArr = new Array();
  maxRemainArr = new Array();
  const [stuNum, ...favoriteStu] = arr[i].split(" ").map(Number);
  checkFavorite(stuNum, favoriteStu);
  if (maxArr.length === 1) {
    const [x, y] = maxArr[0];
    plate[x][y] = stuNum;
    continue;
  }
  checkRemain();
  if (maxRemainArr.length === 1) {
    const [x, y] = maxRemainArr[0];
    plate[x][y] = stuNum;
  } else {
    maxRemainArr.sort((a, b) => {
      if (a[0] === b[0]) {
        return a[1] - b[1];
      }
      return a - b;
    });
    const [x, y] = maxRemainArr[0];
    plate[x][y] = stuNum;
  }
  //   console.log("-------------------------------");
  //   console.log("숫자", stuNum);
  //   console.log("좋아하는 최대 인접 숫자값", maxCount);
  //   console.log("숫자 배열", maxArr);
  //   console.log("좋아하는 최대 빈 곳", maxRemainCount);
  //   console.log("가장 많은 여분의 배열", maxRemainArr);
}
for (let i = 0; i < arr.length; i++) {
  const [stuNum, ...favoriteStu] = arr[i].split(" ").map(Number);
  checkLike(stuNum, favoriteStu);
}
// console.log(plate);
console.log(result);
// console.log(maxCount);
// console.log(maxArr);
// console.log(maxRemainCount);
// console.log(maxRemainArr);

// 각 칸을 돌면서 1번을 구한다.
// maxCount에 넣고 maxArr에 maxCount만큼 배열을 넣는다.
// maxCount가 바뀌면 maxArr도 같이 바꾼다.
function checkFavorite(num, favorite) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (plate[i][j] !== 0) {
        continue;
      }
      let count = 0;
      for (let d = 0; d < 4; d++) {
        let nx = i + dx[d];
        let ny = j + dy[d];
        if (!checkWall(nx, ny)) {
          continue;
        }
        favorite.map((item) => {
          if (item === plate[nx][ny]) {
            count++;
          }
        });
      }
      if (maxCount < count) {
        maxCount = count;
        maxArr = new Array();
        maxArr.push([i, j]);
      } else if (maxCount === count) {
        maxArr.push([i, j]);
      }
    }
  }
}

// 다 돌았는데 maxArr이 한 개면 그 칸에 숫자를 넣는다.
// 여러개면 4방향을 검사해서 비어있는 칸을 센다(0인 칸을 센다)
// maxRemainCount, maxRemainArr
function checkRemain() {
  for (let i = 0; i < maxArr.length; i++) {
    let [x, y] = maxArr[i];
    if (plate[x][y] !== 0) {
      continue;
    }
    let count = 0;
    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];
      if (!checkWall(nx, ny)) {
        continue;
      }
      if (plate[nx][ny] !== 0) {
        continue;
      } else {
        count++;
      }
    }
    if (maxRemainCount < count) {
      maxRemainCount = count;
      maxRemainArr = new Array();
      maxRemainArr.push([x, y]);
    } else if (maxRemainCount === count) {
      maxRemainArr.push([x, y]);
    }
  }
}
function checkLike(num, favorite) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (plate[i][j] === num) {
        let count = 0;
        for (let d = 0; d < 4; d++) {
          let nx = i + dx[d];
          let ny = j + dy[d];

          if (!checkWall(nx, ny)) {
            continue;
          }
          favorite.map((item) => {
            if (item === plate[nx][ny]) {
              count++;
            }
          });
        }
        if (count === 0) {
          result += 0;
        } else if (count === 1) {
          result += 1;
        } else if (count === 2) {
          result += 10;
        } else if (count === 3) {
          result += 100;
        } else if (count === 4) {
          result += 1000;
        }
        return;
      }
    }
  }
}

// maxRemainSort해서 맨 위에 있는 거를 넣는다
function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < N;
}