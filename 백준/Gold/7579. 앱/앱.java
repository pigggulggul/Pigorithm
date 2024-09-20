import java.util.*;
import java.io.*;

class App {
	int memory;
	int cost;

	public App(int memory, int cost) {
		this.memory = memory;
		this.cost = cost;
	}

}

public class Main {

	static int N, M, T;
	static int maxCost = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		App[] app = new App[N];
		int[] a = new int[N];
		int[] b = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			app[i] = new App(a[i], b[i]);
		}
		int[] dp = new int[10001];
		int[] newDp = new int[10001];

//		Arrays.sort(app, (o1, o2) -> {
//			if (o1.cost == 0) {
//				return o1.cost - o2.cost;
//			} else if (o2.cost == 0) {
//				return o1.cost - o2.cost;
//			}
//			return o2.memory / o2.cost - o1.memory / o1.cost;
//		});

//		for (int i = 0; i < N; i++) {
//			System.out.println(app[i].memory + " " + app[i].cost);
//		}

		for (int i = 0; i < N; i++) {
			int value = app[i].memory;
			int cost = app[i].cost;
			newDp = dp.clone();
			for (int j = 0; j < dp.length; j++) {
				if (dp[j] == 0)
					continue;
				if (j + cost < 10001) {
					newDp[j + cost] = Math.max(dp[j + cost], dp[j] + value);
				}
			}
			newDp[cost] = Math.max(newDp[cost], value);
			dp = newDp;
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] >= M) {
				if (min > i) {
					min = i;
				}
			}
		}
		System.out.println(min);

//		5 60
//		10 20 30 35 40
//		0 3 3 5 4

		// dp로 접근했을 때 memory로접근? cost로 접근?
		// memory로 접근했을 때
		// 일단 cost가 0인 값들은 M에서 -해줘야함
		// M= M- cost0인 값들
		// 20 3
		// 30 3
		// 20+30 = 50 6
		// 40
		// 20+40 = 60 7
		// 30+40 = 70 7
		// 50+40 = 90 10
		// 근데 M이 1000만이라 1000만 크기의 배열은 너무 큼
		// cost로 접근
		// 3 20
		// 3 30
		// 6 50
		// 5 35
		// 8 55, 8 65
		// 이런식으로해서 []가 가장 적은 cost로 하자

	}

	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" ");
			}
			System.out.println();
		}

	}

}