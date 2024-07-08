const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let N = Number(input.shift());
let arr = [];
for (let i = 0; i < N; i++) {
  let [item1, item2] = input.shift().split(" ").map(Number);
  arr.push([item1, item2]);
}
arr.sort((a, b) => {
  if (a[1] === b[1]) {
    return a[0] - b[0];
  }
  return a[1] - b[1];
});

let time = 0;
let count = 0;
for (let i = 0; i < N; i++) {
  if (time <= arr[i][0]) {
    time = arr[i][1];
    count++;
  }
}
console.log(count);