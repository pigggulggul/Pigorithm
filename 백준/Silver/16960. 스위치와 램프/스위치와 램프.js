//N개의 스위치 M개의 램프
// 1번~ N번까지의 연결된 램프의 수

// arr만들기 arr마다 램프에 대한 정보가 있다
// ex) arr[0] = [1,3,5]
// arr[1] = [1,2]
// arr[2] = [3,4,5]
// arr[3] = [1,1]

// 램프 flag 를 lamp 개수만큼 만들기

// sortNum=0부터 시작
// i를 arr[0]~arr[i]번까지 돌면서 arr[i] 안에 있는 숫자에 해당하는 lamp[]의 값을 false면 true로 바꾸기
// i === sortNum이면 continue;
// 바꿀 때 falgCount++;
// 다 돌았을때 flagCount === lamp result=1;
// 아니면 continue

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
// console.log(newArr);
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
  // console.log(lampFlag);
  if (flagCount === M) {
    result = 1;
    break;
  }
}
console.log(result);