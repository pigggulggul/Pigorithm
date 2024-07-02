const readline = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

readline
  .on("line", function (line) {
    input = line.split(" ").map((el) => parseInt(el));
    console.log(input[0] - input[1]);
  })
  .on("close", function () {
    /*
		솔루션 작성
	*/
    process.exit();
  });