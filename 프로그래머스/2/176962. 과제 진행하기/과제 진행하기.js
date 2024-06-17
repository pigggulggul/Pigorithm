// 과제 시작 시간이 되면 시작
// 새과제 시작시 기존 과제가 진행중이면 멈추고 새 과제 시작
// 진행중인 과제가 끝났을때 멈춘 과제가 있으면, 멈춘 과제를 이어서
// 과제를 끝낸 시각에 새로 시작해야하는과제와 멈춘 과제가 있으면 새시작 과제부터
// 멈춘 과제가 여러개면 최근 멈춘 과제부터
// 과제를 끝낸 순서대로 이름을 배열에 담아 return
// [name,start,playtime]

let resultCount=0;
let result=[];
let currentTime;
let queue=[];
function solution(plans) {
    var answer = [];
    plans.sort((a, b) => {
      let timeA = a[1].split(':').map(Number);
      let timeB = b[1].split(':').map(Number);
      // 시와 분을 분리하고 비교
      return timeA[0] - timeB[0] || timeA[1] - timeB[1];
    });
    let count=1;
    currentTime=plans[0][1].split(':').map(Number);
    queue.push([plans[0][0],parseInt(plans[0][2])]);
    while(resultCount !== plans.length){
        // console.log(count,queue);
        if(count===plans.length){
            while (queue.length !== 0) {
                let homework = queue.pop();
                result.push(homework[0]);
                resultCount++;
            }
            break;
        }
        if(queue.length == 0){
            queue.push([plans[count][0],plans[count][2]]);
        }else{
            let ct = plans[count][1].split(':').map(Number);
            let minute = 60*(ct[0]-currentTime[0])+ct[1]-currentTime[1]; //지난 시간
            while(minute != 0 && queue.length > 0){
                let homework = queue.pop();
                let remainTime = homework[1] - minute;
                if (remainTime <= 0) {
                    minute = -remainTime;
                    result.push(homework[0]);
                    resultCount++;
                } else {
                    homework[1] = remainTime;
                    queue.push(homework);
                    minute = 0;
                }
            }
            currentTime=ct;
        }
         queue.push([plans[count][0],parseInt(plans[count][2])]);
        count++;
    }
    // console.log(result);
    return result;
}