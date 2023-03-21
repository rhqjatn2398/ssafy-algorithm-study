import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 *
 */
public class BOJ_1727_G2_커플만들기 {
	static int N, M;
	static int[] male, female;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		male = new int[N + 1];
		female = new int[M + 1];
		dp = new int[N + 1][M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			male[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			female[i] = Integer.parseInt(st.nextToken());
		}
		// 최소로 비교하기 위해서는 결국 sort한 경우가 최적인 상태가 되므로 두 배열을 sort 해줌.
		Arrays.sort(male);
		Arrays.sort(female);
		// 모든 N과 M에 대해 짝짓기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 현재 남자와 여자의 인덱스가 같은 경우라면 해당 dp는 이전 dp + 현재 남자와 여자의 차
				dp[i][j] = dp[i - 1][j - 1] + (Math.abs(male[i] - female[j]));
				if (i > j) {
					// 만약 남자의 인덱스가 더 크다면,
					// i-1,j 인 경우와 i,j 인 경우의 dp 최소값을 구해 저장.
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);
				} else if (i < j) {
					// 만약 여자의 인덱스가 더 크다면,
					// i,j-1 인 경우와 i,j 인 경우의 dp 최소값을 구해 저장.
					dp[i][j] = Math.min(dp[i][j - 1], dp[i][j]);
				}
			}
        }
        System.out.println(dp[N][M]);
	}

}
