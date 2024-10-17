import java.util.*;
class Target implements Comparable<Target> {
    int start;
    int end;
    
    public Target(int start,int end){
        this.start=start;
        this.end=end;
    }
    @Override
    public int compareTo(Target o1){
        return this.end-o1.end;
    }
    
}
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        // 최소사용으로 미사일 요격
        // A가 발사하면 x축에 평행한 직선
        // B는 해당 x좌표에서 쭉 발사한다. 걸쳐있는 모든 미사일 관통
        // 모든 폭격 미사일을 요격하기 위한 미사일 수.
        // 최대한 많은 미사일을 맞출려면 먼저 끝나는 순서대로 배치해야된다.
        // 미사일은 0부터 시작이고 그리고 하나씩 방문.
        // 만약에 방문하면서 범위안에 미사일이 없으면 해당 구간의 마지막으로 얻고 미사일+1한다.
        Arrays.sort(targets,(a,b)-> a[1]-b[1]);
        int missile = 0;
        for(int[] target : targets){
            // System.out.println(target[0] +" "+target[1]);
            if(missile > target[0]){
                continue;
            }else{
                missile=target[1];
                answer++;
            }
        }
        return answer;
    }
}