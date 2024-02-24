import java.util.*;
import java.io.*;


class Edge implements Comparable<Edge> {
	int w;
	int e;
	int cost;
	
	public Edge(int w, int e, int cost) {
		this.w = w;
		this.e = e;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge edge) {
		return this.cost - edge.cost;
	}

	@Override
	public String toString() {
		return "Edge [w=" + w + ", e=" + e + ", cost=" + cost + "]";
	}
	
	
}

public class Main {
	static int N, M, T;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int parents[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N= Integer.parseInt(br.readLine());
		M= Integer.parseInt(br.readLine());
		parents= new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(a,b,c));
		}
		makeSet();
		//		printArr();
		int result = 0;
		
		while(!pq.isEmpty()) {
			Edge info = pq.poll();
			
			if(find(info.w) == find(info.e)) continue;
			union(info.w,info.e);
			result+=info.cost;
			
		}
		System.out.println(result);
	}

	private static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i]=i;
		}
	}
	private static int find(int a) {
		if(parents[a]==a) {
			return a;
		}
		return parents[a]=find(parents[a]);
	}
	private static void union(int a,int b) {
		int x = find(a);
		int y = find(b);
		if(x > y) {
			parents[y]= x;
		}else {
			parents[x]= y;
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