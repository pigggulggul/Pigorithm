
import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static long[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		T = Integer.parseInt(br.readLine());
		dp = new long[101];
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			dp[0] = 0;
			dp[1] = 1;
			dp[2] = 1;
			dp[3] = 1;
			for (int i = 4; i <= N; i++) {
				if (dp[i] == 0) {
					dp[i] = dp[i - 3] + dp[i - 2];
				}
			}
			System.out.println(dp[N]);
		}
	}

}
