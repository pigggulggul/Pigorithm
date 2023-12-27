import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static boolean flag;
	static int cheese[][];
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		flag = true;
		cheese[0][0]=2;
		while (flag) {
			flag=false;
			checkAir(0, 0);
			checkCheese();
			deleteCheese();
			T+=1;
			
			if(!flag) {
				System.out.println(T);
				return;
			}
//			printArr();
		}
	}

	private static void deleteCheese() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(cheese[i][j]==2 || cheese[i][j]==3) {
					cheese[i][j]=0;
				}
				else if(cheese[i][j]==1) {
					flag=true;
				}
			}
		}
		
	}

	private static void checkCheese() {
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				if(cheese[i][j]==1) {
					int x = i;
					int y = j;
					int count = 0;
					for (int d = 0; d < 4; d++) {
						int nx = x+dx[d];
						int ny = y+dy[d];
						if(cheese[nx][ny]==2) {
							count++;
						}
						if(count==2) {
							cheese[x][y]=3;
							break;
						}
					}
				}
			}
		}
	}

	private static void checkAir(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];

			if (!checkWall(nx, ny)) {
				continue;
			}
			if(cheese[nx][ny]==0) {
				cheese[nx][ny]=2;
				checkAir(nx,ny);
			}
		}
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(cheese[i][j] + " ");
			}
			System.out.println();
		}
	}
}