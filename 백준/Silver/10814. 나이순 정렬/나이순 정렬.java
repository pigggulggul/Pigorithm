import java.util.*;
import java.io.*;
class board {
	int age;
	String name;
	public board(int age, String name) {
		this.age=age;
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
}
public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board[] arr = new board[N];
		StringTokenizer st;
		for(int i = 0 ; i < N ; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String b = st.nextToken();
			arr[i]=new board(a,b);
		}
		Arrays.sort(arr,(s1,s2)->{
			return s1.age-s2.age;
		});
		
		for(int i = 0 ; i < N ; i++) {
			System.out.println(arr[i].age+" "+arr[i].name);
		}
	}

}