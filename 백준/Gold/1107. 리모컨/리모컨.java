import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static String number;
	static int num;
	static boolean flag;
	static boolean[] broke;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		number = br.readLine();
		num=Integer.parseInt(number);
		N = Integer.parseInt(br.readLine());
		broke = new boolean[10];
		int initialNum = 100;
		if(N!=0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				broke[Integer.parseInt(st.nextToken())]=true;
			}
		}
		int result = Math.abs(num-initialNum);
		for (int i = 0; i <= 999999; i++) {
			String str = String.valueOf(i);
			flag=false;
			for (int j = 0; j < str.length(); j++) {
				if(broke[str.charAt(j)-'0']) {
					flag=true;
					break;
				}
			}
			if(!flag) {
				int min = Math.abs(num-i)+str.length();
				result = Math.min(min, result);
			}
		}
		System.out.println(result);
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