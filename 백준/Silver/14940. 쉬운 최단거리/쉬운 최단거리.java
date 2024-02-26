import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static int startX = 0;
	static int startY = 0;
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };
	static boolean[][] visited;
	static Deque<int[]> deque = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				if (num == 2) {
					startX = i;
					startY = j;
				}
			}
		}
		arr[startX][startY] = 0;
		deque.offer(new int[] { startX, startY });
		while(!deque.isEmpty()) {
			int[] info = deque.poll();
			int x = info[0];
			int y = info[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(!checkWall(nx,ny))continue;
				if(arr[nx][ny]==0 || visited[nx][ny])continue;
				visited[nx][ny]=true;
				arr[nx][ny] = arr[x][y]+1;
				deque.offer(new int[] {nx,ny});
				
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j]==false && arr[i][j]==1) {
					System.out.print("-1 ");
				}else {
					System.out.print(arr[i][j]+" ");	
				}
			}
			System.out.println();
		}
	}

	private static boolean checkWall(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}