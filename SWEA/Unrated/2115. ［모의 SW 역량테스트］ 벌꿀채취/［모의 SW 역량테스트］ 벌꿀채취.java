import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, C; // N:벌통크기, M:벌통개수, C:최대양
	static int[][] honey;
	static int[][] max_honey;
	static int max;
	static int result;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			honey = new int[N][N];
			max_honey = new int[N][N - (M - 1)];
			isSelected = new boolean[M];
			result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// (0,0) ~ 전체를 돌면서 그 구역의 최댓값 구하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					for (int check = 0; check < M; check++) {
					}
					max = 0;
					honeyPerm(i, j, 0);
					max_honey[i][j] = max;

				}
			}

			//가장 높은 수 찾기
			checkTop();
			
			System.out.println("#" + testcase + " " + result);
		}
	}

	private static void checkTop() {

		for (int twice = 0; twice < 2; twice++) {
			int topMax = 0;
			int topX = 0;
			int topY = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - (M - 1); j++) {
					if (topMax < max_honey[i][j]) {
						topMax = max_honey[i][j];
						topX = i;
						topY = j;
					}
				}
			}
			result += topMax;
			max_honey[topX][topY] = 0;
			
			//겹치는 부분 없애기
			for (int i = 0; i < M; i++) {
				if (topY - i >= 0 && max_honey[topX][topY - i] != 0) {
					max_honey[topX][topY - i] = 0;
				}
				if (topY + i < N - (M - 1) && max_honey[topX][topY + i] != 0) {
					max_honey[topX][topY + i] = 0;
				}
			}
		}
	}

	private static void honeyPerm(int x, int y, int cnt) {
		// x,y이면 (x,y)부터 (x,y+(M-1))까지 작동하는데 부분집합으로 한다
		if (cnt == M) {
			int count = 0;
			int value = 0;
			for (int i = 0; i < M; i++) {
				if (isSelected[i])
					count += honey[x][y + i];
			}
			if (count > C || count == 0)
				return;
			for (int i = 0; i < M; i++) {
				if (isSelected[i])
					value += (honey[x][y + i] * honey[x][y + i]);
			}
			max = Math.max(max, value);
			return;
		}
		isSelected[cnt] = true;
		honeyPerm(x, y, cnt + 1);
		isSelected[cnt] = false;
		honeyPerm(x, y, cnt + 1);
	}

}