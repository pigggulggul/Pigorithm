const [first, second, ...third] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const N = Number(first);
const popular = second.split(" ").map(Number);
const arr = new Array(N + 1);
let min = Infinity;
for (let i = 0; i <= N; i++) {
  arr[i] = new Array();
}
for (let i = 0; i < N; i++) {
  const [cnt, ...num] = third[i].split(" ").map(Number);
  for (let j = 0; j < cnt; j++) {
    arr[i + 1].push(num[j]);
  }
}
// console.log(arr);
for (let i = 1; i <= N / 2; i++) {
  let visited = new Array(N + 1);
  perm([], visited, 1, 0, i);
}
if (min === Infinity) {
  console.log(-1);
} else {
  console.log(min);
}
function perm(permArr, visit, start, cnt, maximum) {
  if (cnt === maximum) {
    //인구 나누기. 이제 ab로 나눠야함
    let a = [];
    let b = [];
    for (let i = 1; i <= N; i++) {
      if (permArr.includes(i)) {
        a.push(i);
      } else {
        b.push(i);
      }
    }
    let resultA = checkArr(a);
    let resultB = checkArr(b);
    if (resultA && resultB) {
      let compareNum = calcPopular(a, b);
      if (min > compareNum) {
        min = compareNum;
      }
    }
  } else {
    for (let i = start; i <= N; i++) {
      if (visit[i] === true) {
        continue;
      }
      visit[i] = true;
      permArr.push(i);
      perm(permArr, visit, i, cnt + 1, maximum);
      permArr.pop();
      visit[i] = false;
    }
  }
}
function checkArr(check) {
  // console.log(check);
  if (check.length === 1) {
    return true;
  }

  let visited = new Array(N + 1).fill(false);
  for (let i = 0; i < check.length; i++) {
    visited[check[i]] = true;
  }

  let queue = [];
  queue.push(check[0]);
  let idx = 0;
  while (queue.length > idx) {
    let num = queue[idx];

    for (let i = 0; i < arr[num].length; i++) {
      if (!visited[arr[num][i]]) {
        continue;
      }
      visited[arr[num][i]] = false;
      queue.push(arr[num][i]);
    }
    idx++;
  }

  for (let i = 0; i < N + 1; i++) {
    if (visited[i] === true) {
      return false;
    }
  }
  return true;
}

function calcPopular(arrA, arrB) {
  let numA = 0;
  let numB = 0;
  for (let i = 0; i < arrA.length; i++) {
    numA += popular[arrA[i] - 1];
  }
  for (let i = 0; i < arrB.length; i++) {
    numB += popular[arrB[i] - 1];
  }
  // console.log(arrA, numA, arrB, numB);
  return Math.abs(numA - numB);
}
// 두 선거구로 나누는데 인구수가 최소가 되어야한다
// 인구가 최소화 되어야함

// 나누는걸 어떻게 하나?
// 2구역으로 나눠야함
// 1~N개가 있으니까
// 1개부터 N/2 개까지 perm을 만든다?

// 연결이 되어있는지 확인하고 연결되어있으면

// 차이 검사후 min에 넣기

// 10, 10C2