class Solution {
    public long solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        long start = 0;
        long end = limit;
        while(start<end){
            long mid = (start+end)/2;
            //퍼즐이 풀렸으면
            if(checkPuzzle(diffs,times,mid,limit)){
                end = mid;
                // System.out.println("풀었습니다 "+end);
            }else{
                start = mid+1;
                // System.out.println("못 풀었습니다 "+start);
            }
        }
        if(start==0){
            start=1;
        }
        return start;
    }
    //퍼즐 풀기
    private static boolean checkPuzzle(int[] diffs, int[] times,long level,long limit){
        long total = 0;
        int time_prev=0;
        for(int i = 0 ; i < diffs.length ; i++){
            int diff = diffs[i];
            int time_cur = times[i];
            if(diff <= level){
                total += time_cur;
            }else{
                long circle = diff-level;
                total += (time_prev+time_cur)*circle + time_cur;
            }
            time_prev= time_cur;
        }
        if(limit >= total){
            return true;
        }else{
            return false;
        }
    }
}