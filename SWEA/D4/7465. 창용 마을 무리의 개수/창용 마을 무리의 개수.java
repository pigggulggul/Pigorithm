
import java.util.*;
import java.io.*;

public class Solution {
	static int arr[];
	static int rank[];
	static boolean[] sensor;
	static int N, M, T;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N + 1];
			rank = new int[N + 1];
			sensor = new boolean[N + 1];
			count = 0;
			makeSet();
//			System.out.println(Arrays.toString(arr));
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
//			System.out.println(Arrays.toString(arr));
//			System.out.println(Arrays.toString(rank));
			for (int i = 1; i < N + 1; i++) {
				if(arr[i]==i) {
					count++;
				}
			}
			System.out.println("#" + testcase + " " + count);
		}
	}

	private static void union(int x, int y) {
		// 부모를 가르킴
		int px = findSet(x);
		int py = findSet(y);
		if (px != py) {
			linkRank(px, py);
		}
	}

	private static void linkRank(int x, int y) {
		if (rank[x] > rank[y]) {
			arr[y] = x;
		} else {
			arr[x] = y;
			if (rank[x] == rank[y]) {
				rank[y]++;
			}
		}

	}

	private static int findSet(int x) {
		if (arr[x] == x)
			return x;
		else {
			arr[x] = findSet(arr[x]);
			return arr[x];
		}
	}

	private static void makeSet() {
		for (int i = 1; i < N + 1; i++) {
			arr[i] = i;
			rank[i]=1;
		}
	}

}
