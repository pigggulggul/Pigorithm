import java.util.*;
import java.io.*;
class Solution {
    Map<Integer,int[]> map = new HashMap<>();
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        for(int i = 0 ; i < edges.length ; i++){
            if(!map.containsKey(edges[i][0])){
                map.put(edges[i][0],new int[]{1,0});
            }else{
                int[] info = map.get(edges[i][0]);
                map.put(edges[i][0],new int[]{info[0]+1,info[1]});
            }
            
            if(!map.containsKey(edges[i][1])){
                map.put(edges[i][1],new int[]{0,1});
            }else{
                int[] info = map.get(edges[i][1]);
                map.put(edges[i][1],new int[]{info[0],info[1]+1});
            }
        }
        for(int i : map.keySet()){
            int[] info = map.get(i);
            // System.out.println("key : "+ i +" | value : ["+ info[0]+","+info[1]+"]");
            if(info[0] >= 2 && info[1] ==0){
                answer[0]=i;
            }else if(info[0]==0 && info[1] > 0){
                answer[2]++;
            }else if(info[0]>=2 && info[1] >=2){
                answer[3]++;
            }
        }
        answer[1] = map.get(answer[0])[0] - answer[2]-answer[3];
        
        return answer;
    }
}