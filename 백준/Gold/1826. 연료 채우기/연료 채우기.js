// 주유소 개수 N
// 2~N+1번까지 시작위치에서 주유소까지 거리와 채울 수 있는 연료 양
// 마지막 줄에는 집에서 마을까지 거리, 초기 오일양
// 주유소에서 멈추는 횟수를 최소로 마을에 도착
//  마을에 도착하지 못하면 -1.

//마을이 25이면

// 4
// 4 4
// 5 2
// 11 5
// 15 10
// 25 10

// goalDistance = 25
// startOil = 10;
// 최소로 도착하는 방법?
// 브루트포스..?
// 거치는 경우와 안 거치는 경우 모두 하기
// 거치면서 마지막에 남은거리 계산

class PriorityQueue {
  constructor() {
    this.heap = [];
  }

  length() {
    return this.heap.length;
  }

  push(value) {
    const heap = this.heap;
    heap.push(value);

    let cur = heap.length - 1;
    let par = Math.floor((cur - 1) / 2);

    while (cur > 0 && heap[cur] > heap[par]) {
      [heap[cur], heap[par]] = [heap[par], heap[cur]];
      cur = par;
      par = Math.floor((cur - 1) / 2);
    }
  }

  pop() {
    const heap = this.heap;
    if (heap.length <= 1) return heap.pop();

    const max = heap[0];
    heap[0] = heap.pop();
    let idx = 0;

    while (idx * 2 + 1 < heap.length) {
      let left = idx * 2 + 1;
      let right = idx * 2 + 2;
      let next = idx;

      if (heap[next] < heap[left]) {
        next = left;
      }
      if (right < heap.length && heap[next] < heap[right]) {
        next = right;
      }
      if (next === idx) break;

      [heap[idx], heap[next]] = [heap[next], heap[idx]];
      idx = next;
    }
    return max;
  }
}

let [N, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
N = Number(N);
let arr = new Array();
let cnt = 0;
for (let i = 0; i < N; i++) {
  let [a, b] = input[i].split(" ").map(Number);
  arr.push([a, b]);
}
arr.sort((a, b) => a[0] - b[0]);
let [goalDistance, startOil] = input[N].split(" ").map(Number);
arr.push([goalDistance, 0]);
let pq = new PriorityQueue();

for (let i = 0; i <= N; i++) {
  //   console.log(startOil, arr[i][0], pq, pq.length());
  if (startOil >= arr[i][0]) {
    pq.push(arr[i][1]);
  } else {
    while (pq.length() > 0 && startOil < arr[i][0]) {
      startOil += pq.pop();
      cnt++;
    }
    if (startOil < arr[i][0]) {
      cnt = -1;
      break;
    }
    pq.push(arr[i][1]);
  }
}
console.log(cnt);