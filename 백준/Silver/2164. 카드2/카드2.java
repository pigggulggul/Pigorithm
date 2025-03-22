import java.util.*;
import java.io.*;

public class Main {

	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Deque<Integer> queue = new ArrayDeque<Integer>();
		N = Integer.parseInt(st.nextToken());
		// 1~N번까지의 카드가 있다.
		// 1은 가장 위에 N은 가장 밑에
		for (int i = N; i >=1; i--) {
			queue.offerLast(i);
		}
		while (queue.size() > 1) {
			queue.pollLast();
			int num = queue.pollLast();
//			System.out.println(num);
			queue.addFirst(num);
		}
		System.out.println(queue.peek());

		// 일단 제일 위의 카드를 바닥에 버린다.
		// 제일 위의 카드를 제일 밑으로 옮긴다.
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
