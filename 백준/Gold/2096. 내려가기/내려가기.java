import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[N][3];
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        int[] dpStore = new int[3];

        // Read input values
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        // Initialize the dp arrays with the first row values
        maxDp[0] = arr[0][0];
        maxDp[1] = arr[0][1];
        maxDp[2] = arr[0][2];

        minDp[0] = arr[0][0];
        minDp[1] = arr[0][1];
        minDp[2] = arr[0][2];

        // Update dp arrays for each subsequent row
        for (int i = 1; i < N; i++) {
            dpStore[0] = Math.max(maxDp[0], maxDp[1]) + arr[i][0];
            dpStore[1] = Math.max(Math.max(maxDp[0], maxDp[1]),maxDp[2]) + arr[i][1];
            dpStore[2] = Math.max(maxDp[1], maxDp[2]) + arr[i][2];
            System.arraycopy(dpStore, 0, maxDp, 0, 3);

            dpStore[0] = Math.min(minDp[0], minDp[1]) + arr[i][0];
            dpStore[1] = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]) + arr[i][1];
            dpStore[2] = Math.min(minDp[1], minDp[2]) + arr[i][2];
            System.arraycopy(dpStore, 0, minDp, 0, 3);
        }

        int maxPoint = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int minPoint = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);

        // Output result
        System.out.println(maxPoint + " " + minPoint);

        br.close();
    }
}