import java.util.*;
import java.io.*;


class Maze implements Comparable<Maze>{

	int x;
	int y;
	int cost;
	
	public Maze(int x,int y, int cost) {
		this.x=x;
		this.y=y;
		this.cost=cost;
	}
	
	@Override
	public int compareTo(Maze o) {
		// TODO Auto-generated method stub
		return this.cost-o.cost;
	}
	
}

public class Main {
	static int N, M;
	static int arr[][];
	static int dist[][];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static int count=1;
	static PriorityQueue<Maze> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine());
		while(N != 0) {
			arr=new int[N][N];
			dist = new int[N][N];
			
			//초기화
			for (int i = 0; i < N; i++) {
				st= new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			dist[0][0]=arr[0][0];
			pq.add(new Maze(0,0,dist[0][0]));
			
			while(!pq.isEmpty()) {
				Maze info = pq.poll();
				for (int d = 0; d < 4; d++) {
					int nx = info.x+dx[d];
					int ny = info.y+dy[d];
					
					if(!checkWall(nx,ny)) {
						continue;
					}
					
					if(dist[nx][ny] > dist[info.x][info.y]+arr[nx][ny]) {
						dist[nx][ny] = dist[info.x][info.y]+arr[nx][ny];
						pq.offer(new Maze(nx,ny,dist[nx][ny]));
					}
				}
			}
			System.out.println("Problem "+count+": "+ dist[N-1][N-1]);	
			N=Integer.parseInt(br.readLine());
			count++;
		}
		
	}

	private static boolean checkWall(int x, int y) {
		return x>=0 && x<N &&  y>=0 && y<N;
	}
}