var max_value = Number.MAX_VALUE;
function solution(begin, target, words) {
    var answer = 0;
    var visited = new Array(words.length).fill(false);
    var count = 0;
    
    changeWord(begin,target,words,visited,count);
    // 만약 begin이 target이 되면 그만하기.
    // begin -> target으로 단어 바꾸기. 한번에 하나만. words로 바꿔야함.
    // visited가 있음.
    // 1글자만 다르다면 visited를 true로 바꾸고 count+1하기 그 단어가 begin으로 됨.
    
    if(max_value > words.length){
        return 0;
    }
    return max_value;
}
function changeWord(begin,target,words,visited,count){
    if(begin===target && max_value > count){
        max_value=count;
    }
    for(let i = 0 ; i < words.length ; i++){
        if(!visited[i] && checkWord(begin,words[i])){
            visited[i]=true;
            changeWord(words[i],target,words,visited,count+1);
            
            visited[i]=false;
        }
    }
}
function checkWord(word1,word2){
    let value=0;
    for(let i = 0 ; i  < word2.length ; i++){
        if(word1[i] !== word2[i]){
            value++;
        }
    }
    if(value===1){
        return true;
    }else{
        return false;
    }
}