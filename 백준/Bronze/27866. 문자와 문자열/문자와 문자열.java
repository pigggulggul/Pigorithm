import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a =br.readLine();
		int num = Integer.parseInt(br.readLine());
		System.out.println(a.charAt(num-1));
	}

}