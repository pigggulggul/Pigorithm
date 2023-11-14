import java.util.*;
import java.io.*;
public class Main {
	static int T;
	static int[][] time;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T= Integer.parseInt(br.readLine());
		time = new int[T+1][2];
		dp = new int[T+1];
		for (int i = 0; i < T; i++) {
			st= new StringTokenizer(br.readLine());
			time[i][0]=Integer.parseInt(st.nextToken());
			time[i][1]=Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < T; i++) {
			if(time[i][0]+i<=T) {
				dp[time[i][0]+i]=Math.max(dp[time[i][0]+i],time[i][1]+dp[i] );
			}
			dp[i+1]=Math.max(dp[i+1], dp[i]);
		}
		System.out.println(dp[T]);
	}

}