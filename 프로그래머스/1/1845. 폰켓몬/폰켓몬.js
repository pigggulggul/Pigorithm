function solution(nums) {
    var max = nums.length/2;
    var answer = [...new Set(nums)];
    var result = answer.length > max ? result=max : result=answer.length;
    return result;
}
