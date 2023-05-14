package algoStudy.week14;

import java.util.Scanner;

class SWEA_2105_디저트 카페 {
	static int si, sj, answer, n;
	static int[] di = { 1, 1, -1, -1 };
	static int[] dj = { 1, -1, -1, 1 };
	static boolean[] eaten;
	static int[][] board;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = -1;
			// 입력
			n = sc.nextInt();
			board = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					board[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < n - 2; i++) {
				for (int j = 1; j < n - 1; j++) {
					eaten = new boolean[101];
					eaten[board[i][j]] = true;
					// 마름모 기준 꼭대기 지점
					si = i;
					sj = j;

					dfs(i, j, -1, -1, 0, 0);
				}
			}
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	private static void dfs(int i, int j, int pi, int pj, int cnt, int ld) {
		for (int d = ld; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];

			// 범위
			if (ni < 0 || nj < 0 || ni >= n || nj >= n)
				continue;

			// 이전에 온 방향
			if (ni == pi && nj == pj)
				continue;
			// 원점 복귀
			if (ni == si && nj == sj) {
				answer = Math.max(answer, cnt + 1);
				return;
			}
			// 이미 먹은 디저트
			if (eaten[board[ni][nj]])
				continue;

			eaten[board[ni][nj]] = true;
			dfs(ni, nj, i, j, cnt + 1, d);
			eaten[board[ni][nj]] = false;
		}
	}
}
