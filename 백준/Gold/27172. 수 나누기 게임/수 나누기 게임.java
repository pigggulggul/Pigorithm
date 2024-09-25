import java.util.*;
import java.io.*;

public class Main {

	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] result = new int[N];
		int max = -1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		int[] count = new int[max + 1];
		for (int i = 0; i < N; i++) {
			count[arr[i]] = i + 1;
		}
//		System.out.println(Arrays.toString(count));
		for (int i = 0; i < N; i++) {
			// arr[i]을 돌면서 거기의 배수를 찾는다.
			for (int j = 2; arr[i] * j <= max; j++) {
				// count[]의 값이 0이 아니면 나눠진다는 뜻이다.
				if (count[arr[i] * j] != 0) {
					result[i]++;
					result[count[arr[i]*j]-1]--;
				}

			}

		}

		printArr(result);
	}

	private static void printArr(int[] arr) {
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();

	}

}