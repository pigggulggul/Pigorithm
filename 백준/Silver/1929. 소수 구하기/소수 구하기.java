import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int startNum,finishNum;
	static boolean[] isPrime;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		startNum = Integer.parseInt(st.nextToken());
		finishNum = Integer.parseInt(st.nextToken());
		isPrime = new boolean[finishNum+1];
		isPrime[0]=true;
		isPrime[1]=true;
		for (int i = 1; i < finishNum+1; i++) {
			if(!isPrime[i]) {
				for (int j = 2; i*j <= finishNum; j++) {
					isPrime[i*j]=true;
				}	
			}
		}
		
		for (int i = startNum; i <= finishNum; i++) {
			if(!isPrime[i]) {
				System.out.println(i);
			}
		}
	}
}