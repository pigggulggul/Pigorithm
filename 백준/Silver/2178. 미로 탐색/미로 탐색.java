import java.util.*;
import java.io.*;

public class Main {

	static int N, M, T;
	static int plate[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		plate = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < M; j++) {
				plate[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();

		// 1은 이동, 0은 이동x
		// 1,1에서 출발해서 N,M까지 갈 때 지나야하는 최소의 칸 수
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();

		queue.offer(new int[] { 0, 0 });

		while (!queue.isEmpty()) {
			int[] info = queue.poll();
			int x = info[0];
			int y = info[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (!checkWall(nx, ny))
					continue;

				if (plate[nx][ny] == 1) {
					plate[nx][ny] = plate[x][y] + 1;
					queue.offer(new int[] { nx, ny });
				}
			}
		}

		System.out.println(plate[N - 1][M - 1]);
//		printArr();

	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(plate[i][j] + " ");
			}
			System.out.println();
		}

	}

}
