import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] person;
    static int B;
    static int C;
    static long result=0;
    public static void main(String[] args) throws Exception{
        //N개의 시험장
        //시험장 당 인원 수
        //총감독관과 보조감독관. 총은1명 나머지는 보조
        //인원수 - 총감독 했는데 인원수가 남으면 /C + 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        person = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            person[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B= Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            int a = person[i]-B;
            result++;
            if(a>0){
                result+=a/C ;
                a %= C;
                if(a != 0){
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}