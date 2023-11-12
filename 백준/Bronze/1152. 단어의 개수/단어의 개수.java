import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String sentence = br.readLine();
		int result=1;
		sentence=sentence.trim();
		if(sentence.length()==0) {
			System.out.println("0");
		}else {
			
			for (int i = 0; i < sentence.length(); i++) {
				char a = sentence.charAt(i);
				if(a==' ') {
					result+=1;
				}
				
			}
			System.out.println(result);
		}
	}

}