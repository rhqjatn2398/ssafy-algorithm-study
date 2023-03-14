package algoStudy.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2812_크게만들기_G3 {
	static int N, K;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] numbers = new int[N];
		String input = br.readLine();
		for (int i = 0; i < N; i++) {
			numbers[i] = input.charAt(i) - '0';
		}

		// 1. 앞에서 부터 스택에 넣는다.
		// 2. top에 있는 수보다 새롭게 넣으려고 하는 수가 더 크고 k>0이면 스택에서 pop, k-- -> 2반복
		// 3. k가 0이거나 새롭게 들어오는 숫자가 작거나 같으면 그냥 push
		// 4. 작업을 다 끝내고 k가 0보다 크다면 k개 만큼 pop 해주기(ex. 4 1 9998)

		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		deque.addLast(numbers[0]); // 첫 번째 숫자 push
		// 1번작업
		for (int i = 1; i < N; i++) {
			int top = deque.peekLast(); // top에 있는 수

			// 2번작업
			while (!deque.isEmpty() && top < numbers[i] && K > 0) {
				deque.pollLast();
				if (!deque.isEmpty()) {
					top = deque.peekLast();
				}
				K--;
			}
			// 3번작업
			deque.addLast(numbers[i]);
		}
		// 4번작업
		while (K > 0) {
			deque.pollLast();
			K--;
		}
		// K = N인 case
		if (deque.isEmpty()) {
			System.out.println(0);
		}

		// 출력
		while (!deque.isEmpty()) {
			System.out.print(deque.pollFirst());
		}
	}
}
