class PriorityQueue{
    //초기화
    constructor(){
        this.heap = [];
    }
    
    push(value){
        const heap = this.heap;
        heap.push(value);
        
        let cur = heap.length-1;
        let par = Math.floor((cur-1)/2);
        
        while(cur > 0 && heap[cur] < heap[par]){
            [heap[cur],heap[par]] = [heap[par],heap[cur]];
            cur = par;
            par = Math.floor((cur-1)/2);
        }
    }
    
    pop(){
        const heap = this.heap;
        if(heap.length <=1){
            return heap.pop();
        }
        const max = heap[0];
        heap[0] = heap.pop();
        let idx = 0;
        
        while(idx * 2 + 1 < heap.length){
            let left = idx * 2 + 1;
            let right = idx * 2 + 2;
            let next = idx;
            
            if(heap[next] > heap[left]){
                next= left;
            }
            if(right < heap.length && heap[next] > heap[right]){
                next= right;
            }
            if(next === idx){
                break;
            }
            [heap[idx],heap[next]] = [heap[next],heap[idx]];
            idx=next;
        }
        return max;
    }
}
function solution(n, k, enemy) {
  
  let arr = new PriorityQueue();
  var capacity = 0;
 
  //k번째까지는 일단 무적권 쓰면 capacity의 고려 대상에서 제외
  enemy.slice(0,k).forEach((element)=>arr.push(element));
  for(var i  =k;i<enemy.length;i++){
    arr.push(enemy[i]);
    var popNum = arr.pop();
    if(popNum+capacity>n){
      return i;
    }
    capacity+=popNum;
  }

  return enemy.length;
}
