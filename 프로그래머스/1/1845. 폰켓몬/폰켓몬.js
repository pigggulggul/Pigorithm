function solution(nums) {
    var answer = 0;
    var max = nums.length/2;
    console.log(max);
    var newArr = [];
    nums.map((item)=>{
        if(!newArr.includes(item)){
            newArr.push(item);
        }
        if(newArr.length >= max){
            return;
        }
    });
    if(newArr.length>max){
            answer=max;
            return answer;
    }else{
        answer=newArr.length;
    }
    return answer;
}