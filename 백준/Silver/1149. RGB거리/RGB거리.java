
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int arr[][];
	static int dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[N][3];
		int r = 0;
		int g = 0;
		int b = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = r = arr[0][0];
		dp[0][1] = g = arr[0][1];
		dp[0][2] = b = arr[0][2];
		for (int i = 1; i < N; i++) {
			dp[i][0] = r = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = g = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = b = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
		}
//		for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(dp[i]));
//		}

		int result = Math.min(r, Math.min(g, b));
		System.out.println(result);

	}

}
