package algoStudy.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1493_박스채우기_G3 {
	static int l, w, h, n;
	static int[] cube;
	static int answer;
	static boolean flag = false;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		n = Integer.parseInt(br.readLine());
		cube = new int[20];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}

		fill(l, w, h);
		if (flag) {
			System.out.println(answer);
		} else {
			System.out.println(-1);
		}
	}

	// 시작 포인트에서 끝 포인트까지 생기는 박스에서 가장 큰(가지고 있는) 큐브 넣기
	private static void fill(int el, int ew, int eh) {
		if (el == 0 || ew == 0 || eh == 0) {
			return;
		}
		// 이 공간을 세 변중 가장 작은 변에 fit한 정육면체를 넣고 남은 공간에 대해서 재귀로 구현
		int minSize = Math.min(el, ew);
		minSize = Math.min(minSize, eh); // 가장 작은 변
		int size = 0;
		flag = false;
		// minSize 크기보다 같거나 작은 변을 가진 큐브중에 가장 큰 큐브를 넣는다.
		for (int i = 19; i >= 0; i--) {
			if (cube[i] == 0) {
				continue;
			}
			size = 1 << i;
			if (minSize >= size) {
				answer++;
				cube[i]--; // 큐브 사용
				flag = true;
				break; // 찾음
			}
		}
		if (!flag) { // 시간초과 해결
			return;
		}
		fill(size, ew - size, size);
		fill(el - size, ew, size);
		fill(el, ew, eh - size);
	}
}
