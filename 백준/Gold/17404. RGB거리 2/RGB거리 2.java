import java.util.*;
import java.io.*;

public class Main {

	static int N, M, T;
	static int[][] house;
	static int[][] dp;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		house = new int[N][3];
		dp = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = house[0][0];
		dp[0][1] = house[0][1];
		dp[0][2] = house[0][2];
//		1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
//		N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
//		i(2 ≤ i ≤ N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.

		// DP로 계산 해야겠다...DFS인데 시간계계산 대충해서 초과됐다..
		// dp[i][j]에서 밑으로 내려가면서 이전 값 제외 나머지 2개중 작은 값을 넣기
		for (int start = 0; start < 3; start++) {
			int startNum = start;

			for (int i = 0; i < 3; i++) {
				if (startNum == i) {
					dp[1][i] = -1;
				} else {
					dp[1][i] = dp[0][startNum] + house[1][i];
				}
			}

			if (N > 2) {
				if (dp[1][0] == -1) {
					dp[2][0] = Math.min(dp[1][1], dp[1][2]) + house[2][0];
					dp[2][1] = dp[1][2] + house[2][1];
					dp[2][2] = dp[1][1] + house[2][2];
				} else if (dp[1][1] == -1) {
					dp[2][0] = dp[1][2] + house[2][0];
					dp[2][1] = Math.min(dp[1][0], dp[1][2]) + house[2][1];
					dp[2][2] = dp[1][0] + house[2][2];
				} else {
					dp[2][0] = dp[1][1] + house[2][0];
					dp[2][1] = dp[1][0] + house[2][1];
					dp[2][2] = Math.min(dp[1][0], dp[1][1]) + house[2][2];
				}
			}

			for (int i = 3; i < N; i++) {
				for (int j = 0; j < 3; j++) {
					// 0이면 1과 2중에 작은걸 고르기 1이면 0과 2중에 작은걸 고르기
					dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + house[i][0];
					dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + house[i][1];
					dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + house[i][2];
				}
			}

			dp[N - 1][startNum] = -1;

			for (int i = 0; i < 3; i++) {
				if (dp[N - 1][i] != -1 && dp[N - 1][i] < min) {
					min = dp[N - 1][i];
				}
			}
//			printDp();
		}

		System.out.println(min);
	}

	private static void printDp() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
