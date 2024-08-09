// N개의 섬. M개의 다리. 양방향
// 다리는 start end weight로 주어진다. 여러개도 가능하다. 옮길 수 있는 중량의 최댓값
// 다리가 여러개이다. 근데 어차피 가장 중량이 많은 다리만 사용할것이다
// 맨 마지막에 A -> B로 목적지가 정해진다.

// 1 2 2
// 2 1 4 가 있으면 양방향이므로 4의 다리만 사용할 것이다.
// 2 3 3
// 결국 1->3 일 때 최대 옮길 수 있는 중량은 3이다.

// 1 2 3
// 1 3 4
// 3 4 4
// 1 4 3
// 1->4 일 경우 최대 중량은 1->3->4인 4가 최대이다.

// 양방향 그래프 만들기.
// 그래프 만들 때같은 그래프가 있으면 최댓값 갱신
// visited[] 만들고 목적지start를 시작으로 넣기
// 목적지 start 배열 돌면서 거기 안에 있는 요소들 queue에 넣기

let [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let [N, M] = first.split(" ").map(Number);
let max = 0;

let arr = Array.from({ length: N }, () => Array());
for (let i = 0; i < M; i++) {
  let [a, b, weight] = input[i].split(" ").map(Number);
  arr[a - 1].push([b - 1, weight]);
  arr[b - 1].push([a - 1, weight]);
  if (weight > max) {
    max = weight;
  }
}
let [start, end] = input[input.length - 1].split(" ").map(Number);
// console.log(arr);
binarySearch(start - 1, end - 1, max);

// 1 2 3
// 1 3 4
// 3 4 4
// 1 4 3
// 1->4 일 경우 최대 중량은 1->3->4인 4가 최대이다.
function startRoof(start, end, mid) {
  let visited = new Array(N).fill(false);
  let queue = [start];
  let idx = 0;
  while (queue.length !== idx) {
    let num = queue[idx];
    visited[num] = true;
    if (num === end) {
      return true;
    }
    for (let i = 0; i < arr[num].length; i++) {
      let next = arr[num][i][0];
      let nextCost = arr[num][i][1];

      if (!visited[next] && mid <= nextCost) {
        visited[next] = true;
        queue.push(next);
      }
    }

    idx++;
  }
  return false;
}
function binarySearch(start, end, max) {
  let left = 1;
  let right = max;

  while (left <= right) {
    let mid = Math.floor((left + right) / 2);
    if (startRoof(start, end, mid)) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }
  console.log(right);
}