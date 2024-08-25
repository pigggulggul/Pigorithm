// 도시개수 N
// 버스개수 M
// 출발도시 도착도시 가격 (출발도시 !== 도착도시)
// 자연수
// 여러개가 될 수 있음
// n개의 도시의 모든곳에 도달하기까지 최소비용 구하기

// 현재 dist[next]값이랑  dist[num]+cost 중에 가까운걸로. 어차피 모든곳을 다 출력해야하니까 밸만포드.

// arr에 arr[a].push([b,c]) for i(arr[i]).for let [b,c] of arr[i] 값비교

let [first, second, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let N = Number(first);
let M = Number(second);
let arr = Array.from({ length: N + 1 }, () => Array());
let MAX_VALUE = 10000000;
let dist = Array.from({ length: N + 1 }, () => Array(N + 1).fill(MAX_VALUE));
for (let i = 0; i < M; i++) {
  const [a, b, c] = input[i].split(" ").map(Number);

  arr[a].push([b, c]);
}
// console.log(arr);

for (let i = 1; i <= N; i++) {
  dist[i][i] = 0;
}

for (let i = 1; i <= N; i++) {
  for (let [next, cost] of arr[i]) {
    if (dist[i][next] > cost) {
      dist[i][next] = cost;
    }
  }
}

for (let k = 1; k <= +N; k++) {
  for (let i = 1; i <= N; i++) {
    for (let j = 1; j <= N; j++) {
      if (dist[i][j] > dist[i][k] + dist[k][j] && i !== j) {
        dist[i][j] = dist[i][k] + dist[k][j];
      }
    }
  }
}
for (let i = 1; i <= N; i++) {
  let str = "";
  for (let j = 1; j <= N; j++) {
    if (dist[i][j] === MAX_VALUE) {
      str += "0 ";
    } else {
      str += dist[i][j] + " ";
    }
  }
  console.log(str);
}

// 100 100 100 100 100
// 100 100 100 100 100
// 100 100 100 100 100
// 100 100 100 100 100
// 100 100 100 100 100

// 0 2 3 1 10
// 100 100 100 2 100
// 8 100 100 1 1
// 100 100 100 100 3
// 7 4 100 100 100

// 1->3 : 3
// 3->5 : 1
// i j j k
// if (dist[i][k]<dist[i][j]+dist[j][k])
// dist[i][k] = dist[i][j]+[j][k];
// 1->5 > 1->3 + 3->5
// 1->5 = 1->3 + 3->5