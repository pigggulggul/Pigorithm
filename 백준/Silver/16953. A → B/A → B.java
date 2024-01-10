import java.util.*;
import java.io.*;

public class Main {
	static long N, M, count = 0;
	static Queue<Long> queue = new ArrayDeque<Long>();
	static boolean flag = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		queue.offer(N);
		count++;
		BFS();

		// printArr();
	}

	private static void BFS() {
		if(flag) {
			return;
		}
		int time = queue.size();
		if(queue.size()==0 && !flag) {
			System.out.println(-1);
			flag=true;
		}
		while (time-->0) {
			long num=queue.poll();
			if(num>M || flag) {
				return;
			}
			long a = 2 * num;
			long b = num * 10 + 1;
			if(a==M || b==M) {
				flag=true;
				count++;
				System.out.println(count);
				return;
			}
			if (a < M) {
				queue.offer(a);
			}
			if( b < M) {
				queue.offer(b);
			}
		}
		count++;
		BFS();
		
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