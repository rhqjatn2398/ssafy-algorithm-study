import java.util.Arrays;

class Solution {
    static final int MAX = 100_000 * 299;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX;
        int[][] d = new int[n + 1][n + 1];

        for (int i = 0; i < d.length; i++) {
            Arrays.fill(d[i], MAX);
            d[i][i] = 0;
        }

        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];

            d[from][to] = cost;
            d[to][from] = cost;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, d[s][i] + d[i][a] + d[i][b]);
        }

        return answer;
    }
}