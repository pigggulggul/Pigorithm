// N개 M이상의 부분합 구하기
//투포인터 써서 구현
// arr[0]부터 시작해서 M이상이면 minLength = arrEnd-arrStart+1; 출력. start++;
// M보다 작으면 end++
// 만약 start > end거나 end나 start가 끝까지 갔을 때 종료.  종료

const [first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [N, M] = first.split(" ").map(Number);

const arr = input[0].split(" ").map(Number);
let minLength = Infinity;
let arrEnd = 0;
let arrStart = 0;
let arrValue = arr[arrStart];
while (arrStart <= arrEnd) {
  //   console.log(arrStart, arrEnd, arrValue);

  if (arrStart === arr.length || arrEnd === arr.length) {
    break;
  }
  if (arrValue >= M) {
    if (minLength > arrEnd - arrStart + 1) {
      minLength = arrEnd - arrStart + 1;
    }
    arrValue -= arr[arrStart];
    arrStart++;
  } else {
    arrEnd++;
    arrValue += arr[arrEnd];
  }
}
if (minLength === Infinity) {
  minLength = 0;
}
console.log(minLength);