// 수빈이는 걷거나 순간이동 가능
// 1초 후에는 X-1 or X+1
// 순간이동을 하는 경우에는 0초 후에 2*X의

let input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let [N, M] = input[0].split(" ").map(Number);
let visited = new Array(100001).fill(false);

let answer = bfs();
console.log(answer);
function bfs() {
  let queue = [[N, 0]];
  while (queue.length !== 0) {
    let [next, time] = queue.shift();
    if (next === M) {
      return time;
    }
    if (!visited[next * 2] && next >= 0 && next <= 100000) {
      queue.unshift([next * 2, time]);
      visited[next * 2] = true;
    }
    if (!visited[next - 1] && next >= 0 && next <= 100000) {
      queue.push([next - 1, time + 1]);
      visited[next - 1] = true;
    }
    if (!visited[next + 1] && next >= 0 && next <= 100000) {
      visited[next + 1] = true;
      queue.push([next + 1, time + 1]);
    }
  }
}