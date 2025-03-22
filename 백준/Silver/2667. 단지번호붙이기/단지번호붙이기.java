import java.io.*;
import java.util.*;

// 입력: 지도크기. 단지 배열
// 출력: 단지 수. 단지별 개수

public class Main {

	static int N, M, T;
	static int[][] apart;
	static boolean[][] apartFlag;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Queue<Integer> pq = new PriorityQueue<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1은 집이 있는 곳을, 0은 집이 없는 곳
		// 집이 연결 된 곳은 상하좌우. 단지수 출력하기
		N = Integer.parseInt(st.nextToken());

		apart = new int[N][N];
		apartFlag = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < str.length(); j++) {
				apart[i][j] = str.charAt(j) - '0';
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (apart[i][j] == 1 && !apartFlag[i][j]) {
					count++;
					startbfs(i, j, count);
				}
			}
		}
		System.out.println(count);
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());
		}

	}

	private static void startbfs(int startX, int startY, int count) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { startX, startY });
		
		apart[startX][startY]=count;
		apartFlag[startX][startY]=true;
		int apartcount = 1;
		while (!queue.isEmpty()) {
			int[] info = queue.poll();
			int x = info[0];
			int y = info[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (!checkWall(nx, ny))
					continue;

				if (apart[nx][ny] != 0 && !apartFlag[nx][ny]) {
					apart[nx][ny] = count;
					apartFlag[nx][ny] = true;
					queue.add(new int[] { nx, ny });
					apartcount++;
				}
			}
		}
		pq.add(apartcount);
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(apart[i][j] + " ");
			}
			System.out.println();
		}

	}

}
