import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int goal;
	static int result = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		for (int a = 0; a < arr.length; a++) {
			if(result==goal) {
				break;
			}
			for (int b = a + 1; b < arr.length; b++) {
				for (int c = b + 1; c < arr.length; c++) {
					if ((arr[a] + arr[b] + arr[c]) <= goal && (arr[a]+arr[b]+arr[c]) > result) {
						result = arr[a] + arr[b] + arr[c];
					}
				}
			}
		}
		System.out.println(result);
		// printArr();
	}

	private static void printArr(int[] arr) {
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}