// 문제번호 리스트수
// 순서쌍. 4 2 면 4번이 먼저 풀려야한다.
// 앞에꺼 먼저 풀어야한다.
// 5 4
// 4 2
// 3 1
// 5 1
// 2 3
// a b
// b를 풀려면 a를 먼저 풀어야한다
// 1을 풀려면 3을 먼저 풀어야한다.
// 1을 풀려면 5를 먼저 풀어야한다.
// degree[b]가 높으면 후순위다.
// degree가 낮으면 우선순위.
// 우선순위가 0인걸 queue에 담고 정렬.
// 우선순위인 queue먼저 담고 거기에 담긴 queue들도 담는다.
// queue에 담겼으니 우선순위를 -하고 0이면 나갈 차례니 queue에 넣는다.
// 최소힙을 구현한다.
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

    while (heap[parent] > value) {
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
    //최대값을 끝으로 이동시키고 pop을 통해 최대값 제거.
    let value = heap.pop();

    //끝에 있는 값이 루트로 이동했으니까 루트값을 맞는 곳에 놔둬야함
    while (idx < lastIdx) {
      let leftChild = idx * 2 + 1;
      let rightChild = idx * 2 + 2;

      // 왼쪽 자식이 존재하지 않는 경우. 전체 Idx보다 크니까 없음.
      if (leftChild >= lastIdx) {
        break;
      } else if (rightChild >= lastIdx) {
        // 왼쪽 자식만 있는경우 자식과 비교해서 크면 스왑
        if (heap[idx] > heap[leftChild]) {
          this.swap(idx, leftChild);
          idx = leftChild;
        } else {
          break;
        }
      }
      // 둘 다 있고
      else {
        if (heap[leftChild] < heap[idx] && heap[rightChild] < heap[idx]) {
          // 둘 다 루트보다 크면
          if (heap[leftChild] < heap[rightChild]) {
            this.swap(idx, leftChild);
            idx = leftChild;
          } else {
            this.swap(idx, rightChild);
            idx = rightChild;
          }
        } else if (heap[leftChild] < heap[idx]) {
          // 왼쪽 자식만 루트보다 크면
          this.swap(leftChild, idx);
          idx = leftChild;
        } else if (heap[rightChild] < heap[idx]) {
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

let [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, M] = first.split(" ").map(Number);
let problemArr = Array.from({ length: N + 1 }, () => []);
let degree = new Array(N + 1).fill(0);
let queue = new MaxHeap();
let result = [];
// 문제의 의존 관계 설정
for (let i = 0; i < M; i++) {
  const [a, b] = input[i].split(" ").map(Number);
  problemArr[a].push(b);
  degree[b]++;
}
// console.log(problemArr);
// console.log(degree);
for (let i = 1; i <= N; i++) {
  if (degree[i] === 0) {
    queue.push(i);
  }
}

while (queue.size() !== 0) {
  const current = queue.pop();
  result.push(current);

  for (let num of problemArr[current]) {
    degree[num]--;
    if (degree[num] === 0) {
      queue.push(num);
    }
  }
}
console.log(result.join(" "));