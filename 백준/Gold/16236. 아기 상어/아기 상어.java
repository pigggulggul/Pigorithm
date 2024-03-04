import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int[][] plate;
	static int sharkSize = 2;
	static int sharkPosX;
	static int sharkPosY;
	static int sharkEat=0;
	static int behavior = 0;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] isvisited;
	static int[][] minimumDistance;
	static int minimumX;
	static int minimumY;
	static int minimumSize;
	static boolean moveFlag = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		plate = new int[N][N];
		// 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 9) {
					sharkPosX = i;
					sharkPosY = j;
				}
				plate[i][j] = num;
			}
		}

		while(searchFish()) {
			BFS();
			if(!moveFlag) {
				break;
			}
			checkMinimum();
			if(minimumSize==400) {
				break;
			}
			moveShark();
//			printArr();
		}
		System.out.println(behavior);
		
	}

	private static void moveShark() {
		//기존의 샤크의 위치를 0으로
		plate[sharkPosX][sharkPosY] = 0;
		
		//새로운 샤크 위치 등록
		sharkPosX= minimumX;
		sharkPosY= minimumY;
		plate[sharkPosX][sharkPosY] = 9;
		//행동 +1
		behavior+=minimumSize;
		//샤크 사이즈 변화
		sharkEat++;
		if(sharkEat == sharkSize) {
			sharkSize++;
			sharkEat=0;
		}
	}

	/** 가장 가까운 먹이 찾기 */
	private static void checkMinimum() {
		minimumX = 0;
		minimumY = 0;
		minimumSize = 400;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plate[i][j] > 0 && plate[i][j] < sharkSize && plate[i][j] < 7 && minimumDistance[i][j]>0) {
					if (minimumSize > minimumDistance[i][j]) {
						minimumX = i;
						minimumY = j;
						minimumSize = minimumDistance[i][j];
					} else if (minimumSize == minimumDistance[i][j]) {
						if (minimumX > i) {
							minimumX = i;
							minimumY = j;
							minimumSize = minimumDistance[i][j];
						} else if (minimumX == i) {
							if (minimumY > j) {
								minimumX = i;
								minimumY = j;
								minimumSize = minimumDistance[i][j];
							}
						}
					}
				}
			}
		}
	}

	private static void BFS() {
		Deque<int[]> deque = new ArrayDeque<>();
		isvisited = new boolean[N][N];
		minimumDistance = new int[N][N];
		int searchNum=0;

		deque.offerLast(new int[] { sharkPosX, sharkPosY });
		isvisited[sharkPosX][sharkPosY] = true;
		while (!deque.isEmpty()) {
			int[] info = deque.poll();
			int x = info[0];
			int y = info[1];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (!checkWall(nx, ny)) {
					continue;
				}
				if (isvisited[nx][ny]) {
					continue;
				}
				if (plate[nx][ny] > sharkSize) {
					isvisited[nx][ny] = true;
					continue;
				}
				moveFlag=true;
				isvisited[nx][ny] = true;
				minimumDistance[nx][ny] = minimumDistance[x][y] + 1;
				if(searchNum>0 && searchNum < minimumDistance[nx][ny]) {
					return;
				}
				if(plate[nx][ny] > 0 && plate[nx][ny] < sharkSize && plate[nx][ny] < 7) {
					searchNum=minimumDistance[nx][ny];
				}
				deque.offerLast(new int[] { nx, ny });
			}
		}
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static boolean searchFish() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plate[i][j]>0 && plate[i][j] < sharkSize && plate[i][j]<7) {
					return true;
				}
			}
		}
		return false;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(minimumDistance[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

//N x N 크기의 공간에 물고기M 아기상어1 한 칸 당 물고기는 최대 1마리
//아기상어 크기는 2
//0 빈칸 1~6 물고기의 크기
//9 아기상어의 위치
//아기상어의 처음 크기는 2.

// 조건
// 먹을 물고기가 없으면 엄마상어를 부름(끝나는 조건)
// 먹을수 있는 물고기가 1마리 이상이면 먹으러 간다 (가장 가까운 물고기를, 거리가 같으면 가장 위,왼쪽에 물고기 우선)
// 1칸당 숫자 1칸이 증가함

// 1. 먹을 수 있는 물고기가 있는지 탐색 (아기상어의 크기보다 작아야함. 만약에 먹을 수 있는 물고기가 없으면 끝.)
// 2. 만약에 있으면 최소 거리 계산. BFS를 이용하여 최소거리 구하기 이때 같은 거리가 있으면 y좌표가 작은게 우선이고,y도 같으면 x가 작은게 우선
// 3. 상어는 그 위치로 이동하고 1번부터 반복(먹을때마다 자기 크기만큼 먹으면 커짐.)