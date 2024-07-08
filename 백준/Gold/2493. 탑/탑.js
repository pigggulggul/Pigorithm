const [N, inputArr] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const arr = inputArr.split(" ").map(Number);
const dist = new Array(Number(N)).fill(0);
let stack = [0];
for (let i = 0; i < Number(N); i++) {
  while (stack.length != 0 && arr[stack[stack.length - 1]] < arr[i]) {
    stack.pop();
  }
  if (stack.length != 0 && arr[stack[stack.length - 1]] >= arr[i]) {
    dist[i] = stack[stack.length - 1] + 1;
  }
  stack.push(i);
}
dist[0] = 0;
console.log(dist.join(" "));