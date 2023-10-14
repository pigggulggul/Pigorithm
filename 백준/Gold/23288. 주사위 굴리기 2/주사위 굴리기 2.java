import java.io.*;
import java.util.*;

public class Main {
	static int direction = 0; // 우하좌상
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
		//시작 방향은 1
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
		// DP로 그 위치의 점수 만들기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(dp[i][j]==0)
				makeDP(i, j,arr[i][j]);
			}
		}
		
		//0,0부터 시작하고 시작은 동쪽
		while(T-->0) {
			int x = currentX+dx[direction];
			int y = currentY+dy[direction];
			
			//이동 방향으로 구르기 위한 벽  체크
			if(!checkWall(x,y)) {
				direction = (direction+2)%4;
				x= currentX+dx[direction];
				y= currentY+dy[direction];
			}
			dice = rollDice(dice);
//			System.out.println("주사위 : "+Arrays.toString(dice));
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
		// 초기값 dice[] = {2,4,1,3,5,6 };
		// 우측 한 칸 {2,6,4,1,5,3} //dice[3],[5]는 우측 2칸이동,dice[1],dice[2]는 우측 1칸이동
		// 남쪽 한 칸 {3,6,2,1,4,5} //dice[0],[2] 2칸 dice[4][5]는 1칸
		// 좌쪽 한 칸 {3,2,1,5,4,6} //dice[1],[5] 좌측2칸,dice[2],[3]은 좌측 1칸
		// 북쪽 한 칸은 초기를 기준으로{1,4,5,3,6,2} //dice[0][2]우측 2칸 dice[5][6] 우측 1칸
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
//1,1 시작
//N,M까지
//주사위 1~6

//동쪽으로 이동
//1.이동방향으로이동. 앞에 칸이 없으면 반대로
//2.도착한 칸에 대한 점수를 획득
//3.칸의 주사위와 A>B면 이동방향 90도 시계
//3-2 A<B면 90도 반시계
//A=B면 이동방향에 변화 X
//A는 주사위의 눈금의 아랫면 
//B는 좌표 x,y의 정수B
//점수=B x 연속으로 이동할 수 있는 칸의 수C
//주사위는
/*
 * 2 413 5 6
 */