import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static long b;
    static int[][] matrix;
    static final int MOD = 1000;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());

        matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        matrix = go(matrix, b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] go(int[][] a, long b) {
        if (b == 1) {
            return a;
        } else if (b == 2) {
            return multiplication(a, a);
        }

        // b가 짝수면 a^(b / 2) * a^(b / 2)
        if (b % 2 == 0) {
            return go(go(a, b / 2), 2);
        } else {
            // b가 홀수면 a^(b / 2) * a^(b / 2) * a
            return multiplication(go(go(a, b / 2), 2), a);
        }
    }

    /**
     * 행렬곱
     * @param a 행렬1
     * @param b 행렬2
     * @return
     */
    static int[][] multiplication(int[][] a, int[][] b) {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += (a[i][k] * b[k][j]) % MOD;
                }

                result[i][j] = sum % MOD;
            }
        }

        return result;
    }
}