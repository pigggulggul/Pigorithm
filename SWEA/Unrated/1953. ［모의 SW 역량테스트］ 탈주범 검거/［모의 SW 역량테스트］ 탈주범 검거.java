
import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, R, C, time;
	static int map[][];
	static boolean visited[][];
	static int current;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			current = 0;
			count = 1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited[R][C] = true;
			bfs(R, C);

			System.out.println("#" + testcase + " " + count);
		}
	}

	private static void bfs(int startX, int startY) {
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] { startX, startY });
		while (!que.isEmpty()) {
			if (current == time - 1)
				break;
			current++;

			int queSize = que.size();
			for (int size = 0; size < queSize; size++) {
				int[] info = que.poll();
				// 현재 좌표
				int x = info[0];
				int y = info[1];
				if (map[x][y] == 1) {
					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (!check(nx, ny))
							continue;

						if (map[nx][ny] == 0 || visited[nx][ny])
							continue;

						if (d == 0 && !checkLoad(nx, ny, 0))
							continue;
						else if (d == 1 && !checkLoad(nx, ny, 1))
							continue;
						else if (d == 2 && !checkLoad(nx, ny, 2))
							continue;
						else if (d == 3 && !checkLoad(nx, ny, 3))
							continue;
						else {
							visited[nx][ny] = true;
							que.add(new int[] { nx, ny });
							count++;
						}
					}
				} else if (map[x][y] == 2) {
					for (int d = 0; d < 4; d += 2) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (!check(nx, ny))
							continue;

						if (map[nx][ny] == 0 || visited[nx][ny])
							continue;

						if (d == 0 && !checkLoad(nx, ny, 0))
							continue;
						else if (d == 2 && !checkLoad(nx, ny, 2))
							continue;
						else {
							visited[nx][ny] = true;
							que.add(new int[] { nx, ny });
							count++;
						}
					}
				} else if (map[x][y] == 3) {
					for (int d = 1; d < 4; d += 2) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (!check(nx, ny))
							continue;

						if (map[nx][ny] == 0 || visited[nx][ny])
							continue;

						if (d == 1 && !checkLoad(nx, ny, 1))
							continue;
						else if (d == 3 && !checkLoad(nx, ny, 3))
							continue;
						else {
							visited[nx][ny] = true;
							que.add(new int[] { nx, ny });
							count++;
						}
					}
				} else if (map[x][y] == 4) {
					for (int d = 0; d < 2; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (!check(nx, ny))
							continue;

						if (map[nx][ny] == 0 || visited[nx][ny])
							continue;

						if (d == 0 && !checkLoad(nx, ny, 0))
							continue;
						else if (d == 1 && !checkLoad(nx, ny, 1))
							continue;
						else {
							visited[nx][ny] = true;
							que.add(new int[] { nx, ny });
							count++;
						}
					}
				} else if (map[x][y] == 5) {
					for (int d = 1; d < 3; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (!check(nx, ny))
							continue;

						if (map[nx][ny] == 0 || visited[nx][ny])
							continue;

						if (d == 1 && !checkLoad(nx, ny, 1))
							continue;
						else if (d == 2 && !checkLoad(nx, ny, 2))
							continue;
						else {
							visited[nx][ny] = true;
							que.add(new int[] { nx, ny });
							count++;
						}
					}
				} else if (map[x][y] == 6) {
					for (int d = 2; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (!check(nx, ny))
							continue;

						if (map[nx][ny] == 0 || visited[nx][ny])
							continue;

						if (d == 2 && !checkLoad(nx, ny, 2))
							continue;
						else if (d == 3 && !checkLoad(nx, ny, 3))
							continue;
						else {
							visited[nx][ny] = true;
							que.add(new int[] { nx, ny });
							count++;
						}
					}
				} else if (map[x][y] == 7) {
					for (int d = 0; d < 4; d += 3) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (!check(nx, ny))
							continue;

						if (map[nx][ny] == 0 || visited[nx][ny])
							continue;

						if (d == 0 && !checkLoad(nx, ny, 0))
							continue;
						else if (d == 3 && !checkLoad(nx, ny, 3))
							continue;
						else {
							visited[nx][ny] = true;
							que.add(new int[] { nx, ny });
							count++;
						}
					}
				}
			}
		}
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	/** number에 해당하는 숫자인지 확인 숫자면 true 포함 안 되면 false */
	private static boolean checkLoad(int x, int y, int num) {
		// 위방향에 정당하게 올 수 있는것 검사
		if (num == 0 && (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6)) {
			return true;
		} else if (num == 1 && (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7)) {
			return true;
		} else if (num == 2 && (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 7)) {
			return true;
		} else if (num == 3 && (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5)) {
			return true;
		}
		return false;
	}

}
