let giftArr;
let pointArr;
let giftPoint;
function solution(friends, gifts) {
    giftArr = Array.from(Array(friends.length),()=>new Array(friends.length).fill(0));
    pointArr = new Array(friends.length).fill(0);
    giftPoint = new Array(friends.length).fill(0);
    var answer = 0;
    for(let i = 0 ; i < gifts.length;i++){
        var value = gifts[i].split(" ");
        var a = friends.indexOf(value[0]);
        var b = friends.indexOf(value[1]);
        giftArr[a][b]++;
        giftPoint[a]++;
        giftPoint[b]--;
    }
    // console.log(giftArr);
    for(let i = 0 ; i < giftArr.length;i++){
        for(let j = 0 ; j < giftArr[i].length;j++){
            if(i===j) continue;
            if(giftArr[i][j] > giftArr[j][i])
                pointArr[i]++;
            else if (giftArr[i][j] === giftArr[j][i] || (giftArr[i][j]===0 &&giftArr[j][i]===0)){
                if(giftPoint[i]>giftPoint[j]){
                    pointArr[i]++;
                }
            }
        }
    }
   console.log(pointArr);
    for(let i = 0 ; i < pointArr.length; i++){
        if(answer < pointArr[i]){
            answer=pointArr[i];
        }
    }
    return answer;
}