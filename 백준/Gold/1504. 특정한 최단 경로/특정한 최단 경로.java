import java.util.*;
import java.io.*;

class Node_1504 implements Comparable<Node_1504> {
	int w;
	int cost;

	public Node_1504(int w, int cost) {
		this.w = w;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node_1504 o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}

	@Override
	public String toString() {
		return "Node_1504 [w=" + w + ", cost=" + cost + "]";
	}
	
	

}

public class Main {
	static int N, M;
	static int req1, req2 = 0;
	static List<Node_1504> nodeList[];
	static boolean[] visited;
	static int arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodeList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		visited = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			nodeList[a].add(new Node_1504(b, c));
			nodeList[b].add(new Node_1504(a, c));
		}
		st = new StringTokenizer(br.readLine());
		req1 = Integer.parseInt(st.nextToken());
		req2 = Integer.parseInt(st.nextToken());

		int val1 = dijklstra(1, req1);
		val1 += dijklstra(req1,req2);
		val1 += dijklstra(req2,N);
		
		int val2 = dijklstra(1, req2);
		val2 += dijklstra(req2,req1);
		val2 += dijklstra(req1,N);

		if(val1>val2) {
			System.out.println(val2);
		}else if(val1 >= 200000000 || val2 >= 200000000) {
			System.out.println(-1);
		}
			else {
			System.out.println(val1);
		}
		// printArr();
	}

	private static int dijklstra(int start, int end) {
		visited=new boolean[N+1];
		arr = new int[N+1];
		Arrays.fill(arr, 200000000);
		arr[start]=0;
		PriorityQueue<Node_1504> pq = new PriorityQueue<>();
		pq.offer(new Node_1504(start, 0));
		while (!pq.isEmpty()) {
			Node_1504 info = pq.poll();
			if (visited[info.w])
				continue;
			visited[info.w] = true;
			if(info.w == end) {
				return info.cost;
			}
			for (Node_1504 node : nodeList[info.w]) {
				if (arr[node.w] > arr[info.w] + node.cost) {
					arr[node.w] = arr[info.w]+ node.cost; 
					pq.offer(new Node_1504(node.w, arr[node.w]));
				}

			}

		}
		return arr[N];
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