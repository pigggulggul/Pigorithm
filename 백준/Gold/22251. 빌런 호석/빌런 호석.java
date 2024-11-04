import java.util.*;
import java.io.*;

public class Main {
	static int N, K, P, X;
	static int result = 0;
	static int[] numPos;
	static int[] limitPos;
	static int[][] numArr = { { 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 1, 0, 0, 1, 0 }, { 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 1 }, { 0, 1, 1, 1, 0, 1, 0 }, { 1, 1, 0, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 0, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N = 최대 100만 사이의 수. 1~999,999
		// K = 최대 6. 6자리 수까지 표시.
		// P = 최대 42개 바꿀 수 있다.
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		numPos = new int[K];
		limitPos = new int[K];

		// 9 1 2 5 일떄 N(1~N층) K(K자리수) P(P번 바꿀 수 있음) X(현재 층)

		// 5 일 때 경우의 수는 3,6,8,9로 4개의 LED를 조작 할 수 있다.
		// 반전시킬 LED를 고를 수 있는 경우의 수

		// P=8이고
		// 5555 일 때
		// 처음이 3으로 정해지면 남은 P=6이고 남은 것들중에서 정하기
		// K자릿수가 N층보다 크면 그 앞은 0으로 대체.
		// ex) K=4고 N=30이면 앞에 0030이 시작이다.
		// 1. 앞에 자리수부터 시작해서 넣을 수 있는 경우의 수를 넣기
		// 1-1. K > N이면 앞에 0으로 간주하기
		// 1-2. 그게 아니면 써있는 수로 시작
		// 2. 넣을 수 있는 숫자들을 뽑고 다음 자릿수의 수와 남은 P를 다시 넣어서 돌리기.
		// 2-1. 이 때 바꾸지 않는 수도 포함.
		// 3. 자릿수를 모두 바꿨거나 남은 P가 없으면 answer++;
		// 3-1. 0030 일 때 P=1이면 8030 0830 0030 0038 가 나오 수 있다.
		// 3-2. 0131 일 때 P=2이면 8131 1831 1131 1138 가 나오 수 있다. 1은 1~2개로 바꿀 수 있는게 없으므로
		// 무조건 자기자신인 경우의 수는 1개가 나올테니 나온 값에서 -1.

		// 1.
		// 40 4 2 30
		String limitNum = N + "";
		String startNum = X + "";
		if (startNum.length() < K) {
			String inputNum = "";
			for (int plus = 0; plus < K - startNum.length(); plus++) {
				inputNum += "0";
			}
			startNum = inputNum + startNum;

			String inputLimit = "";
			for (int plus = 0; plus < K - limitNum.length(); plus++) {
				inputLimit += "0";
			}
			limitNum = inputLimit + limitNum;
		}
		for (int i = 0; i < K; i++) {
			numPos[i] = startNum.charAt(i) - '0';
			limitPos[i] = limitNum.charAt(i) - '0';
		}
//		System.out.println(limitNum);
//		System.out.println(startNum);
		startChange(0, P, 0, 0);
		System.out.println(result);
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}

	}

	private static void startChange(int currentK, int p, int makeNum, int makeLimit) {
		if (currentK == K) {
			if(makeNum==0 || makeNum/10 == X || makeNum/10 > makeLimit) return;
//			System.out.println(makeNum/10);
			result++;
			return;
		} else {
			// limit 검사를 해야한다. 숫자를 초과하면 안된다.
			int num = numPos[currentK];
			int limit = limitPos[currentK];
			makeLimit += limit;
			// 현재 숫자에서
			for (int i = 0; i <= 9; i++) {
				if (makeNum+i > N)
					break;
				if (num == i) {
					startChange(currentK + 1, p, (makeNum+i) * 10, makeLimit * 10);
					continue;
				}
				int changeP = isChange(num, i, p);
				if (changeP > -1) {
//					if (changeP == 0) {
//						result++;
//						System.out.println((makeNum+i));
//						continue;
//					}
					startChange(currentK + 1, changeP, (makeNum+i) * 10, makeLimit * 10);
				}
			}
		}
	}

	private static int isChange(int num, int goal, int p) {
		// { 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 1, 0, 0, 1, 0 }
		// num에서 goal로 갈 때 필요 한 p갯수
		int[] compA = numArr[num];
		int[] compB = numArr[goal];
		int point = 0;
		for (int i = 0; i < 7; i++) {
			if (compA[i] != compB[i]) {
				point++;
			}
		}
		if (p - point > -1) {
			return p - point;
		}
		return -1;
	}
}