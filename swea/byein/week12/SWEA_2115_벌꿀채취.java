package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int T, N, M, C, answer;
	static int[][] board;
	static int[] profits;
	static int[][] workers;
	static boolean[][] visited;
	static boolean[] isSelected;

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			board = new int[N][N];
			workers = new int[2][M];
			isSelected = new boolean[M];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 벌통 선택.
			comb(0, 0);
			System.out.println("#" + tc + " " + answer);
		}
	}
    /**
	 * 벌통 선택.
	 * @param cnt
	 * @param start
	 */
	static void comb(int cnt, int start) {
		if (cnt == 2) {
			// 두 명 모두 선택하면 종료.
			profits = new int[2];
			// 일꾼마다 벌집 최대 벌꿀 뽑기.
			for (int i = 0; i < 2; i++) {
				honeyCombination(i, 0, 0, 0);
			}
			// 최대값 구하기.
			int total = profits[0] + profits[1];

			answer = Math.max(total, answer);

			return;
		}
		for (int i = start; i < N; i++) {
			boolean isValid = true;
			for (int j = 0; j < N + 1 - M; j++) {
				// 연속하여 유효한 경우만 돌기.
				for (int k = 0; k < M; k++) {
					if (visited[i][j + k]) {
						isValid = false;
						break;
					}
				}
				if (!isValid) {
					break;
				}

				// 방문 처리 후 조합에 넣기.
				for (int k = 0; k < M; k++) {
					visited[i][j + k] = true;
					workers[cnt][k] = board[i][j + k];
				}

				comb(cnt + 1, start + 1);
				
				// 방문 복귀.
				for (int k = 0; k < M; k++) {
					visited[i][j + k] = false;
				}
			}
		}
	}

	/**
	 * 일꾼마다 벌집 골라서 최대 수익 꿀 채취.
	 * @param workIdx: 현재 일꾼.
	 * @param sum: 수익 합.
	 * @param cnt: 현재 뽑은 벌집 수.
	 * @param mx: 최대값.
	 */
	private static void honeyCombination(int workIdx, int sum, int cnt, int mx) {
		// 수익은 항상 최대값이 들어가야 함.
		profits[workIdx] = Math.max(profits[workIdx], mx);
		
		// M개 중에서 
		for (int i = 0;i<M;i++) {
			if (isSelected[i]) continue;
			// 합이 C 이내인 동안 가능한 조합 탐색.
			if (sum+workers[workIdx][i] <= C) {
				isSelected[i] = true;
				honeyCombination(workIdx, sum + workers[workIdx][i], cnt, mx + workers[workIdx][i]*workers[workIdx][i]);
				isSelected[i] = false;
			}
			
		}
	}
}