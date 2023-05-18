import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, ans;
    static int[][] grid;
    static Map<Integer, Point> map;
    static Map<Point, Point> wormHoles;
    static final int[] dy = new int[] {0, 1, 0, -1};
    static final int[] dx = new int[] {1, 0, -1, 0};
    static final int[][] rotations = new int[][] {
            {2, -1, 1, 2},
            {2, 2, -1, 1},
            {1, 2, 2, -1},
            {-1, 1, 2, 2},
    };

    public static void main(String[] args) throws Exception {
        int tcCnt = Integer.parseInt(br.readLine());
        for (int tcNum = 1; tcNum <= tcCnt; tcNum++) {
            System.out.print("#" + tcNum + " ");
            solution();
        }

    }

    static void solution() throws Exception {
        n = Integer.parseInt(br.readLine().trim());
        grid = new int[n][n];
        map = new HashMap<>();
        wormHoles = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());

                // 웜홀
                if (6 <= grid[i][j] && grid[i][j] <= 10) {
                    if (map.containsKey(grid[i][j])) {
                        wormHoles.put(new Point(j, i), map.get(grid[i][j]));
                        wormHoles.put(map.get(grid[i][j]), new Point(j, i));
                    } else {
                        map.put(grid[i][j], new Point(j, i));
                    }
                }
            }
        }

        ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }

                for (int dir = 0; dir < 4; dir++) {
                    ans = Math.max(ans, go(i, j, dir));
                }
            }
        }

        System.out.println(ans);
    }

    static int go(int row, int col, int dir) {
        int startRow = row;
        int startCol = col;
        
        int score = 0;
        while (true) {
            int nRow = row + dy[dir];
            int nCol = col + dx[dir];

            if (!inRange(nRow, nCol)) {
                score++;
                dir = (dir + 2) % 4; // 180도 회전
                row = nRow;
                col = nCol;
                continue;
            }

            // 블랙홀 또는 시작점
            if (grid[nRow][nCol] == -1 || (nRow == startRow && nCol == startCol)) {
                return score;
            }

            if (grid[nRow][nCol] == 5) {
                score++;
                dir = (dir + 2) % 4; // 180도 회전
                row = nRow;
                col = nCol;
                continue;
            }

            // 1 ~ 4번 블록
            if (1 <= grid[nRow][nCol] && grid[nRow][nCol] <= 4) {
                score++;
                dir = (4 + dir  + rotations[grid[nRow][nCol] - 1][dir]) % 4;
                row = nRow;
                col = nCol;
                continue;
            }

            // 웜홀
            if (6 <= grid[nRow][nCol] && grid[nRow][nCol] <= 10) {
                Point point = wormHoles.get(new Point(nCol, nRow));
                row = point.y;
                col = point.x;
                continue;
            }



            // 빈 공간
            row = nRow;
            col = nCol;
        }
    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}
