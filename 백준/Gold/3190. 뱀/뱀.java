import java.util.*;
import java.io.*;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Rot {
	int time;
	char rotation;

	public Rot(int time, char rotation) {
		this.time = time;
		this.rotation = rotation;
	}
}

public class Main {
	static int N, M, T;
	static int timer = 0;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		// NxN개의 보드 크기
		M = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];

		Deque<Pos> queue = new LinkedList<>();
		queue.offerLast(new Pos(0, 0));
		int dir = 1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			arr[x][y] = 1;
		}
		T = Integer.parseInt(br.readLine());
		Rot[] rot = new Rot[T];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char rotation = st.nextToken().charAt(0);
			rot[i] = new Rot(time, rotation);
		}
		int nextIdx = 0;
		int nextTime = rot[0].time;
		char nextAlpha = rot[0].rotation;
		arr[0][0] = 2;

		// 뱀은 0 0에서부터 시작한다. 방향은 오른쪽
		// 게임 조건 : 벽이나 자기 자신의 몸과 부딪히면 게임이 끝난다.사과를 다 먹으면 끝난다
		while (true) {
			timer++;
			if (!queue.isEmpty()) {
				Pos info = queue.peekFirst();
				int x = info.x;
				int y = info.y;

				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (!checkWall(nx, ny))
					break;

				// 뱀에 부딪힐거 같으면
				if (arr[nx][ny] == 2) {
					break;
				}
				// 이동한 칸에 사과가 있다면 사과가 사라지고 꼬리는 움직이지 않는다.
				if (arr[nx][ny] == 1) {
					arr[nx][ny] = 2;
					queue.offerFirst(new Pos(nx, ny));

				} else if (arr[nx][ny] != 1) {
					arr[nx][ny] = 2;
					queue.offerFirst(new Pos(nx, ny));
					Pos tail = queue.pollLast();
					arr[tail.x][tail.y] = 0;
				}

				if (T > nextIdx && timer == nextTime) {
					// idx 0 time 8 D
					// idx 1 time 10 D
					// idx 2 time 11 D
					// idx 3 time 13 L
					char alpha = nextAlpha;
					if (alpha == 'D') {
						dir = (dir + 1) % 4;
					} else {
						dir = dir - 1;
						if (dir == -1) {
							dir = 3;
						}
					}
					nextIdx++;
					if(nextIdx < T) {
						nextTime = rot[nextIdx].time;
						nextAlpha = rot[nextIdx].rotation;
					}
				}
			}
//			printArr(arr);
		}
		System.out.println(timer);
		// 이동한 칸에 사과가 있다면 사과가 사라지고 꼬리는 움직이지 않는다.
		// 사과가 없으면 몸길이를 줄인다.
		// 사과의 위치와 뱀의 이동경로가 주어질 떄 몇초에 게임이 끝나는지.
		// 사과1 뱀2
		// 마지막 뱀의 꼬리 부분에 대한 제어가 들어가야 하므로 Deque를 사용한다.
		// 움직일 때 현재 Deque의 맨 앞을 peek한다.offerFirst로 머리 넣기

		// 머리 넣었을때 나의 몸이거나 벽이면 게임 종료

		// 사과가 없으면 queue.pollLast해서 거기 있는 값을 없애기

		// printArr();
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void printArr(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}