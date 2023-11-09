import java.util.*;
import java.io.*;

public class Main {
	static int N,M,T;
	static int numZero,numOne;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		
		T= Integer.parseInt(br.readLine());
		int[][] dp=new int[41][2];
		for (int test = 0; test < T; test++) {
			N=Integer.parseInt(br.readLine());
			numZero=0;
			numOne=0;
			dp[0][0]=1;
			dp[0][1]=0;
			dp[1][0]=0;
			dp[1][1]=1;
			
			if(N==0) {
				System.out.println("1 0");
			}else if(N==1) {
				System.out.println("0 1");
			}else {
				for (int i = 2; i <= N; i++) {
					if(dp[i][0]==0) {
						dp[i][0]=dp[i-1][0]+dp[i-2][0];
						dp[i][1]=dp[i-1][1]+dp[i-2][1];
					}
				}
				System.out.println(dp[N][0] +" "+dp[N][1]);
			}
		}
		
//		printArr();
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