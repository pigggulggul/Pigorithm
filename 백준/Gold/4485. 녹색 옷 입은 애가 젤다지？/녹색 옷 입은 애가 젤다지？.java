import java.util.*;
import java.io.*;

public class Main {
	static int N = 0;
	static int maze[][];
	static int min;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int testcase = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		// 한 번 이동 시 마다 해당 소지금 만큼 돈을 잃는다.돈을 최소화하여 배열 끝까지 도착
		// 더이상 갈 수 없거나 N-1,N-1에 도착하면 종료
		while (N != 0) {
			// 초기화
			maze = new int[N][N];
			int visited[][] = new int[N][N];
			min = Integer.MAX_VALUE;
			testcase++;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maze[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j]=Integer.MAX_VALUE;
				}
			}
			visited[0][0] = maze[0][0];
			dfs(0, 0, visited, maze[0][0]);
			sb.append("Problem " + testcase + ": " + min).append("\n");
//			System.out.println("Problem " + testcase + ": " + min);
			N = Integer.parseInt(br.readLine());

		}
		System.out.println(sb.toString());
	}

	private static void dfs(int x, int y, int[][] visited, int coin) {
		if(coin >= min) return;
		if (x == N - 1 && y == N - 1) {
			min = Math.min(min, coin);
//			printboolean(visited);
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (!check(nx, ny) || visited[nx][ny]<=coin+maze[nx][ny])
				continue;
			
			visited[nx][ny] = coin+maze[nx][ny];
			dfs(nx,ny,visited,coin + maze[nx][ny]);
		}
	}

	private static void printboolean(int[][] visited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}