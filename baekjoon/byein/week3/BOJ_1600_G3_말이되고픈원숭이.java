package byein.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_G3_말이되고픈원숭이 {

	static int K, W, H, cnt;
	static boolean[][][] visited;
	static int[][] board;
	// 말 이동. 
	static int[] dxHorse = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dyHorse = { -2, -1, 1, 2, 2, 1, -1, -2 };
	// 원숭이 이동.
	static int[] dxMonkey = { -1, 0, 1, 0 };
	static int[] dyMonkey = { 0, -1, 0, 1 };

	static class Node {
		int x;
		int y;
		int cnt; // 이동횟수.
		int k; // 말 점프.

		public Node(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int answer = -1;
		board = new int[H][W];
		visited = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// bfs.
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (cur.x == H - 1 && cur.y == W - 1) {
				answer = cur.cnt;
				break;
			}
			// 원숭이 이동. 
			for (int i = 0; i < 4; i++) {
				Node next = new Node(cur.x + dxMonkey[i], cur.y + dyMonkey[i], cur.cnt + 1, cur.k);
				if (next.x < 0 || next.x >= H || next.y < 0 || next.y >= W || board[next.x][next.y] == 1
						|| visited[next.x][next.y][cur.k])
					continue;
				visited[next.x][next.y][cur.k] = true;
				queue.offer(next);
			}
			// 말 이동.
			if (cur.k < K) {
				for (int i = 0; i < 8; i++) {
					Node next = new Node(cur.x + dxHorse[i], cur.y + dyHorse[i], cur.cnt + 1, cur.k + 1);
					if (next.x < 0 || next.x >= H || next.y < 0 || next.y >= W || board[next.x][next.y] == 1
							|| visited[next.x][next.y][cur.k + 1])
						continue;
					visited[next.x][next.y][cur.k + 1] = true;
					queue.offer(next);
				}
			}

		}
		System.out.println(answer);
	}
}
