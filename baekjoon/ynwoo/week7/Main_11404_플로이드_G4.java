package algoStudy.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11404_플로이드_G4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		final int INF = 10000001; // 100개의 도시, 비용은 100,000 이하 자연수이므로

		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i < n + 1; i++) {
			Arrays.fill(graph[i], INF);
		}
		for (int i = 0; i < n + 1; i++) {
			graph[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a][b] = Math.min(graph[a][b], c);

		}
		// 플로이드
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					graph[i][j] = Integer.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

		// 출력
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] == INF) {
					graph[i][j] = 0;
				}
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}

}