import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_G4_뱀 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    int n, k, L, curDir;
    int grid[][];
    int dirs[];
    int dy[] = { 0, 1, 0, -1 };
    int dx[] = { 1, 0, -1, 0 };
    Queue<Pair> q;

    public static void main(String[] args) throws IOException {
        Main ps = new Main();

        ps.solution();
    }

    void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        q = new ArrayDeque<>();

        grid = new int[n][n]; // 빈칸 := 0, 사과 := 1 몸 := 2
        dirs = new int[10001];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            grid[row][col] = 1;
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            int dir = st.nextToken().charAt(0) == 'L' ? -1 : 1;

            dirs[sec] = dir;
        }

        q.add(new Pair(0, 0));
        go(0, 0, 0);
    }

    void go(int sec, int row, int col) {
        // 명시적 기저 조건 없음

        curDir = (curDir + 4 + dirs[sec]) % 4;
        int nRow = row + dy[curDir];
        int nCol = col + dx[curDir];

        if (!inRange(nRow, nCol) || grid[nRow][nCol] == 2) {
            // 다음 칸에 갔을 때 종료되므로 현재 초 + 1
            System.out.println(sec + 1);
            return;
        }
        else if (grid[nRow][nCol] == 0) {
            grid[nRow][nCol] = 2;
            q.add(new Pair(nRow, nCol));
            Pair p = q.remove();
            grid[p.row][p.col] = 0;
            go(sec + 1, nRow, nCol);
        } else if (grid[nRow][nCol] == 1) {
            grid[nRow][nCol] = 2;
            q.add(new Pair(nRow, nCol));
            go(sec + 1, nRow, nCol);
        }

    }

    boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}
