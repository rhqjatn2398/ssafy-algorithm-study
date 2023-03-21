import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 최장 증가 수열(LIS) 이용.
 * 가장 적게 움직이는 경우는 증가되어 있는 부분은 그대로 두고 나머지만 이동시키는 경우이므로
 * 전체에서 최장증가수열의 크기를 빼주면 된다.
 * 
 * @author SSAFY
 *
 */
public class BOJ_2631_G4_줄세우기 {

	static int N; // 입력 크기
	static int[] input; // 입력
	static int[] dp; // LIS

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		int mx = 1; // 최댓값
		// 증가할 때 +1, 아니면 이전 값을 그대로 가져오며 증가하는 수가 얼마나 되는지 저장.
		for (int i = 0;i<N;i++) {
			dp[i] = 1;
			for (int j = 0;j<i;j++) {
				if (input[j] < input[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			mx = Math.max(mx, dp[i]);
		}
		// 전체 입력 크기에서 최장증가수열의 크기를 빼주면 됨.
		System.out.println(N - mx);
	}
}