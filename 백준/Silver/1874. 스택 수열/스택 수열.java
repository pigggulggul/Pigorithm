import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N;
	static int arr[];
	static Deque<Integer> deque = new ArrayDeque<Integer>();
	static Deque<Character> saveDeque = new ArrayDeque<Character>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		arr = new int[N];
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int num = 1;
		int check = 0;
		while(num<=N && check<=N) {
			saveDeque.addLast('+');
			deque.addLast(num);
			if(arr[check]==num) {
				while(!deque.isEmpty() && arr[check] == deque.peekLast()) {
					saveDeque.addLast('-');
					deque.pollLast();
					check++;
					if(check==N) {
						break;
					}
				}
				num++;
				continue;
			}
			num++;
		}
		if(!deque.isEmpty()) {
			System.out.println("NO");
		}else {
			while(!saveDeque.isEmpty()) {
				System.out.println(saveDeque.pollFirst());
			}
		}
		
	}

}
