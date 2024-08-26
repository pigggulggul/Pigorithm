// N개의 집과 M개의 길로 이루어져 있음
// 양방향. 길의 유지비가 있음.
// 마을을 2개로 분리할 계획.집들이서로 연결되도록 분할. (서로 경로가 있어야함.)
// 2개로 분리 할 때 무조건 한 구역에 1개 이상의 마을이 존재해야함.

// 분리된 마을 사이에 길을 없앨 수 있음
// 마을 안에서도 경로만 있으면 길 없애기 가능.

// 길들을 없앤 남은 길의 유지비의 합을 구하여라

// 1->2 3
// 1->3 2
// 1->5 5
// 1->6 2
// 2->3 1
// 2->5 2
// 3->4 4
// 3->7 6
// 4->5 3
// 4->6 1
// 5->6 3
// 6->7 4

// 1,2,3,5,6,8
// 7

// 그룹나누기
// 유지비 구하기.(나뉜 그룹끼리 연결되는 유지비만 구하기)
// MST 최단경로

// N은 2~100,000이하의 정수
// N개를 2개로 나눠양함

// 1 3 2 6 4 5
// 8
// 최소간선트리. 마지막연결되는거는 가장 큰 값이므로 -

class MaxHeap {
  constructor() {
    this.heap = [];
  }

  swap(a, b) {
    [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
  }

  size() {
    return this.heap.length;
  }

  push(value) {
    const heap = this.heap;
    heap.push(value);
    let idx = heap.length - 1;
    let parent = Math.floor((idx - 1) / 2);

    while (idx > 0 && heap[parent][1] > heap[idx][1]) {
      this.swap(parent, idx);
      idx = parent;
      parent = Math.floor((idx - 1) / 2);
    }
  }

  pop() {
    const heap = this.heap;
    if (heap.length === 0) return null;

    this.swap(0, heap.length - 1);
    const value = heap.pop();
    let idx = 0;
    const lastIdx = heap.length - 1;

    while (idx <= lastIdx) {
      let leftChild = idx * 2 + 1;
      let rightChild = idx * 2 + 2;
      let smallest = idx;

      if (leftChild <= lastIdx && heap[leftChild][1] < heap[smallest][1]) {
        smallest = leftChild;
      }
      if (rightChild <= lastIdx && heap[rightChild][1] < heap[smallest][1]) {
        smallest = rightChild;
      }
      if (smallest !== idx) {
        this.swap(idx, smallest);
        idx = smallest;
      } else {
        break;
      }
    }
    return value;
  }
}

const [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let [N, M] = first.split(" ").map(Number);

let arr = Array.from({ length: N + 1 }, () => Array());
let totalMin = Infinity;
let maxValue = -1;
for (let i = 0; i < M; i++) {
  const [start, end, cost] = input[i].split(" ").map(Number);
  arr[start].push([end, cost]);
  arr[end].push([start, cost]);
}
startMst(1);
console.log(totalMin - maxValue);

function startMst(start) {
  //최대힙 만들어야해...우선순위 큐

  let queue = new MaxHeap();
  queue.push([start, 0]);

  let total = 0;
  let visited = new Array(N + 1).fill(false);
  while (queue.size() !== 0) {
    let [cur, value] = queue.pop();
    if (visited[cur]) continue;
    if (value > maxValue) {
      maxValue = value;
    }
    total += value;
    visited[cur] = true;
    for (let [next, cost] of arr[cur]) {
      queue.push([next, cost]);
    }
  }
  if (totalMin > total) {
    totalMin = total;
  }
}