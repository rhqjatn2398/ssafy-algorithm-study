import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DFS 이용
 * @author SSAFY
 *
 */
public class BOJ_1937_G3_욕심쟁이판다 {

	static int N, mx = Integer.MIN_VALUE;
	static int[][] map, dp; // 입력과 이동 정보 저장 배열.
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 모든 경우에서 출발할 수 있으므로 dfs를 모든 경우에서 돌림.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// dfs의 결과와 mx 중 최대값 저장.
				mx = Math.max(mx, dfs(i, j));
			}
		}
		System.out.println(mx + 1);
	}

	/**
	 * dfs
	 * @param x
	 * @param y
	 * @return
	 */
	static int dfs(int x, int y) {
		// 이미 방문한 경우이므로 해당 값.
		if (dp[x][y] > 0)
			return dp[x][y];
		// 4방 탐색.
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 범위 벗어나거나 다음 대나무가 현재 대나무보다 작거나 같으면 넘어감.
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] <= map[x][y])
				continue;
			// 다음 좌표로 dfs를 돌린 것에서 1 더한 값과 현재 좌표의 값 중 큰 값 저장.
			dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);

		}
		// 현재 좌표 리턴.
		return dp[x][y];
	}

}
