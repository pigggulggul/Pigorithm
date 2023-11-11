import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		String b = br.readLine();
		
		int num = 0;
		for (int i = 0; i < a; i++) {
			num+=b.charAt(i)-'0';
		}
		System.out.println(num);

	}

}