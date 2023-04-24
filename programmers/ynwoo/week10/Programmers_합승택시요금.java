import java.util.*;

class Programmers_합승택시요금 {
	static int INF = 1000000000;

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = INF;
		int[][] graph = new int[n + 1][n + 1];
		int l = fares.length;
		for (int i = 0; i < l; i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int w = fares[i][2];

			graph[start][end] = graph[end][start] = w;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] == 0 && i != j) {
					graph[i][j] = INF;
				}
			}
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}

			}
		}

		// 임의의 점 k에 대하여 (s -> k 비용) + (k -> a 비용) + (k -> b 비용) 의 최솟값 구하기
		for (int k = 1; k <= n; k++) {
			if (graph[s][k] == INF || graph[k][a] == INF || graph[k][b] == INF)
				continue;
			answer = Math.min(answer, graph[s][k] + graph[k][a] + graph[k][b]);
		}

		return answer;
	}
}