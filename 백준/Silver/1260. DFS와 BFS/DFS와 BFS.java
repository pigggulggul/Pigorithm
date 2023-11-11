import java.util.*;
import java.io.*;

public class Main {

	static int N, M, start;
	static List<Integer>[] list;
	static boolean[] visited;
	static String dfsResult = "";
	static String bfsResult = "";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		for (int i = 0; i < N; i++) {
			Collections.sort(list[i]);
		}

		dfsResult+=start+" ";
		bfsResult+=start+" ";
		visited = new boolean[N];
		visited[start]=true;
		DFS(start);
		visited = new boolean[N];
		visited[start]=true;
		BFS(start);
		System.out.println(dfsResult);
		System.out.println(bfsResult);

	}

	private static void BFS(int start) {
		Queue<Integer> que = new ArrayDeque<>();
		que.add(start);
		
		while(!que.isEmpty()) {
			int num = que.poll();
			for (int i = 0; i < list[num].size(); i++) {
				if (!visited[list[num].get(i)]) {
					bfsResult += list[num].get(i) + " ";
					visited[list[num].get(i)] = true;
					que.add(list[num].get(i));
				}
			}
		}
	}

	private static void DFS(int num) {
		
		for (int i = 0; i < list[num].size(); i++) {
			if (!visited[list[num].get(i)]) {
				dfsResult += list[num].get(i) + " ";
				visited[list[num].get(i)] = true;
				DFS(list[num].get(i));
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