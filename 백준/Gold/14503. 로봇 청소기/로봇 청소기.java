import java.util.*;
import java.io.*;

public class Main {
	static int N,M;
	static int arr[][];
	static boolean[][] visited;
	static boolean flag;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int result= 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j]= num;
				if(num==1) {
					visited[i][j]=true;
				}
			}
		}
		
		Operate_Robot(startX,startY,direction);
//		printRobot();
	}
	/**로봇 작동*/
	private static void Operate_Robot(int startX, int startY, int direction) {
		int curX=startX;
		int curY=startY;
		while(true) {
			if(flag) {
				break;
			}
			//1. 현재 칸이 청소되지 않은경우 현재 칸 청소
			if(arr[curX][curY]==0 && !visited[curX][curY]) {
				visited[curX][curY]=true;
				result++;
			}
			
			//4방향 모두 청소가 된 경우
			if(checkClean(curX,curY)) {
				//후진 X,Y주소
				int behindX= curX+dx[(direction+2) % 4];
				int behindY= curY+dy[(direction+2) % 4];
				//후진이 가능하면 후진
				if(arr[behindX][behindY]==0) {
					curX=behindX;
					curY=behindY;
					continue;
				}
				//뒤에 벽이면 작동을 멈춘다
				else if(arr[behindX][behindY]==1) {
					flag=true;
				}
			}
			//4방향 중 청소가 안 된 곳이 있으면
			else {
				//반시계 회전
				direction=(direction+3) % 4;
				int forwordX = curX+dx[direction];
				int forwordY = curY+dy[direction];
				//땅이고 청소가 되지 않았으면
				if(arr[forwordX][forwordY]==0 && !visited[forwordX][forwordY]) {
					curX=forwordX;
					curY=forwordY;
				}
			}
			
		}
		System.out.println(result);
		
		//2-1. 현재 칸의 주변 4칸 중 4칸 모두 청소된 경우 - 바라보는 방향을 유지한 채 한칸 후진 할 수 있다면 후진하고 1번으로 돌아감
		//2-2. 바라보는 방향의 뒤쪽칸이 벽이면 작동을 멈춘다.
		
		//3-1 주변 4칸 중 청소되지 않은 빈칸이 있으면 - 반시계로 90도 회전 후 그 방향의 앞 칸이 청소되지 않은 빈 칸이면 전진하고 1번으로 돌아감
	}
	
	/**4방향이 모두 청소됐는지 검사*/
	private static boolean checkClean(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			//4방향중에 청소할 곳이 남아있으면
			if(arr[nx][ny]==0 && !visited[nx][ny]) {
				return false;
			}
		}
		//4방향 모두 청소가 되어있거나 벽인 경우 true 리턴
		return true;
	}
	
	/**배열 출력*/
	private static void printRobot() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}