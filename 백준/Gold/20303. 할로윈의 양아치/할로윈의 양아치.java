import java.util.*;
import java.io.*;

class Candy {
	int count;
	int value;

	public Candy(int count, int value) {
		this.count = count;
		this.value = value;
	}
}

public class Main {
	static int N, M, K;
	static ArrayList<Integer>[] arrList;
	static int[] arr;
	static boolean[] visited;
	static ArrayList<Candy> candy = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N 아이 수 | M 관계 수 | K 제한 수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		arrList = new ArrayList[N];
		visited = new boolean[N];
		int dp[] = new int[K + 1];

		for (int i = 0; i < N; i++) {
			arrList[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			arrList[a].add(b);
			arrList[b].add(a);
		}

		// 각각의 아이와 관계를 가진 아이를 찾아내어 그룹으로 만든다. {count : x, value : y}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				startGroup(i);
			}
		}

		// 이것을 배낭문제처럼 dp로 만든다.
		// 관계는 1부터 N까지 움직이면서 관계가 있는 친구들 true로하고 값을 저장.
		// 그래프 + DP
		for (int i = 0; i < candy.size(); i++) {
//			System.out.println("아이 수 : " + candy.get(i).count + " | 사탕 수 : " + candy.get(i).value);

			int count = candy.get(i).count;
			int value = candy.get(i).value;

			if (count > K) {
				continue;
			}
			for (int j = K; j >= count; j--) {
				dp[j] = Math.max(dp[j], dp[j - count] + value);
			}
			dp[count] = Math.max(dp[count], value);
		}

		int result = 0;
		for (int i = 0; i < K; i++) {
			if (result < dp[i]) {
				result = dp[i];
			}
		}
		System.out.println(result);
		// printArr();
	}

	private static void startGroup(int start) {

		Deque<Integer> queue = new LinkedList<>();
		queue.add(start);

		int count = 0;
		int value = 0;

		while (!queue.isEmpty()) {
			int num = queue.pollFirst();

			if (visited[num]) {
				continue;
			}
			visited[num] = true;
			count++;
			value += arr[num];
			for (int next : arrList[num]) {
				if (!visited[next]) {
					queue.add(next);
				}
			}
		}
		candy.add(new Candy(count, value));
	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print("");
			}
			System.out.println();
		}
	}
}