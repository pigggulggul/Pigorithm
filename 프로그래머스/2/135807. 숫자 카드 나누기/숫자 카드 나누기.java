import java.util.*;
class Solution {
    int maxValue=0;
    int maxValue2=0;
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        //오름차순하기. arrayA의 첫번쨰를 최고 수로 두고 
        //나누기
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        maxValue=arrayA[0];
        maxValue2=arrayB[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a,b)->b-a);

        for(int i = 2 ; i <= maxValue ; i++){
            if(checkA(i,arrayA)){
                pq.offer(i);
            }
        }
        for(int i = 2 ; i <= maxValue2 ; i++){
            if(checkA(i,arrayB)){
                pq2.offer(i);
            }
        }
        while(!pq.isEmpty()){
            int num = pq.poll();
            if(checkB(num,arrayB)){
                if(answer < num){
                    answer = num;
                }
                pq.clear();
                break;
            }
        }
        while(!pq2.isEmpty()){
            int num = pq2.poll();
            if(answer > num){
                break;
            }
            if(checkB(num,arrayA)){
                if(answer < num){
                    answer = num;
                }
            }
        }
        return answer;
    }
    private static boolean checkA(int num,int[] arr){
        for(int i = 0 ; i <arr.length ; i++){
            if(arr[i]%num !=0) return false;
        }
        return true;
    }
    private static boolean checkB(int num,int[] arr){
        for(int i = 0 ; i <arr.length ; i++){
            if(arr[i]%num ==0) return false;
        }
        return true;
    }
}