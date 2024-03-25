function solution(s) {
    var answer = "";
    
    if(s.includes("one")){
    s=s.replaceAll("one","1");
    }
    if(s.includes("two")){
    s=s.replaceAll("two","2");
    }
    if(s.includes("three")){
    s=s.replaceAll("three","3");
    }
    if(s.includes("four")){
    s=s.replaceAll("four","4");
    }
    if(s.includes("five")){
    s=s.replaceAll("five","5");
    }
    if(s.includes("six")){
    s=s.replaceAll("six","6");
    }
    if(s.includes("seven")){
    s=s.replaceAll("seven","7");
    }
    if(s.includes("eight")){
    s=s.replaceAll("eight","8");
    }
    if(s.includes("nine")){
    s=s.replaceAll("nine","9");
    }
    if(s.includes("zero")){
    s=s.replaceAll("zero","0");
    }
    answer=+s;
    return answer;
}