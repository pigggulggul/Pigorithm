var sosu;
var numberList;
function solution(numbers) {
    var answer = 0;
    // 소수찾기, numbers가 주어졌을때 그 수로 만들 수 있는 소수는?
    // 일단 소수 리스트가 있어야함
    let maxNum=1;
    numberList= new Set();
    for(let i = 0 ; i < numbers.length; i++){
        maxNum*=10;
    }
    sosu = new Array(maxNum).fill(true);
    sosu[0]=false;
    sosu[1]=false;
    findSosu(maxNum);
    // 수의 조합으로 소수를 뽑기
    let numArr=[];
    let numBoolean = new Array(numbers.length).fill(true);
    for(let i = 1 ; i <= numbers.length;i++){
        pickComb(numBoolean,numArr,numbers,0,i);
    }
    // console.log(numberList.size);
    return numberList.size;
}
function findSosu(num){
    for(let i = 2 ; i < num ; i++){
        for(let j = 2 ; i * j < num ; j++ ){
            if(sosu[i*j]) sosu[i*j]=false;
        }
    }
}
//소수 뽑기
function pickComb(bool,numArr,num,cnt,limit){
    if(cnt==limit){
        checkSosu(num,numArr);
    }
    else{
        for(let i = 0 ; i < num.length ; i++){
            if(bool[i]){
                bool[i]=false;
                numArr.push(i);
                pickComb(bool,numArr,num,cnt+1,limit);
                numArr.pop(); 
                bool[i]=true;
            }
        }    
    }
}
function checkSosu(num,numArr){
    let compSosu = "";
    for(let i = 0 ; i < numArr.length ; i++){
        compSosu += num[numArr[i]]
    }
    compSosu=+compSosu;
    if(sosu[compSosu]){
        numberList.add(compSosu);
    }
}