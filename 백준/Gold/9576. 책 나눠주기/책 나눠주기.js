const [T, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let startInput = 0;
for (let t = 0; t < T; t++) {
  let [N, M] = input[startInput++].split(" ").map(Number);
  //   console.log(N, M);
  // 책의 수만큼 arr를 만든다.
  let arr = new Array(N + 1).fill(false);
  let ans = 0;
  let pointArr = [];
  for (let i = 0; i < M; i++) {
    const [a, b] = input[startInput++].split(" ").map(Number);
    pointArr.push([a, b]);
  }
  pointArr.sort((a, b) => {
    if (a[1] === b[1]) {
      return a[0] - b[0];
    }
    return a[1] - b[1];
  });
  for (let j = 0; j < pointArr.length; j++) {
    const [min, max] = pointArr[j];
    for (let a = min; a <= max; a++) {
      if (arr[a] === false) {
        arr[a] = true;
        ans++;
        break;
      }
    }
  }
  //   console.log(pointArr);
  //   console.log(arr);
  console.log(ans);
}