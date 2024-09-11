import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] arr;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < M - 1; i++) {
			int left = confirm(0, i);
			int right = confirm(1, i);
			int total = Math.min(left, right);
			if (total > arr[i]) {
				count += total - arr[i];
			}
		}
		System.out.println(count);
	}

	private static int confirm(int dir, int number) {
		int max = 0;
		if (dir == 0) {
			for (int i = number; i >= 0; i--) {
				if (arr[i] > max) {
					max = arr[i];
				}
			}
		} else {
			for (int i = number; i < M; i++) {
				if (arr[i] > max) {
					max = arr[i];
				}
			}
		}

		return max;
	}
}