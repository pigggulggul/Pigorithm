import java.util.*;
import java.io.*;

public class Main  {
	static int N, M, T;
	static int arr[][];
	static int cloneArr[][];
	static int numberZero[];
	static boolean visited[];
	static int p[];
	static Deque<Integer> deqX = new ArrayDeque<Integer>();
	static Deque<Integer> deqY = new ArrayDeque<Integer>();
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		arr= new int[N][M];
		p= new int[3];
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		//배열에 0의 위치 담기
		int countZero=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==0) {
					countZero++;
				}
			}
		}
		numberZero = new int[countZero];
		visited = new boolean[countZero];
		countZero=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==0) {
					numberZero[countZero++]=i*M+j;
				}
			}
		}
		
		Perm(0,0);
		System.out.println(result);
	}

	//이 수 배열에 저장된 수 중 3개 찾기
	private static void Perm(int cnt,int start) {
		if(cnt==3) {
			BFS();
		}else {
			for (int i = start; i < numberZero.length; i++) {
				if(visited[i]) continue;
				visited[i]=true;
				p[cnt]=numberZero[i];
				Perm(cnt+1,i);
				visited[i]=false;
			}
		}
	}

	private static void BFS() {
		cloneArr= new int[N][M];
		for (int i = 0; i < N; i++) {
			cloneArr[i] = arr[i].clone();
		}
		
		for (int i = 0; i < 3; i++) {
			int a = p[i];
			cloneArr[a/M][a%M]=1;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(cloneArr[i][j]==2) {
					deqX.addLast(i);
					deqY.addLast(j);
				}
			}
		}
		while(!deqX.isEmpty() && !deqY.isEmpty()) {
			int x = deqX.pollFirst();
			int y = deqY.pollFirst();
			
			for (int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(!checkwall(nx,ny)) {
					continue;
				}
				
				if(cloneArr[nx][ny]==0) {
					cloneArr[nx][ny]=2;
					deqX.addLast(nx);
					deqY.addLast(ny);
				}
				
			}
		}
		searchZero();
	}

	private static void searchZero() {
		int count=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(cloneArr[i][j]==0) {
					count++;
				}
			}
		}
		if(result <count) {
			result=count;
		}
	}

	private static boolean checkwall(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(cloneArr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
