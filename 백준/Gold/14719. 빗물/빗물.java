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
		// i=1~M-1부터 돌아가면서 각 칸에서 좌로 가장 높은 수, 우로 가장 높은 수 각각 구해서 둘 중 min에 맞춰서 count올리기
		for (int i = 1; i < M - 1; i++) {
			int left = confirmLeft(i);
			int right = confirmRight(i);
			int total = Math.min(left, right);
			if (total > arr[i]) {
				count += total - arr[i];
			}
		}
		System.out.println(count);
	}

	private static int confirmLeft(int number) {
		int max = 0;
		for (int i = number; i >= 0; i--) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	private static int confirmRight(int number) {
		int max = 0;
		for (int i = number; i < M; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
}