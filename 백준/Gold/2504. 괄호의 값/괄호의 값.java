import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Stack<Character> stack = new Stack<>();
		// printArr();

		// ( () [ [] ] )
		// value=1이고 (를 만나면 *2 [를 만나면 *3, )를 만나면 value/2하고 answer에 더하기
		// 4

		String str = br.readLine();
		int value = 1;
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (a == '(') {
				stack.push(a);
				value *= 2;
			} else if (a == ')') {
				if (stack.isEmpty() || stack.peek() != '(') {
					result = 0;
					break;
				} else if (str.charAt(i-1) == '(') {
					result += value;
				}
				stack.pop();
				value /= 2;
			} else if (a == '[') {
				stack.push(a);
				value *= 3;
			} else if (a == ']') {
				if (stack.isEmpty() || stack.peek() != '[') {
					result = 0;
					break;
				} else if (str.charAt(i-1) == '[') {
					result += value;
				}
				stack.pop();

				value /= 3;
			}
//			System.out.println("value = " + value + " | result = " + result);
		}
		if (!stack.isEmpty())
			System.out.println(0);
		else
			System.out.println(result);
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