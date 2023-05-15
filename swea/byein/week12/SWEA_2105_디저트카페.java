import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int TC, N, map[][], ans = -1;
    static int[] dx = { 1, 1, -1, -1 }, dy = { 1, -1, -1, 1 };
    static boolean[] isVisited;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            ans = -1;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    isVisited = new boolean[101];
                    dfs(i, j, i, j, 0, 0);
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
 
    }
 
    private static void dfs(int x, int y, int initX, int initY, int dir, int cnt) {
        if (x == initX && y == initY && dir == 3) {
            ans = Math.max(ans, cnt);
            return;
        }
 
        for (int i = dir; i < 4; i++) {
            if (i <= dir + 1) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;
                if (isVisited[map[nx][ny]])
                    continue;
                isVisited[map[nx][ny]] = true;
                dfs(nx, ny, initX, initY, i, cnt + 1);
                isVisited[map[nx][ny]] = false;
            }
        }
    }
 
}
