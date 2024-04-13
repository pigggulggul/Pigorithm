function solution(s){
    var answer = true;
    var stack=[];
    for(let i = 0 ; i < s.length ; i++){
        if(s[i]==='('){
            stack.push('(');
        }else{
            if(stack.length > 0 ){
                stack.pop();    
            }else{
                return false;
            }
        }
    }
    if(stack.length===0){
        return true;
    }else{
        return false;
    }
}