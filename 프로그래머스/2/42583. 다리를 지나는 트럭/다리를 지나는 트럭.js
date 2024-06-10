class Truck{
    constructor(weight,time){
        this.weight=weight;
        this.time=time;
    }
}
let truckArr = [];
let result = 0;
function solution(bridge_length, weight, truck_weights) {
    var answer = 0;
    // 순서대로 가는데 다리길이까지만 수용가능. 한 공간에 넣을 수 있는지 판단해서 가능하면 넣고
    // 불가능하면 초과된거니까 truck줄이기
    
    for(let i = 0 ; i < truck_weights.length ; i++){
        
        if(possibleTruck(truck_weights[i],weight)){
            reduceArr(1);
            let t = new Truck(truck_weights[i],bridge_length);
            truckArr.push(t);
            result++;
        }else {
            let flag = false;
            while(!flag){
                if(!possibleTruck(truck_weights[i],weight) || truckArr.length!==0){
                    checkRemain();
                } 
                if(possibleTruck(truck_weights[i],weight) || truckArr.length===0){
                    flag=true;
                }
            }
            let t = new Truck(truck_weights[i],bridge_length);
            truckArr.push(t);
        }
    }
    if(truck_weights.length > 0){
        let max = -1;
        truckArr.map(item=>{
            if(item.time > max){
                max = item.time;
            }
        });
        reduceArr(max);
        result+=max;
    }
    return result;
}

function possibleTruck(truckWeight,weight){
    if(truckArr.length === 0){
        return true;
    }
    let totalWeight=0;
    truckArr.map(item=>{
        totalWeight += item.weight;
    })
    if(totalWeight+truckWeight <= weight){
        return true;
    }else {
        return false;
    }
}
function reduceArr(num){
    truckArr.map((item,index)=>{
        item.time-=num;
    })
    for(let i = 0 ; i < truckArr.length ; i++){
        if(truckArr[i].time <= 0){
            truckArr.splice(i,1);
            i--;
        }
    }
}
function checkRemain(){
    if(truckArr.length > 0){
        let min = truckArr[0].time;
        reduceArr(min);
        result+=min;
    }
}