import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int arr[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr= new int[N+1][N+1];
		for (int i = 0; i < N+1; i++) {
			for (int j = 0; j < N+1; j++) {
				arr[i][j]=9999999;
			}
		}
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b]=1;
			arr[b][a]=1;
		}
		for (int k = 1; k < N+1; k++) {
			for (int i = 1; i < N+1; i++) {
				for (int j = 1; j < N+1; j++) {
					arr[i][j]=Math.min(arr[i][j], arr[i][k]+arr[k][j]);
				}
			}
		}
		
		int resultNum=0;
		int resultValue=Integer.MAX_VALUE;
		for (int i = 1; i < N+1; i++) {
			int sum=0;
			for (int j = 1; j < N+1; j++) {
				sum+=arr[i][j];
			}
			if(resultValue>sum) {
				resultNum=i;
				resultValue=sum;
			}
		}
		
		
//		printArr();
		System.out.println(resultNum);
//		System.out.println(resultValue);
	}

	private static void printArr() {
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}

	}
}