var max_count = Number.MAX_VALUE;
function solution(name) {
    var answer = 0;
    var compAlpha="";
    var index=0;
    var count=0;
    for(let i = 0 ; i < name.length ; i++){
        compAlpha+="A";
    }
    joyStick(compAlpha,name,index,count,0);
    
    return max_count;
}
function joyStick(compAlpha,name,index,count,cnt){
    if(compAlpha == name || cnt==name.length){
        if(max_count > count){
            max_count=count;
            console.log(max_count);
        }
    }
    else{
        // 알파벳 맞추기
        let num = compAlpha[index].charCodeAt(0);
        let num2 = name[index].charCodeAt(0);
        let newCompAlpha = compAlpha.split('');
        newCompAlpha[index]=name[index];
        compAlpha=newCompAlpha.join('');
        if(num2-num > 13){
            count += 26-(num2-num);
        }else{
            count += num2-num;
        }
        // 왼쪽으로 가까운 거리 찾기
        let leftCount = moveLeftIndex(compAlpha,name,index);
        if(index-leftCount < 0){
            let newIndex = (index-leftCount) + name.length;
            joyStick(compAlpha,name,newIndex,count+leftCount,cnt+1);
        }else{
            joyStick(compAlpha,name,index-leftCount,count+leftCount,cnt+1);
        }
        // 오른쪽으로 가까운 거리 찾기
        let rightCount = moveRightIndex(compAlpha,name,index);
        if(index+rightCount >= name.length){
            let newIndex = (index+rightCount) - name.length;
            joyStick(compAlpha,name,newIndex,count+rightCount,cnt+1);
        }else{
            joyStick(compAlpha,name,index+rightCount,count+rightCount,cnt+1);
        }
        
    }
    
}
function moveLeftIndex(compAlpha,name,index){
    let cnt=0;
    let left=0;
    let leftCount=0;
    
    // 왼쪽 이동
    if(name==compAlpha){
        return 0;
    }
    for(let i = index-1 ; cnt < name.length ; i--){
        //왼쪽으로 이동하면서 name[i]과 compAlpha[i]가 다르면 left에 저장
        leftCount +=1;
        cnt++;
        if(i<0){
            i= name.length-1;
        }
        if(name[i] != compAlpha[i]){
            left=i;
            break;
        }
    }

    return leftCount;
}
function moveRightIndex(compAlpha,name,index){
    let cnt=0;
    let right=0;
    let rightCount=0;
    
    // 오른쪽 이동
    if(name==compAlpha){
        return 0;
    }
    for(let i = index+1 ; cnt <name.length ; i++){
        //왼쪽으로 이동하면서 name[i]과 compAlpha[i]가 다르면 left에 저장
        rightCount +=1;
        cnt++;
        if(i>=name.length){
            i= 0;
        }
        if(name[i] != compAlpha[i]){
            right=i;
            break;
        }
    }

    return rightCount;
}