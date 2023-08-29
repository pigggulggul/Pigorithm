import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[][] cheese;
	static int[][] newCheese;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean zero = false;
	static int stack = 1;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		newCheese = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if(N==1 && M==1) {
			if(cheese[0][0]==1) {
				System.out.println(1);
				System.out.println(1);
			}
			else if(cheese[0][0]==0) {
				System.out.println(0);
				System.out.println(0);
			}
			return;
		}
		
		while (true) {

			duplicateCheese();

			cheeseAirDfs(0, 0);

			cheeseCheck(0, 0);

			if(remainCheese())break;

			cheeseMelt();

			stack++;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(newCheese[i][j]==3) {
					count++;
				}
			}
		}
		System.out.println(stack);
		System.out.println(count);
		
	}

	private static boolean remainCheese() {
		int value = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(newCheese[i][j]==1) {
					value=0;
				}
			}
		}
		if(value==0) {
			return false;
		}
		return true;
	}

	private static void duplicateCheese() {
		for (int i = 0; i < N; i++) {
			newCheese[i] = cheese[i].clone();
		}
	}

	private static void cheeseAirDfs(int x, int y) {
		if(newCheese[x][y]==0) {
			newCheese[x][y] = 2;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!check(nx, ny))
				continue;
			if (newCheese[nx][ny] == 0)
				cheeseAirDfs(nx, ny);
		}
	}

	private static void cheeseCheck(int x, int y) {
		for (int i = x; i < N; i++) {
			for (int j = y; j < M; j++) {
				if (newCheese[i][j] == 1) {
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (!check(nx, ny))
							continue;
						if (newCheese[nx][ny] == 2) {
							newCheese[i][j] = 3;
						}
					}
				}

			}
		}
	}

	private static void cheeseMelt() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newCheese[i][j] == 3) {
					cheese[i][j] = 0;
				}
			}
		}
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}