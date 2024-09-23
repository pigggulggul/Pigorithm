import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int[] arr;
	static long max = 0;
	static int result = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		// 나무의 수 N과 나무의 길이 M이 주어진다.
		// 나무를 자르고 남은 길이를 가져가야한다.20 15 10 17 일 때 15라고하면 15 15 10 15가 되고 나무는 5+2=7을
		// 가져가게된다.
		// 그래서 적어도 M보다 같거나 크게 가져가야한다.높이의 max값을 구해야함.조건에 충족하면 start=mid, 아니면 end=mid;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (max < arr[i])
				max = arr[i];
		}
		long start = 0;
		long end = max + 1;

		while (start + 1 < end) {

			long mid = (start + end) / 2;

//			System.out.println(mid);
			if (check(mid)) {
				start = mid;
			} else {
				end = mid;
			}
		}
		System.out.println(start);
		// printArr();
	}

	private static boolean check(long mid) {
		long count = 0;
		for (int i = 0; i < N; i++) {
			if (mid < arr[i]) {
				count += (arr[i] - mid);
			}
			if (count >= M) {
				return true;
			}
		}
		return false;
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