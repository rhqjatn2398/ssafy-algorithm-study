import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m, c, ans;
    static int[][] grid, memo;
    static int[] honeyLefts;

    public static void main(String[] args) throws Exception {
        int tcCnt = Integer.parseInt(br.readLine());
        for (int tcNum = 1; tcNum <= tcCnt; tcNum++) {
            System.out.print("#" + tcNum + " ");
            solution();
        }

    }

    static void solution() throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        memo = new int[n][n];

        honeyLefts = new int[2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        go(0, 0);

        System.out.println(ans);
    }

    /**
     * 조합
     */
    static void go(int cnt, int start) {
        if (cnt == 2) {
            ans = Math.max(ans, collect());
            return;
        }

        for (int i = start; i < n * n; i++) {
            if ((i % n) <= n - m) {
                honeyLefts[cnt] = i;
                go(cnt + 1, i + m);
            }

        }
    }

    static int collect() {
        int result = 0;

        for (int i = 0; i < 2; i++) {
            int row = honeyLefts[i] / n;
            int col = honeyLefts[i] % n;

            if (memo[row][col] != 0) {
                result += memo[row][col];
            } else {
                memo[row][col] = go2(0, row, col, new boolean[m]);
                result += memo[row][col];
            }

        }

        return result;
    }

    static int go2(int cnt, int row, int col, boolean[] selected) {
        if (cnt == m) {
            int value = 0;
            int amount = 0;
            for (int j = 0; j < m; j++) {
                if (!selected[j]) {
                    continue;
                }

                int honey = grid[row][col + j];

                value += honey * honey;
                amount += honey;
            }

            if (amount <= c) {
                return value;
            }

            return 0;
        }

        selected[cnt] = true;
        int ret1 = go2(cnt + 1, row, col, selected);
        selected[cnt] = false;
        int ret2 = go2(cnt + 1, row, col, selected);

        return Math.max(ret1, ret2);
    }
}
