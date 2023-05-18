package algoStudy.week14;

import java.util.Scanner;

class SWEA_5650 {
	static int si, sj, answer, n, score;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static boolean[] eaten;
	static int[][] board;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			// 입력
			n = sc.nextInt();
			board = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			// 위치
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (board[i][j] != 0) {
						continue;
					}
					// 4방향에 대해
					for (int d = 0; d < 4; d++) {
						score = 0;
						si = i;
						sj = j;
						dfs(i, j, d);
					}

				}
			}
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	private static void dfs(int i, int j, int d) {
		int ni = i;
		int nj = j;
		while (true) {
			ni = ni + di[d];
			nj = nj + dj[d];

			// 벽에 부딪힌 경우
			if (ni < 0 || nj < 0 || ni >= n || nj >= n) {
				score++;
				// 방향 바꾸기
				d = changeDir(d);
				ni = ni + di[d];
				nj = nj + dj[d];
			}

			// 블록에 부딪히는 경우
			if (board[ni][nj] == 1) {
				score++;
				if (d == 1) {
					d = 3;
				} else if (d == 2) {
					d = 0;
				} else {
					d = changeDir(d);
				}
			}
			if (board[ni][nj] == 2) {
				score++;
				if (d == 2) {
					d = 1;
				} else if (d == 0) {
					d = 3;
				} else {
					d = changeDir(d);
				}
			}
			if (board[ni][nj] == 3) {
				score++;
				if (d == 3) {
					d = 1;
				} else if (d == 0) {
					d = 2;
				} else {
					d = changeDir(d);
				}
			}
			if (board[ni][nj] == 4) {
				score++;
				if (d == 1) {
					d = 2;
				} else if (d == 3) {
					d = 0;
				} else {
					d = changeDir(d);
				}
			}
			if (board[ni][nj] == 5) {
				score++;
				d = changeDir(d);
			}
			// 웜홀 이동
			if (board[ni][nj] >= 6 && board[ni][nj] <= 10) {
				label: for (int k = 0; k < n; k++) {
					for (int l = 0; l < n; l++) {
						if (!(k == ni && l == nj) && board[k][l] == board[ni][nj]) {
							ni = k;
							nj = l;
							break label;
						}
					}
				}
			}
			// 블랙홀 도착
			if (board[ni][nj] == -1) {
				answer = Math.max(answer, score);
				break;
			}
			// 원점 복귀
			if (ni == si && nj == sj) {
				answer = Math.max(answer, score);
				break;
			}
		}
	}

	private static int changeDir(int d) {
		return (d % 2 == 0) ? d + 1 : d - 1;
	}
}
