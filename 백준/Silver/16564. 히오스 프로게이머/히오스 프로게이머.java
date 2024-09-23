import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static long[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N];
		long start = Integer.MAX_VALUE;
		long end = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			if (end < arr[i]) {
				end = arr[i];
			}
			if (start > arr[i]) {
				start = arr[i];
			}

		}
		start -= 1;
		end += M+1;

		// N개의 캐릭터.M개의 기회. 가능한 최대 팀 목표 레벨
		// 10 15 20 25 | 10

		while (start + 1 < end) {
			long mid = (start + end) / 2;

			if (check(mid)) {
				// true면 더 높은 값 탐색
				start = mid;
			} else {
				// false면 더 낮은 값 탐색
				end = mid;
			}
		}
		
		System.out.println(start);

		// printArr();
	}

	private static boolean check(long mid) {
		long count = M;
		for (int i = 0; i < N; i++) {
			if (mid > arr[i]) {
				long cal = mid - arr[i];
				count -= cal;
			}
		}
		if (count >= 0) {
			return true;
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