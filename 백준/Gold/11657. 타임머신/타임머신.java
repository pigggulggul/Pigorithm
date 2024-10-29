import java.io.*;
import java.util.*;
class Edge{
	int v;
	int w;
	int cost;
	
	public Edge(int v,int w,int cost) {
		this.v=v;
		this.w=w;
		this.cost=cost;
	}
}
public class Main {

	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		Edge graph[]= new Edge[M];
		for(int i = 0 ; i < M ; i++) {
			st= new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[i] = (new Edge(v,w,cost));
		}
		
		// cycle 검사를 해야함
		// 벨만포드
		long dist[] = new long[N+1];
		for(int i = 0 ; i <= N ; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1]=0;
		for(int i = 1 ; i <= N ; i++) {
//			System.out.println(i+" 번 째 탐색을 시작합니다.");
			for(int j = 0 ; j < M ; j++) {
				Edge edge = graph[j];
				if(dist[edge.v] != Integer.MAX_VALUE && dist[edge.w] > dist[edge.v]+edge.cost) {
					dist[edge.w] =dist[edge.v]+edge.cost;
				}
			}
		}
//		System.out.println("사이클을 탐색합니다.");
		for(int i = 1 ; i <= N ; i++) {
//			System.out.println(i+" 번 째 탐색을 시작합니다.");
			for(int j = 0 ; j < M ; j++) {
				Edge edge = graph[j];
				if(dist[edge.v] != Integer.MAX_VALUE && dist[edge.w] > dist[edge.v]+edge.cost) {
					System.out.println(-1);
					return ;
				}
			}
		}
		for(int i = 2 ; i <= N ; i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				System.out.println(-1);
			}else {
				System.out.println(dist[i]);
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