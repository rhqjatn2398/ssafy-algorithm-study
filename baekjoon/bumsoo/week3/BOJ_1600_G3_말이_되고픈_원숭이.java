import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);
    static StringTokenizer st;

    static int k, w, h, ans;
    static int[][] grid;
    static int[][][] dist;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[] jdy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] jdx = {2, 1, -1, -2, -2, -1, 1, 2};

    static class MyPoint extends Point {
        int jumpCnt;

        public MyPoint(int x, int y, int jumpCnt) {
            super(x, y);
            this.jumpCnt = jumpCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        grid = new int[h][w];
        dist = new int[h][w][k + 1];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go();

        ans = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            int candidate = dist[h - 1][w - 1][i];

            // 방문하지 않은 곳은 -1
            if (candidate != -1) {
                ans = Math.min(ans, candidate);
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void go() {
        Queue<MyPoint> queue = new ArrayDeque<>();

        // 초기화
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        dist[0][0][0] = 0;
        queue.add(new MyPoint(0, 0, 0));

        while (!queue.isEmpty()) {
            MyPoint cur = queue.poll();

            // jump를 안하는 경우
            for (int i = 0; i < 4; i++) {
                int nRow = cur.y + dy[i];
                int nCol = cur.x + dx[i];

                // 범위를 벗어나거나 이미 방문했거나 장애물이 있는 경우
                if (!inRange(nRow, nCol) || dist[nRow][nCol][cur.jumpCnt] != -1 || grid[nRow][nCol] == 1) {
                    continue;
                }

                dist[nRow][nCol][cur.jumpCnt] = dist[cur.y][cur.x][cur.jumpCnt] + 1;
                queue.add(new MyPoint(nCol, nRow, cur.jumpCnt));
            }

            // 점프를 할 수 있어서 점프하는 경우
            if (cur.jumpCnt < k) {
                for (int i = 0; i < 8; i++) {
                    int nRow = cur.y + jdy[i];
                    int nCol = cur.x + jdx[i];

                    // 범위를 벗어나거나 이미 방문했거나 장애물이 있는 경우
                    if (!inRange(nRow, nCol) || dist[nRow][nCol][cur.jumpCnt + 1] != -1 || grid[nRow][nCol] == 1) {
                        continue;
                    }

                    dist[nRow][nCol][cur.jumpCnt + 1] = dist[cur.y][cur.x][cur.jumpCnt] + 1;
                    queue.add(new MyPoint(nCol, nRow, cur.jumpCnt + 1));
                }
            }
        }
    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < h && 0 <= col && col < w;
    }
}
