//N
//최소 비용 식재료 찾기
//단백질, 지방, 탄수화물, 비타민, 비용
//최소비용과 거기에 만족하는 식재료 3개 오름차순

// DFS 해야한다.
// N개가 주어지고 첫번쨰부터 브루트하는데 백트래킹으로 최소비용보다 높으면 리턴.
// 요구한 것들에 부합하는지 검사 해야한다.

let [N, first, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
N = Number(N);
let needFood = first.split(" ").map(Number);
let foodArr = new Array(N);
let minPrice = Infinity;
let minArr = [];
for (let i = 0; i < N; i++) {
  foodArr[i] = input[i].split(" ").map(Number);
}

// console.log(foodArr);
dfs([0, 0, 0, 0, 0], [], 0);
if (minPrice === Infinity) {
  console.log(-1);
} else {
  minArr.sort((a, b) => {
    return a - b;
  });
  console.log(minPrice);
  console.log(minArr.join(" "));
}
function dfs(totalStat, foodList, start) {
  for (let i = start; i < N; i++) {
    let [a, b, c, d, e] = foodArr[i];

    let newTotalStat = JSON.parse(JSON.stringify(totalStat));
    newTotalStat[0] += a;
    newTotalStat[1] += b;
    newTotalStat[2] += c;
    newTotalStat[3] += d;
    newTotalStat[4] += e;

    let newFoodList = JSON.parse(JSON.stringify(foodList));
    newFoodList.push(i + 1);

    // console.log("새영양소", newTotalStat);
    if (!checkNeed(newTotalStat)) {
      dfs(newTotalStat, newFoodList, i + 1);
    } else {
      if (minPrice > newTotalStat[4]) {
        minPrice = newTotalStat[4];
        minArr = newFoodList;
        // console.log(minPrice, minArr);
      }
    }
  }
}
function checkNeed(arr) {
  if (
    needFood[0] <= arr[0] &&
    needFood[1] <= arr[1] &&
    needFood[2] <= arr[2] &&
    needFood[3] <= arr[3]
  ) {
    return true;
  }
  return false;
}