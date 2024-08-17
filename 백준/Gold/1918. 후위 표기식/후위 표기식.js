const [input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let stack = [];
let answer = "";

for (let i = 0; i < input.length; i++) {
  const word = input[i];
  //알파벳 넣기
  if (word >= "A" && word <= "Z") {
    answer += word;
  }
  //괄호 일 때
  if (word === "(") {
    stack.push(word);
  }
  //괄호 끝에 도달하면 괄호 안에것들을 다 빼기 + 괄호도 빼기
  if (word === ")") {
    while (stack.length > 0 && stack[stack.length - 1] !== "(") {
      answer += stack.pop();
    }
    stack.pop();
  }
  //최근 넣은 연산자가 * 나 / 인 것을 answer에 추가
  if (word === "*" || word === "/") {
    while (
      stack.length > 0 &&
      (stack[stack.length - 1] === "*" || stack[stack.length - 1] === "/")
    ) {
      answer += stack.pop();
    }
    stack.push(word);
  }
  // ( 에 도착 할 때 까지 or 끝까지 내용물을 빼기
  if (word === "+" || word === "-") {
    while (stack.length > 0 && stack[stack.length - 1] !== "(") {
      answer += stack.pop();
    }
    stack.push(word);
  }
  //   console.log("word : ", word, "stack 값 : ", stack);
}
while (stack.length > 0) {
  answer += stack.pop();
}
console.log(answer);