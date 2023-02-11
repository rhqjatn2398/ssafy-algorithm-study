import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Problem implements Comparable<Problem> {
		int p; // 문제번호
		int l; // 난이도
		boolean isRecommended;

		public Problem(int p, int l, boolean isRecommended) {
			this.p = p;
			this.l = l;
			this.isRecommended = isRecommended;
		}

		@Override
		public int compareTo(Problem o) {
			if (o.l == this.l)
				return this.p - o.p; // 오름차순
			return this.l - o.l;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Problem> minHeap = new PriorityQueue<Problem>();
		PriorityQueue<Problem> maxHeap = new PriorityQueue<Problem>(Collections.reverseOrder());

		Set<Integer> solvedSet = new HashSet<Integer>(); // 푼 문제번호를 저장하는 set

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			Problem problem = new Problem(p, l, true);
			maxHeap.add(problem);
			minHeap.add(problem);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "add":
				int p = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				Problem problem = new Problem(p, l, false);
				maxHeap.add(problem);
				minHeap.add(problem);
				break;
			case "recommend":
				int num = Integer.parseInt(st.nextToken());
				PriorityQueue<Problem> pq = num == 1 ? maxHeap : minHeap;
				while (pq.peek().isRecommended && solvedSet.contains(pq.peek().p)) {
					pq.poll();
				}
				sb.append(pq.peek().p).append("\n");
				break;
			case "solved":
				solvedSet.add(Integer.parseInt(st.nextToken())); // 푼 문제 관리하기
				break;
			default:
				break;
			}

		}
		System.out.println(sb.toString());
	}

}
