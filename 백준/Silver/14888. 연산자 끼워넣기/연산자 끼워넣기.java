import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] num;
    static int[] cal = new int[4];
    static int[] p;
    static int[] pCal = new int [4];
    static int max =Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        num = new int[N];
        p = new int[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cal[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);
        //연산자가 0 보다 클 때 1개씩 줄여가면서 만들기. num-1개 만큼
        //2 1 1 1 일 때 배열에 담기는건 [0,1,2,3,0] [0,0,1,2,3] 등등
        System.out.println(max);
        System.out.println(min);
    }
    public static void perm(int cnt){
        if(cnt==N-1){
//            System.out.println(Arrays.toString(p));
            int n = calculate(p);

            if(n > max){
                max=n;
            }
            if (n < min){
                min = n;
            }
//            여기에 계산하는 함수 만들기
        }else{
            for (int i = 0; i < 4; i++) {
//               하나 선택하고 perm 돌리기
//               선택을 할 때 if 문으로 검사 i번째 샌택 할 게 기존 cal[i]보다 작아야함
                if(cal[i] <= pCal[i]){
                    continue;
                }
                p[cnt] = i;
                pCal[i]+=1;
                perm(cnt+1);
                pCal[i]-=1;
                p[cnt] = 0;
            }
        }
    }
    public static int calculate(int[] arr){
//        num배열을 처음부터 돌면서 해당 연산자 계산하기
        int c = num[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0){
                c += num[i+1];
            }
            else if(arr[i]==1){
                c -= num[i+1];
            }
            else if(arr[i]==2){
                c *= num[i+1];
            }else if(arr[i]==3){
                c /= num[i+1];
            }
        }
        return c;
    }
}