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

    static int n, m;
    static int[][] grid;
    static boolean[][][] visited;
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};

    static class MyPoint extends Point {
        int breakCnt;

        public MyPoint(int x, int y, int breakCnt) {
            super(x, y);
            this.breakCnt = breakCnt;
        }

    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m][2]; // 0 := 벽을 안 부순 경우 1 := 벽을 부순 경우

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(go());
    }

    static int go() {
        Queue<MyPoint> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(visited[i][j], false);
            }
        }
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        queue.add(new MyPoint(0, 0, 0));

        int len = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                MyPoint cur = queue.poll();

                if (cur.y == n - 1 && cur.x == m - 1) {
                    return len;
                }

                for (int i = 0; i < 4; i++) {
                    int nRow = cur.y + dy[i];
                    int nCol = cur.x + dx[i];

                    if (!inRange(nRow, nCol)) {
                        continue;
                    }

                    // 이동할 칸이 벽이고 부술 수 있는 경우
                    if (grid[nRow][nCol] == 1 && cur.breakCnt == 0 && !visited[nRow][nCol][cur.breakCnt + 1]) {
                        visited[nRow][nCol][cur.breakCnt + 1] = true;
                        queue.add(new MyPoint(nCol, nRow, cur.breakCnt + 1));

                        continue;
                    }

                    // 이동할 칸이 벽이 아닌 경우
                    if (grid[nRow][nCol] == 0 && !visited[nRow][nCol][cur.breakCnt]) {
                        visited[nRow][nCol][cur.breakCnt] = true;
                        queue.add(new MyPoint(nCol, nRow, cur.breakCnt));
                    }
                }

            }

            len++;
        }

        return -1;
    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }
}
