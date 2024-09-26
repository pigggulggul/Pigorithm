import java.util.*;
import java.io.*;

public class Main {
	static int N = 5, M, T;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static char[][] arr;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < N; j++) {
				char cha = str.charAt(j);
				arr[i][j] = cha;
			}
		}
		// 7번의 이동이 필요하다.
		// S와 Y가 있는데 S가 4명 이상 필요하다.
		// 푸는 방법. 7번의 이동이 끝나고 같은 자리에 있으면 안되니까 결과를 hashSet에 담는다.
		// 만약에 3이 넘었는데 이다솜파의 수가 0이면 back

		// 00부터 55까지니까 한 번 지나면 거기는 false로 막는것도 좋을듯..?

		// 근데 DFS로 해보니까 숫자가 부족하다. 그래서 2가지로 나눌려고한다.
		// 1. 0부터 24까지 중에서 7자리의 숫자를 뽑기.
		// 2. 그 수가 조건이 되는지 확인.

		int[] combiArr = new int[7];
		checkCombi(combiArr, 0, 0);

//		 printArr(arr);
		System.out.println(result);
	}

	private static void checkCombi(int[] combiArr, int start, int cnt) {
		if (cnt == 7) {
//			System.out.println(Arrays.toString(combiArr));
			startBFS(combiArr);
		} else {
			for (int i = start; i < 25; i++) {
				combiArr[cnt] = i;
				checkCombi(combiArr, i + 1, cnt + 1);
			}
		}
	}

	private static void startBFS(int[] combiArr) {
		boolean[][] visited = new boolean[N][N];

		int numS = 0;

		for (int i = 0; i < 7; i++) {
			visited[combiArr[i] / 5][combiArr[i] % 5] = true;
			if (arr[combiArr[i] / 5][combiArr[i] % 5] == 'S') {
				numS++;
			}
		}
		if (numS < 4) {
			return;
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == true) {
					count = checkLine(visited, i, j);
					if (count == 7) {
						result++;
					} else {
						return;
					}
				}
			}
		}
	}

	private static int checkLine(boolean[][] visited, int startX, int startY) {
		int count = 0;
		// 55 배열에 true false가 있다. true에 시작점 있으니 여기서 count==7이 되어야한다.

		Deque<int[]> queue = new LinkedList<>();

		queue.offer(new int[] { startX, startY });
		while (!queue.isEmpty()) {
			int[] info = queue.pollFirst();
			int x = info[0];
			int y = info[1];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (!checkWall(nx, ny))
					continue;

				if (visited[nx][ny] == true) {
					visited[nx][ny] = false;
					count++;
					queue.offerLast(new int[] { nx, ny });
				}
			}
		}
		return count;
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void printArr(char[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void printBoolean(boolean[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}