import java.util.*;
import java.io.*;

class Node_1916 implements Comparable<Node_1916>{
	int w;
	int cost;
	
	public Node_1916(int w, int cost) {
		this.w=w;
		this.cost=cost;
	}
	
	@Override
	public int compareTo(Node_1916 o) {
		// TODO Auto-generated method stub
		return this.cost-o.cost;
	}
	
}

public class Main {
	static int N, M,start,end;
	static List<Node_1916> graph[];
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node_1916(b,c));
		}
		st = new StringTokenizer(br.readLine());
		start= Integer.parseInt(st.nextToken());
		end= Integer.parseInt(st.nextToken());
		
		int result = Dijklstra(start,end);
		System.out.println(result);
		//		printArr();
		
	}

	private static int Dijklstra(int s, int e) {
		visited=new boolean[N+1];
		PriorityQueue<Node_1916> queue = new PriorityQueue<>(); 
		int dist[] = new int[N+1];
		Arrays.fill(dist, 100000000);
		queue.add(new Node_1916(s,0));
		dist[s]=0;
		
		while(!queue.isEmpty()) {
			Node_1916 info = queue.poll();
			if(visited[info.w]) continue;
			visited[info.w]= true;
			
			if(info.w==e) {
				return dist[e];
			}
			for (Node_1916 next : graph[info.w]) {
				if(dist[next.w] > dist[info.w]+next.cost) {
					dist[next.w] = dist[info.w] + next.cost;
					queue.offer(new Node_1916(next.w,dist[next.w]));
				}
			}
			
		}
		return dist[e];
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