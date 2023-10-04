import java.util.*;
import java.io.*;

public class Main {
	static int[] lps;
	static String txt;
	static String pat;
	static int N, M;
	static int count=0;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 초기화
		txt = br.readLine();
		pat = br.readLine();
		lps = new int[pat.length()];
		N = txt.length();
		M = pat.length();
		// 초기화
		createOrder();

		searchString();
		
		System.out.println(count);
		System.out.println(sb.toString());
	}

	private static void createOrder() {
		int len = 0;
		int i = 1;
		lps[0] = 0;
		while (i < M) {
			if (pat.charAt(len) == pat.charAt(i)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = len;
					i++;
				}
			}
		}
	}

	private static void searchString() {
		int i = 0; // 전체길이
		int j = 0; // 찾을길이
		while ((N - i) >= (M - j)) {
			if (pat.charAt(j) == txt.charAt(i)) {
				i++;
				j++;
			}
			if (j == M) {
				count++;
				sb.append(i-j+1).append("\n");
				j = lps[M - 1];
			} else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				if (j != 0) {
					j = lps[j - 1];
				} else if (j == 0) {
					i += 1;
				}
			}
		}
	}

}