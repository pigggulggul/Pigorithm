import java.io.*;
import java.util.*;

public class Main {

	static int[][] plate;
	static int N, M;

	static int max = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		plate = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				plate[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		checkLine();
		checkRock();
		checkL();
		checkThunder();
		checkMountain();
		System.out.println(max);
	}

	private static void checkMountain() {
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				int checkman = plate[i][j] + plate[i+1][j] + plate[i+2][j] + plate[i+1][j+1];
				max = Math.max(checkman, max);
				checkman = plate[i][j+1] + plate[i+1][j+1] + plate[i+2][j+1] + plate[i+1][j];
				max = Math.max(checkman, max);
			}
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				int checkman = plate[i][j] + plate[i][j+1] + plate[i][j+2] + plate[i+1][j+1];
				max = Math.max(checkman, max);
				checkman = plate[i+1][j] + plate[i+1][j+1] + plate[i+1][j+2] + plate[i][j+1];
				max = Math.max(checkman, max);
			}
		}
	}

	private static void checkThunder() {
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				int checkman = plate[i][j] + plate[i+1][j] + plate[i+1][j+1] + plate[i+2][j+1];
				max = Math.max(checkman, max);
				checkman = plate[i][j+1] + plate[i+1][j+1] + plate[i+1][j] + plate[i+2][j];
				max = Math.max(checkman, max);
			}
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				int checkman = plate[i+1][j] + plate[i+1][j+1] + plate[i][j+1] + plate[i][j+2];
				max = Math.max(checkman, max);
				checkman = plate[i][j] + plate[i][j+1] + plate[i+1][j+1] + plate[i+1][j+2];
				max = Math.max(checkman, max);
			}
		}
	}

	private static void checkL() {
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				int checkman = plate[i][j] + plate[i+1][j] + plate[i+2][j] + plate[i+2][j+1];
				max = Math.max(checkman, max);
				checkman = plate[i][j+1] + plate[i+1][j+1] + plate[i+2][j+1] + plate[i+2][j];
				max = Math.max(checkman, max);
				checkman = plate[i][j] + plate[i+1][j] + plate[i+2][j] + plate[i][j+1];
				max = Math.max(checkman, max);
				checkman = plate[i][j] + plate[i][j+1] + plate[i+1][j+1] + plate[i+2][j+1];
				max = Math.max(checkman, max);
			}
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				int checkman = plate[i][j] + plate[i][j+1] + plate[i][j+2] + plate[i+1][j];
				max = Math.max(checkman, max);
				checkman = plate[i][j] + plate[i+1][j] + plate[i+1][j+1] + plate[i+1][j+2];
				max = Math.max(checkman, max);
				checkman = plate[i][j] + plate[i][j+1] + plate[i][j+2] + plate[i+1][j+2];
				max = Math.max(checkman, max);
				checkman = plate[i+1][j] + plate[i+1][j+1] + plate[i+1][j+2] + plate[i][j+2];
				max = Math.max(checkman, max);
			}
		}
	}

	private static void checkLine() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M - 3; j++) {
				int checkman = plate[i][j] + plate[i][j + 1] + plate[i][j + 2] + plate[i][j + 3];
				max = Math.max(checkman, max);
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N - 3; j++) {
				int checkman = plate[j][i] + plate[j + 1][i] + plate[j + 2][i] + plate[j + 3][i];
				max = Math.max(checkman, max);
			}
		}
	}

	private static void checkRock() {
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				int checkman = plate[i][j] + plate[i][j + 1] + plate[i + 1][j] + plate[i + 1][j + 1];
				max = Math.max(checkman, max);
			}
		}
	}
}
