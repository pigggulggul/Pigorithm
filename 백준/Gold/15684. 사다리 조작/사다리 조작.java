import java.util.*;
import java.io.*;

class Ladder {
	int x;
	int y;

	public Ladder(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, M, H;
	static ArrayList<Ladder> ladder = new ArrayList<>();
	static int ladderCount=1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N개의 세로줄과 M개의 가로줄.세로선에 놓을 수 있는 최대 위치의 개수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		int[][] arr = new int[H][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			arr[a][b] = ladderCount;
			arr[a][b + 1] = ladderCount;
			ladderCount++;
		}
		checkLadder(arr);

// 		check 제대로 됐는지 확인
//		for (int i = 0; i < ladder.size(); i++) {
//			System.out.print(ladder.get(i).x +" "+ladder.get(i).y + " | ");
//		}
//		System.out.println();
		if (run(arr)) {
			System.out.println(0);
			return;
		} else {
			for (int count = 1; count < 4; count++) {
				// count가 1개면 1개씩 넣고
				// count가 2개면 2개씩 넣는등.
				if (startPutLadder(arr, count)) {
					System.out.println(count);
					return;
				}
			}
		}
		System.out.println(-1);
//		printArr(arr);
		// M개의 선이 주어진다. a b. arr[a][b]위치~arr[a][b+1]까지 선이 그어진다.

		// 사다리를 타고 내려오는데 i에서 출발해서 i에 도착하도록 사다리를 추가로 놔야한다.
		// 사다리를 놓을 수 있는 모든 공간을 표시한다.
		// 3개 이상 놔둬야하니까 1개 2개 3개 시험한다.
		// 사다리가 0개,1개 놔서 성공하는경우,2개,3개

		// run() i번에서 시작해서 i번으로 떨어지는지 확인
		// checkPlate() 사다리를 놓을 수 있ㅇ는 공간
		// true,false로 구분하기. 사다리 놔둔곳은 true로 표시. 좌우만 영향받음

//		5 5 6
//		1 1
//		3 2
//		2 3
//		5 1
//		5 4
	}

	private static void printArr(int[][] arr) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void checkLadder(int[][] arr) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (arr[i][j] == 0 && arr[i][j + 1] == 0) {
					ladder.add(new Ladder(i, j));
				}
			}
		}
	}

	private static boolean run(int[][] arr) {
//		printArr(arr);
//		System.out.println();
		for (int i = 0; i < N; i++) {
//			System.out.println("시작 " + i);
			int start = i;
			int count = 0;
			while (count != H) {
				if (arr[count][start] != 0) {
					if (start == 0)
						start++;
					else if (start == N - 1)
						start--;
					else if (arr[count][start - 1] == arr[count][start])
						start--;
					else
						start++;
				}
				count++;
			}
			if (i != start)
				return false;
		}
		return true;
	}

	private static boolean startPutLadder(int[][] arr, int count) {
		int[] ladderState = new int[ladder.size()];

		// count가 1개면 1개씩 모든 경우의 수를 넣고
		// count가 2개면 2개씩 모든 경우의 수를 넣는 등.
		if (count == 1) {
			int cnt = ladderCount+1;
			for (int i = 0; i < ladder.size(); i++) {
				ladderState[i] = cnt;
				int x = ladder.get(i).x;
				int y = ladder.get(i).y;
//				System.out.println(x + " " + y);
				arr[x][y] = cnt;
				arr[x][y + 1] = cnt;
				if (run(arr)) {
					return true;
				}
				ladderState[i] = 0;
				arr[x][y] = 0;
				arr[x][y + 1] = 0;
			}
		} else if (count == 2) {
			int cnt = ladderCount+1;
			for (int i = 0; i < ladder.size(); i++) {
				ladderState[i] = cnt;
				int x = ladder.get(i).x;
				int y = ladder.get(i).y;
				arr[x][y] = cnt;
				arr[x][y + 1] = cnt;
				for (int j = i + 1; j < ladder.size(); j++) {
					ladderState[j] = cnt + 1;
					int x2 = ladder.get(j).x;
					int y2 = ladder.get(j).y;
					if (arr[x2][y2] != 0 || arr[x2][y2 + 1] != 0)
						continue;
//					System.out.println(x + " " + y+" | "+x2+" "+y2);
					arr[x2][y2] = cnt + 1;
					arr[x2][y2 + 1] = cnt + 1;
					if (run(arr)) {
						return true;
					}
					ladderState[j] = 0;
					arr[x2][y2] = 0;
					arr[x2][y2 + 1] = 0;
				}
				ladderState[i] = 0;
				arr[x][y] = 0;
				arr[x][y + 1] = 0;
			}
		} else if (count == 3) {
			int cnt = ladderCount+1;
			for (int i = 0; i < ladder.size(); i++) {
				ladderState[i] = cnt;
				int x = ladder.get(i).x;
				int y = ladder.get(i).y;
				arr[x][y] = cnt;
				arr[x][y + 1] = cnt;
				for (int j = i + 1; j < ladder.size(); j++) {
					ladderState[j] = cnt + 1;
					int x2 = ladder.get(j).x;
					int y2 = ladder.get(j).y;
					if (arr[x2][y2] != 0 || arr[x2][y2 + 1] != 0)
						continue;
					arr[x2][y2] = cnt + 1;
					arr[x2][y2 + 1] = cnt + 1;
					for (int k = j + 1; k < ladder.size(); k++) {
						ladderState[k] = cnt + 2;
						int x3 = ladder.get(k).x;
						int y3 = ladder.get(k).y;
						if (arr[x3][y3] != 0 || arr[x3][y3 + 1] != 0)
							continue;
//						System.out.println(x + " " + y+" | "+x2+" "+y2+" | "+x3+" "+y3);
						arr[x3][y3] = cnt + 2;
						arr[x3][y3 + 1] = cnt + 2;
						if (run(arr)) {
							return true;
						}
						ladderState[k] = 0;
						arr[x3][y3] = 0;
						arr[x3][y3 + 1] = 0;
					}
					ladderState[j] = 0;
					arr[x2][y2] = 0;
					arr[x2][y2 + 1] = 0;
				}
				ladderState[i] = 0;
				arr[x][y] = 0;
				arr[x][y + 1] = 0;
			}
		}
		return false;
	}

}