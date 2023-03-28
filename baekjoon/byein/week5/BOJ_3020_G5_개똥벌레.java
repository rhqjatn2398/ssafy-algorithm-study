package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_3020_G5_개똥벌레 {

	static int N, H;
	static int[] upSum, downSum;
	static TreeMap<Integer, Integer> map = new TreeMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		// 종유석과 석
		upSum = new int[H+1];
		downSum = new int[H+1];
		// 종유석과 석순 입력에 따라 다르게 처리. 존재하면 해당 위치 증가.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			if (i % 2 == 1) {
				upSum[Integer.parseInt(st.nextToken())]++;
			} else {
				downSum[Integer.parseInt(st.nextToken())]++;
			}
		}
		// 누적합 이용.
		for (int i = H-1;i>0;i--) {
			upSum[i] += upSum[i+1];
			downSum[i] += downSum[i+1];
		}

		int mn = Integer.MAX_VALUE;
		int cnt = 0;
		// 종유석과 석순 합을 더해 최소보다 작은 걸로 갱신하며 개수 세기.
		for (int i = 1;i<H+1;i++) {
			if (mn > upSum[i] + downSum[H-i+1]) {
				mn = upSum[i] + downSum[H-i+1];
				cnt = 0;
			}
			if (mn == upSum[i] + downSum[H-i+1]) {
				cnt++;
			}
		}
		System.out.println(mn+" "+cnt);
	}

}
