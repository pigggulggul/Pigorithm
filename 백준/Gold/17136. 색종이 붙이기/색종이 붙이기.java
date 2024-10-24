import java.util.*;
import java.io.*;

public class Main {

	static int N, M, T;
	static int[][] paper = new int[10][10];
	static boolean[][] isVisited = new boolean[10][10];
	static boolean[][] originVisited = new boolean[10][10];
	static int[] paperCount = { 5, 5, 5, 5, 5 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if (paper[i][j] == 1) {
					originVisited[i][j] = true;
				}
			}
		}

		// 그리디로 하면 안됨. 그 위치에 적절한 위치의 색종이가 들어가야함.
		// 그려면? 그 위치에 5,4,3,2,1 어떤게 들어가는게 맞을지 하나싞 넣어서 진행하면됨

		// 만약 1이면
		checkPaper(0, 0, isVisited, 0, paperCount);
		if(result==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
	}

	private static void printArr(boolean[][] visited) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (visited[i][j]) {
					System.out.print(1 + " ");
				} else {
					System.out.print(0 + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// x,y좌표에 대해서 5~1까지 색종이 대입.
	private static void checkPaper(int x, int y, boolean[][] visited, int cnt, int[] paperCount) {
		// 기저조건 : 모든 색종이가 놔졌을 때
		if (cnt > result || !checkCount(paperCount)) {
			return;
		} else if (checkFinish(visited)) {
			if (result > cnt) {
				result = cnt;
//				System.out.println(result);
			}
			return;
		} else {
			for (int a = x; a < 10; a++) {
				for (int b = 0; b < 10; b++) {
					if (paper[a][b] == 1 && !visited[a][b]) {
//						System.out.println(a + " " + b);
						for (int num = 5; num > 0; num--) {
							if (!checkWall(a + num - 1, b + num - 1)) {
								continue;
							}
							if (!checkRange(a, b, num, visited)) {
								continue;
							}

							boolean[][] mirrorVisited = new boolean[10][10];
							for (int i = 0; i < 10; i++) {
								mirrorVisited[i] = visited[i].clone();
							}
							// 만약 범위에 맞으면
							for (int i = a; i < a + num; i++) {
								for (int j = b; j < b + num; j++) {
									mirrorVisited[i][j] = true;
								}
							}
							paperCount[num - 1] -= 1;
//							if (num == 3 && paperCount[2] < 4 ) {
//								System.out.println(a+" "+b);
//								System.out.println(Arrays.toString(paperCount));
//								printArr(mirrorVisited);
//							}
//							printArr(mirrorVisited);
							checkPaper(a, b, mirrorVisited, cnt + 1, paperCount);
							paperCount[num - 1] += 1;
							// 5개 제한 지켜야함
						}
						return;
					}
				}
			}
		}

	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < 10 && y >= 0 && y < 10;
	}

	private static boolean checkRange(int x, int y, int num, boolean[][] visited) {
		for (int i = x; i < x + num; i++) {
			for (int j = y; j < y + num; j++) {
				if (paper[i][j] != 1 || visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkFinish(boolean[][] visit) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (visit[i][j] != originVisited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkCount(int[] count) {
		for (int i = 0; i < 5; i++) {
			if (count[i] < 0) {
				return false;
			}
		}
		return true;
	}

}