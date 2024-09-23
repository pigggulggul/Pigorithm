import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static long[] arr;
	static long result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		long start = 0;
		long end = arr[N - 1] + 1;

		while (start + 1 < end) {
			long mid = (start + end) / 2;

//				System.out.println(mid);
			if (check(mid)) {
				start = mid;
			} else {
				end = mid;
			}
		}
		System.out.println(result);

		// printArr();
	}

	private static boolean check(long mid) {
		int count = M;
		long startVal = arr[0];
		count--;
//		System.out.println(mid + " 시작합니다");

		// arr[0]+mid까지가 와이파이가 설치가 됨. arr[0]+mid보다 값이 작으면 영향권임. 만야 arr[0]+mid보다 크면 영향권이
		// 아니라 공유기를 다시 세워야함
		for (int i = 1; i < N; i++) {
			if (arr[i] > startVal + mid - 1) {
//				System.out.println(startVal + mid-1 + " 까지 와이파이가 설치됨.");
				startVal = arr[i];
				count--;
			}
			if (count <= 0) {
				if (count == 0 && result < mid) {
					result = mid;
				}
				return true;
			}
		}
		//count가 큰 경우 공유기를 다 못 놓은경우다. 이 경우 최대값을 줄여야한다.
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