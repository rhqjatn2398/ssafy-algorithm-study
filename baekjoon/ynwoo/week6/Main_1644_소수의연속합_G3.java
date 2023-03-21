package algoStudy.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_1644_소수의연속합_G3 {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 구현
		ArrayList<Integer> primeNums = getPrimeNums(N); // N 이하의 소수 리스트 생성
		int l = primeNums.size();

		long[] dp = new long[l + 1]; // 소수의 누적합
		for (int i = 1; i < l + 1; i++) {
			dp[i] = dp[i - 1] + primeNums.get(i - 1); // 누적 합 구하기
		}
		int left = 0, right = 1, answer = 0;
		
		while (right < l + 1) {
			// N보다 작으면 right 값 증가
			if (dp[right] - dp[left] < N) {
				right++;
			} else if (N < dp[right] - dp[left]) { // N보다 크면 left 값 증가
				left++;
			} else { // 같은 경우
				answer++;
				right++;
			}
		}

		// 출력
		System.out.println(answer);
	}

	private static ArrayList<Integer> getPrimeNums(int n) {
		// n이하의 소수 리스트
		ArrayList<Integer> list = new ArrayList<Integer>();

		// 소수이면 true, 소수가 아니면 false
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = isPrime[1] = false; // 0과 1은 소수가 아니다.
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (isPrime[i]) {
				for (int j = i * 2; j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}

		// 소수이면 리스트에 추가
		for (int i = 1; i <= n; i++) {
			if (isPrime[i]) {
				list.add(i);
			}
		}

		return list;
	}
}