import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int hear;
	static int see;
	static List<String> newSentence;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		hear = Integer.parseInt(st.nextToken());
		see = Integer.parseInt(st.nextToken());
		newSentence = new ArrayList<String>();

		HashMap<String,String> map = new HashMap<String,String>();
		
		for (int i = 0; i < hear; i++) {
			String a = br.readLine();
			map.put(a, "");
		}
		for (int i = 0; i < see; i++) {
			String val = br.readLine();
			if(map.containsKey(val)) {
				newSentence.add(val);
			}
			map.remove(val);
		}
		Collections.sort(newSentence);
		System.out.println(newSentence.size());
		for (int i = 0; i < newSentence.size(); i++) {
			System.out.println(newSentence.get(i));
		}
	}

}