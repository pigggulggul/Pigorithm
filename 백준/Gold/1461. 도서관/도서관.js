// 한 번에 N권씩만 이동 할 수 있다.
// -37 2 -6 -39 -29 11 -28 이 있다
// -39 -37 -29 -28 -6 2 11 로 정렬.
// 0에서 시작해서 가장 짧은 거리 구하기
// 2권을 가지고 시작
// 11 11 6 6 29 29 39 < 최저

// -18 -9 -4 50 22 -26 40 -45이 있다.
// -45 -26 -18 -9 -4 22 40 50로 정렬
// 90[45,26,18] 18[9,4] 50[22,40,50]

// 규칙 : 정렬하고 arr[0] arr[size]중 더 거리의 숫자를 구한다.
// 더 먼거리의 arr을 기준으로 M개만큼 숫자 삭제. 부호가 같아야함
// 더 먼거리의 값을 result에 값*2해서 더한다. 이 때 가장 큰 숫자는 max로 갱신.
// arr.size가 0일때까지 반복.
// arr.size===0이면 끝났다는 거니까 result -= max

let [first, second] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let [N, M] = first.split(" ").map(Number);
let bookArr = second.split(" ").map(Number);
let result = 0;
let findMax = 0;
let maxNum = 0;
bookArr.sort((a, b) => a - b);

// console.log(bookArr, N, M);
while (bookArr.length !== 0) {
  if (bookArr.length === 1) {
    result += Math.abs(bookArr[0]) * 2;
    if (maxNum < Math.abs(bookArr[0])) {
      maxNum = Math.abs(bookArr[0]);
    }
    break;
  }

  //최고값이 처음인지 마지막인지 찾기
  let firstValue = Math.abs(bookArr[0]);
  let lastValue = Math.abs(bookArr[bookArr.length - 1]);
  if (firstValue > lastValue) {
    if (firstValue > maxNum) {
      maxNum = firstValue;
    }
    result += firstValue * 2;
    findMax = 0;
  } else {
    if (lastValue > maxNum) {
      maxNum = lastValue;
    }
    result += lastValue * 2;
    findMax = 1;
  }

  //bookArr[0]이 크면
  let deleteCount = 0;
  if (findMax === 0) {
    deleteCount = checkRangeStart();
    bookArr.splice(0, deleteCount);
  } else {
    deleteCount = checkRangeFinish();
    bookArr.splice(bookArr.length - deleteCount, deleteCount);
  }
  //   console.log(bookArr, result);
}
console.log(result - maxNum);

function checkRangeStart() {
  let startRange = 0;
  let plusMinus = 1;
  if (bookArr.length < M) {
    startRange = bookArr.length;
  } else {
    startRange = M;
  }
  if (bookArr[0] > 0) {
    plusMinus = 1;
  } else {
    plusMinus = -1;
  }
  for (let i = 0; i < startRange; i++) {
    if (plusMinus === -1 && bookArr[i] > 0) {
      return i;
    }
  }
  return startRange;
}
function checkRangeFinish() {
  let startRange = 0;
  let plusMinus = 1;
  if (bookArr.length < M) {
    startRange = bookArr.length;
  } else {
    startRange = M;
  }
  if (bookArr[bookArr.length - 1] > 0) {
    plusMinus = 1;
  } else {
    plusMinus = -1;
  }
  let count = 0;
  for (let i = bookArr.length - 1; i > bookArr.length - 1 - startRange; i--) {
    if (plusMinus === 1 && bookArr[i] < 0) {
      return count;
    }
    count++;
  }
  return count;
}