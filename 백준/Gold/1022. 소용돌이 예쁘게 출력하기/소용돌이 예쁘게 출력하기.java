import java.io.*;
import java.util.*;

public class Main {

	static int[][] tonado;
	static int r1, c1, r2, c2;
	static int N, M;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int end = 0;
	static int number;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// r1 c1 r2 c2
		// r1,c1은 왼쪽 위. r2,c2는 오른쪽 아래.
		// 전체 배열 길이는 |r2-r1+1| |c2-c1+1|
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		N = Math.abs(r2 - r1 + 1);
		M = Math.abs(c2 - c1 + 1);
		tonado = new int[N][M];

		tonado();

		print();
	}

	private static void tonado() {
		int x = -r1;
		int y = -c1;
		// 숫자
		number = 1;
		//
		int size = 0;
		int run = 0;
		// 방향 0=우 1=상 2=좌 3=하
		int direction = 3;

		//0자리에 1넣기
		if (check(x, y)) {
			tonado[x][y] = number;
			end++;
		}
		
		while (end < N * M) {
			if (run == size) {
				direction = (direction + 1) % 4;
				if (direction == 0 || direction == 2)
					size++;
				run = 0;
			}
			if (run < size) {
				x += dx[direction];
				y += dy[direction];
				run++;
				number++;
			}
			// -3 -3
			// 2 0
			if (check(x, y)) {
				tonado[x][y] = number;
				end++;
			}
		}

	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < Math.abs(r2 - r1 + 1) && y >= 0 && y < Math.abs(c2 - c1 + 1);
	}

	private static void print() {
		int max = (int) Math.log10(number);
		int len;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				len=max - (int) Math.log10(tonado[i][j]);
				for (int k = 0; k < len; k++) {
					System.out.print(" ");
				}
				System.out.print(tonado[i][j] + " ");
			}
			System.out.println();
		}

	}

}

// 0,0 (1)