package algoStudy.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2631_줄세우기_G4 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N : 아이들의 수
		int N = Integer.parseInt(br.readLine());
		// 임의의 순서대로 줄을 서 있는 아이들
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// dp[i] : i번째 원소를 마지막으로 하는 가장 긴 오름차순의 길이
		int[] dp = new int[N];
		int maxIdx = 0; // 가장 긴 오름차순의 길이를 구하기 위한 인덱스 변수

		for (int i = 0; i < N; i++) {
			// 기본 값 : 1
			dp[i] = 1;
			// i보다 앞쪽의 원소들을 탐색하면서
			for (int j = 0; j < i; j++) {
				// 1. i번째 수가 앞에 나온 수보다 커야한다.(증가 하는 방향)
				// 2. 가장 긴 오름차순 길이를 저장해야한다.
				if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}

			// 가장 긴 오름차순의 길이 구하기
			if (dp[maxIdx] < dp[i]) {
				maxIdx = i;
			}
		}

		// 출력
		// answer = 전체 길이 - 가장 긴 오름차순의 길이
		System.out.println(N - dp[maxIdx]);
	}
}