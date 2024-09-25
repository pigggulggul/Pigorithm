import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (find(a) == find(b)) {
				System.out.println(i + 1);
				return;
			} else {
				union(a, b);
			}
		}
		System.out.println(0);
		// printArr();
	}

	private static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);
		if (x < y) {
			parent[x] = y;
		} else {
			parent[y] = x;
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