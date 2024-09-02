import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr= new int[N];
		st= new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i <N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = N-1;
		int left=0;
		int right=0;
		int max= Integer.MAX_VALUE;
		//2포인트
		//0과 index-1 크기를 가지고
		// 둘의 합이 +면 index -=1;
		// 둘의 합이 -면 0 +=1;
		// 이 과정에서 
		
		while(start<end) {
			int cal = arr[start] + arr[end];
			if(Math.abs(max) >= Math.abs(cal)) {
				max=cal;
				left=arr[start];
				right=arr[end];
			}
			if(cal > 0) {
				end-=1;
			}else {
				start+=1;
			}
			
		}
		System.out.println(left+" "+right);
		// printArr();
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