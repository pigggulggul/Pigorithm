import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class block{
    int startPos;
    int value;

    public block(int startPos,int value){
        this.startPos=startPos;
        this.value=value;
    }
}
public class Main {
    static int []dx = {0,1,0,-1};
    static int []dy = {-1,0,1,0};
    static int p[];
    static int N;
    static int maxValue = -1;

    static int plate[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        plate=new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                plate[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        p = new int[5];
        PermDirection(0);
        System.out.println(maxValue);
    }

    private static void PermDirection(int cnt) {
        if(cnt==5){
//            System.out.println(Arrays.toString(p));
            start2048(plate);
            return;
        }
        else{
            for (int i = 0; i < 4; i++) {
                p[cnt]=i;
                PermDirection(cnt+1);
            }
        }
    }

    private static void start2048(int[][] plateArr) {
        int[][] newArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            newArr[i]=plateArr[i].clone();
        }
        for (int i = 0; i < 5; i++) {
            mixBlock(newArr,p[i]);
            moveBlock(newArr,p[i]);
        }
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(newArr[i]));
//        }
//        System.out.println();
        int max = topBlock(newArr);
        if(maxValue < max){
            maxValue = max;
        }
    }

    private static void mixBlock(int[][] plateArr,int dir) {
        // 방향이 0이면 위에 방향으로 붙여야하니까 0,0에서 y값 증가하면서 비교
        if(dir==0){
            for (int j = 0; j < N; j++) {
                block lastBlock = new block(0,plateArr[0][j]);
                for (int i = 0; i < N; i++) {
                    if(i==0 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != lastBlock.value){
                        lastBlock= new block(i,plateArr[i][j]);
                    }
                    else if(plateArr[i][j] == lastBlock.value){
                        plateArr[lastBlock.startPos][j] *=2;
                        plateArr[i][j] =0;
                        lastBlock = new block(i,0);
                    }
                }
            }
        }else if (dir==2){
            for (int j = 0; j < N; j++) {
                block lastBlock = new block(N-1,plateArr[N-1][j]);
                for (int i = N-1; i >=0; i--) {
                    if(i==N-1 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != lastBlock.value){
                        lastBlock= new block(i,plateArr[i][j]);
                    }
                    else if(plateArr[i][j] == lastBlock.value){
                        plateArr[lastBlock.startPos][j] *=2;
                        plateArr[i][j] =0;
                        lastBlock = new block(i,0);
                    }
                }
            }
        }else if (dir==1){
            for (int i = 0; i < N; i++) {
                block lastBlock = new block(N-1,plateArr[i][N-1]);
                for (int j = N-1; j >=0; j--) {
                    if(j==N-1 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != lastBlock.value){
                        lastBlock= new block(j,plateArr[i][j]);
                    }
                    else if(plateArr[i][j] == lastBlock.value){
                        plateArr[i][lastBlock.startPos] *=2;
                        plateArr[i][j] =0;
                        lastBlock = new block(j,0);
                    }
                }
            }
        }else if (dir==3){
            for (int i = 0; i < N; i++) {
                block lastBlock = new block(0,plateArr[i][0]);
                for (int j = 0; j < N; j++) {
                    if(j==0 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != lastBlock.value){
                        lastBlock= new block(j,plateArr[i][j]);
                    }
                    else if(plateArr[i][j] == lastBlock.value){
                        plateArr[i][lastBlock.startPos] *=2;
                        plateArr[i][j] =0;
                        lastBlock = new block(j,0);
                    }
                }
            }
        }

    }
    private static void moveBlock(int[][] plateArr, int dir) {
        if(dir==0){
            for (int j = 0; j < N; j++) {
                block lastBlock = new block(-1,plateArr[0][0]);
                for (int i = 0; i < N; i++) {
                    if(lastBlock.startPos ==-1){
                        if(plateArr[i][j]==0){
                            lastBlock = new block(i,0);
                        }else{
                            continue;
                        }
                    }
                    if(i==0 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != 0 && i != lastBlock.startPos){
                        int temp = plateArr[i][j];
                        plateArr[i][j] =0;
                        plateArr[lastBlock.startPos][j]=temp;
                        lastBlock= new block(lastBlock.startPos+1,plateArr[i][j]);
                    }
                }
            }
        }else if(dir==2){
            for (int j = 0; j < N; j++) {
                block lastBlock = new block(-1,plateArr[0][0]);
                for (int i = N-1; i >=0; i--) {
                    if(lastBlock.startPos ==-1){
                        if(plateArr[i][j]==0){
                            lastBlock = new block(i,0);
                        }else{
                            continue;
                        }
                    }
                    if(i==N-1 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != 0 && i != lastBlock.startPos){
                        int temp = plateArr[i][j];
                        plateArr[i][j] =0;
                        plateArr[lastBlock.startPos][j]=temp;
                        lastBlock= new block(lastBlock.startPos-1,plateArr[i][j]);
                    }
                }
            }
        }else if(dir==1){
            for (int i = 0; i < N; i++) {
                block lastBlock = new block(-1,plateArr[0][0]);
                for (int j = N-1; j >=0; j--) {
                    if(lastBlock.startPos ==-1){
                        if(plateArr[i][j]==0){
                            lastBlock = new block(j,0);
                        }else{
                            continue;
                        }
                    }
                    if(j==N-1 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != 0 && j != lastBlock.startPos){
                        int temp = plateArr[i][j];
                        plateArr[i][j] =0;
                        plateArr[i][lastBlock.startPos]=temp;
                        lastBlock= new block(lastBlock.startPos-1,plateArr[i][j]);
                    }
                }
            }
        }
        else if(dir==3){
            for (int i = 0; i < N; i++) {
                block lastBlock = new block(-1,plateArr[0][0]);
                for (int j = 0; j < N; j++) {
                    if(lastBlock.startPos ==-1){
                        if(plateArr[i][j]==0){
                            lastBlock = new block(j,0);
                        }else{
                            continue;
                        }
                    }
                    if(j==0 || plateArr[i][j]==0){
                        continue;
                    }
                    if(plateArr[i][j] != 0 && j != lastBlock.startPos){
                        int temp = plateArr[i][j];
                        plateArr[i][j] =0;
                        plateArr[i][lastBlock.startPos]=temp;
                        lastBlock= new block(lastBlock.startPos+1,plateArr[i][j]);
                    }
                }
            }
        }
    }
    private static int topBlock(int[][] plateArr) {
        int maxNum = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(plateArr[i][j]>maxNum){
                    maxNum=plateArr[i][j];
                }
            }
        }
        return maxNum;
    }
}