import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T, n, map[][], ans = -1;
    static int[] dx = { 1, 1, -1, -1 }, dy = { 1, -1, -1, 1 };
    static boolean[] visited;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            ans = -1;
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited = new boolean[101];
                    dfs(i, j, i, j, 0, 0);
                }
            }
            sb.append("#" + t + " " + ans + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
 
    private static void dfs(int x, int y, int startX, int startY, int dir, int cnt) {
        if (x == startX && y == startY && dir == 3) {
            ans = Math.max(ans, cnt);
            return;
        }
 
        for (int i = dir; i < 4; i++) {
            if (i <= dir + 1) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[map[nx][ny]]) continue;
                visited[map[nx][ny]] = true;
                dfs(nx, ny, startX, startY, i, cnt + 1);
                visited[map[nx][ny]] = false;
            }
        }
    }
 
}
