import java.io.*;
import java.util.*;

class Cloud {
	int x;
	int y;

	public Cloud(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, M;
	static int[][] arr;
	static Deque<Cloud> cloud = new ArrayDeque<>();
	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static boolean[][] visited;
	static int result=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		// 처음 구름
		cloud.add(new Cloud(N - 1, 0));
		cloud.add(new Cloud(N - 1, 1));
		cloud.add(new Cloud(N - 2, 0));
		cloud.add(new Cloud(N - 2, 1));
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			visited = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			int godirection = Integer.parseInt(st.nextToken()) - 1;
			int time = Integer.parseInt(st.nextToken());

			goCloud(godirection, time);
			rain();
			minusWater();
			
//			printCloud();

		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result+=arr[i][j];
			}
		}
		System.out.println(result);
	}

	private static void printCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void minusWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j]>=2 && !visited[i][j]) {
					arr[i][j]-=2;
					cloud.add(new Cloud(i, j));
				}
			}
		}
	}

	private static void rain() {
		if (!cloud.isEmpty()) {
			for (int i = 0; i < cloud.size(); i++) {
				Cloud info = cloud.pollFirst();
				arr[info.x][info.y]++;
				visited[info.x][info.y]=true;
				cloud.addLast(new Cloud(info.x, info.y));
			}
		}
		if (!cloud.isEmpty()) {
			int size =cloud.size();
			for (int i = 0; i < size; i++) {
				Cloud info = cloud.pollFirst();
				raincopy(info.x, info.y);
			}
		}
	}

	private static void raincopy(int x, int y) {
		int count=0;
//		System.out.println(x+" "+y);
		for (int d = 1; d < 8; d = d + 2) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!checkWall(nx, ny))
				continue;
			if(arr[nx][ny]>0) {
				count++;
			}
		}
//		System.out.println(count);
		arr[x][y]+=count;

	}

	private static boolean checkWall(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

	private static void goCloud(int dir, int time) {

		if (!cloud.isEmpty()) {
			for (int i = 0; i < cloud.size(); i++) {
				Cloud info = cloud.pollFirst();
				time = time % N;
				int x = (info.x + (dx[dir] * time)) % N;
				int y = (info.y + (dy[dir] * time)) % N;
				if (x < 0) {
					x = N + x;
				}

				if (y < 0) {
					y = N + y;
				}
//				System.out.println("구름 이동 | x =" + x + " | y = " + y);
				cloud.addLast(new Cloud(x, y));
			}
		}

	}

}
