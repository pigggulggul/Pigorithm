import java.io.InputStreamReader;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dp;
	static int N;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		dp[0] = dp[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1]+1; // 1증가
			if(i%2==0) {
				dp[i]= Math.min(dp[i], dp[i/2]+1); //1뺀값과 2로 나눈 값 중에 최솟값 저장
			}
			if(i%3==0) {
				dp[i]=Math.min(dp[i], dp[i/3]+1);
			}
		}
		
		System.out.println(dp[N]);
	}


}
