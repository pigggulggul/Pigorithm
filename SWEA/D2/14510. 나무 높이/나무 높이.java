import java.io.*;
import java.util.*;
public class Solution {
	static int[] arr;
	static int[] newArr;
	static int max;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			st= new StringTokenizer(br.readLine());
			arr= new int[N];
			newArr = new int[N];
			max=-1;
			result=0;
			int two = 0;
			int one = 0;
			for (int i = 0; i < N; i++) {
				arr[i]= Integer.parseInt(st.nextToken());
				if(arr[i]>max) max=arr[i];
			}
			for (int i = 0; i < N; i++) {
				int diff = max-arr[i];
				two+=diff/2;
				one+=diff%2;
			}
			if(two>one) {
				while(Math.abs(two - one) > 1) {
					two--;
					one += 2;
				}
			}

			if(one>two) {
				result= one*2-1;
			} else if(two>one) {
				result= two*2;
			} else {
				result= one + two;
			}

			System.out.println("#"+testcase+" "+result);

		}
	}

}