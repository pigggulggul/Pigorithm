import java.util.*;
import java.io.*;

// 내가 푼 방법. clone해서 boolean에 넣었는데 한 곳에

public class Main {
	static int N, M, T;
	static int[][] arr;
	static int[] dx = { -1, 1, 1, -1 };
	static int[] dy = { 1, 1, -1, -1 };
	static int max = 0;
	static boolean[][] visited;
	static int black = 0;
	static int white = 0;
	static int[][] chess;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N*N칸.
		// 비숍 놓기. 1인 곳에만 놓을 수 있다.
		// 대각선이 아니면 놓을 수 없다.
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		chess = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				chess[i][j] = (i + j) % 2;
			}
		}
		dfs(0, 0, chess[0][0], 0);
		dfs(0, 1, chess[0][1], 0);
		System.out.println(black + white);
	}

	// 위치찾기
	private static void dfs(int x, int y, int color, int count) {

		// 1이면 놓을 수 있고 0이면 놓을 수 없다.
		// visited
		// 만약 그 자리 확인하고 놓을 수 있으면
		if (x >= N) {
			if (color == 0) {
				black = Math.max(count, black);
			} else {
				white = Math.max(count, white);
			}
			return;
		}
		int nx = x;
		int ny = y + 2;
		if (ny >= N) {
			nx++;
			if (nx < N) {
				if (chess[nx][0] == color) {
					ny = 0;
				} else {
					ny = 1;
				}
			}
		}
		if (arr[x][y] == 0) {
			dfs(nx, ny, color, count);
			return;
		}
		if (checkBishop(x, y)) {
			visited[x][y] = true;
			dfs(nx, ny, color, count + 1);
			visited[x][y] = false;
		}
		dfs(nx, ny, color, count);
	}

	private static boolean checkBishop(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			while (checkWall(nx, ny)) {
				if (visited[nx][ny] == true) {
					return false;
				}
				nx += dx[d];
				ny += dy[d];
			}
		}
		return true;
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}

	}
}