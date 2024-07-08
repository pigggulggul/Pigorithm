const [N, inputArr] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const arr = inputArr.split(" ").map(Number);
const dist = new Array(Number(N)).fill(0);
let stack = [];
for (let i = 0; i < Number(N); i++) {
  while (stack.length != 0 && arr[stack[stack.length - 1]] < arr[i]) {
    arr[stack.pop()] = arr[i];
  }
  stack.push(i);
}
while (stack.length != 0) {
  arr[stack.pop()] = -1;
}
arr[arr.length - 1] = -1;
console.log(arr.join(" "));