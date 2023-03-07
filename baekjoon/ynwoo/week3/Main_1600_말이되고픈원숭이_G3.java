package algoStudy.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_말이되고픈원숭이_G3 {
	static class MyPoint {
		int x, y, currentK;

		public MyPoint(int x, int y, int currentK) {
			this.x = x;
			this.y = y;
			this.currentK = currentK;
		}

	}

	static int K, W, H;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = bfs(0, 0, K);
		System.out.println(answer);
	}

	static int[][] horseMove = { { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 },
			{ -1, 2 } };
	static int[][] move = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private static int bfs(int i, int j, int k) {
		Queue<MyPoint> queue = new ArrayDeque<MyPoint>();
		boolean[][][] visited = new boolean[k + 1][H][W];
		int level = 0, size = 0;

		visited[k][i][j] = true;
		queue.offer(new MyPoint(i, j, k));
		while (!queue.isEmpty()) {
			size = queue.size();
			while (--size >= 0) {
				MyPoint current = queue.poll();
				if (current.x == H - 1 && current.y == W - 1) {
					// 도착
					return level;
				}
				// 이동할 수 있는 곳이라면 큐에 추가, 방문처리

				// 평범한 이동
				for (int d = 0; d < 4; d++) {
					int ni = current.x + move[d][0];
					int nj = current.y + move[d][1];
					if (ni < 0 || H <= ni || nj < 0 || W <= nj || visited[current.currentK][ni][nj]
							|| map[ni][nj] == 1) {
						continue;
					}
					visited[current.currentK][ni][nj] = true;
					queue.offer(new MyPoint(ni, nj, current.currentK));
				}
				// 말처럼 이동할 수 있다.
				if (current.currentK > 0) {
					for (int d = 0; d < 8; d++) {
						int ni = current.x + horseMove[d][0];
						int nj = current.y + horseMove[d][1];
						if (ni < 0 || H <= ni || nj < 0 || W <= nj || visited[current.currentK - 1][ni][nj]
								|| map[ni][nj] == 1) {
							continue;
						}
						visited[current.currentK - 1][ni][nj] = true;
						queue.offer(new MyPoint(ni, nj, current.currentK - 1));
					}
				}

			}

			level++;
		}
		return -1;
	}
}
