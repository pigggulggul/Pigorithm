
import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static int home[][];
	static final int garo = 1;
	static final int sero = 1;
	static final int dx[] = { 0, 1, 1 };
	static final int dy[] = { 1, 1, 0 };
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		home = new int[N][N];
		count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 1, 1);
		System.out.println(count);
	}

	/** dir 1 = 가로, 2 = 세로, 3 = 대각 */
	private static void dfs(int x, int y, int dir) {
		if (x == N - 1 && y == N - 1) {
			count++;
			return;
		}
//		System.out.println("x : " + x);
//		System.out.println("y : " + y);
//		System.out.println("dir : " + dir);
		int nx = x + dx[0];
		int ny = y + dy[0];
		int nx1 = x + dx[1];
		int ny1 = y + dy[1];
		int nx2 = x + dx[2];
		int ny2 = y + dy[2];

		// 가로
		if (dir == 1) {
			if (check(nx, ny)) {
				if (zeroCheck(nx, ny)) {
					dfs(nx, ny, dir);
				}
			}
			if(check(nx1,ny1)) {
				if (zeroCheck(nx, ny) && zeroCheck(nx1, ny1) && zeroCheck(nx2, ny2)) {
					dfs(nx1, ny1, 3);
				}
			}
		} else if (dir == 2) {
			if (check(nx2, ny2)) {
				if (zeroCheck(nx2, ny2)) {
					dfs(nx2, ny2, dir);
				}
			}
			if(check(nx1,ny1)) {
				if (zeroCheck(nx, ny) && zeroCheck(nx1, ny1) && zeroCheck(nx2, ny2)) {
					dfs(nx1, ny1, 3);
				}
			}
		} else if (dir == 3) {

			if (check(nx, ny)) {
				if (zeroCheck(nx, ny)) {
					dfs(nx, ny, 1);
				}
			}
			if (check(nx1, ny1)) {
				if (zeroCheck(nx, ny) && zeroCheck(nx1, ny1) && zeroCheck(nx2, ny2)) {
					dfs(nx1, ny1, dir);
				}
			}
			if (check(nx2, ny2)) {
				if (zeroCheck(nx2, ny2)) {
					dfs(nx2, ny2, 2);
				}
			}

		}
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static boolean zeroCheck(int x, int y) {
		if (home[x][y] == 0) {
			return true;
		}
		return false;
	}

}

// 파이프를 이동시켜서 NN까지 닿게
// 가로는 무조건 가로체크>가로+1 하거나 
// 대각체크(가로1 세로1 대각1) > 대각+1 하거나
// 세로체크>세로+1하기
// 이전이 가로였으면 다음은 가로 or 대각
// 이전이 대각였으면 가로 대각 세로
// 이전이 세로였으면 세로 대각
// N N에 도착하면 count++;
// visited[]