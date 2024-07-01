function solution(cap, n, deliveries, pickups) {
    var answer = -1;
    while(true){
        let delive = deliverFunc(deliveries,cap)+1;
        let pickup = pickupFunc(pickups,cap)+1;
        if(delive === 0 && pickup === 0){
            return answer + 1;
        }
        else if(delive>pickup){
            answer+=delive * 2;
        }
        else{
            answer+=pickup *2;
        }
    }
    return answer;
}
function findIdx(arr){
    let arrLen = arr.length
    for(let i = arrLen-1 ; i >= 0 ; i--){
        if(arr[i] !== 0){
            return i;
        }else{
            arr.pop();
        }
    }
    return -1;
}
function deliverFunc(arr,cap){
    let index = findIdx(arr);
    let len = index;
    while(index !== -1 && cap > 0){
        index = findIdx(arr);
        if(arr[index] >= cap){
            arr[index]-=cap;
            cap=0;
            break;
        }else{
            cap -= arr[index];
            arr[index] = 0;
            index--;
        }
    }   
    return len;
}
function pickupFunc(arr,cap){
    let index = findIdx(arr);
    let len = index;
    while(index !== -1 && cap > 0){
        index = findIdx(arr);
        if(arr[index] >= cap){
            arr[index]-=cap;
            cap=0;
            break;
        }else{
            cap -= arr[index];
            arr[index] = 0;
            index--;
        }
    }   
    return len;
}