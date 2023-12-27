import java.util.*;
import java.io.*;


//N하고 M 1~N중 중복없이 M개 뽑기 nCm
public class Main {
	static int N, M;
	static int[] p;
	static boolean[] flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[M];
		flag = new boolean[N];
		perm(1,0);
		//		printArr();
	}

	private static void perm(int start,int cnt) {
		if(cnt==M) {
			for (int i = 0; i < M; i++) {
				System.out.print(p[i]+" ");
			}
			System.out.println();
			return;
		}else {
			for (int i = start; i <= N; i++) {
				p[cnt]=i;
				cnt+=1;
				perm(i+1, cnt);
				cnt-=1;
			}
			
		}
		
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