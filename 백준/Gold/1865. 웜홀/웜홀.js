const [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const T = Number(first);
let N, M, W, road;
let inputIdx = 0;

for (let t = 0; t < T; t++) {
  const [a, b, c] = input[inputIdx++].split(" ").map(Number);
  N = a;
  M = b;
  W = c;
  road = [];
  for (let i = 0; i < M; i++) {
    let [to, from, cost] = input[inputIdx++].split(" ").map(Number);
    road.push([to, from, cost]);
    road.push([from, to, cost]);
  }
  for (let i = 0; i < W; i++) {
    let [to, from, cost] = input[inputIdx++].split(" ").map(Number);
    road.push([to, from, -cost]);
  }

  let hasNegativeCycle = false;
  for (let start = 1; start <= N; start++) {
    if (startTravel(start)) {
      hasNegativeCycle = true;
      break;
    }
  }

  if (hasNegativeCycle) console.log("YES");
  else console.log("NO");
}

function startTravel(start) {
  let MAX = 25000001;
  let dist = new Array(N + 1).fill(MAX);
  dist[start] = 0;
  for (let i = 1; i <= N; i++) {
    let updated = false;
    for (let [u, v, cost] of road) {
      if (dist[u] !== MAX && dist[u] + cost < dist[v]) {
        dist[v] = dist[u] + cost;
        updated = true;

        if (i === N) {
          return true;
        }
      }
    }
    if (!updated) break;
  }
  return false;
}