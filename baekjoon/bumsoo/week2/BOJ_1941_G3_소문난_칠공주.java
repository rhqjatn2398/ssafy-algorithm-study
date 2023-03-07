import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static StringTokenizer st;

    int ans;
    char[][] grid;
    int[] students;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        Main ps = new Main();

        ps.solution();
    }

    void solution() throws IOException {
        grid = new char[5][5];
        students = new int[7];

        for (int i = 0; i < 5; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 5; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        /**
         * 인접한지 안한지와 무관하게 조합으로 다솜파가 우위인 7명을 뽑는다
         */
        go(0, 0, 0);

        System.out.println(ans);
    }

    /**
     * @param cnt      포함 된 학생 수
     * @param start    start 이상인 idx를 가진 학생을 포함시킬 수 있음
     * @param dasomCnt 포함 된 다솜파 학생 수
     */
    void go(int cnt, int start, int dasomCnt) {
        if (cnt == 7) {
            if (dasomCnt >= 4) {
                if (validate()) {
                    ans++;
                }
            }

            return;
        }

        for (int i = start; i < 25; i++) {
            students[cnt] = i;
            int nDasomCnt = dasomCnt + (grid[i / 5][i % 5] == 'S' ? 1 : 0);
            go(cnt + 1, i + 1, nDasomCnt);
        }

    }

    boolean validate() {
        // 선택된 7명 중 인접해있는 인원 수
        boolean visited[][] = new boolean[5][5];
        visited[students[0] / 5][students[0] % 5] = true;
        int cnt = dfs(students[0] / 5, students[0] % 5, visited);

        return cnt == 7;
    }

    int dfs(int row, int col, boolean[][] visited) {
        int result = 1;

        // 四方探索
        for (int i = 0; i < 4; i++) {
            int nRow = row + dy[i];
            int nCol = col + dx[i];

            if (!inRange(nRow, nCol) || visited[nRow][nCol]) {
                continue;
            }

            for (int j = 0; j < 7; j++) {
                if (students[j] == (nRow * 5 + nCol)) {
                    visited[nRow][nCol] = true;
                    result += dfs(nRow, nCol, visited);
                }
            }
        }

        return result;
    }


    boolean inRange(int row, int col) {
        return 0 <= row && row < 5 && 0 <= col && col < 5;
    }

}
