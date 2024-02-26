import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static Deque<Integer> deque = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			if (a.contains("push")) {
				String b[] = a.split(" ");
				int pushNum = Integer.parseInt(b[1]);
				deque.offerLast(pushNum);
			} else if (a.equals("top")) {
				if (deque.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(deque.peekLast());
				}
			} else if (a.equals("size")) {
				System.out.println(deque.size());
			} else if (a.equals("empty")) {
				if (deque.isEmpty()) {
					System.out.println("1");
				} else {
					System.out.println("0");
				}
			} else if (a.equals("pop")) {
				if (deque.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(deque.pollLast());
				}
			}
		}
	}

}