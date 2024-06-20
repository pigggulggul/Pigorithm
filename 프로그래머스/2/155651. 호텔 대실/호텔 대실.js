// 최소한의 객실만을 사용하여 예약 손님 받기
// 한 번 사용한 객실은 퇴실 시간 기준 10분 청소 후 사용가능
// 예약시간 "HH:MM"이 배열로 주어짐
// 필요한 최소 객실의 수.
// 끝나는 시간을 기준으로 배열 .
// 시간의 순서대롤 큐를 쭉쭉죽 넣고 큐의 최대길이를 방이라고 함
// [14:10 19:20] [14:20 15:20] [15:00 17:00] [16:40 18:20]
// 넣을때마다 그 시간대로 변경 -> 큐를 map돌면서 끝나는 시간이 현재 시간-10분 보다 작은건 빼기 -> 넣기
// 큐의 크기를 재서 최대 크기 갱신하기
let newBookTime=[];
let maxCount = 1;
let currentTime=[];
function solution(book_time) {
    var answer = 0;
    book_time.sort((a,b)=> {
        let firstA = a[0].split(':').map(Number);
        let firstB = b[0].split(':').map(Number);
        return firstA[0]-firstB[0] || firstA[1]-firstB[1];
    })
    let queue = [];
    currentTime= book_time[0][0].split(':').map(Number);
    queue.push(book_time[0]);
    for(let i = 1 ; i < book_time.length ; i++){
        queue.push(book_time[i]);
        currentTime= book_time[i][0].split(':').map(Number);
        let checkTime = currentTime[0]*60 + currentTime[1]-10;
        for(let j = 0 ; j < queue.length ; j++){
            let queueTime = queue[j][1].split(':').map(Number);
            let finishTime = queueTime[0]*60 + queueTime[1];
            if(checkTime >= finishTime){
                queue.splice(j,1);
                j--
            }
        }
        //방 개수
        if(maxCount < queue.length){
            maxCount = queue.length;
        }
    }
    // console.log(queue);
    return maxCount;
}