function solution(sequence) {
    var answer = 0;
    let odd = [];
    let even = [];
    //-1 1 -1 일 때랑 1 -1 1 일 때 구하기
    //2 -3, -2 3
    //최소값보다 크니까 저장하고 다음
    // 2 -3 -6, -2 3 6, 
    // 최소값보다 크니까 저장하고 다음
    for(let i = 0 ; i < sequence.length ;i++){
        const s = sequence[i];
        if(i===0){
            odd.push(s);
            even.push(-s);
        }
        else if(i%2===0){
            odd.push(Math.max(s,odd[i-1]+s));
            even.push(Math.max(-s,even[i-1]-s));
        }else{
            odd.push(Math.max(-s,odd[i-1]-s));
            even.push(Math.max(+s,even[i-1]+s));
        }
        answer = Math.max(odd[i],even[i],answer);
    }
    return answer;
}