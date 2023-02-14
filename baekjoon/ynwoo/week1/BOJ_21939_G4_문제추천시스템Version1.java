package algoStudy.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Problem implements Comparable<Problem> {
	Integer difficulty;
	Integer pNum;

	public Problem(int difficulty, int pNum) {
		this.difficulty = difficulty;
		this.pNum = pNum;
	}

	@Override
	public int compareTo(Problem o) {
		if (this.difficulty > o.difficulty) {
			return 1;
		} else if (this.difficulty == o.difficulty) {
			if (this.pNum > o.pNum) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}
}

public class Main_21939_문제추천시스템Version1_G4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] isRecommend = new int[100001]; // 문제가 있는지 없는지가 아니라, 어떤 난이도를 가지고 있는지 판단해야함
		PriorityQueue<Problem> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Problem> minHeap = new PriorityQueue<>();
		// 초기 문제 입력
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			Problem problem = new Problem(l, p);
			isRecommend[p] = l;

			maxHeap.offer(problem);
			minHeap.offer(problem);
		}
		// 명령문 입력
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			// 문제 추가
			if (command.equals("add")) {
				int p = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				Problem problem = new Problem(l, p);
				isRecommend[p] = l;

				maxHeap.offer(problem);
				minHeap.offer(problem);
			} else if (command.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					while (isRecommend[maxHeap.peek().pNum] != maxHeap.peek().difficulty) { // 업데이트가 안된 문제라면 
						maxHeap.poll(); // 제거
					}
					bw.write(maxHeap.peek().pNum.toString());
					bw.newLine();
					bw.flush();
				} else {
					while (isRecommend[minHeap.peek().pNum] != minHeap.peek().difficulty) { // 업데이트가 안된 문제라면 
						minHeap.poll(); // 제거
					}
					bw.write(minHeap.peek().pNum.toString());
					bw.newLine();
					bw.flush();
				}
			} else { // command.equals("solved")
				int p = Integer.parseInt(st.nextToken());
				isRecommend[p] = 0;
			}
		}
		br.close();
		bw.close();
	}

}
