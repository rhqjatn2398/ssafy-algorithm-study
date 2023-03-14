package algoStudy.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3020_개똥벌레_G5 {
	static int N, H;
	static int min = Integer.MAX_VALUE, answer;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		int[] s = new int[H + 2]; // 석순
		int[] j = new int[H + 2]; // 종유석

		for (int i = 0; i < N / 2; i++) {
			s[Integer.parseInt(br.readLine())]++; // (index)번째 구간을 끝으로하는 석순의 개수
			j[H + 1 - Integer.parseInt(br.readLine())]++; // (index)번째 구간을 끝으로하는 종유석의 개수
		}

		for (int i = H; i >= 1; i--) {
			s[i] += s[i + 1]; // (index)번째 구간을 지나가는 석순의 개수
		}
		for (int i = 1; i <= H; i++) {
			j[i] += j[i - 1]; // (index)번째 구간을 지나가는 종유석의 개수
		}

		for (int i = 1; i <= H; i++) {
			if (min > s[i] + j[i]) { // 더 작은 값을 찾으면
				min = s[i] + j[i]; // 최솟값 업데이트
				answer = 1;
			} else if (min == s[i] + j[i]) {
				answer++; // 최솟값을 가지는 구간 개수 증가
			}
		}
		// 출력
		sb.append(min).append(" ").append(answer);
		System.out.println(sb);
	}
}