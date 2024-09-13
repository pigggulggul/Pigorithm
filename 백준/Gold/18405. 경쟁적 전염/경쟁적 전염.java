import java.util.*;
import java.io.*;

class InfectionPos {
	int x;
	int y;
	int num;

	public InfectionPos(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}
public class Main {

	static int N, K, S;
	static int[][] arr;
	static int resultX, resultY;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static Deque<InfectionPos> queue = new LinkedList<InfectionPos>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		resultX = Integer.parseInt(st.nextToken()) - 1;
		resultY = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 1; i <= K; i++) {
			startInfection(i);
		}

		for (int t = 0; t < S; t++) {
			goInfection(arr);
		}

		System.out.println(arr[resultX][resultY]);
	}
	private static void startInfection(int num) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == num) {
					queue.offerLast(new InfectionPos(i, j, num));

				}
			}
		}
	}
	private static void goInfection(int[][] arr) {
		int size = queue.size();

		while (size-- > 0) {
			InfectionPos info = queue.pollFirst();
			int x = info.x;
			int y = info.y;
			int num = info.num;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (!checkWall(nx, ny))
					continue;
				if (arr[nx][ny] == 0) {
					arr[nx][ny] = num;
					queue.offerLast(new InfectionPos(nx, ny, num));
				}
			}
		}
	}
	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	private static void printArr(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

}