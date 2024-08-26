// N개의 보석. K개의 가방
// 보석 당 무게 M, 가격 V
// 가방의 최대무개
// 가방에는 최대 한 개의 보석만 넣을 수 있다.

// n개의 가방이고 가방마다 무게가 있다.

// 가치가 높은 순으로 정렬.
// 무게가

// 3 2
// 3 65
// 5 23
// 2 99
// 10
// 2

// 보석은 가치가 높은순으로
// 가방은 무게가 작은순으로 정렬
// 앞에서부터 넣는데 이미 넣은가방은 visited로 표시하고
// 넣을 수 있으면 넣는다.

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

    while (heap[parent] < value) {
      this.swap(parent, idx);
      idx = parent;
      parent = Math.floor((idx - 1) / 2);
    }
  }
  pop() {
    const heap = this.heap;
    const lastIdx = heap.length - 1;
    let idx = 0;
    this.swap(0, lastIdx);
    let value = heap.pop();

    while (idx < lastIdx) {
      let leftChild = idx * 2 + 1;
      let rightChild = idx * 2 + 2;

      // 왼쪽자식 인덱스가 더 크다는 뜻은 자식노드가 없다는 뜻
      if (leftChild >= lastIdx) {
        break;
      } else if (rightChild >= lastIdx) {
        // 왼쪽 자식만 있는경우 자식과 비교해서 크면 스왑
        if (heap[idx] < heap[leftChild]) {
          this.swap(idx, leftChild);
          idx = leftChild;
        } else {
          break;
        }
      } else {
        // 둘 다 있고 둘 다 루트보다 크면
        if (heap[leftChild] > heap[idx] && heap[rightChild] > heap[idx]) {
          if (heap[leftChild] > heap[rightChild]) {
            this.swap(idx, leftChild);
            idx = leftChild;
          } else {
            this.swap(idx, rightChild);
            idx = rightChild;
          }
        } else if (heap[leftChild] > heap[idx]) {
          // 왼쪽 자식만 루트보다 크면
          this.swap(leftChild, idx);
          idx = leftChild;
        } else if (heap[rightChild] > heap[idx]) {
          // 오른쪽 자식만 크면
          this.swap(rightChild, idx);
          idx = rightChild;
        } else {
          //둘 다 작으면 안 바꿈
          break;
        }
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

let [N, K] = first.split(" ").map(Number);
let mineral = new Array(N);
let backpack = new Array(K);
let idx = 0;
let visited = new Array(K).fill(false);
let answer = 0;
const heap = new MaxHeap();
for (let i = 0; i < N; i++) {
  mineral[i] = input[i].split(" ").map(Number);
  idx++;
}
let backpackIdx = 0;
for (let i = idx; i < input.length; i++) {
  backpack[backpackIdx++] = Number(input[i]);
}
mineral.sort((a, b) => {
  return a[0] - b[0];
});
backpack.sort((a, b) => {
  return a - b;
});

let j = 0;
for (let i = 0; i < K; i++) {
  while (j < N && mineral[j][0] <= backpack[i]) {
    heap.push(mineral[j][1]);
    j++;
  }
  if (heap.size()) {
    answer += heap.pop();
  }
}
console.log(answer);