import java.io.*;
import java.util.*;

public class Main {
	static int direction; 
	static int arr[][], dp[][];
	static boolean visited[][];
	static int N, M, T;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int point = 0;
	static int currentX,currentY=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		direction = 1;
		currentX=0;
		currentY=0;
		int dice[] = { 2, 4, 1, 3, 5, 6 };
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(dp[i][j]==0)
				makeDP(i, j,arr[i][j]);
			}
		}
		
		while(T-->0) {
			int x = currentX+dx[direction];
			int y = currentY+dy[direction];
			
			if(!checkWall(x,y)) {
				direction = (direction+2)%4;
				x= currentX+dx[direction];
				y= currentY+dy[direction];
			}
			dice = rollDice(dice);
			point+=dp[x][y];
			
			if(dice[5] > arr[x][y]) {
				direction = (direction+1)%4;
			}
			else if(dice[5] < arr[x][y]) {
				direction = (direction-1);
				if(direction==-1) {
					direction=3;
				}
			}
			currentX=x;
			currentY=y;
		}
		System.out.println(point);

	}

	private static int[] rollDice(int[] dice) {
		int newDice[] = new int[6];
		if(direction==1) {
			newDice[0]=dice[0];
			newDice[1]=dice[5];
			newDice[2]=dice[1];
			newDice[3]=dice[2];
			newDice[4]=dice[4];
			newDice[5]=dice[3];
		} else if(direction==2) {
			newDice[0]=dice[5];
			newDice[1]=dice[1];
			newDice[2]=dice[0];
			newDice[3]=dice[3];
			newDice[4]=dice[2];
			newDice[5]=dice[4];
		} else if(direction==3) {
			newDice[0]=dice[0];
			newDice[1]=dice[2];
			newDice[2]=dice[3];
			newDice[3]=dice[5];
			newDice[4]=dice[4];
			newDice[5]=dice[1];			
		} else if(direction==0) {
			newDice[0]=dice[2];
			newDice[1]=dice[1];
			newDice[2]=dice[4];
			newDice[3]=dice[3];
			newDice[4]=dice[5];
			newDice[5]=dice[0];
		}
		
		return newDice;
	}

	private static void makeDP(int datax, int datay,int value) {
		visited=  new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { datax, datay });
		int size = 1;
		while (!queue.isEmpty()) {
			int[] info = queue.poll();
			int x = info[0];
			int y = info[1];
			visited[x][y]=true;
			for (int d = 0; d < 4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if (!checkWall(nx, ny) || visited[nx][ny])
					continue;
				else {
					if(arr[nx][ny]==value) {
						queue.add(new int[] {nx,ny});
						visited[nx][ny]=true;
						size++;
					}
				}
			}
		}
		
		dp[datax][datay]=value*size;

	}

	private static boolean checkWall(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}