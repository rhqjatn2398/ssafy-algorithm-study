package byein.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_G3_벽부수고이동하기 {

	static int N, M;
	static int[][] board;
	// 방문 처리를 3차원으로 처리. row, column, 벽 부수는 횟수.
	static boolean[][][] visited;
	// 이동 방향.
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// 이동하는 칸에 대한 클래스.
	static class Node {
		int x, y; // 좌표.
		int breakCnt; // 벽 부수는 횟수.
		int cnt; // 이동 횟수.

		public Node(int x, int y, int breakCnt, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.breakCnt = breakCnt;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M][2];
		// 입력 처리.
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j) - 48;
			}
		}
		
		// bfs.
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		int answer = -1;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			// 종료 조건. (N,M) 위치 도달 시 종료. 끝나는 칸 포함해서 세므로 1 증가.
			if (cur.x == N - 1 && cur.y == M - 1) {
				answer = cur.cnt + 1;
				break;
			}
			// 네 방향에 대해서 탐색.
			for (int i = 0; i < 4; i++) {
				// 다음 노드는 좌표는 다음 좌표, 벽을 부수는 횟수는 동일, 이동횟수는 1 증가.
				Node next = new Node(cur.x + dx[i], cur.y + dy[i], cur.breakCnt, cur.cnt + 1);
				// 범위를 벗어나거나 벽이 있거나 방문한 경우는 넘어가고.
				if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= M || board[next.x][next.y] == 1
						|| visited[next.x][next.y][cur.breakCnt])
					continue;
				// 그 외 유효한 경우 방문처리 후 큐에 넣기.
				visited[next.x][next.y][cur.breakCnt] = true;
				queue.add(next);
			}
			
			// 벽을 부수는 횟수가 유효하면.
			if (cur.breakCnt < 1) {
				for (int i = 0; i < 4; i++) {
					// 다음 노드는 좌표는 다음 좌표, 벽 부수는 횟수 1 증가, 이동 횟수 1 증가.
					Node next = new Node(cur.x + dx[i], cur.y + dy[i], cur.breakCnt + 1, cur.cnt + 1);
					// 범위를 벗어나거나 방문한 경우는 넘어가기. 
					// 벽을 부수므로 벽이면 넘어가는 조건은 넣지 않음.
					if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= M 
							|| visited[next.x][next.y][cur.breakCnt + 1])
						continue;
					// 그 외 유효한 경우 방문처리 후 큐에 넣기.
					visited[next.x][next.y][cur.breakCnt + 1] = true;
					queue.add(next);
				}
			}
		}
		System.out.println(answer);

	}

}
