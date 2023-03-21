package algoStudy.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1937_욕심쟁이판다_G3 {
	static class MyPoint implements Comparable<MyPoint> {
		int b, x, y;

		public MyPoint(int b, int x, int y) {
			this.b = b; // 대나무 길이
			this.x = x; // 좌표
			this.y = y;
		}

		@Override
		public int compareTo(MyPoint o) {
			if (this.b < o.b) {
				return -1;
			} else if (this.b == o.b) {
				return 0;
			} else {
				return 1;
			}
		}

	}

	static int n, max = Integer.MIN_VALUE;
	static int[][] board, dp;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());

		board = new int[n][n];
		dp = new int[n][n]; // dp[i][j] : i,j지역을 마지막으로 오는 경우 중 최대 횟수
		PriorityQueue<MyPoint> pq = new PriorityQueue<MyPoint>(); // 대나무의 길이 값이 작은 것 부터 큰 것 순서대로 위치 인덱스 저장

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

				pq.offer(new MyPoint(board[i][j], i, j));
			}
		}

		while (!pq.isEmpty()) {
			MyPoint cur = pq.poll();
			getMaxNumOfMoves(cur.x, cur.y);
		}

		// 출력
		System.out.println(max + 1);
	}

	private static void getMaxNumOfMoves(int x, int y) {
		int[] di = { -1, 1, 0, 0 };
		int[] dj = { 0, 0, -1, 1 };
		// 인접한 상하좌우 탐색
		for (int d = 0; d < 4; d++) {
			int ni = x + di[d];
			int nj = y + dj[d];
			if (ni < 0 || n <= ni || nj < 0 || n <= nj) {
				continue;
			}
			// 인접한 지역이 더 낮은 대나무의 길이라면
			if (board[ni][nj] < board[x][y]) {
				dp[x][y] = Math.max(dp[x][y], 1 + dp[ni][nj]); // i,j 위치에서의 최대값 저장
			}
		}
		max = Math.max(max, dp[x][y]); // 전체 최대값 저장
	}
}