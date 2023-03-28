package com.ssafy.w02;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15683_G4_감시 {

	// 4방 탐색할 방향 배열
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	// 입력받은 배열과 계속 갱신할 배열
	static int[][] board, updateBoard;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int mn = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		updateBoard = new int[N][M];
		// cctv 담을 리스트.
		ArrayList<Point> cctv = new ArrayList<>();
		// 입력 처리. cctv 추가.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

				if (board[i][j] > 0 && board[i][j] < 6)
					cctv.add(new Point(i, j));
			}
		}

		// cctv의 경우 최소 1가지에서 최대 4가지의 경우가 나올 수 있음. 
		// cctv 개수만큼 4가지 경우가 있으므로 4^cctv.size() 만큼 탐색 시도.
		for (int curIdx = 0; curIdx < Math.pow(4, cctv.size()); curIdx++) {
			// 갱신할 배열 초기화.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					updateBoard[i][j] = board[i][j];
				}
			}
			// 현재 얼마만큼 탐색했는지 담은 변수.
			int tmp = curIdx;
			// 모든 cctv에 대해 탐색.
			for (int j = 0; j < cctv.size(); j++) {
				// 현재 cctv.
				Point cur = cctv.get(j);
				// 네 방향이므로 4로 나눈 나머지로 실제 dir 사용할 것.
				int dir = tmp % 4;
				// 현재 네 방향에 대해 탐색하므로 4로 나눠줌.
				tmp /= 4;
				// 현재 위치가 cctv가 있는 곳이라면
				switch (board[cur.x][cur.y]) {
					// 1이면 한 방향.
					case 1:
						update(cur.x, cur.y, dir);
						break;
					// 2이면 서로 반대인 두 방향.
					case 2:
						update(cur.x, cur.y, dir);
						update(cur.x, cur.y, dir + 2);
						break;
					// 3이면 서로 직각인 두 방향.
					case 3:
						update(cur.x, cur.y, dir);
						update(cur.x, cur.y, dir + 1);
						break;
					// 4이면 세 방향.
					case 4:
						update(cur.x, cur.y, dir);
						update(cur.x, cur.y, dir + 1);
						update(cur.x, cur.y, dir + 2);
						break;
					// 5이면 네 방향.
					case 5:
						update(cur.x, cur.y, dir);
						update(cur.x, cur.y, dir + 1);
						update(cur.x, cur.y, dir + 2);
						update(cur.x, cur.y, dir + 3);
						break;
					default:
						break;
				}
			}

			// 최소값 구하기.
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (updateBoard[i][j] == 0) {
						cnt++;
					}
				}
			}
			mn = Math.min(mn, cnt);
		}

		System.out.println(mn);

	}
	

	/**
	 * (x,y)에서 dir 방향으로 진행하며 벽 만나기 전까지 모든 빈칸을 -1로 갱신.
	 * @param x
	 * @param y
	 * @param dir
	 */
	private static void update(int x, int y, int dir) {
		// 무한루프로 종료 조건 만족 시 빠져나옴.
		while (true) {
			// dir%4한 값을 계속 더해줌.
			x += dx[dir % 4];
			y += dy[dir % 4];
			// 벽을 만나면 빠져나옴.
			if (x < 0 || y < 0 || x >= N || y >= M || board[x][y] == 6)
				break;
			// cctv가 있는 경우는 넘어감.
			if (board[x][y] > 0)
				continue;
			// 갱신.
			updateBoard[x][y] = -1;
		}
	}

}
