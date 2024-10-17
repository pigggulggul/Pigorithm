import java.util.*;
import java.io.*;
class Solution {
    static Map<Double,Integer> map = new HashMap<>();
    public long solution(int[] weights) {
        long answer = 0;
        // 배열을 다 돌기에는 불필요한 값들이 너무 많다 -> map 이용
        // 겹치면 안되니까 원본만 저장 한다?
        // 될 수 있는 값들을 다 탐색한다.
        // 그러면 원본이랑 비교했을 때 [1, 2/3, 2, 4/3]에 해당되면 된다.
        Arrays.sort(weights);

        for(int weight : weights){
            Double kg_1 = weight*1.0*1;
            Double kg_2 = weight*2.0/3;
            Double kg_3 = weight*1.0/2;
            Double kg_4 = weight*3.0/4;
            
            if(map.containsKey(kg_1)) answer+= map.get(kg_1);
            if(map.containsKey(kg_2)) answer+= map.get(kg_2);
            if(map.containsKey(kg_3)) answer+= map.get(kg_3);
            if(map.containsKey(kg_4)) answer+= map.get(kg_4);
            if(map.containsKey(weight*1.0)){
                map.put(weight*1.0,map.get(weight*1.0)+1);
            }else{
                map.put(weight*1.0,1);
            }
        }
        return answer;
    }
}