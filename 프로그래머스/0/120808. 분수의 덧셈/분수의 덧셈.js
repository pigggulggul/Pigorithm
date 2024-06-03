function solution(numer1, denom1, numer2, denom2) {
    var answer = [];
    var num1 = numer1 * denom2;
    var num2 = numer2 * denom1;
    var den1 = denom1 * denom2;
    var top = num1+num2;
    for(var i = 2 ; i <= (top > den1 ? top : den1) ; i++ ){
        if(top % i === 0 && den1 % i === 0){
            top /= i;
            den1 /= i;
            i--;
        }
    }
    answer.push(top,den1);
    return answer;
}