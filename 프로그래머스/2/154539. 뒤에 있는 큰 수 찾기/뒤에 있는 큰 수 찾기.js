function solution(numbers) {
    var answer = [];
    let arr = new Array(numbers.length).fill(-1);
    let stack = [];
    for(let i = 0 ; i < numbers.length ;i++){
        while(stack.length != 0 && numbers[stack.at(-1)]<numbers[i]){
            arr[stack.pop()] = numbers[i];
        }
        stack.push(i);
    }
    return arr;
}

