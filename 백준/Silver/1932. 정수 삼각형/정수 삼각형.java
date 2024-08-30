import java.util.*;
import java.io.*;

// 아래층까지 내려올 때 선택된 수의 합이 최대가 되는 경우

//5 5층
//7
//3 8
//8 1 0
//2 7 4 4
//4 5 2 6 5

//8
//10 15
//18 16 15
//20 25 20 19
//24 30 27 26 25

// 1층 1, 2층 3, 3층 6, 4층 10, 5층 15
// n층 = 1층부터 n층까지의 합.



// dp[n] = new Int[n];
//0=0 [1]=[0]+1 [2]=[0]+2
//[3]=
public class Main {

	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int num = findArrSize(N);
		int[] arr = new int[num];
		int[] dp = new int[num];
		int max = 0;
		int a=0;
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <= i ; j++) {
				arr[a++] = Integer.parseInt(st.nextToken());				
			}
		}
		int count=1;
		int prevCount=0;
		dp[0]=arr[0];
		max=dp[0];
		for(int i = 1 ; i < N; i++) {
			int saveCount = prevCount;
			prevCount=count;
			for(int j = 0 ; j <= i ; j++) {
				if(j==0 || j==i) {
//					System.out.print(arr[saveCount]+" ");
					dp[count]=dp[saveCount]+arr[count];
				}else {
//					System.out.print(arr[saveCount]+""+arr[saveCount+1]);
					dp[count]=Math.max(dp[saveCount]+arr[count], dp[saveCount+1]+arr[count]);
					saveCount++;
				}
//				System.out.print(dp[count]+" ");
				if(i==N-1 && dp[count] > max) {
					max=dp[count];
				}
				count++;
			}
//			System.out.println();
		}
		System.out.println(max);
//		for(int i = 0 ; i < num ;i++) {
//			System.out.println(arr[i]);
//		}
	}
	private static int findArrSize(int N) {
		int answer=0;
		for(int i = 0 ; i < N; i++) {
			answer+=i+1;
		}
		return answer;
	}
}