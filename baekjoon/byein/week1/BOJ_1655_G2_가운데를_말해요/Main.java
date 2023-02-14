package baekjoon.김예빈.week01.BOJ_1655_가운데를_말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	// minHeap + maxHeap으로 중간 구하기
	// maxHeap : 정수들 중 작은 것들을 모아놓는 heap. root 값이 작은 것들 중 최대. 중간 값 중 작은
	// minHeap : 정수들 중 큰 것들을 모아놓는 heap.
	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // minHeap
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			// 짝수 개라면 중간에 있는 두 수 중 작은 수를 말해야 하므로
			// maxHeap보다 minHeap이 작거나 같은 경우 maxHeap에 숫자 추가
			// 그 외에 경우 minHeap에 숫자 추가.
			if (maxHeap.size() <= minHeap.size()) {
				maxHeap.add(num);
			} else {
				minHeap.add(num);
			}

			// maxHeap과 minHeap이 모두 채워져 있을 때,
			// maxHeap의 루트값이 minHeap의 루트값보다 크다면
			// 중간값 정렬이 되지 않은 것이므로 루트값을 뽑아서 다른 heap에 넣어줌.
			if (maxHeap.size() > 0 && minHeap.size() > 0 && maxHeap.peek() > minHeap.peek()) {
				int minTop = minHeap.peek();
				int maxTop = maxHeap.peek();

				minHeap.remove();
				maxHeap.remove();
				minHeap.add(maxTop);
				maxHeap.add(minTop);
			}
			sb.append(maxHeap.peek() + "\n");
		}
		System.out.println(sb);
	}
}
