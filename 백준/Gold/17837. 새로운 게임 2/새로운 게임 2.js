const [first, ...second] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [N, M] = first.split(" ").map(Number);
const dx = [0, 0, -1, 1];
const dy = [1, -1, 0, 0];

let plate = new Array(N);
let location = new Array(N);
const map = new Map();
for (let i = 0; i < N; i++) {
  plate[i] = new Array();
  location[i] = new Array(N).fill().map(() => []);
  plate[i] = second.shift().split(" ").map(Number);
}
// plate에 0,1,2 , 같은 크기의 location 배열 만들기
// 순서대로 턴에 대한 정보가 있다 x,y,dir
// location[x][y]에 [dir,turn] 등록
// Map에 turn을 키로 해서 거기에 데이터 넣기.
for (let i = 0; i < M; i++) {
  const [x, y, dir] = second[i].split(" ").map(Number);
  map.set(i, [x - 1, y - 1, dir - 1]);
  location[x - 1][y - 1].push([dir - 1, i]);
}
// 배열이 움직이면 움직인 배열들 다 추가

// 말이 4개 쌓이면 게임 종료
let maxCount = 0;
let turn = 0;
for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (location[i][j].length > 0) {
      if (maxCount < location[i][j].length) {
        maxCount = location[i][j].length;
      }
    }
  }
}
// for (let i = 0; i < N; i++) {
//   console.log(location[i]);
// }
// console.log();

while (maxCount < 4 && turn < 1000) {
  turn++;

  // 1번부터 이동
  for (let i = 0; i < M; i++) {
    // i 번 순서의 x,y값
    const [x, y, dir] = map.get(i);

    // console.log(map.get(i), i);
    for (let j = 0; j < location[x][y].length; j++) {
      if (location[x][y][j][1] === i) {
        //특정 인덱스부터
        moveLocation(x, y, j, location[x][y][j][0]);
        // for (let i = 0; i < N; i++) {
        //   console.log(location[i]);
        // }
        // console.log();
        if (location[x][y].length >= 4) {
          return console.log(turn);
        }
        break;
      }
    }
    for (let a = 0; a < N; a++) {
      for (let b = 0; b < N; b++) {
        if (location[a][b].length > 0) {
          if (maxCount < location[a][b].length) {
            maxCount = location[a][b].length;
          }
        }
      }
    }
  }
  //   for (let i = 0; i < N; i++) {
  //     console.log(location[i]);
  //   }
  //   console.log();
}
if (maxCount >= 4) {
  console.log(turn);
} else {
  console.log(-1);
}

function moveLocation(x, y, index, dir) {
  //위 아래 왼쪽 오른쪽
  //0이면1 2면3
  let originDir = dir;
  let nx = x + dx[dir];
  let ny = y + dy[dir];
  if (!checkWall(nx, ny)) {
    //이동 할 곳의 x,y좌표
    if (dir < 2) {
      dir = (dir + 1) % 2;
    } else {
      dir = ((dir + 1) % 2) + 2;
    }
    nx = x + dx[dir];
    ny = y + dy[dir];
  }
  //   일반 색일 때
  if (plate[nx][ny] === 0) {
    let count = 0;
    map.set(location[x][y][index][1], [x, y, dir]);
    location[x][y][index][0] = dir;
    for (let i = index; i < location[x][y].length; i++) {
      location[nx][ny].push(location[x][y][i]);
      let saveDir = location[x][y][i][0];
      map.set(location[x][y][i][1], [nx, ny, saveDir]);
      count++;
    }
    for (let i = 0; i < count; i++) {
      location[x][y].pop();
    }
  }
  // 빨간 색일 때
  else if (plate[nx][ny] === 1) {
    let count = 0;
    map.set(location[x][y][index][1], [x, y, dir]);
    location[x][y][index][0] = dir;
    for (let i = location[x][y].length - 1; i >= index; i--) {
      location[nx][ny].push(location[x][y][i]);
      let saveDir = location[x][y][i][0];
      map.set(location[x][y][i][1], [nx, ny, saveDir]);
      count++;
    }
    for (let i = 0; i < count; i++) {
      location[x][y].pop();
    }
  } else {
    // 바꾼적이 없고 다음곳이 2면
    if (originDir === dir) {
      if (dir < 2) {
        dir = (dir + 1) % 2;
      } else {
        dir = ((dir + 1) % 2) + 2;
      }
      nx = x + dx[dir];
      ny = y + dy[dir];
    }
    map.set(location[x][y][index][1], [x, y, dir]);
    location[x][y][index][0] = dir;
    // 방향을 반대로 바꾸고 바꿨는데 벽이거나 파란색이 아니면
    if (checkWall(nx, ny) && plate[nx][ny] !== 2) {
      // 일반 색일 때
      if (plate[nx][ny] === 0) {
        let count = 0;
        for (let i = index; i < location[x][y].length; i++) {
          location[nx][ny].push(location[x][y][i]);
          let saveDir = location[x][y][i][0];
          map.set(location[x][y][i][1], [nx, ny, saveDir]);
          count++;
        }
        for (let i = 0; i < count; i++) {
          location[x][y].pop();
        }
      }
      // 빨간 색일 때
      else if (plate[nx][ny] === 1) {
        let count = 0;
        for (let i = location[x][y].length - 1; i >= index; i--) {
          location[nx][ny].push(location[x][y][i]);
          let saveDir = location[x][y][i][0];
          map.set(location[x][y][i][1], [nx, ny, saveDir]);
          count++;
        }
        for (let i = 0; i < count; i++) {
          location[x][y].pop();
        }
      }
    }
  }
}

// 1 3 2 가 한 자리에 있으면 1 3 2 가 같이 이동.그럼 갱신 해줘야하는데
function checkWall(x, y) {
  return x >= 0 && x < N && y >= 0 && y < N;
}