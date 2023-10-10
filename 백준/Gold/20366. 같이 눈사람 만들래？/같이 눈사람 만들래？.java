import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int arr[];
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		int left;
		int right;
		for (int i = 0; i < N - 3; i++) {
			for (int j = i + 3; j < N; j++) {
				left = i + 1;
				right = j - 1;
				
				while(left < right) {
					int size = (arr[left]+arr[right])-(arr[i] + arr[j]);
					if (min > size)
					{
						min = Math.min(min, Math.abs(size));
					}
					if(size > 0) {
						right-=1;
					}
					else {
						left+=1;
					}
				}
				
			}
		}

		System.out.println(min);
	}

}