import java.io.*;
import java.util.*;

public class Main {
	static int K;
	static int N;
	static int M;
	static int chess[][];
	static int mkx[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int mky[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		chess = new int[N][M];
		visited = new boolean[N][M][K+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
				if (chess[i][j] == 1) {
					chess[i][j] = -1;
				}
			}
		}

		bfs();

	}

	private static void bfs() {
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { 0, 0, 0, 0 });
		visited[0][0][0] = true;
		while (!que.isEmpty()) {
			int[] info = que.poll();
			int x = info[0];
			int y = info[1];
			int movek = info[2];
			int cnt = info[3];
			if (x == N - 1 && y == M - 1) {
				System.out.println(cnt);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (!check(nx, ny))
					continue;
				if (chess[nx][ny] == 0 && !visited[nx][ny][movek]) {
					visited[nx][ny][movek] = true;
//					printChess(movek);
					que.offer(new int[] { nx, ny, movek, cnt + 1 });
				}
			}
			if (movek == K)
				continue;
			for (int d = 0; d < 8; d++) {
				int nx = x + mkx[d];
				int ny = y + mky[d];
				if (!check(nx, ny))
					continue;
				if (chess[nx][ny] == 0 && !visited[nx][ny][movek+1]) {
					visited[nx][ny][movek+1] = true;
//					printChess(movek);
					que.offer(new int[] { nx, ny, movek + 1, cnt + 1 });
				}
			}
		}
		System.out.println("-1");
		return;
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static void printChess() {
		System.out.println("========================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(chess[i][j] + " ");
			}
			System.out.println();
		}
	}
	// 0,0에서 시작. N,N까지 이동. K번까지 이동 가능
	// [x][y][0] : K를 안 쓰고 이동한 경우
	// [x][y][1] : K를 1번 쓰고 이동한 경우
	// [x][y][2] : K를 2번 쓰고 이동한 경우
}
