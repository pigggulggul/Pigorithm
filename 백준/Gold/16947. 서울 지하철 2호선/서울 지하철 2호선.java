import java.util.*;
import java.io.*;

class Node{
	int v;
	int count;
	
	public Node(int v,int count) {
		this.v=v;
		this.count=count;
	}
}

public class Main {
	static int N, M, T;
	static ArrayList<Integer>[] list;
	static boolean[] cycle;
	static int[] result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		result=new int[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		cycle=new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			if(checkCycle(i,i,i)) {
				break;
			}
			cycle = new boolean[N+1];
		}
		for (int i = 1; i <= N; i++) {
			if(!cycle[i]) {
				result[i]=bfs(i);
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(result[i]+" ");
		}
	}
	private static int bfs(int node) {
		Queue<Node> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		q.offer(new Node(node,0));
		visited[node]=true;
		
		while(!q.isEmpty()) {
            Node current = q.poll();
            if(cycle[current.v]) return current.count;
 
            for(int i = 0; i < list[current.v].size(); i++) {
                int next = list[current.v].get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(new Node(next, current.count + 1));
                }
            }
        }
		return 0;
	}
	private static boolean checkCycle(int prev, int current, int start) {
		
		cycle[current]=true;
		
		for (int i = 0; i < list[current].size(); i++) {
			int next = list[current].get(i);
			
			if(!cycle[next]) {
				if(checkCycle(current,next,start)) return true;
			} else if(next != prev && next == start) return true;
		}
		cycle[current] = false;
		
		return false;
	}

}