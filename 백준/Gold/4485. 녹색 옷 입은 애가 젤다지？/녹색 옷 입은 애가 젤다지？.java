import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	int x;
	int y;
	int cost;

	public Node(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int N = 0;
	static int maze[][];
	static int min;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int testcase = 0;
	static int dist[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		// 한 번 이동 시 마다 해당 소지금 만큼 돈을 잃는다.돈을 최소화하여 배열 끝까지 도착
		// 더이상 갈 수 없거나 N-1,N-1에 도착하면 종료
		while (N != 0) {
			// 초기화
			maze = new int[N][N];
			dist = new int[N][N];
			min = Integer.MAX_VALUE;
			testcase++;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maze[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			dist[0][0] = maze[0][0];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0, maze[0][0]));
			while (!pq.isEmpty()) {
				Node node = pq.poll();

				for (int d = 0; d < 4; d++) {
					int nx = node.x + dx[d];
					int ny = node.y + dy[d];
					
					if(!check(nx,ny)) continue;
					if(dist[nx][ny]>dist[node.x][node.y]+maze[nx][ny]) {
						dist[nx][ny] = dist[node.x][node.y]+maze[nx][ny];
						pq.add(new Node(nx,ny,dist[nx][ny]));
					}
				}

			}

			sb.append("Problem " + testcase + ": " + dist[N-1][N-1]).append("\n");
//			System.out.println("Problem " + testcase + ": " + min);
			N = Integer.parseInt(br.readLine());

		}
		System.out.println(sb.toString());
	}

	private static void printboolean(int[][] visited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}