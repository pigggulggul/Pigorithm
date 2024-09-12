import java.util.*;
import java.io.*;

class Fireball {
	int x;
	int y;
	int mass;
	int speed;
	int dir;
	int count;

	public Fireball(int x, int y, int mass, int speed, int dir) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.speed = speed;
		this.dir = dir;
		this.count = 1;
	}

	public Fireball(int x, int y, int mass, int speed, int dir, int count) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.speed = speed;
		this.dir = dir;
		this.count = count;
	}
}

class FireballPlate {
	int mass;
	int speed;
	int dir;
	int count;
	boolean compare;

	public FireballPlate(int mass, int speed, int dir, int count) {
		this.mass = mass;
		this.speed = speed;
		this.dir = dir;
		this.count = count;
		this.compare = false;
	}

	public FireballPlate(int mass, int speed, int dir, int count, boolean compare) {
		this.mass = mass;
		this.speed = speed;
		this.dir = dir;
		this.count = count;
		this.compare = compare;
	}
}

public class Main {
	static int N, M, T;
	static Deque<Fireball> fireballQueue = new LinkedList<>();
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N*N 격자에 파이어볼 M개.T번의 이동 명령 후 남아있는 파이어볼
		// x,y,m,s,d (x좌표,y좌표,질량,속력,방향)
		// 매 턴 마다 방향으로 s칸 이동.
		// 이동이 끝난 뒤 , 2개 이상의 파이어볼이 있는 칸은 합쳐진다
		// 합쳐진 질량의 합/5, 합쳐진 속력의 합/갯수, 방향이모두 홀수or짝수면 +모양 아니면 x모양
		// 결국 한 칸에 4개가 생기게 된다.

		// 순서 1. 파이어볼을 queue에 담는다.
		// 순서 2. 매턴마다 파이어볼을 이동한다. 파이어볼이 겹치는 곳이 있는지 검사한다.
		// 순서 3. 같은 자리에 있는 파이어볼이 있으면 합치고 분리한다.
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int result = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Fireball fb = new Fireball(x, y, m, s, d);
			fireballQueue.addLast(fb);
		}

		while (T-- > 0) {
			int size = fireballQueue.size();
			// 이동
//			System.out.println("이동 전 ");
//			printFireball();
			while (size-- > 0) {
				Fireball fb = fireballQueue.pollFirst();

				int x = fb.x;
				int y = fb.y;
				int m = fb.mass;
				int s = fb.speed;
				int d = fb.dir;
//				System.out.println("이동 전 위치 : " + x + " " + y);
				for (int i = 0; i < s; i++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (!checkWall(nx, ny)) {
						if (nx < 0) {
							nx = N - 1;
						} else if (nx >= N) {
							nx = 0;
						}
						if (ny < 0) {
							ny = N - 1;
						} else if (ny >= N) {
							ny = 0;
						}
					}
					x = nx;
					y = ny;
//					System.out.println(x + " " + y);
				}
//				System.out.println("이동 후 위치 : " + x + " " + y);
				fireballQueue.addLast(new Fireball(x, y, m, s, d));
				// 파이어볼 이동
			}
//			System.out.println("이동 후 ");
//			printFireball();

			checkFireball();
			conbineFireball();
//			System.out.println();
//			printFireball();
		}
		result = checkPoint();
		System.out.println(result);
	}

	private static void checkFireball() {
		// 파이어볼이 겹치는 곳이 있는지 검사.
		// queue에는 현재 (1,3,5,2,2) (1,3,7,1,6)이 있다. 2차원 배열 놓고 그 안에 m,s,d를 저장한다.m과s는 합치고
		// d는 값이 다 홀수나 짞수면 0아니면 1
		int size = fireballQueue.size();
		FireballPlate[][] arr = new FireballPlate[N][N];
		// 합치기
		while (size-- > 0) {
			Fireball fb = fireballQueue.pollFirst();

			int x = fb.x;
			int y = fb.y;
			int m = fb.mass;
			int s = fb.speed;
			int d = fb.dir;
			int c = fb.count;
			if (arr[x][y] == null)
				arr[x][y] = new FireballPlate(m, s, d, 1);
			else {
				int nm = arr[x][y].mass;
				int ns = arr[x][y].speed;
				int nd = arr[x][y].dir;
				int cnt = arr[x][y].count;

				// 홀수면 1 짝수면 0 둘이 값이 다르면 2 << 이렇게하면 안된다.

				// nd가 만약에 2의 방향을 가졌다면??
				if (arr[x][y].compare == true) {
					arr[x][y] = new FireballPlate(m + nm, s + ns, nd, cnt + c, true);
				} else {
					int aDir = d % 2;
					int bDir = nd % 2;
					if (aDir == bDir && aDir == 0) {
						arr[x][y] = new FireballPlate(m + nm, s + ns, 0, cnt + c);
					} else if (aDir == bDir && aDir == 1) {
						arr[x][y] = new FireballPlate(m + nm, s + ns, 1, cnt + c);
					} else {
						arr[x][y] = new FireballPlate(m + nm, s + ns, 2, cnt + c, true);
					}
				}

//				System.out.println("합쳐졌습니다" + arr[x][y].mass + " " + arr[x][y].speed + " " + arr[x][y].dir);

			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != null) {
					fireballQueue.addLast(
							new Fireball(i, j, arr[i][j].mass, arr[i][j].speed, arr[i][j].dir, arr[i][j].count));
				}
			}
		}
//		System.out.println(fireballQueue.size());
	}

	private static void conbineFireball() {
		int size = fireballQueue.size();

		// 이동
		while (size-- > 0) {
			Fireball fb = fireballQueue.pollFirst();

			int x = fb.x;
			int y = fb.y;
			int m = fb.mass;
			int s = fb.speed;
			int d = fb.dir;
			int c = fb.count;
			if (m == 0)
				continue;

			if (c > 1) {
				int nm = m / 5;
				int ns = s / c;
				if (nm == 0)
					continue;
				// 짝수 : 0,2,4,6
				if (d == 0 || d == 1) {
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 0, 1));
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 2, 1));
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 4, 1));
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 6, 1));
				} else {
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 1, 1));
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 3, 1));
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 5, 1));
					fireballQueue.addLast(new Fireball(x, y, nm, ns, 7, 1));
				}
			} else {
				fireballQueue.addLast(new Fireball(x, y, m, s, d, c));
			}

		}
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static int checkPoint() {
		int point = 0;
		while (!fireballQueue.isEmpty()) {
			Fireball fb = fireballQueue.pollFirst();
			point += fb.mass;

		}
		return point;
	}

	private static void printFireball() {
		int size = fireballQueue.size();
		while (size-- > 0) {
			Fireball fb = fireballQueue.pollFirst();

			int x = fb.x;
			int y = fb.y;
			int m = fb.mass;
			int s = fb.speed;
			int d = fb.dir;
			int c = fb.count;

			System.out.println(
					"x : " + x + " | y : " + y + " | m : " + m + " | s : " + s + " | d : " + d + " | c : " + c);
			fireballQueue.addLast(new Fireball(x, y, m, s, d, c));
		}
	}
}