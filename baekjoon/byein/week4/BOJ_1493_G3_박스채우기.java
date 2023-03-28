package byein.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1493_G3_박스채우기 {

	static int length, width, height, N, cnt;
	static int[] cube;
	static boolean flag = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());

		N = Integer.parseInt(br.readLine());
		cube = new int[20];
		// 입력 받기.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		// 분할정복 시작.
		divide(length, width, height);
		// 플래그가 참이면 큐브가 채워진 것으로 cnt 출력, 아니면 큐브가 안 채워진 것으로 -1 출력.
		System.out.println(flag ? cnt : -1);
	}
	/**
	 * 분할정복 재귀 함수
	 * @param l: 현재 length
	 * @param w: 현재 width
	 * @param h: 현재 height
	 */
	static void divide(int l, int w, int h) {
		// 하나라도 0보다 작거나 같다면 종료.
		if (l <= 0 || w <= 0 || h <= 0)
			return;
		int i;
		// 모든 큐브에 대해서 탐색. 
		// 큐브가 큰 것부터 채워져야 최소로 다 채울 수 있으므로 역순 탐색.
		for (i = 19; i >= 0; i--) {
			// 현재 인덱스의 큐브의 길이는 2^i. 비트연산으로 계산.
			int cur = 1 << i;
			// 현재 인덱스의 큐브가 존재하고 세 변 모두 현재 인덱스의 큐브보다 크거나 같다면 채울 수 있는 경우.
			if (cube[i] > 0 && l >= cur && w >= cur && h >= cur) {
				// 큐브 개수 감소. 
				cube[i]--;
				// 사용한 큐브 수 증가.
				cnt++;
				// 현재 큐브 채운 뒤 남은 공간을 세 직사각형으로 나눠서 분할 정복!
				divide(l, w, h - cur);
				divide(l, w - cur, cur);
				divide(l - cur, cur, cur);
				// 분할 정복 끝나면 리턴.
				return;
			}
		}
		// 이전에 리턴되지 않고 여기까지 도달한 경우는 큐브가 하나도 채워지지 않은 경우를 의미.
		if (i == -1) {
			// 플래그를 바꿔줌.
			flag = false;
		}
	}

}