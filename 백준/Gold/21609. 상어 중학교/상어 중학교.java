import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[][] pointArr;
	static boolean[][] isvisited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int findzero, findX, findY;
	static int count;
	static int max = -1;
	static int maxZero = -1;
	static int point = 0;
	static int maxX, maxY;
	static int maxNum;
	static int[] maxPosition;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// NxN배열 M=3이면 3가지 색상
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int a = 5;
		// 가장 많은 그룹 찾기
		while (true) {
			pointArr = new int[N][N];
			int prevNum = 0;
			count = 0;
			maxPosition = new int[2];
			max = 0;
			maxX = 0;
			maxY = 0;
			maxNum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > 0 && pointArr[i][j] == 0) {
						isvisited = new boolean[N][N];
						count = 0;
						findzero = 0;
						findX = i;
						findY = j;
						prevNum = arr[i][j];
						findGroup(i, j, prevNum);
						if (count > max) {
							max = count;
							maxNum = prevNum;
							maxPosition[0] = i;
							maxPosition[1] = j;
							maxZero = findzero;
							maxX = findX;
							maxY = findY;
						} else if (count == max) {
							if (maxZero < findzero) {
//								System.out.println("i :" + i + " | j : " + j + " | 변경1 | maxZero,findzero :" + maxZero+" "+findzero);
								maxPosition[0] = i;
								maxPosition[1] = j;
								maxZero = findzero;
								maxNum = prevNum;
								maxX=findX;
								maxY=findY;
							} else if (maxZero == findzero && maxX < findX) {
//								System.out.println("i :" + i + " | j : " + j + " | 변경2 | maxZero,findzero :"+maxZero+" "+findzero);
								maxPosition[0] = i;
								maxPosition[1] = j;
								maxX=findX;
								maxY=findY;
								maxNum = prevNum;
							} else if (maxZero == findzero && maxX == findX && maxY < findY) {
//								System.out.println("i :" + i + " | j : " + j + " | 변경3");
								maxPosition[0] = i;
								maxPosition[1] = j;
								maxX=findX;
								maxY=findY;
								maxNum = prevNum;
							}
						}
						addPointGroup(i, j);
					}
				}
			}
			if (max < 2) {
				System.out.println(point);
				return;
			}
//			System.out.println("기준 i,j | x,y"+maxPosition[0]+" "+maxPosition[1]+" "+maxX+" "+maxY);
			// 그룹의 갯수의 제곱만큼 점수 얻음
			getPoint();

//			System.out.println("점수 ++" + point);

			// 블록그룹 제거
//			System.out.println("------------point---------");
//			printPoint();

//			System.out.println("삭제 - x : "+maxPosition[0] + "| y : "+maxPosition[1]);
			deleteBlock(maxPosition[0], maxPosition[1]);

//			printPoint();
//			System.out.println("--------------------------");

//			System.out.println("--------중력적용전");
//			printArr();
			// 중력적용
			gravity();
//			System.out.println("--------중력적용후");
//			printArr();
			// 배열 반시계 90도 돌리기
			arr = rotate90();

//			System.out.println("--------돌린 후");
//			printArr();
			// 중력적용
			gravity();
//			printArr();
		}
	}

	private static void deleteBlock(int x, int y) {
		arr[x][y] = -2;
		pointArr[x][y] = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!check(nx, ny))
				continue;
			if (pointArr[nx][ny] != max)
				continue;
			if (arr[nx][ny] == maxNum || arr[nx][ny] == 0) {
				deleteBlock(nx, ny);
			}

		}
	}

	private static void addPointGroup(int x, int y) {
		if (pointArr[x][y] < count) {
			pointArr[x][y] = count;
		}
		isvisited[x][y] = false;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!check(nx, ny))
				continue;
			if (!isvisited[nx][ny])
				continue;
			addPointGroup(nx, ny);
		}
	}

	private static void findGroup(int x, int y, int prevNum) {
		count++;
		if (arr[x][y] == 0)
			findzero++;
		if (findX > x && arr[x][y] != 0) {
			findX = x;
			findY = y;
		} else if (findX == x && findY > y && arr[x][y] != 0) {
			findX = x;
			findY = y;
		}
		isvisited[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!check(nx, ny))
				continue;
			if (isvisited[nx][ny])
				continue;
			if (arr[nx][ny] == prevNum || arr[nx][ny] == 0) {
				findGroup(nx, ny, prevNum);
			}
		}
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static int[][] rotate90() {

		int[][] arr_copy = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr_copy[i][j] = arr[j][N - i - 1];
			}
		}
		return arr_copy;
	}

	private static void gravity() {
		for (int i = 0; i < N; i++) {
			int blank = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (arr[j][i] == -2) {
					if (blank == 0) {
						blank = j;
					}
				} else if (arr[j][i] >= 0) {
					if (blank == 0)
						continue;
					arr[blank][i] = arr[j][i];
					arr[j][i] = -2;
					blank--;
				} else if (arr[j][i] == -1) {
					blank = 0;
				}
			}
		}
	}

	private static void changeNum(int blank, int num, int start, int i) {
		if (blank > num) {
			while (start != num) {
				arr[blank - 1][i] = arr[num - 1][i];
				arr[num - 1][i] = -2;
				num--;
				blank--;
			}
		}
	}

	private static void getPoint() {
		point += max * max;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == -2) {
					System.out.print("x ");
				} else {
					System.out.print(arr[i][j] + " ");
				}

			}
			System.out.println();
		}
		System.out.println();
	}

	private static void printPoint() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(pointArr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}