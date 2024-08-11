[first, str] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
let [N, cnt] = first.split(" ").map(Number);

let stack = [];
let idx = 0;
while (cnt !== 0) {
  if (idx >= N) {
    while (cnt !== 0) {
      stack.pop();
      cnt--;
    }
    break;
  }

  let num = Number(str[idx]);
  if (stack.length === 0) {
    stack.push(num);
  } else {
    while (stack.length !== 0 && stack[stack.length - 1] < num) {
      stack.pop();
      cnt--;
      if (cnt === 0) {
        break;
      }
    }
    stack.push(num);
  }
  idx++;
}

let content = "";

for (let i = 0; i < stack.length; i++) {
  content += stack[i];
}
for (let i = idx; i < str.length; i++) {
  content += str[i];
}
console.log(content);