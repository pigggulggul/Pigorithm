import java.io.*;
import java.util.*;

public class Main {
	static long N;
	static long[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int pisano = 1500000;
		N = Long.parseLong(br.readLine()) % pisano;
		dp = new long[pisano+1];
		dp[0]=0;
		dp[1]=1;
		
		for (int i = 2; i <= pisano; i++) {
			dp[i]=(dp[i-1]+dp[i-2])%1000000;
		}
		System.out.println(dp[(int)N]);
	}

}
