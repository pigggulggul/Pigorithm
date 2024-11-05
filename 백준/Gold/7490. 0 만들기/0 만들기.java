import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			// N 숫자. 1~N까지 수로 0을 만들 수 있는 경우를 말하여라
			// + - ' ' 3개가 주어짐

			// 1. N-1개의 조합을 구함. 조합은 +++부터 " "" "" "까지 000~222;
			// 그리고 cal로 검증
			int[] arr = new int[N - 1];
			perm(arr, 0);
			System.out.println();
		}
	}

	private static void perm(int[] p, int cnt) {
		if (cnt == (N - 1)) {
//			System.out.println(Arrays.toString(p));
			cal(p);
			return;
		} else {
			// {0,0,0,0,0,0}~{2,2,2,2,2,2}
			for (int i = 0; i < 3; i++) {
				p[cnt] = i;
				perm(p, cnt + 1);
			}
		}
	}

	private static void cal(int p[]) {
		String str = "1";

		int num = 1;
		int count = 0;
		int saveNum = 1;
		for (int i = 2; i <= N; i++) {
			// calNum은 +,-," "
			if (count == N - 1) {
				break;
			}
			int calNum = p[count++];
			if (calNum == 1) {
				str += "+";
				num = num + i;
				saveNum = i;
			} else if (calNum == 2) {
				str += "-";
				num = num - i;
				saveNum = -i;
			} else {
				str += " ";
				num -= saveNum;
				if (saveNum >= 0) {
					saveNum = (saveNum * 10) + i;
				} else {
					saveNum = (saveNum * 10) - i;
				}
				num += saveNum;
			}
			str = str + i;
		}
		if(num==0) {
			System.out.println(str);
		}
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}

	}

}