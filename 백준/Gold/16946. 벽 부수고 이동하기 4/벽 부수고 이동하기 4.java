
import java.util.*;
import java.io.*;

// N*N의 배열이 주어지고
// 1과0이 존재. 1은 이동 할 수 없는 칸이고 0은 이동 할 수 있는 칸.
// 기본적인 방법. NN순환하면서 하나씩 0으로 바꾸고 0의 개수 BFS로 세기.
// 1000x1000이면 무조건 터지는데 0을 계속 true로 바꾸고 하는거 힘들다.	
// 반대로 0을 찾아서 0이 보일 때마다 근처 1칸의 1에 +해주면?
// 0을 BFS로 만들고 다 BFS 개수만큼 저장해두면 . 근처 1칸만 확인해서 0이 있는 개수를 불러 올 수 있다.

// 1과0만 있는 배열 plate,
// 연결된 0이 몇개 있나 표시된 배열 zeroNum[]
// 결과 result[]
// 모든 배열 돌면서 1이 있으면 근처 1칸에 zeroNum의 총 수 +1을 result에 담기

//4 5
//11001
//00111
//01010
//10101

//46003
//00732
//06040
//50403

// 시간 초과가남.BFS 할 때영역 찾아서 count 세주고 바구는 구간에서 시간초과가 발생하는거같음.
// 번호를 매기고 그 번호에 대한 값을 count에 저장. group 1: 5, group 2: 13 이런식으로
// 찾을때는 4방향 탐색해서 해당 번호에 대한 값을 가져옴.
// 4방향이니까. 4개 배열 만들고 id가 있는지 검사하면 댈듯
public class Main {
	static int N, M, T;
	static int plate[][], zeroNum[][], result[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Map<Integer, Integer> map = new HashMap<>();
	static boolean[][] flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		plate = new int[N][M];
		zeroNum = new int[N][M];
		result = new int[N][M];
		flag = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String numberStr = st.nextToken();
			for (int j = 0; j < M; j++) {
				plate[i][j] = numberStr.charAt(j) - '0';
			}
		}
		int id = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (plate[i][j] == 0 && !flag[i][j]) {
					startBFS(i, j, id);
					id++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (plate[i][j] == 1) {
					getMapValue(i, j);
				}
			}
		}
//		printArr(plate);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(zeroNum[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		printArr(result);
	}

	// 0인 부분 count하기.0인부분 바꾸기. queue 2개
	private static void startBFS(int startX, int startY, int id) {
		int count = 1;
		Queue<int[]> checkQueue = new LinkedList<int[]>();

		checkQueue.add(new int[] { startX, startY });
		flag[startX][startY] = true;
		zeroNum[startX][startY] = id;
		while (!checkQueue.isEmpty()) {
			int[] info = checkQueue.poll();
			int x = info[0];
			int y = info[1];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (!checkWall(nx, ny))
					continue;

				if (plate[nx][ny] == 0 && !flag[nx][ny]) {
					flag[nx][ny] = true;
					count++;
					zeroNum[nx][ny] = id;
					checkQueue.add(new int[] { nx, ny });
				}
			}
		}
		map.put(id, count);

	}

	private static void getMapValue(int x, int y) {
		int count = 0;
		int[] checkMany = new int[4];
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (!checkWall(nx, ny)) {
				continue;
			}
			boolean checkFlag = false;
			if (zeroNum[nx][ny] != 0) {
				for (int i = 0; i < 4; i++) {
					if (checkMany[i] == zeroNum[nx][ny]) {
						checkFlag = true;
					}
				}
				if (!checkFlag) {
					count += map.get(zeroNum[nx][ny]);
					checkMany[d] = zeroNum[nx][ny];
				}

			}
		}
		result[x][y] = count;
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static void printArr(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (plate[i][j] == 1) {
					sb.append((arr[i][j] + 1) % 10);
				} else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
