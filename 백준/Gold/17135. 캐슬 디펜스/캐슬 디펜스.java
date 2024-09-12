import java.util.*;
import java.io.*;

public class Main {
	static int N, M, D;
	static int arr[][];
	static int archerCount = 3;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int maxEnemy = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] archer = new int[3];
		boolean[] visited = new boolean[M];
		perm(0, 0, visited, archer);
		// NxM 크기의 D거리

		// 순서 0. M개의 칸에서 3개를 뽑기.
		// 순서 1. 공격
		// 순서 2. 말 이동
		// 순서 3. 잡은 수 max

		// 방법 1. perm으로 중복없이 M개중에 3개를 뽑는다
		// 방법 2. 뽑은결과로 startCastle(archerPos)을 한다
		// 방법 2-1. 공격 - 말이동 - 판 검사의 반복. 판에 없으면 max값 비교

		System.out.println(maxEnemy);
	}

	private static void perm(int start, int cnt, boolean[] visited, int[] p) {
		if (cnt == archerCount) {
//			System.out.println(Arrays.toString(p));
			startCastle(p);
		} else {
			for (int i = start; i < M; i++) {
				if (visited[i])
					continue;
				visited[i] = true;
				p[cnt] = i;
				perm(i, cnt + 1, visited, p);
				visited[i] = false;
				p[cnt] = 0;
			}
		}
	}

	private static void startCastle(int[] p) {
		// 방법 2-1. 공격 - 말이동 - 판 검사의 반복. 판에 없으면 max값 비교
		int[][] cloneArr = new int[N + 1][M];
		int enemy = 0;
		for (int i = 0; i <= N; i++) {
			cloneArr[i] = arr[i].clone();
		}
		cloneArr[N][p[0]] = 3;
		cloneArr[N][p[1]] = 3;
		cloneArr[N][p[2]] = 3;

		int[][] checkArr0 = new int[N][M];
		int[][] checkArr1 = new int[N][M];
		int[][] checkArr2 = new int[N][M];
		checkArr0 = makeDistance(checkArr0, p[0]);
		checkArr1 = makeDistance(checkArr1, p[1]);
		checkArr2 = makeDistance(checkArr2, p[2]);

		while (checkEnemy(cloneArr)) {
			// 공격
			for (int i = 0; i < archerCount; i++) {
				if (i == 0)
					findEnemy(cloneArr, p[i], checkArr0);
				else if (i == 1)
					findEnemy(cloneArr, p[i], checkArr1);
				else
					findEnemy(cloneArr, p[i], checkArr2);
			}
			enemy += attack(cloneArr);
			replacePlate(cloneArr);

//			printArr(cloneArr);
//			System.out.println();
		}
		if (enemy > maxEnemy) {
			maxEnemy = enemy;
		}
	}

	private static boolean checkEnemy(int[][] arr) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}

	// 공격
	// 격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.
	// (4,4) (3,3)인 경우 1 + 1 이므로 거리가 2다.
	// 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다.
	private static int attack(int[][] arr) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 2) {
					arr[i][j] = 0;
					count++;
				}

			}
		}
		return count;
	}

	private static void replacePlate(int[][] arr) {
		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = arr[i - 1][j];
			}
		}
		for (int j = 0; j < M; j++) {
			arr[0][j] = 0;
		}
	}

	private static int[][] makeDistance(int[][] arr, int p) {
		int startX = N - 1;
		int startY = p;
		int[][] checkArr = new int[N][M];
		Deque<int[]> queue = new LinkedList<>();
		queue.addLast(new int[] { startX, startY, 1 });
		checkArr[startX][startY] = 1;
		while (!queue.isEmpty()) {
			int[] info = queue.pollFirst();
			int x = info[0];
			int y = info[1];
			int cnt = info[2];
			if (cnt >= D)
				continue;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (!checkWall(nx, ny))
					continue;

				if (checkArr[nx][ny] == 0) {
					checkArr[nx][ny] = cnt + 1;
					queue.addLast(new int[] { nx, ny, cnt + 1 });
				}
			}
		}
		return checkArr;
	}

	private static void findEnemy(int[][] arr, int p, int[][] checkArr) {
		int max = 100;
		int maxX = -1;
		int maxY = -1;
		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				if (arr[i][j] == 0 || checkArr[i][j] == 0)
					continue;
				else if (arr[i][j] != 0 && checkArr[i][j] != 0) {
					if (max > checkArr[i][j]) {
						max = checkArr[i][j];
						maxX = i;
						maxY = j;
					}
				}
			}
		}
		if (maxX != -1 && maxY != -1) {
			arr[maxX][maxY] = 2;
		}
//		printArr(arr);
//		System.out.println();
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static void printArr(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + "");
			}
			System.out.println();
		}
	}
}