import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String num;
		while ((num = br.readLine())!=null && !num.isEmpty()) {{
				st = new StringTokenizer(num);
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				System.out.println(a + b);
			}

		}

	}

}