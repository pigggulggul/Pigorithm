import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//	6
		//	10 20 10 30 20 50
		//	시간초과가 난다. 이분 탐색을 통해서 찾아야한다.
		// 하나씩 넣으면서
		N = Integer.parseInt(br.readLine());
		int max = 0;
		int result = 0;
		int[] arr = new int[N];
		int[] resultArr = new int[N];
		st = new StringTokenizer(br.readLine());
		// 값을 넣고 
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int lastIndex = 1;
		//처음 값을 넣는다.
		resultArr[0] = arr[0];
		for(int i = 1 ; i < N ; i++) {
			if(resultArr[lastIndex-1] < arr[i]) {
				lastIndex++;
				resultArr[lastIndex-1]=arr[i];
			}else {
				int low = 0;
				int high = lastIndex;
				while(low<high) {
					int mid = (high+low)/2;
					if(resultArr[mid] < arr[i]) {
						low = mid+1;
					}else {
						high= mid;
					}
				}
				resultArr[low]= arr[i];
			}
		}
		System.out.println(lastIndex);
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