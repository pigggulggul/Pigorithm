// 자기가 키 몇번째인지 알 수 있는 방법.
// 자기보다 키 큰 사람이 몇 명이고 키 작은 사람이 몇 명인지 알 수 있어야한다.

// 예제1
// [[5], [], [4], [2, 6], [4, 2], []];
// [[], [4, 5], [], [3, 5], [1], [4]];

// 1부터 확인. 자기보다 키큰 사람인건 연결이 되어있는 사람을 확인한다.
// 위의경우 5,4,2,6
// 자기보다 작은 사람인것도 확인 가능. 1의경우 없다.
// true인건 1,2,4,5,6
// 2의경우 2,4,5,3,1
// 3의경우 3,4,2,6
// 4의 경우 4,2,6,3,5,1
// cnt 개수가 N이면 result++;

const [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, count] = first.split(" ").map(Number);

let tall = Array.from({ length: N + 1 }, () => Array());
let short = Array.from({ length: N + 1 }, () => Array());
let result = 0;

for (let i = 0; i < count; i++) {
  const [a, b] = input[i].split(" ").map(Number);
  tall[a].push(b);
  short[b].push(a);
}
// console.log(tall);
// console.log(short);

for (let i = 1; i <= N; i++) {
  let visited = new Array(N + 1).fill(false);
  dfs(i, tall, visited);
  dfs(i, short, visited);

  let cnt = 0;
  for (let i = 1; i <= N; i++) {
    if (visited[i] === true) {
      cnt++;
    }
  }
  if (cnt === N) {
    result++;
  }
}
console.log(result);

function dfs(start, arr, flag) {
  flag[start] = true;

  for (let i = 0; i < arr[start].length; i++) {
    let next = arr[start][i];

    if (flag[next]) continue;
    dfs(next, arr, flag);
  }
}