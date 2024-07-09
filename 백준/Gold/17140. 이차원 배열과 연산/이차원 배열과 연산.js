// 첫째줄에 r c k, arr[r][c] ===k 가 되기위해서 몇번을 해야할지
//배열 A는 3x3이고

// 행의 개수 >= 열의개수면 R연산 아니면 C연산
// 행을 돌며 개수를 새며 map에 저장.
// map은 [a,b]일때 b 오름차순. b 같으면 a 오름차순
// 끝날떄마다 A[r][c]를 검사해서 100초가 지나도 A[r][c]=k가 되지 않으면 -1출력
const [first, ...inputArr] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [r, c, k] = first.split(" ").map(Number);

let arr = new Array(3);
//3x3 배열로 시작
for (let i = 0; i < 3; i++) {
  arr[i] = inputArr[i].split(" ").map(Number);
}

let time = 0;
if (r - 1 <= arr.length && c - 1 <= arr[0].length && arr[r - 1][c - 1] === k) {
  console.log(time);
  return;
}
while (time < 100) {
  if (arr[0].length <= arr.length) {
    calcR();
  } else {
    calcC();
  }
  //   for (let i = 0; i < arr.length; i++) {
  //     console.log(arr[i]);
  //   }
  //   console.log("\n");

  time++;
  if (
    r - 1 <= arr.length &&
    c - 1 <= arr[0].length &&
    arr[r - 1][c - 1] === k
  ) {
    console.log(time);
    return;
  }
}
console.log(-1);
function calcR() {
  let arrStore = [];
  let maxLength = 0;

  for (let i = 0; i < arr.length; i++) {
    let map = new Map();
    for (let j = 0; j < arr[0].length; j++) {
      if (arr[i][j] === 0) {
        continue;
      }
      if (map.has(arr[i][j])) {
        map.set(arr[i][j], map.get(arr[i][j]) + 1);
      } else {
        map.set(arr[i][j], 1);
      }
    }
    let maptoArr = Array.from(map).sort((a, b) => {
      if (a[1] === b[1]) {
        return a[0] - b[0];
      }
      return a[1] - b[1];
    });
    arrStore.push(maptoArr);
  }
  for (let i = 0; i < arrStore.length; i++) {
    if (arrStore[i].length > maxLength) {
      maxLength = arrStore[i].length;
    }
  }
  let newArr = new Array(arr.length);
  for (let i = 0; i < arr.length; i++) {
    newArr[i] = new Array(maxLength * 2).fill(0);
  }
  for (let i = 0; i < arrStore.length; i++) {
    for (let j = 0; j < arrStore[i].length; j++) {
      let [a, b] = arrStore[i][j];
      newArr[i][j * 2] = a;
      newArr[i][j * 2 + 1] = b;
    }
  }
  arr = newArr;
}
function calcC() {
  let arrStore = [];
  let maxLength = 0;

  for (let j = 0; j < arr[0].length; j++) {
    let map = new Map();
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][j] === 0) {
        continue;
      }
      if (map.has(arr[i][j])) {
        map.set(arr[i][j], map.get(arr[i][j]) + 1);
      } else {
        map.set(arr[i][j], 1);
      }
    }
    let maptoArr = Array.from(map).sort((a, b) => {
      if (a[1] === b[1]) {
        return a[0] - b[0];
      }
      return a[1] - b[1];
    });
    arrStore.push(maptoArr);
    //여기서 새로운 배열을 만들어줘야함
  }
  for (let i = 0; i < arrStore.length; i++) {
    if (arrStore[i].length > maxLength) {
      maxLength = arrStore[i].length;
    }
  }
  let newArr = new Array(maxLength * 2);

  for (let i = 0; i < maxLength * 2; i++) {
    newArr[i] = new Array(arr[0].length).fill(0);
  }
  for (let i = 0; i < arrStore.length; i++) {
    for (let j = 0; j < arrStore[i].length; j++) {
      let [a, b] = arrStore[i][j];

      newArr[j * 2][i] = a;
      newArr[j * 2 + 1][i] = b;
    }
  }
  arr = newArr;
}