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
		Arrays.sort(arr);

		int start,mid,end;
		long cal;
		int left=0;
		int midVal=0;
		int right=0;
		long max= Long.MAX_VALUE;
		for(int i = 0 ; i < N-2; i++) {
			start = i;
			mid = i+1;
			end = N-1;
			
			while(mid<end) {
				cal = (long) arr[start]+arr[mid]+arr[end];
				if(Math.abs(cal) < Math.abs(max)) {
					max=cal;
					left=arr[start];
					midVal=arr[mid];
					right=arr[end];
				}
				if(cal<0) {
					mid++;
				}else {
					end--;
				}
			}
		}
		System.out.println(left+" "+midVal+" "+right);
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