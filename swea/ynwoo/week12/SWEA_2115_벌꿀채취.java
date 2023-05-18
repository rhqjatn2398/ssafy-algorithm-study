import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C;
	static int answer;
	static int[][] board;
	static int[][] profit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			answer = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			board = new int[N][N];
			profit = new int[N][N - M + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//printBoard(board);
			// 부분집합
			makingProfit();
			//printBoard(profit);
			// 조합
			combA(profit);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	private static void makingProfit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				subSet(0, i, j, 0, 0);
			}
		}

	}

	private static void subSet(int cnt, int i, int j, int sum, int h) {
		if (cnt == M) {
			if (sum <= C) {
				//System.out.println(sum + " " + i + " " + (j - M));
				if (profit[i][j - M] < h) {
					profit[i][j - M] = h;
				}
			}
			return;
		}

		subSet(cnt + 1, i, j + 1, sum + board[i][j], h + board[i][j] * board[i][j]);
		subSet(cnt + 1, i, j + 1, sum, h);

	}

	private static void combA(int[][] profit) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				//System.out.println("A: " + i + " " + j); // A의 벌 꿀
				combB(profit, i, j);
			}
		}

	}

	private static void combB(int[][] profit, int ai, int aj) {
		int y = aj + M;
		for (int i = ai; i < N; i++) {
			for (int j = y; j < N - M + 1; j++) {
				//System.out.println("B: " + i + " " + j); // B의 벌 꿀
				// 여기서 각 프로핏 더해서 현재 토탈보다 크면 업데이트
				answer = Math.max(answer, profit[ai][aj] + profit[i][j]);
			}
			y = 0;
		}

	}

	private static void printBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}

	}
}