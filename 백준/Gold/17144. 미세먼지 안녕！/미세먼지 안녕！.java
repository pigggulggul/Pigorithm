import java.util.*;
import java.io.*;

public class Main {
	static int arr[][];
	static int spread[][];
	static int N, M, time;
	static int cleanerUpX = 0;
	static int cleanerUpY = 0;
	static int cleanerDownX = 0;
	static int cleanerDownY = 0;
	static boolean cleaner;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {
					if (cleaner) {
						cleanerDownX = i;
						cleanerDownY = j;
					} else {
						cleanerUpX = i;
						cleanerUpY = j;
						cleaner = true;
					}
				}
			}
		}
		while (time-- > 0) {
			spread = new int[N][M];
			// 5이상인 부분 찾아서 spread[][]에 값 더하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] >= 5) {
						spreadAir(i, j);
					}
				}
			}

			plusAir();
			operateUpClean();
			operateDownClean();

//			printArr();

		}
		int result = checkAirMount();
		System.out.println(result);
	}

	private static int checkAirMount() {
		int air = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == -1)
					continue;
				air += arr[i][j];
			}
		}
		return air;
	}

	private static void operateUpClean() {
		// 위로
		int dir = 0;
		int x = cleanerUpX + dx[dir];
		int y = cleanerDownY + dy[dir];
		while (true) {

			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx == cleanerUpX && ny == cleanerDownY) {
				arr[x][y] = 0;
				return;
			}
			if (!check(nx, ny) || nx > cleanerUpX) {
				dir += 1;
				continue;
			}
			arr[x][y] = arr[nx][ny];
			x = nx;
			y = ny;
		}
	}

	private static void operateDownClean() {
		// 아래로
		int dir = 2;
		int x = cleanerDownX + dx[dir];
		int y = cleanerDownY + dy[dir];
		while (true) {

			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx == cleanerDownX && ny == cleanerDownY) {
				arr[x][y] = 0;
				return;
			}
			if (!check(nx, ny) || nx < cleanerDownX) {

				dir -= 1;
				if (dir == -1) {
					dir = 3;
				}
				continue;
			}
			arr[x][y] = arr[nx][ny];
			x = nx;
			y = ny;
		}
	}

	/** spread air에 더하기 */
	private static void plusAir() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (spread[i][j] == 0)
					continue;
				if (i == cleanerUpX && j == cleanerUpY || i == cleanerDownX && j == cleanerDownY)
					continue;
				arr[i][j] += spread[i][j];
			}
		}
	}

	private static void printSpread() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(spread[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void spreadAir(int x, int y) {
		int totalAir = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!check(nx, ny))
				continue;
			if (arr[nx][ny] == -1)
				continue;
			spread[nx][ny] += arr[x][y] / 5;
			totalAir += arr[x][y] / 5;
		}
		spread[x][y] -= totalAir;
	}

	private static boolean check(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}