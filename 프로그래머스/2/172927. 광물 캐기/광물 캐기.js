// 피로도 소모가 있다 돌은 돌 > 철 > 다이아
// 철은 철 > 돌 > 다이아 
// 다이아는 다이아 > 철 > 돌
// 내구도는 무조건 5
// 사용한 곡괭이는 사용할 수 없을 때까지
// 광물은 주어진 순서로만 캘 수 있고 다 캐거나 곡괭이 없으랟까지 캔다.
// 곡괭이는 [1,2,3] 이렇게 있고 0~5개까지 있다.
// 중요 포인트. 곡괭이를 하나 썻을때 최대의 효율을 내야한다.
// 무조건 5개씩 검사하니까

// [1,1,0]인데 [d,d,d,i,i][d,d,d,d,d]면 철->다 순으로 해야함
// 일단 mineral/5 가 총 picks의 개수고 사용할 picks는 무조건 앞에서부터 쓰는게 좋다
// 그래서 경량화된 pick를 고르고 순혈돌리기
// 그럼 [1,2,0] 이 경우에 0, 1, 1 , 1,0,1 1,1,0 이런시긍로 될 것이다
let newPicks = [0,0,0];
let checkPicks = [0,0,0];
let p=[];
let picksNum=0;
let minStress = 999999999;
let mineralsCopy;
function solution(picks, minerals) {
    picksNum=0;
    let [a,b,c] = picks;
    let totalPicks = a+b+c;
    mineralsCopy=minerals;
    if(minerals.length%5 ===0){
        picksNum=Math.floor(minerals.length/5);
    }else{
        picksNum=Math.floor(minerals.length/5 + 1);
    }
    if(totalPicks < picksNum){
        picksNum=totalPicks;
    }
    let picksNumCopy=picksNum;
    while(picksNumCopy-->0){
        if(picks[0] > newPicks[0]){
            newPicks[0]+=1;
        }
        else if(picks[0]===newPicks[0] && picks[1] > newPicks[1]){
            newPicks[1]+=1;
        }else{
            newPicks[2]+=1;
        }
    }
    perm(0);
    
    var answer = 0;
     
    return minStress;
}
function perm(cnt){
    if(picksNum===cnt){
        calStemina(p);
        return
    }else{
        for(let i = 0 ; i < 3 ; i++){
            if(newPicks[i] > checkPicks[i]){
                p.push(i);
                checkPicks[i]+=1;
                perm(cnt+1);
                p.pop();
                checkPicks[i]-=1;
            }
            
        }
    }
}
function calStemina(arr){
    // console.log(arr);
    let stress=0;
    // [1,2] 등과 같은 배열. 숫자 하나당 5개씩 돌아가면서 계산하기
    for(let i = 0 ; i < mineralsCopy.length ; i++){
        if(i >= picksNum * 5) break;
        let picksObj = arr[Math.floor(i/5)];
        if(mineralsCopy[i]==='diamond'){
            if(picksObj===0){ //다이아몬드 곡괭이
                stress+=1;
            }else if(picksObj===1){ //철 곡괭이
                stress+=5;
            }else{ // 돌 곡괭이
                stress+=25;
            }
        } else if(mineralsCopy[i]==='iron'){
            if(picksObj===0){ //다이아몬드 곡괭이
                stress+=1;
            }else if(picksObj===1){ //철 곡괭이
                stress+=1;
            }else{ // 돌 곡괭이
                stress+=5;
            }
        }else{
            stress+=1;
        }
    }
    // console.log(stress);
    if(minStress > stress){
        minStress=stress;
    }
}