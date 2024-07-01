let emot;
let userCopy;
let p=[];
let emotNum;
let percent=[10,20,30,40]
let maxCount=-1;
let maxMoney=-1;
function solution(users, emoticons) {
    var answer = [];
    emot=emoticons;
    userCopy=users;
    emotNum = emoticons.length;
    comb(p,0);
    return [maxCount, maxMoney];
}
function comb(arr,cnt){
    if(cnt===emotNum){
        // console.log(arr);
        calc(arr);
    }else{
        for(let i = 0 ; i < 4 ; i++){
            arr.push(i);
            comb(arr,cnt+1);
            arr.pop();
        }
    }
}
function calc(arr){
    // [0,0,0,0] user배열은 7개
    let result=0;
    let totalPrice=0;
    let flag = false;
    
    //유저 순회
    for(let i = 0 ; i < userCopy.length ; i++){
        let price = 0;
        flag= false;
        //유저마다 할인율 순회
        for(let j = 0 ; j < arr.length; j++){
            if(userCopy[i][0] > (arr[j]+1)*10){
                continue;
            }
            if(userCopy[i][0] <= (arr[j]+1)*10){
                price += emot[j] - emot[j]/10*(arr[j]+1);
            }
            if(userCopy[i][1] <= price ){
                flag=true;
                break;
            }
        }
        if(flag===true){
            result++;
        }else{
            totalPrice += price;
        }
    }
    if(maxCount < result){
        maxCount = result;
        maxMoney = totalPrice;
    }
    if(maxCount == result){
        if(maxMoney < totalPrice){
            maxMoney = totalPrice;
        }
    }
}
// n명 사용자에게 임티 m개 할인하여 판매. 할인율은 10,20,30,40%
// 사용자들은 기준에 따라 일정 비율 이상 할인 이모티콘은 모두 구매
// 사용자들은 이모티콘 구매 비용 합이 일정 이상이면 모두 취소하고 서비스에 가입
// 비율 이상이면 다 사고 특정 가격 이상이면 구매대신 서비스 가입
// n명의 user[n]에 임티m개의 정가 emotions. 행사는 서비스 가입자 최대로
// 판매액 최대로 했을 때 임티 플러스 가입 수와 이모티콘 매출액을 1차원 정수에담기

//풀이. user[0] 퍼센트 이상인 사람만 산다.
// 40 40이면 user[0]은 둘 다 사고 40 30이면 하나만 사고 유저는 100명. 이모티콘은 7개
//이모티콘 최대 28가지 선택지. [0,1,2,3] 중복 돌리면 될듯..?
// 1. Comb돌려서 0~3까지 배열 만들기.
// 2. calc돌려서 최대 가입수와 최대 이익 구하기.