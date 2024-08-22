// D는 수를 2배로. 10000이상이면 10000나머지 값
// S는 n-1을 저장. n이 0이면 9999가 저장됨
// L은 각 자리를 왼편으로 이동
// R은 오른쪽으로 이동

// A -> B 일 때 최소 몇번을 이용해서 바꿀 수 있을까

// D S L R 하나식 하기

// 메모리 초과
//

const [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const T = Number(first);

for (let t = 0; t < T; t++) {
  let [start, end] = input[t].split(" ").map(Number);
  let number = start;

  startBfs(number, end);
}

function startBfs(start, end) {
  let queue = [[start, ""]];
  let visited = Array(10000).fill(false); // 0부터 9999까지 방문 여부 확인
  let idx = 0;

  while (queue.length !== idx) {
    let [curNum, curAlpha] = queue[idx++];

    if (curNum === end) {
      console.log(curAlpha);
      return;
    }
    let d = funcD(curNum);
    let s = funcS(curNum);
    let l = funcL(curNum);
    let r = funcR(curNum);

    if (!visited[d]) {
      queue.push([d, curAlpha + "D"]);
      visited[d] = true;
    }
    if (!visited[s]) {
      queue.push([s, curAlpha + "S"]);
      visited[s] = true;
    }
    if (!visited[l]) {
      queue.push([l, curAlpha + "L"]);
      visited[l] = true;
    }
    if (!visited[r]) {
      queue.push([r, curAlpha + "R"]);
      visited[r] = true;
    }
  }
}
function funcD(num) {
  return (num * 2) % 10000;
}
function funcS(num) {
  if (num === 0) {
    return 9999;
  } else return num - 1;
}
function funcL(num) {
  let str = ("0000" + num).slice(-4);
  let rotatedStr = str.slice(1) + str[0];
  return Number(rotatedStr);
}
function funcR(num) {
  let str = ("0000" + num).slice(-4);
  let rotatedStr = str.slice(-1) + str.slice(0, -1);
  return Number(rotatedStr);
}