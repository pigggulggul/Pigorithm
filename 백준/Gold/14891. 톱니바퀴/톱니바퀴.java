import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// K 회전횟수
	static int K;
	static int magnetNum = 4;
	static int magnetSlice = 8;
	static int score;
	static List<ArrayList<Integer>> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		list = new ArrayList<>();
		for (int i = 0; i < magnetNum; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < magnetNum; i++) {
			String input = br.readLine();
			for (int j = 0; j < magnetSlice; j++) {
				list.get(i).add(input.charAt(j)-'0');
			}
		}
		K = Integer.parseInt(br.readLine());
		score = 0;
		

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int spinGear = Integer.parseInt(st.nextToken());
			int spinDir = Integer.parseInt(st.nextToken());
			spin(spinGear, spinDir);
		}

//			printMagnet();
		if (list.get(0).get(0) == 1) {
			score += 1;
		}
		if (list.get(1).get(0) == 1) {
			score += 2;
		}
		if (list.get(2).get(0) == 1) {
			score += 4;
		}
		if (list.get(3).get(0) == 1) {
			score += 8;
		}
		System.out.println(score);
		// 자석 1칸 회전 시 붙어있는 자석은 다른 극일 때 반대방향으로 1칸 회전
		// 회전시 시계방향 1 반시계방향 -1
		// N극이 0 S극이 1
	}

	// 없으면 혼자 돌아감
	private static void spin(int spinGear, int spinDir) {
		boolean spinflag[] = new boolean[4];
		spinflag[spinGear - 1] = true;
		if (spinGear == 1) {
			// spin하는 플래그 구하기
			for (int i = 0; i < 3; i++) {
				if ((list.get(i).get(2) + list.get(i + 1).get(6)) % 2 == 1) {
					spinflag[i] = true;
					spinflag[i + 1] = true;
				} else {
					break;
				}
			}
			// 2번기어가 1이면 1번기어는 -1로 작동함
			for (int i = 0; i < 4; i++) {
				if (spinflag[i]) {
					if (spinDir == 1) {
						list.get(i).add(0, list.get(i).get(7));
						list.get(i).remove(8);
					} else if (spinDir == -1) {
						list.get(i).add(8, list.get(i).get(0));
						list.get(i).remove(0);
					}
				}
				if (spinDir == 1)
					spinDir = -1;
				else if (spinDir == -1)
					spinDir = 1;
			}
		} else if (spinGear == 2) {
			if ((list.get(0).get(2) + list.get(1).get(6)) % 2 == 1) {
				spinflag[0] = true;
				spinflag[1] = true;
			}
			for (int i = 1; i < 3; i++) {
				if ((list.get(i).get(2) + list.get(i + 1).get(6)) % 2 == 1) {
					spinflag[i] = true;
					spinflag[i + 1] = true;
				} else {
					break;
				}
			}
			// 2번기어가 1이면 1번기어는 -1로 작동함
			if (spinGear == 2 && spinDir == 1)
				spinDir = -1;
			else if (spinGear == 2 && spinDir == -1)
				spinDir = 1;
			for (int i = 0; i < 4; i++) {
				if (spinflag[i]) {
					if (spinDir == 1) {
						list.get(i).add(0, list.get(i).get(7));
						list.get(i).remove(8);
					} else if (spinDir == -1) {
						list.get(i).add(8, list.get(i).get(0));
						list.get(i).remove(0);
					}

				}
				if (spinDir == 1)
					spinDir = -1;
				else if (spinDir == -1)
					spinDir = 1;
			}
		} else if (spinGear == 3) {
			// spin하는 플래그 구하기
			if ((list.get(3).get(6) + list.get(2).get(2)) % 2 == 1) {
				spinflag[3] = true;
				spinflag[2] = true;
			}
			for (int i = 2; i >= 1; i--) {
				if ((list.get(i).get(6) + list.get(i - 1).get(2)) % 2 == 1) {
					spinflag[i] = true;
					spinflag[i - 1] = true;
				} else {
					break;
				}
			}
			// 3번기어가 1이면 4번기어는 -1로 작동함
//			if(spinGear==3 && spinDir==1)spinDir=-1;
//			else if(spinGear==3 && spinDir==-1)spinDir=1;
			for (int i = 0; i < 4; i++) {
				if (spinflag[i]) {
					if (spinDir == 1) {
						list.get(i).add(0, list.get(i).get(7));
						list.get(i).remove(8);
					} else if (spinDir == -1) {
						list.get(i).add(8, list.get(i).get(0));
						list.get(i).remove(0);
					}

				}
				if (spinDir == 1)
					spinDir = -1;
				else if (spinDir == -1)
					spinDir = 1;
			}
		} else if (spinGear == 4) {
			// spin하는 플래그 구하기
			for (int i = 3; i >= 1; i--) {
				if ((list.get(i).get(6) + list.get(i - 1).get(2)) % 2 == 1) {
					spinflag[i] = true;
					spinflag[i - 1] = true;
				} else {
					break;
				}
			}
			for (int i = 3; i >= 0; i--) {
				if (spinflag[i]) {
					if (spinDir == 1) {
						list.get(i).add(0, list.get(i).get(7));
						list.get(i).remove(8);
					} else if (spinDir == -1) {
						list.get(i).add(8, list.get(i).get(0));
						list.get(i).remove(0);
					}

				}
				if (spinDir == 1)
					spinDir = -1;
				else if (spinDir == -1)
					spinDir = 1;
			}
		}

//		printMagnet();
	}

	private static void printMagnet() {
		for (int i = 0; i < magnetNum; i++) {
			for (int j = 0; j < magnetSlice; j++) {
				System.out.print(list.get(i).get(j) + " ");
				;
			}
			System.out.println();
		}
	}

}
