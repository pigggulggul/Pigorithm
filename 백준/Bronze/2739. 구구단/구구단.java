import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int a = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < 10; i++) {
			System.out.println(a+" * "+i+" = "+a*i);
		}

	}

}