import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int a = Integer.parseInt(br.readLine());
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < a; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(max<num) {
				max=num;
			}
			if(min>num) {
				min=num;
			}
		}
		System.out.println(min+" "+max);

	}

}