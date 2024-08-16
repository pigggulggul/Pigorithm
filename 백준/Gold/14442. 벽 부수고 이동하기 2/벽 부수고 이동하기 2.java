import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] wall;
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        wall = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                wall[i][j] = line.charAt(j) - '0';
                visited[i][j] = 11;  // 11로 초기화, 자바에서 인피니티 대신 사용
            }
        }

        bfs();

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 1});
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int floor = current[2];
            int count = current[3];

            if (x == N - 1 && y == M - 1) {
                min = Math.min(min, count);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!checkWall(nx, ny)) continue;

                if (wall[nx][ny] == 0 && visited[nx][ny] > floor) {
                    visited[nx][ny] = floor;
                    queue.add(new int[]{nx, ny, floor, count + 1});
                } else if (wall[nx][ny] == 1 && floor < K && visited[nx][ny] > floor + 1) {
                    visited[nx][ny] = floor + 1;
                    queue.add(new int[]{nx, ny, floor + 1, count + 1});
                }
            }
        }
    }

    static boolean checkWall(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}