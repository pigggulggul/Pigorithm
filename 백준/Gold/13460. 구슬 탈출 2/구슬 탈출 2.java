import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean redFlag = false;
	static boolean blueFlag = false;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] plate = new char[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				plate[i][j] = input.charAt(j);
			}
		}
//		printArr(plate);

		// 성공 조건 : 빨간 구슬을 구멍을 통해 뺴내기. 파란 구슬이 빠지면 실패
		// 상 하 좌 우로 흔들 수 있다. 흔들면 그쪽면에 붙는다.
		// 흔들면 R과 B의 위치가 이동이 된다. 흔들면 흔드는 방향쪽에 가까운 구슬이 먼저 움직인다. RB인데 우측이면 B가 먼저 좌측이면 R이
		// 먼저
		// 한 번 흔들고 흔든 상태를 cnt+1 해서 새롭게 보내고 되돌리기
		// 흔드는 과정에서 O를 지나면 R은 사라지고 redFlag =true;
		// 흔들기 과정이 끝나고 redFlag만 true 일 때 성공. 나머지는 실패
		// 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1 출력

		// 1. 상하좌우 어디부터 흔들지 정하기
		// 2. 정했으면 가까운 것부터 이동시키기 move(x,y,dir)
		// 3. 흔든 후에 clone해서 보내기 solution(cloneArr,cnt+1);

		solution(plate, 1);
		
		if(answer==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
	}

	private static void solution(char[][] arr, int cnt) {

		if(answer < cnt || cnt>10) {
			return;
		}
		// 상 우 하 좌
		for (int d = 0; d < 4; d++) {
			redFlag = false;
			blueFlag = false;
			
			char[][] cloneArr = new char[N][M];
			for (int i = 0; i < N; i++) {
				cloneArr[i] = arr[i].clone();
			}
			if (d == 0) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (cloneArr[i][j] == 'B' || cloneArr[i][j] == 'R') {
							move(i, j, d, cloneArr[i][j], cloneArr);
						}
					}
				}
			} else if (d == 1) {
				for (int j = M-1; j >= 0; j--) {
					for (int i = 0; i < N; i++) {
						if (cloneArr[i][j] == 'B' || cloneArr[i][j] == 'R') {
							move(i, j, d, cloneArr[i][j], cloneArr);
						}
					}
				}
			} else if (d == 2) {
				for (int i = N-1; i >= 0; i--) {
					for (int j = 0; j < M; j++) {
						if (cloneArr[i][j] == 'B' || cloneArr[i][j] == 'R') {
							move(i, j, d, cloneArr[i][j], cloneArr);
						}
					}
				}
			} else {
				for (int j = 0; j < M; j++) {
					for (int i = 0; i < N; i++) {
						if (cloneArr[i][j] == 'B' || cloneArr[i][j] == 'R') {
							move(i, j, d, cloneArr[i][j], cloneArr);
						}
					}
				}
			}
			// 움직인 후 정답확인
			
			if (redFlag == true && blueFlag == false) {
				if (answer > cnt) {
					answer = cnt;
				}
				return;
			} else if (blueFlag == true) {
				continue;
			}
			// 이동했으니 이동 후에 solution넣기

//			System.out.println();
//			printArr(cloneArr);
//			System.out.println();
			solution(cloneArr, cnt + 1);
		}
	}

	// B나 G를 옮기기
	private static void move(int x, int y, int dir, char alpha, char[][] arr) {
		int nx = x;
		int ny = y;
		while (true) {
			
			
			nx += dx[dir];
			ny += dy[dir];

			if (!checkWall(nx, ny))
				continue;
			if (arr[nx][ny] == '.') {
				arr[nx][ny] = alpha;
				arr[nx - dx[dir]][ny - dy[dir]] = '.';
			} else if (arr[nx][ny] == 'O') {
//				System.out.println(alpha);
				arr[nx - dx[dir]][ny - dy[dir]] = '.';
				if (alpha == 'B') {
					blueFlag = true;
				} else if (alpha == 'R') {
					redFlag = true;
				}
//				printArr(arr);
				break;
			} else {
				break;
			}
		}
	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static void printArr(char[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + "");
			}
			System.out.println();
		}
	}
}