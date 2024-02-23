import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static char[] updown;
    static char[] numChar;
    static boolean[] numberFlag;
    static long minNum = Long.MAX_VALUE;
    static long maxNum = Long.MIN_VALUE;
    static String minString;
    static String maxString;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        updown = new char[N];
        numChar = new char[N+1];
        numberFlag = new boolean[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            updown[i] = st.nextToken().charAt(0);
        }
        BT(0,0);
        System.out.println(maxString);
        System.out.println(minString);
//        printArr();
    }
//14321
    //
    private static void BT(int x, long num) {
         if(x == N+1){

            if(num > maxNum){
//                System.out.println("max 갱신"+num);
//                System.out.println(Arrays.toString(numberFlag));

                String a="";
                for (int i = 0; i < numChar.length; i++) {
                    a+=numChar[i]-'0'+"";
                }
                maxNum = num;
                maxString=a;

                maxNum = num;
            }
            if(num < minNum){
                String a="";
                for (int i = 0; i < numChar.length; i++) {
                    a+=numChar[i]-'0'+"";
                }
                minNum = num;
                minString=a;
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (numberFlag[i]) {continue;}
            numberFlag[i]=true;
            num *=10;
            num += i;
            numChar[x]=(char)(i+'0');

            if(checkNum(x)){
                numberFlag[i]=false;
                num -= i;
                num /=10;
                continue;
            }
            BT(x+1,num);

            num -= i;
            num /=10;
            numberFlag[i]=false;
        }
    }

    private static boolean checkNum(int x) {

        if(x>=1 && updown[x-1]=='<' && numChar[x-1]-'0'>numChar[x]-'0'){
            return true;
        }
        if(x>=1 && updown[x-1]=='>' && numChar[x-1]-'0'<numChar[x]-'0'){
            return true;
        }else{
            return false;
        }
    }

    private static void printArr() {
        for (int j = 0; j < updown.length; j++) {
            System.out.println(updown[j]);
        }
    }

}