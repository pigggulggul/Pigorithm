
import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int[][] city ;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int x = Integer.parseInt(br.readLine());
			int y = Integer.parseInt(br.readLine());
			city = new int[x+1][y+1];
			for (int j = 0; j < x+1; j++) {
				city[j][0]=1;
			}
			for (int j = 0; j < y+1; j++) {
				city[0][j]=j+1;
			}
			
			count = building(x,y-1); //1,3
			System.out.println(count);

			
		}
	}
	private static int building(int x, int y) {
		if(city[x][y]!=0) {
			return city[x][y];
		}
		
		return city[x][y] = building(x-1,y)+building(x,y-1);
	}

}