import java.io.*;
import java.util.*;
public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a= Integer.parseInt(st.nextToken());
		int b= Integer.parseInt(st.nextToken());
		if(a<b) {
			System.out.println("<");
		}else if(a>b) {
			System.out.println(">");
		}else {
			System.out.println("==");
		}
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}

	}
}