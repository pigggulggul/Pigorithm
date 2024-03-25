//더하기 혹은 빼기로 타겟을 만든다.
//numbers 개수가 있으면 부호는 numbers-1
//부호가 0이면 +, 1이면 -로 생각하기
//중복만들기
//중복 
var answer = 0;
function solution(numbers, target) {
    var size=numbers.length;
    var newArr=[];
    Perm(newArr,0,size,numbers,target);
    return answer;
}

function Perm(newArr,cnt,size,numbers,target){
    if(cnt==size){
        let num = cal(newArr,numbers);
        // console.log(num);
        if(num==target){
            answer++;
        }
    }else{
        for(let i = 0 ; i < 2 ; i++){
        newArr.push(i);
        Perm(newArr,cnt+1,size,numbers,target);            
        newArr.pop();
        }
    }
}
function cal(newArr,numbers){
    
    let result;
    if(newArr[0]===0){
        result = numbers[0]
    }else{
        result = numbers[0] * -1;
    }
    for(let i = 1; i < newArr.length ; i++){
        if(newArr[i]===0){
            result += numbers[i];
        }else{
            result -= numbers[i];
        }
    }
    return result;
}