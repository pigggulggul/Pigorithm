function solution(nums) {
    var answer = [];
    answer = new Set([...nums]);
    const set = [...answer];
    if(nums.length/2 < set.length){
        return nums.length/2
    }else{
        return set.length
    }
}