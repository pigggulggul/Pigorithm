import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int arr[];
	static boolean[] cycle;
	static int num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for (int cs = 0; cs < T; cs++) {
			N=Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[N+1];
			cycle = new boolean[N+1];
			num=0;
			for (int i = 1; i <= N; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= N; i++) {
				if(cycle[i]==false) {
					num++;
					int a =i;
					while(cycle[a]==false) {
						cycle[a]=true;
						a=arr[a];
					}
					
				}
			}
			System.out.println(num);
			
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