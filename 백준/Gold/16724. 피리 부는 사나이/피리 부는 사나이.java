import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static char[][] arr;
	static boolean[][] visited;
	static int[][] countArr;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		visited = new boolean[N][M];
		countArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		// false인 부분 탐색을해서 가다가 만약에 다음 곳이 true인 곳을 만나면 거기는 사이클이 발생해서 safezone을 세워야한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == false) {
					DFS(i, j);
//					printBoolean(visited);
					countUp(i,j);
				}
			}
		}
//		printCount(countArr);
		System.out.println(result);
	}

	private static void DFS(int x, int y) {
		visited[x][y] = true;
		countArr[x][y]++;
		int dir;
		if (arr[x][y] == 'U') {
			dir = 0;
		} else if (arr[x][y] == 'R') {
			dir = 1;
		} else if (arr[x][y] == 'D') {
			dir = 2;
		} else {
			dir = 3;
		}
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		// 이전 visited를 받아서 거기에 포함되면 그냥 진행 아니면 result++;
		if (visited[nx][ny] == true) {
			if (countArr[nx][ny] > 1) {
				return;
			} else {
				result++;
				return;
			}
		} else {
			DFS(nx, ny);
		}
	}
	
	private static void countUp(int x, int y) {
		countArr[x][y]++;
		int dir;
		if (arr[x][y] == 'U') {
			dir = 0;
		} else if (arr[x][y] == 'R') {
			dir = 1;
		} else if (arr[x][y] == 'D') {
			dir = 2;
		} else {
			dir = 3;
		}
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if(countArr[nx][ny]==1) {
			countUp(nx,ny);
		}
	}

	private static void printArr(char[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + "");
			}
			System.out.println();
		}
	}

	private static void printCount(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + "");
			}
			System.out.println();
		}
	}

	private static void printBoolean(boolean[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + "");
			}
			System.out.println();
		}
		System.out.println();
	}
}