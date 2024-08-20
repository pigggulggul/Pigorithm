import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M, K;
    static int[][] arr;
    static boolean[][] visited;
    static boolean answerFlag = false;
    static int count = 0;
    static List<int[]> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        arr = new int[N][M];
        visited = new boolean[N][M];
        sc.nextLine(); // move to the next line after reading integers

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    int value = bfs(i, j);
                    checkValue(value);
                    count++;
                }
                if (answerFlag) {
                    break;
                }
            }
            if (answerFlag) {
                break;
            }
        }

        if (answerFlag && answer.size() == 0) {
            System.out.println(0);
        } else {
            if (answer.size() > 1) {
                answer.sort((a, b) -> {
                    if (a[0] == b[0]) return a[1] - b[1];
                    return a[0] - b[0];
                });
            }
            System.out.println(answer.size());
            for (int[] ans : answer) {
                System.out.println(ans[0] + " " + ans[1]);
            }
        }
    }

    static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        int oneValue = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!checkWall(nx, ny)) continue;

                if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    oneValue++;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return oneValue;
    }

    static void checkDir(int startX, int startY, List<int[]> checkArr) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        int dir = -1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            checkArr.add(new int[]{x + 1, y + 1});

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!checkWall(nx, ny)) continue;

                if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                    if (dir == -1) dir = d;
                    if (dir != d) {
                        answer = Arrays.asList(new int[]{x + 1, y + 1});
                        answerFlag = true;
                        return;
                    }
                    if (dir == d) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    static boolean checkWall(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void checkValue(int num) {
        if (num == K * 2) {
            answerFlag = true;
            answer = new ArrayList<>();
        } else if (num == K) {
            if (count == 0) {
                answer = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (visited[i][j]) {
                            answer.add(new int[]{i + 1, j + 1});
                        }
                    }
                }
            } else {
                answerFlag = true;
                answer = new ArrayList<>();
            }
        } else if (num == K * 2 - 1) {
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1 && !visited[i][j]) {
                        List<int[]> checkArr = new ArrayList<>();
                        checkDir(i, j, checkArr);
                        if (!answerFlag) {
                            answer = Arrays.asList(checkArr.get(K - 1));
                            answerFlag = true;
                        }
                    }
                    if (answerFlag) break;
                }
                if (answerFlag) break;
            }
        } else {
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1 && !visited[i][j]) {
                        List<int[]> checkArr = new ArrayList<>();
                        checkDir(i, j, checkArr);
                        if (!answerFlag && checkArr.size() >= K) {
                            int len = checkArr.size();
                            answer = new ArrayList<>(checkArr.subList(len - K, len));
                            answerFlag = true;
                        }
                    }
                    if (answerFlag) break;
                }
                if (answerFlag) break;
            }
        }
    }
}