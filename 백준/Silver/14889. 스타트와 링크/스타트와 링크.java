import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[] p;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        p= new int[N/2];
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        boolean[] visited = new boolean[N];
        perm(0,0,visited);
        System.out.println(min);
    }

    static void perm(int cnt,int start,boolean[] visited){
        if(cnt==N/2){
//            System.out.println(Arrays.toString(p));
            int num=cal();
            if(min > num){
                min = num;
            }
            return;
        }else {
            for (int i = start; i < N; i++) {
                if(visited[i]){
                    continue;
                }
                visited[i]=true;
                p[cnt] = i;
                perm(cnt+1,i,visited);
                p[cnt] = 0;
                visited[i]=false;
            }
        }
    }
    static int cal(){
        int[] startTeam = p;
        int[] linkTeam = new int [N/2];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N/2; i++) {
            visited[startTeam[i]]=true;
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if(!visited[i]){
                linkTeam[cnt]=i;
                cnt++;
            }
        }
        int startPoint =0;
        int linkPoint=0;
        for (int i = 0; i < N/2; i++) {
            for (int j = 0; j < N/2; j++) {
                if(i==j){
                    continue;
                }
                startPoint += arr[startTeam[i]][startTeam[j]];
                linkPoint += arr[linkTeam[i]][linkTeam[j]];
            }
        }
        return Math.abs(startPoint-linkPoint);
    }
}