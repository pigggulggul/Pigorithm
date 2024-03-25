
function solution(clothes) {
    console.log(clothes.length);
    let obj = {};

    clothes.map(item=>{
        if(obj[item[1]]){
            obj[item[1]]++;
        }else {
            obj[item[1]]=1;
        }

    })
    let value = Object.values(obj);
    console.log(value);
    var answer = 1;
    for(let i = 0; i<value.length ;i++){
        answer *= value[i]+1
    }
    return answer-1;
}