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

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
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
		ArrayList<Point> cctv = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

				if (board[i][j] > 0 && board[i][j] < 6)
					cctv.add(new Point(i, j));
			}
		}

		// 4방향씩 경우의 수가 곱해짐.
		for (int curIdx = 0;curIdx < Math.pow(4, cctv.size());curIdx++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					updateBoard[i][j] = board[i][j];
				}
			}
			int tmp  = curIdx;
			for (int j = 0; j < cctv.size(); j++) {
				Point cur = cctv.get(j);
				int dir = tmp % 4;
				tmp/=4;
				switch (board[cur.x][cur.y]) {
				case 1:
					update(cur.x, cur.y, dir);
					break;
				case 2:
					update(cur.x, cur.y, dir);
					update(cur.x, cur.y, dir + 2);
					break;
				case 3:
					update(cur.x, cur.y, dir);
					update(cur.x, cur.y, dir + 1);
					break;
				case 4:
					update(cur.x, cur.y, dir);
					update(cur.x, cur.y, dir + 1);
					update(cur.x, cur.y, dir + 2);
					break;
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

	private static void update(int x, int y, int dir) {
		while (true) {
			x += dx[dir % 4];
			y += dy[dir % 4];
			if (x < 0 || y < 0 || x >= N || y >= M || board[x][y] == 6)
				break;
			if (board[x][y] > 0)
				continue;
			updateBoard[x][y] = -1;
		}
	}

}
