import java.util.*;
import java.io.*;

public class Main {
	static int N, M, posX, posY, count;
	static int[][] arr;
	static int[] dice = { 0, 0, 0, 0, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		posX = Integer.parseInt(st.nextToken());
		posY = Integer.parseInt(st.nextToken());
		count = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int dir;
		for (int i = 0; i < count; i++) {
			dir = Integer.parseInt(st.nextToken());
			int nx = posX + dx[dir - 1];
			int ny = posY + dy[dir - 1];

			if (!checkWall(nx, ny))
				continue;
			moveDice(dir);
			posX=nx;
			posY=ny;
			changeNumber();	
			System.out.println(dice[5]);
			
		}

	}

	private static void changeNumber() {
		if(arr[posX][posY]==0) {
			arr[posX][posY]=dice[0];
		}else {
			dice[0]=arr[posX][posY];
			arr[posX][posY]=0;
		}
	}

	private static void moveDice(int dir) {
		int dice1 = dice[0];
		int dice2 = dice[1];
		int dice3 = dice[2];
		int dice4 = dice[3];
		int dice5 = dice[4];
		int dice6 = dice[5];
		// 동
		if (dir == 1) {
			dice[2] = dice1;
			dice[0] = dice4;
			dice[3] = dice6;
			dice[5] = dice3;
		}
		// 서
		else if (dir == 2) {
			dice[2] = dice6;
			dice[5] = dice4;
			dice[3] = dice1;
			dice[0] = dice3;
		}
		// 북
		else if (dir == 3) {
			dice[1] = dice1;
			dice[5] = dice2;
			dice[4] = dice6;
			dice[0] = dice5;
		}
		// 남
		else if (dir == 4) {
			dice[4] = dice1;
			dice[5] = dice5;
			dice[1] = dice6;
			dice[0] = dice2;
		}
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print("");
			}
			System.out.println();
		}
	}
}

// N M posX, posY, count
// 방향키에 따라서 동서남북으로 움직이고 (posX,posY 값이 변함)
// 6면에 0을 가진 주사위의 현재 위치도 바뀜
// {0,0,0,0,0,0 } 1 2 3 4 5 6
// 동 = 3번이 1번이 되고 1번이 4번, 4번이 6번, 6번이 3번이 됨
// 서 = 3번이 6번이 되고 6번ㅇ이 4번, 4번이 1번, 1번이 3번이 됨
// 남 = 5번이 1번이 되고 6번이 5번, 2번이 6번, 1번이 2번이 됨
// 북 = 2번이 1번, 6번이 2번,5번이 6번, 1번이 5번
// 바닥의 값이 0이면 주사위의 값을 바닥에 붙이고 바닥의 값이 숫자면 주사위에 붙는다