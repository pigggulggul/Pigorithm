import java.util.*;
import java.io.*;

public class Main {

	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue pq = new PriorityQueue<Integer>();
		PriorityQueue pq2 = new PriorityQueue<Integer>((a, b) -> b - a);

		N = Integer.parseInt(st.nextToken());

		int j = 0;

		for (int i = 0; i < N; i++) {
			if (i == 2)
				break;

			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if ((i + 1) % 2 == 1) {
				pq2.add(num);
				
				sb.append(pq2.peek()).append("\n");
			} else {
				pq.add(num);
				int a = (int) pq.peek();
				int b = (int) pq2.peek();
				
				sb.append(Math.min(a, b)).append("\n");
			}
		}
		for (int i = 2; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			// 홀수면
			if ((i + 1) % 2 == 1) {
				int a = (int) pq.peek();
				if (a > num) {
					pq2.add(num);
					int compB = (int) pq2.peek();
					sb.append(Math.min(a, compB)).append("\n");
				} else {
					pq2.add(pq.poll());
					pq.add(num);
					int compA = (int) pq.peek();
					int compB = (int) pq2.peek();

					sb.append(Math.min(compA, compB)).append("\n");
				}
			} else {
				int a = (int) pq.peek();
				if (a > num) {
					pq.add(pq2.poll());
					pq2.add(num);
					int compA = (int) pq.peek();
					int compB = (int) pq2.peek();
					sb.append(Math.min(compA, compB)).append("\n");
				} else {
					pq.add(num);
					int compA = (int) pq.peek();
					int compB = (int) pq2.peek();

					sb.append(Math.min(compA, compB)).append("\n");
				}
			}

		}
		System.out.println(sb);
	}
}