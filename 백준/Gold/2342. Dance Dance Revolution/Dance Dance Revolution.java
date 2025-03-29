import java.util.*;
import java.io.*;

/* 
 * (0,0)에서 시작 1~4까지 이동 가능. 
 * 0에서 발을 땔 때 2, 인접한 곳 이동 3, 같은자리 누르기 1, 반대편 이동 4
 * 가장 힘이 적게 들도록 만들 때의 파워는 몇인가.
 *  둘다 0이면 하나 이동. 2
 *  위의 경우 왼좌표 오른좌표 이동에 따른 값이 다름
 *  2 4 3 2 2 2 2 2 2 2 2 2 2
 *223(2를 이동) 3 111111111 (최종위치는 2,4) 2->4->3 할 때 "왼 오 왼" 순으로 발판 이동
 *223(4를 이동) 1 111111111 (최종위치는 2,3) 2->4->3 할 때 "왼 오 오" 순으로 발판 이동
 *
 * ex1)
 * 2 3 1 3 2 2 2
 * 왼2 오2 왼3 오1 왼3 왼1 왼1 13
 * 왼2 오2 오4 오4 왼1 왼1 왼1 15
 * 2 3 4 3 2 2 2
 * 왼2 오2 왼4 오1 왼4 왼1 왼1 15
 * 왼2 오2 오3 오3 왼1 왼1 왼1 13
 * 
 * ex)2
 * 2 3 4 3 3 3 3
 * 왼2 오2 왼4 오1 오1 오1 오1 12
 * 왼2 오2 오3 오3 오1 오1 오1 13
 * 
 * 2를 이동할지 4를 이동할지 어떻게 알지?
 * 총 10만번의 숫자가 주어지는데 분기점이 나올 때 새로운 배열을 만들면 안된다. 무조건 터진다
 *
 * DP로 계산하는 방법은?
 * 숫자쌍을 dp[][]로 두면 [0][0]부터 [4][4]까지 5x5 배열이 존재한다.
 * 숫자쌍 10만에 계산당 모든 dp배열을 돌리면 25 * 10 만 =250만.
 * 안터질거같은데
 * dp[0][0]
 * dp[2][0] = 2 dp[0][2] = 2
 * dp[2][3] = 4 dp[3][2] = 4, dp[3][0]= dp[2][0]+3
 * dp[2][4]= dp[2][3]+3 ,dp[4][3]= dp[2][3]+4, dp[3][4]=dp[3][2]+4, dp[3][4]=dp[3][0]+2
 * 
 * 결과값이 아닌 경우는 모두 작동.
 * 
 * Queue에 값을 계속 추가하다보니 메모리 초과 발생. 해결하기 위해서 입력 숫자만큼 N을 두고 배열로해서 해결하기로.
 * 
 * 기존 방식 -> queue에 담고 바꾼 값들을 넣기
 */
public class Main {

	static int N, M, T;
	static int count = 0;
	static int dp[][][];
	static int min = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

//		System.out.println(st.countTokens());
		// 바로 끝냈을 때
		if (st.countTokens() == 1) {
			System.out.println(0);
			return;
		} else {
			N = st.countTokens() - 1;
			dp = new int[N][5][5];

			// 초기화
			for (int t = 0; t < N; t++) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						dp[t][i][j] = Integer.MAX_VALUE;
					}
				}
			}

			int initialValue = Integer.parseInt(st.nextToken());
			dp[0][initialValue][0] = 2;
			dp[0][0][initialValue] = 2;
			for (int t = 1; t < N; t++) {

				int inputNum = Integer.parseInt(st.nextToken());
				if (inputNum == 0) {
					break;
				}
//				System.out.println("input: " + inputNum);

				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (dp[t - 1][i][j] != Integer.MAX_VALUE) {
							plusNum(t, i, j, inputNum);
						}
					}
				}

//				printDp();
			}
			min = Integer.MAX_VALUE;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (dp[N - 1][i][j] < min) {
						min = dp[N - 1][i][j];
					}
				}
			}

			System.out.println(min);
		}
	}

	// 1
	// 0 2 0 0 0
	// 2 0 0 0 0
	// 0 0 0 0 0
	// 0 0 0 0 0
	// 0 0 0 0 0

	// 2
	// 0 2 0 0 0
	// 2 0 0 0 0
	// 0 0 0 0 0
	// 0 0 0 0 0
	// 0 0 0 0 0

	private static void plusNum(int time, int left, int right, int num) {
		// 왼쪽 바꾸기, 오른쪽 바꾸기
//		System.out.println("left:" + left + " | right:" + right + " | num:" + num);

		// 왼쪽 숫자 바꾸기
		if (num != right) {
			int leftValue = checkValue(left, num);
			dp[time][num][right] = Math.min(dp[time][num][right], dp[time - 1][left][right] + leftValue);

		}
		// 오른쪽 숫자 바꾸기
		if (num != left) {
			int rightValue = checkValue(right, num);
			dp[time][left][num] = Math.min(dp[time][left][num], dp[time - 1][left][right] + rightValue);

		}
	}

	/** int a에서 int b로 바꾸는데 걸리는 비용. */
	private static int checkValue(int prevVal, int changeVal) {
		// 0에서 발을 땔 때 2, 인접한 곳 이동 3, 같은자리 누르기 1, 반대편 이동 4
		if (prevVal == 0) {
			return 2;
		}
		int diff = Math.abs(prevVal - changeVal);
		if (prevVal == changeVal) {
			return 1;
		} else if (diff == 1 || diff == 3) {
			return 3;
		} else if (diff == 2) {
			return 4;
		}
		return 2;
	}

	private static void printDp() {
		System.out.println("최솟값" + min);
		for (int t = 0; t < N; t++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(dp[t][i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

		System.out.println();
	}

}
