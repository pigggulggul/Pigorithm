import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static long[] arrN, arrM;
	static long[] sumN, sumM;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arrN = new long[N];
		sumN = new long[N * (N + 1) / 2];
		st = new StringTokenizer(br.readLine());
		// 두 배열의 합을 구해야한다.
		// 배열의 구한 합들을 모두 배열에 넣고 오름차순을 한 다음 투포인트 배열을 쓴다ㅣ
		for (int i = 0; i < N; i++) {
			arrN[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		arrM = new long[M];
		sumM = new long[M * (M + 1) / 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arrM[i] = Integer.parseInt(st.nextToken());
		}

		int countN = 0;
		for (int i = 0; i < N; i++) {
			long sumValue = 0;
			for (int j = i; j < N; j++) {
				sumValue += arrN[j];
				sumN[countN++] = sumValue;
			}
		}
		int countM = 0;
		for (int i = 0; i < M; i++) {
			long sumValue = 0;
			for (int j = i; j < M; j++) {
				sumValue += arrM[j];
				sumM[countM++] = sumValue;
			}
		}
		Arrays.sort(sumN);
		Arrays.sort(sumM);

//		System.out.println(Arrays.toString(sumN));
//		System.out.println(Arrays.toString(sumM));
		int left = 0;
		int right = sumM.length - 1;
		// 투포인터
		long result = 0;
		while (left < sumN.length && right >= 0) {
			long currentSumN = sumN[left];
			long currentSumM = sumM[right];
			long sum = currentSumN + currentSumM;
			if (sum == T) {
				long ac = 0;
				long bc = 0;
				while (left < sumN.length && currentSumN == sumN[left]) {
					left++;
					ac++;
				}
				while (right >= 0 && currentSumM == sumM[right]) {
					right--;
					bc++;
				}
				result += (ac * bc);
			}

			if (sum > T) {
				right--;
			} else if (sum < T) {
				left++;
			}
		}
		System.out.println(result);

	}
}