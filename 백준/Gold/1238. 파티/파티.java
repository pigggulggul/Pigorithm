import java.util.*;
import java.io.*;

class PartyNode implements Comparable<PartyNode>{
	int end;
	int dis;

	public PartyNode(int end, int dis) {
		this.end = end;
		this.dis = dis;
	}

	@Override
	public int compareTo(PartyNode o) {
		return this.dis - o.dis;
	}
}

public class Main {

	static int N, M, T;
	static ArrayList<PartyNode>[] graph;
	static ArrayList<PartyNode>[] graph2;
	static int result = 0;
	static int dist[];
	static int dist2[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
		// N,M,T n명의 학생, m개의 리스트, 목적지 t
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		graph2 = new ArrayList[N + 1];
		dist = new int[N + 1];
		dist2 = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new PartyNode(b, c));
			graph2[b].add(new PartyNode(a, c));
		}
		Arrays.fill(dist, 100000000);
		Arrays.fill(dist2, 100000000);
		startGraph(T, graph, dist);
		startGraph(T, graph2, dist2);
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, dist[i] + dist2[i]);
		}
		System.out.println(result);
	}

	private static void startGraph(int start, ArrayList<PartyNode>[] graph, int[] dist) {
		dist[start] = 0;
		PriorityQueue<PartyNode> deque = new PriorityQueue<>();
		deque.add(new PartyNode(start, 0));
		while (!deque.isEmpty()) {
			PartyNode num = deque.poll();
			for (PartyNode next : graph[num.end]) {
				if (dist[next.end] > dist[num.end] + next.dis) {
					dist[next.end] = dist[num.end] + next.dis;
					deque.add(new PartyNode(next.end, dist[next.end]));
				}
			}
		}
	}

}