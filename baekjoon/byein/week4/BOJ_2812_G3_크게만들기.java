package byein.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_2812_G3_크게만들기 {

	static int N, K;
	static char[] arr;
	// 스택에서는 문자열이 반대로 나오므로 덱 사용.
	static ArrayDeque<Character> deque = new ArrayDeque();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String nums = br.readLine();
		String answer = "";
		arr = nums.toCharArray();

		for (int i = 0; i < arr.length; i++) {
			// K가 0보다 크고 덱의 마지막이 현재 숫자보다 작다면 덱에서 마지막 제거 후 K 감소.
			while (K > 0 && !deque.isEmpty() && deque.getLast() < arr[i]) {
				deque.removeLast();
				K--;
			}
			// 덱에 추가.
			deque.addLast(arr[i]);
		}

		StringBuilder sb = new StringBuilder();

		while (deque.size() > K) {
			sb.append(deque.removeFirst());
		}
		System.out.println(sb.toString());

		System.out.println(answer);

	}

}
