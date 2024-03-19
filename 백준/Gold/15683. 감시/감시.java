import java.util.*;
import java.io.*;

class cctv_15683 {
    int x;
    int y;
    int dir;

    public cctv_15683(int x,int y,int dir){
        this.x=x;
        this.y=y;
        this.dir=dir;
    }
}
public class Main {
    static int dx[]={0,1,0,-1};
    static int dy[]={-1,0,1,0};
    static int N,M;
    static int[][] arr;
    static List<cctv_15683> list = new ArrayList();
    static int result=Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0 ; i < N ; i++){
            st= new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0 ; i < N ; i++){
            for (int j = 0 ; j < M ; j++){
                if(arr[i][j]==5){
                    operationFive(i,j);
                }else if(arr[i][j]==1 ||arr[i][j]==2 ||arr[i][j]==3 ||arr[i][j]==4 ){
                    list.add(new cctv_15683(i,j,arr[i][j]));
                }
            }
        }

//        for (int j = 0; j < list.size(); j++) {
//            System.out.print(list.get(j).x + " | " + list.get(j).y + " | "+ list.get(j).dir);
//            System.out.println();
//        }
//        System.out.println("시작 Arr");
        int[] combiArr= new int[list.size()];
        Combi(combiArr,0);

        System.out.println(result);
    }

    private static void Combi(int[] combiArr,int cnt) {
        if(cnt==list.size()){
            int[][] cloneArr = new int[N][M];
            for (int i = 0; i < N; i++) {
                cloneArr[i] = arr[i].clone();
            }
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).dir==2 && (combiArr[i]==2 || combiArr[i]==3))return;
                cloneArr=detectCCTV(list.get(i),combiArr[i],cloneArr);
//                for (int j = 0; j < N; j++) {
//                    for (int k = 0; k < M; k++) {
//                        System.out.print(cloneArr[j][k]+" ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("-------");
            }
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(cloneArr[i][j]==0){
                        count++;
                    }
                }
            }
            if(result > count){
                result=count;
            }
            return;
        }
        else{
            for (int i = 0; i < 4; i++) {
                combiArr[cnt]=i;
                Combi(combiArr,cnt+1);
            }
        }
    }

    private static int[][] detectCCTV(cctv_15683 o, int i,int[][] cloneArr) {
        int x = o.x;
        int y = o.y;
        int dir = o.dir;
        if(dir==1){
            int nx = x+dx[i];
            int ny = y+dy[i];
            while(checkWall(nx,ny)){
                if(cloneArr[nx][ny]==6){
                    break;
                }
                if (cloneArr[nx][ny]==0) {
                    cloneArr[nx][ny] = -1;
                }
                nx += dx[i];
                ny += dy[i];
            }
        } else if(dir==2){
            if(i==2 || i==3){
                return cloneArr;
            }
            for (int j = 0; j < 2; j++) {
                int nx = x+dx[i+j*2];
                int ny = y+dy[i+j*2];
                while(checkWall(nx,ny)){
                    if(cloneArr[nx][ny]==6){
                        break;
                    }
                    if (cloneArr[nx][ny]==0) {
                        cloneArr[nx][ny] = -1;
                    }
                    nx += dx[i+j*2];
                    ny += dy[i+j*2];
                }
            }
        }
        else if(dir==3){
            for (int j = 0; j < 2; j++) {
                int nx = x+dx[(i+j)%4];
                int ny = y+dy[(i+j)%4];
                while(checkWall(nx,ny)){
                    if(cloneArr[nx][ny]==6){
                        break;
                    }
                    if (cloneArr[nx][ny]==0) {
                        cloneArr[nx][ny] = -1;
                    }
                    nx += dx[(i+j)%4];
                    ny += dy[(i+j)%4];
                }
            }
        }else {
            for (int j = 0; j < 3; j++) {
                int nx = x+dx[(i+j)%4];
                int ny = y+dy[(i+j)%4];
                while(checkWall(nx,ny)){
                    if(cloneArr[nx][ny]==6){
                        break;
                    }
                    if (cloneArr[nx][ny]==0) {
                        cloneArr[nx][ny] = -1;
                    }
                    nx += dx[(i+j)%4];
                    ny += dy[(i+j)%4];
                }
            }
        }
        return cloneArr;
    }

    private static void printArr() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void operationFive(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x+dx[d];
            int ny = y+dy[d];
            while(checkWall(nx,ny)){
                if(arr[nx][ny]==6){
                    break;
                }
                if (arr[nx][ny]==0) {
                    arr[nx][ny] = -1;
                }
                nx += dx[d];
                ny += dy[d];
            }
        }
    }

    private static boolean checkWall(int x, int y) {
        return x>=0 && x<N && y>=0 && y<M;
    }
}
//최소 감시 크기 구하기
//1번은 한 방향
//2번은 양방향
//3번은 ㄱ방향
//4번은 3방향
//5번은 네방향
//NxM 배열이 주어졌을시
//5번이 있으면 일단 다 칠해놓는다
//백트래킹 문제