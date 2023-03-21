package algoStudy.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1727_커플만들기_G2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] man = new int[n + 1];
		int[] woman = new int[m + 1];
		int[][] dp = new int[n + 1][m + 1]; // dp[i][j] : 남자 1번~i번, 여자 1번~j번으로 만들 수 있는 성격의 차이의 합의 최소

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			man[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			woman[i] = Integer.parseInt(st.nextToken());
		}

		// 정렬
		Arrays.sort(man);
		Arrays.sort(woman);

		if (n < m) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (i == j) {
						dp[i][j] = dp[i - 1][j - 1] + Math.abs(man[i] - woman[j]);
					} else {
						dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + Math.abs(man[i] - woman[j]));
					}
				}
			}
		} else {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (i == j) {
						dp[i][j] = dp[i - 1][j - 1] + Math.abs(man[i] - woman[j]);
					} else {
						dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1] + Math.abs(man[i] - woman[j]));
					}
				}
			}
		}

//		input
//		4 3
//		1 2 4 4
//		3 3 3

//		output
//		3

//		input
//		3 6
//		10 20 30
//		9 10 11 100 101 102

//		output
//		30

		// 출력
		System.out.println(dp[n][m]);
	}

}