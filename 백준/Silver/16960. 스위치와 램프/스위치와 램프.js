const [NM, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [N, M] = NM.split(" ").map(Number);
const newArr = new Array(N);
for (let i = 0; i < N; i++) {
  newArr[i] = new Array();
}
for (let i = 0; i < N; i++) {
  const [count, ...arrNum] = input[i].split(" ").map(Number);
  for (let j = 0; j < count; j++) {
    newArr[i].push(arrNum[j]);
  }
}
let result = 0;
let flagCount;
let sortNum;
let lampFlag;
for (let start = 0; start < N; start++) {
  sortNum = start;
  lampFlag = new Array(M + 1).fill(false);
  flagCount = 0;
  for (let i = 0; i < newArr.length; i++) {
    if (i === sortNum) {
      continue;
    }
    for (let j = 0; j < newArr[i].length; j++) {
      if (lampFlag[newArr[i][j]] === false) {
        lampFlag[newArr[i][j]] = true;
        flagCount++;
      }
    }
  }
  if (flagCount === M) {
    result = 1;
    break;
  }
}
console.log(result);