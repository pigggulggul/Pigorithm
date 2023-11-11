import java.io.*;
import java.util.*;
public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double a= Double.parseDouble(st.nextToken());
		double b= Double.parseDouble(st.nextToken());
		System.out.println(a/b);
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