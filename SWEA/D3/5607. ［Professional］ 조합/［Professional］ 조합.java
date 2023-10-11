
import java.io.*;
import java.util.*;

public class Solution {
	static long MOD = 1234567891;
	static int N, R;
	static long factorial[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			makeFac();
			long result = nCr();
			System.out.println("#" + testcase + " " + result);
		}
	}

	private static void makeFac() {
		factorial = new long[N + 1];
		factorial[0] = 1;
		for (int i = 1; i < N + 1; i++) {
			factorial[i] = i * factorial[i - 1] % MOD;
		}
	}

	private static long nCr() {
		long numor = factorial[N];
		long denomor = factorial[R] * factorial[N - R] % MOD;

		long invert = power(denomor, MOD - 2);

		return numor * invert % MOD;
	}

	private static long power(long a, long b) {
		if (b == 0)
			return 1L;
		if (b % 2 == 0) {
			long half = power(a, b / 2);
			return half * half % MOD;
		} else {
			return a * power(a, b - 1) % MOD;
		}
	}
}
