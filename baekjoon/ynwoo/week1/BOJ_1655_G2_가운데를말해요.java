package algoStudy.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_1655_가운데를말해요_G2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (i % 2 == 0) {
				maxHeap.offer(num);
			} else {
				minHeap.offer(num);
			}
			if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if (maxHeap.peek() > minHeap.peek()) {
					int tmp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(tmp);
				}
			}

			bw.write(maxHeap.peek().toString());
			bw.newLine();
			// 시간초과...
			// System.out.println(maxHeap.peek());
		}
		br.close();
		bw.close();
	}

}
