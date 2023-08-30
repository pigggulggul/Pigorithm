
import java.util.*;
import java.io.*;

public class Solution {

	static int T;
	static int num, M, N;
	static int permArr[];
	static int arr[][];
	static int plate[][];
	static int count;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			arr = new int[N][M];
			permArr = new int[num];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Perm(0);
			System.out.println("#"+testcase+" "+min);
		}
	}

	private static void Perm(int cnt) {
		if (cnt == num) {
			plate = new int[N][M];
			for (int i = 0; i < N; i++) {
				plate[i] = arr[i].clone();
			}
			min = Math.min(min, Tetris(permArr));
			return;
		}
		for (int i = 0; i < M; i++) {
			permArr[cnt] = i;
			Perm(cnt + 1);
		}
	}

	private static int Tetris(int[] perm) {
		count = 0;
		for (int pos = 0; pos < perm.length; pos++) {
			int getSero = findSero(perm[pos]);

			if (plate[getSero][perm[pos]] == 1) {
				plate[getSero][perm[pos]] = 0;
			} else {
				Bomb(getSero, perm[pos], plate[getSero][perm[pos]]);
				Sort();
			}

		}
		checkRemain();
		return count;
	}

	private static void checkRemain() {
		for (int i = N - 1; i >= 0; i--) {
			int check = 0;
			for (int j = 0; j < M; j++) {
				if (plate[i][j] != 0) {
					count++;
					check++;
				}
			}
			if (check == 0) {
				return;
			}
		}
	}

	private static void Sort() {
		for (int j = 0; j < M; j++) {
			boolean flag = false;
			int start = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (plate[i][j] == 0 && !flag) {
					start = i;
					flag = true;
				}
				if (plate[i][j] != 0 && flag) {
					plate[start][j] = plate[i][j];
					plate[i][j] = 0;
					start--;
				}
			}
		}
	}
	private static void Bomb(int x, int y, int bombSize) {
		int rightSize;
		int bottomSize;
		int leftSize;
		int topSize;
		if ((y + bombSize - 1) >= M) {
			rightSize = M - 1;
		} else {
			rightSize = y + bombSize - 1;
		}
		if ((x + bombSize - 1) >= N) {
			bottomSize = N - 1;
		} else {
			bottomSize = x + bombSize - 1;
		}
		if ((y - bombSize + 1) < 0) {
			leftSize = 0;
		} else {
			leftSize = y - bombSize + 1;
		}
		if ((x - bombSize + 1) < 0) {
			topSize = 0;
		} else {
			topSize = x - bombSize + 1;
		}

		for (int i = topSize; i < x; i++) {
			if (plate[i][y] == 1) {
				plate[i][y] = 0;
			} else if (plate[i][y] != 0) {
				int newBoom = plate[i][y];
				plate[i][y] = 0;
				Bomb(i, y, newBoom);
			}

		}
		for (int i = rightSize; i > y; i--) {
			if (plate[x][i] == 1) {
				plate[x][i] = 0;
			} else if (plate[x][i] != 0) {
				int newBoom = plate[x][i];
				plate[x][i] = 0;
				Bomb(x, i, newBoom);
			}
		}
		for (int i = leftSize; i < y; i++) {
			if (plate[x][i] == 1) {
				plate[x][i] = 0;
			} else if (plate[x][i] != 0) {
				int newBoom = plate[x][i];
				plate[x][i] = 0;
				Bomb(x, i, newBoom);
			}
		}
		for (int i = bottomSize; i > x; i--) {
			if (plate[i][y] == 1) {
				plate[i][y] = 0;
			} else if (plate[i][y] != 0) {
				int newBoom = plate[i][y];
				plate[i][y] = 0;
				Bomb(i, y, newBoom);
			}

		}
		if (plate[x][y] != 0) {
			plate[x][y] = 0;
		}
	}
	private static int findSero(int pos) {
		for (int i = 0; i < N; i++) {
			if (plate[i][pos] != 0) {
				return i;
			}
		}
		return 0;
	}
}
