import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int TC, N, M, map[][], houseCnt, mx;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			houseCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						houseCnt++;
					}
				}
			}
			mx = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (houseCnt == mx)
						break;
					go(i, j);
				}
			}
			System.out.println("#" + tc + " " + mx);
		}
	}

	private static void go(int x, int y) {
		for (int k = 1; k < 2 * N; k++) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((Math.abs(i - x) + Math.abs(j - y)) < k) {
						if (map[i][j] == 1)
							cnt++;
					}
				}
			}
			int cost = k * k + (k - 1) * (k - 1);
			if (cost <= cnt * M) {
				if (mx < cnt)
					mx = cnt;
			}
		}
	}
}

