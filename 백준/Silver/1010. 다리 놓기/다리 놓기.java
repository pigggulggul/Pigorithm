import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int arr[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n;
		int r;
		int result;
		T=Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st= new StringTokenizer(br.readLine());	
			n=Integer.parseInt(st.nextToken());
			r=Integer.parseInt(st.nextToken());
			arr=new int[31][31];
			result=bridge(n,r);
			arr[0][0]=1;
			arr[1][0]=1;
			arr[1][1]=1;
			System.out.println(result);
		}
		
		
	}
	private static int bridge(int n, int r) {
		if(n==0 || r ==0 || n==r) {
			return 1;
		}
		if(arr[n][r]!=0) {
			return arr[n][r];
		}
		return arr[n][r] = bridge(n-1,r-1)+bridge(n,r-1);
	}
}
