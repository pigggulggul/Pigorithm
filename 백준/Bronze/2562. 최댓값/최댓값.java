import java.io.*;
import java.util.*;
public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int index=0;
		int value=0;
		for (int i = 0; i < 9; i++) {
			int num = Integer.parseInt(br.readLine());
			if(value<num) {
				value=num;
				index=i+1;
			}
		}
		System.out.println(value);
		System.out.println(index);
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