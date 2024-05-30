import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int L;
    static int arr[][];
    static int availLine=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        L= Integer.parseInt(st.nextToken());
        arr=new int[N][N];
        for (int i = 0; i < N; i++) {
            st= new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j]= Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            boolean visited[] =new boolean[N];
            int num = arr[i][0];
            if(checkArrRow(i,visited,num)){
//                System.out.println(i + " | clear");
                availLine++;
            }
        }
        for (int i = 0; i < N; i++) {
            boolean visited[] =new boolean[N];
            int num = arr[0][i];
            if(checkArrCol(i,visited,num)){
//                System.out.println(i + " | clear");
                availLine++;
            }
        }
        System.out.println(availLine);
    }
    private static boolean checkArrCol(int j, boolean[] visited, int num) {
        for (int i = 1; i < N; i++) {
            if(arr[i][j] != num) {
                if (Math.abs(arr[i][j] - num) > 1) {
//                    System.out.println("i : " + i + " j :  " + j +" | "+"숫자가다름 : false");
                    return false;
                }else if(arr[i][j] > num){
                    if(i - L < 0){
//                        System.out.println("i : " + i + " j :  " + j +" | "+"길이가딸림 : false");
                        return false;
                    }
                    int count=0;
                    for (int k = i-1; k > i-1-L; k--) {
                        if(!visited[k] && arr[k][j]==num){
                            count++;
                            visited[k]=true;
                        }
                    }
                    if(count != L){
//                        System.out.println("i : " + i + " j :  " + j +" |  count : "+count+" | "+"경사가 부족함 : false");
                        return false;
                    }
                    num = arr[i][j];
                } else if(arr[i][j] < num){
                    if(i + L > N){
//                        System.out.println("i : " + i + " j :  " + j +" | "+"숫자가초과 : false");
//                        System.out.println("num : "+num);
                        return false;
                    }
                    int count=0;
                    for (int k = i; k < i+L; k++) {
                        if(!visited[k] && arr[k][j]==num-1){
                            count++;
                            visited[k]=true;
                        }
                    }
                    if(count != L){
//                        System.out.println("i : " + i + " j :  " + j +" |  count : "+count+" | "+"경사가 부족함 : false");
                        return false;
                    }
                    num = arr[i][j];
                }

            }
        }
        return true;
    }
    private static boolean checkArrRow(int i, boolean[] visited, int num) {
        for (int j = 1; j < N; j++) {
            if(arr[i][j] != num) {
                if (Math.abs(arr[i][j] - num) > 1) {
                    return false;
                }else if(arr[i][j] > num){
                    if(j - L < 0){
                        return false;
                    }
                    int count=0;
                    for (int k = j-1; k > j-1-L; k--) {
                        if(!visited[k] && arr[i][k]==num){
                            count++;
                            visited[k]=true;
                        }
                    }
                    if(count != L){
                        return false;
                    }
                    num = arr[i][j];
                } else if(arr[i][j] < num){
                    if(j + L > N){
                        return false;
                    }
                    int count=0;
                    for (int k = j; k < j+L; k++) {
                        if(!visited[k] && arr[i][k]==num-1){
                            count++;
                            visited[k]=true;
                        }
                    }
                    if(count != L){
                        return false;
                    }
                    num = arr[i][j];
                }

            }

        }
        return true;
    }
}