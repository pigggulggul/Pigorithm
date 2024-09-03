import java.util.*;
import java.io.*;

class Shark {
	int speed;
	int dir;
	int size;

	public Shark(int speed, int dir, int size) {
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
}

class PosShark {
	int x;
	int y;
	int speed;
	int dir;
	int size;

	public PosShark(int x, int y, int speed, int dir, int size) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
}

public class Main {
	static int N, M, SharkNum;
	static int inventory = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Deque<PosShark> queue = new LinkedList<PosShark>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		SharkNum = Integer.parseInt(st.nextToken());

		Shark[][] plate = new Shark[N][M];
		for (int i = 0; i < SharkNum; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			plate[x - 1][y - 1] = new Shark(speed, dir - 1, size);
		}
//		printArr(plate);

		// N M SharkTotal
		// x y speed dir size []
		// queue()를 놓고 x,y,speed,dir,size를 저장한다. or [][]에 저장한다
		// 각 열에 이동하면서 가까운 상어를 잡는다

		for (int start = 0; start < M; start++) {
			// 해당 열의 상어를 잡는다
			catchShark(start, plate);
			// 상어들이 이동한다
			plate = moveShark(plate);

//			System.out.println();
//			printArr(plate);
		}
//		System.out.println();
//		printArr(plate);
		System.out.println(inventory);

		// 상어가 겹칠거 같으면 큰상어가 잡아먹는다
		// 끝까지 가고 남은 상어들의 합을 구한다

	}

	private static void catchShark(int row, Shark[][] arr) {
		for (int i = 0; i < N; i++) {
			if (arr[i][row] != null) {
				inventory += arr[i][row].size;
				arr[i][row] = null;
				return;
			}
		}
	}

	private static Shark[][] moveShark(Shark[][] arr) {
		// 원본 배열을 돌면서 visited를 표시하고 clone을해서 clone을 이동시킨다.
		Shark[][] cloneArr = new Shark[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != null) {
					// speed dir size. dir방향으로 speed만큼 이동
					// 결과를 queue에 담기.
					move(i, j, arr[i][j].speed, arr[i][j].dir, arr[i][j].size, cloneArr);
				}
			}
		}
		while(!queue.isEmpty()) {
			PosShark shark = queue.pollFirst();
			int x = shark.x;
			int y = shark.y;
			int speed = shark.speed;
			int dir = shark.dir;
			int size= shark.size;
			if(cloneArr[x][y]==null) {
				cloneArr[x][y] = new Shark(speed,dir,size);
			}else {
				if(cloneArr[x][y].size > size) {
					continue;
				}else {
					cloneArr[x][y] = new Shark(speed,dir,size);
				}
			}
			
		}
		return cloneArr;
	}

	private static void move(int x, int y, int speed, int dir, int size, Shark[][] arr) {
		// dir: 위 아래 왼쪽 오른쪽
		// N이 4고 x,y = 0 아래로 speed=13이면 한칸 전진.
		// x=1이고 14면 x=1이니 15로 계산
		// x=2고 14면 16으로 계산
		// 주어진 값을 speed에 더해서 이동하면 된다.
		int calSpeed = speed;
		int origindir = dir;
		// 만약 위로 가는거면
		if (dir == 0) {
			int nx = x;
			if (nx == 0)
				dir = 1;
			while (calSpeed-- > 0) {
				nx += dx[dir];
				if (nx == N - 1)
					dir = 0;
				else if (nx == 0)
					dir = 1;
			}
			queue.offerLast(new PosShark(nx,y,speed, dir, size));
//			if (arr[nx][y] == null) {
//				if (arr[x][y].speed == speed && arr[x][y].dir == origindir && arr[x][y].size == size) {
//					arr[x][y] = null;
//				}
//				arr[nx][y] = new Shark(speed, dir, size);
//			} else {
//				if (x >= nx && arr[nx][y].size > size) {
//					arr[x][y] = null;
//				} else {
//					arr[x][y] = null;
//					arr[nx][y] = new Shark(speed, dir, size);
//				}
//			}

		} else if (dir == 1) {
			int nx = x;
			if (nx == N - 1)
				dir = 0;
			while (calSpeed-- > 0) {
				nx += dx[dir];
				if (nx == N - 1)
					dir = 0;
				else if (nx == 0)
					dir = 1;
			}
			queue.offerLast(new PosShark(nx,y,speed, dir, size));
//			if (arr[nx][y] == null) {
//				if (arr[x][y].speed == speed && arr[x][y].dir == origindir && arr[x][y].size == size) {
//					arr[x][y] = null;
//				}
//				arr[nx][y] = new Shark(speed, dir, size);
//			} else {
//				if (x >= nx && arr[nx][y].size > size) {
//					arr[x][y] = null;
//				} else {
//					arr[x][y] = null;
//					arr[nx][y] = new Shark(speed, dir, size);
//				}
//			}
		} else if (dir == 2) {
			int ny = y;
			if (ny == M - 1)
				dir = 3;
			while (calSpeed-- > 0) {
				ny += dy[dir];
				if (ny == M - 1)
					dir = 3;
				else if (ny == 0)
					dir = 2;
			}
			queue.offerLast(new PosShark(x,ny,speed, dir, size));
//			if (arr[x][ny] == null) {
//				if (arr[x][y].speed == speed && arr[x][y].dir == origindir && arr[x][y].size == size) {
//					arr[x][y] = null;
//				}
//				arr[x][ny] = new Shark(speed, dir, size);
//			} else {
//				if (y >= ny && arr[x][ny].size > size) {
//					arr[x][y] = null;
//				} else {
//					arr[x][y] = null;
//					arr[x][ny] = new Shark(speed, dir, size);
//				}
//			}
		} else if (dir == 3) {
			int ny = y;
			if (ny == 0)
				dir = 2;
			while (calSpeed-- > 0) {
				ny += dy[dir];
				if (ny == M - 1)
					dir = 3;
				else if (ny == 0)
					dir = 2;
			}
			queue.offerLast(new PosShark(x,ny,speed, dir, size));
//			if (arr[x][ny] == null) {
//				if (arr[x][y].speed == speed && arr[x][y].dir == origindir && arr[x][y].size == size) {
//					arr[x][y] = null;
//				}
//				arr[x][ny] = new Shark(speed, dir, size);
//			} else {
//				if (y >= ny && arr[x][ny].size > size) {
//					arr[x][y] = null;
//				} else {
//					arr[x][y] = null;
//					arr[x][ny] = new Shark(speed, dir, size);
//				}
//			}
		}
		// speed/(N-1) 했을때 짝수면 dir방향으로 speed % (N-1)로 이동
		// speed/(N-1) 했을때 홀수면 dir방향으로 N-(speed % (N-1))로 이동
	}

	private static void printArr(Shark[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == null) {
					System.out.print(0 + " " + 0 + " " + 0 + " |");
				} else {
					System.out.print(arr[i][j].speed + " " + arr[i][j].dir + " " + arr[i][j].size + " |");
				}
			}
			System.out.println();
		}
	}
}