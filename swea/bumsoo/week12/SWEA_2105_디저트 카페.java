import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, ans;
    static int[][] grid;
    static final int[] dy = new int[] {1, 1, -1, -1};
    static final int[] dx = new int[] {1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        int testNum = Integer.parseInt(br.readLine());
        for (int testCnt = 1; testCnt <= testNum; testCnt++) {
            System.out.print("#" + testCnt + " ");
            solution();
            System.out.println();
        }
    }

    static void solution() throws Exception {
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                go(i, j);
            }
        }

        System.out.print(ans);
    }

    static void go(int row, int col) {
        // 7 to 1 diagonal len
        for (int i = 1; i < n; i++) {
            // 11 to 5 diagonal len
            for (int j = 1; j < n; j++) {
                if (isRecInRange(row, col, i, j)) {
                    ans = Math.max(ans, calDessertCnt(row, col, i, j));
                }
            }
        }
    }

    static int calDessertCnt(int row, int col, int diagLen7, int diagLen11) {
        Set<Integer> set = new HashSet<>();
        int dir = 0;
        int moveCnt = 0;
        int totalMove = diagLen7 * 2 + diagLen11 * 2;

        for (int i = 0; i < totalMove; i++) {
            int nRow = row + dy[dir];
            int nCol = col + dx[dir];

            int curDessert = grid[nRow][nCol];
            if (set.contains(curDessert)) {
                return -1;
            }

            set.add(curDessert);

            moveCnt++;

            if (moveCnt == diagLen11 || moveCnt == diagLen7 + diagLen11 || moveCnt == diagLen7 + diagLen11 + diagLen11) {
                dir = (dir + 1) % 4;
            }

            row = nRow;
            col = nCol;
        }

        return set.size();
    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }

    static boolean isRecInRange(int row, int col, int diagLen7, int diagLen11) {
        return (0 <= row + diagLen11 && row + diagLen11 < n && 0 <= col + diagLen11 && col + diagLen11 < n) && (0 <=  row + diagLen11 + diagLen7 && row + diagLen11 + diagLen7 < n && 0 <= col + diagLen11 - diagLen7 && col + diagLen11 - diagLen7 < n) && (0 <= row + diagLen7 && row + diagLen7 < n && 0 <= col - diagLen7 && col - diagLen7 < n);
    }
}
