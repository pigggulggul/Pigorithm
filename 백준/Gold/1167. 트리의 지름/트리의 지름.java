import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	int w;
	int cost;
	
	public Node(int w,int cost) {
		this.w =w;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node e) {
		return this.cost - e.cost;
	}

	@Override
	public String toString() {
		return "Node [w=" + w + ", cost=" + cost + "]";
	}
	
}

public class Main {
	static int N, M;
	static List<Node> nodeList[];
	static int max = Integer.MIN_VALUE;
	static boolean visited[];
	static int farNode=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		nodeList = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			nodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				int token = Integer.parseInt(st.nextToken());
				if (token != -1) {
					int point = Integer.parseInt(st.nextToken());
					nodeList[key].add(new Node(token,point));
				}else {
					break;
				}
			}
		}

		visited = new boolean[N+1];
		visited[1]=true;
		BT(1,0);
	
		visited = new boolean[N+1];
		visited[farNode]=true;
		BT(farNode,0);
		
		 System.out.println(max);
	}

	private static void BT(int x, int result) {
		if(max<result) {
			farNode=x;
			max=result;
		}
		for (int i = 0; i < nodeList[x].size(); i++) {
			if(visited[nodeList[x].get(i).w]) {
				continue;
			}
			visited[nodeList[x].get(i).w] = true;
			result += nodeList[x].get(i).cost;
			BT(nodeList[x].get(i).w,result);
			visited[nodeList[x].get(i).w] = false;
			result -= nodeList[x].get(i).cost;
		}
		
		
	}

}