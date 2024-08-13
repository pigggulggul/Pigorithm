//bfs문제인데 한 번 queue를 돌 때 6번씩 검사하는 문제

const [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [snake, ladder] = first.split(" ").map(Number);
let totalArr = new Array(101).fill(0);
let visited = new Array(101).fill(false);
for (let i = 0; i < snake; i++) {
  const [a, b] = input[i].split(" ").map(Number);
  totalArr[a] = b;
}
for (let i = 0; i < ladder; i++) {
  const [a, b] = input[snake + i].split(" ").map(Number);
  totalArr[a] = b;
}

// console.log(totalArr);
let min = bfs(1, 0);
console.log(min);

function bfs(start, cnt) {
  let queue = [[start, cnt]];
  let idx = 0;
  visited[start] = true;
  while (queue.length > idx) {
    let [num, dice] = queue[idx++];
    for (let i = 1; i <= 6; i++) {
      let move = num + i;
      if (move === 100) return dice + 1;
      else if (move < 100) {
        if (totalArr[move] !== 0) {
          move = totalArr[move];
        }
        if (!visited[move]) {
          visited[move] = true;
          queue.push([move, dice + 1]);
        }
      }
    }
  }
}