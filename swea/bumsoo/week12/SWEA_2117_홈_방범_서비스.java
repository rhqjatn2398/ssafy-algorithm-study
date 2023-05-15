import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t;
    static int n, m;
    static int[][] grid;
    static int ans;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int tcNum = 1; tcNum <= t; tcNum++) {
            System.out.print("#" + tcNum + " ");
            solution();
            System.out.println();
        }
    }

    static void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 1; k <= 2 * n; k++) {
                    go(i, j, k);
                }
            }
        }

        System.out.print(ans);
    }

    static void go(int centerRow, int centerCol, int serviceLen) {
        int row = centerRow - (serviceLen - 1);
        int col = centerCol - (serviceLen - 1);
        int len = 2 * serviceLen - 1;

        int houseCnt = 0;

        for (int i = 0; i < len; i++) {
            int k = Math.abs(i - len / 2);

            for (int j = 0; j < len; j++) {
                int curRow = row + i;
                int curCol = col + j;

                if (inRange(curRow, curCol) && k <= j && j < len - k) {
                    if (grid[curRow][curCol] == 1) {
                        houseCnt++;
                    }
                }
            }
        }


        int profit = houseCnt * m - (serviceLen * serviceLen + (serviceLen - 1) * (serviceLen - 1));

        if (profit >= 0) {
            ans = Math.max(ans, houseCnt);
        }

    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}
