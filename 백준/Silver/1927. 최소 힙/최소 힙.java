import java.io.*;
import java.util.*;

public class Main {

	static int N, M, T;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < N ; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num==0 && pq.isEmpty()) {
				System.out.println(0);
			}else if(num==0 && !pq.isEmpty()) {
				System.out.println(pq.poll());
			}else {
				pq.add(num);
			}
		}
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}

	}

}