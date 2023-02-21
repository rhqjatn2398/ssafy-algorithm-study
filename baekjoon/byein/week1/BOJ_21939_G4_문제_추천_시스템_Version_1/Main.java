package baekjoon.김예빈.week01.BOJ_21939_문제_추천_시스템_Version_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());

		// Map, 우선순위 큐 사용.
		// Map은 우선순위 큐에서 제거를 수월하게 하기 위한 도구.
		Map<Integer, Problem> map = new HashMap<>();
		// 어려운 문제 순으로 정렬한 우선순위 큐
		PriorityQueue<Problem> pq = new PriorityQueue<>(new Comparator<Problem>() {
			@Override
			public int compare(Problem p1, Problem p2) {
				if (p1.level != p2.level) {
					return p2.level - p1.level;
				} else {
					return p2.idx - p1.idx;
				}
			}
		});
		// 쉬운 문제 순으로 정렬한 우선순위 큐
		PriorityQueue<Problem> reversedPq = new PriorityQueue<>(new Comparator<Problem>() {
			@Override
			public int compare(Problem p1, Problem p2) {
				if (p1.level != p2.level) {
					return p1.level - p2.level;
				} else {
					return p1.idx - p2.idx;
				}
			}
		});

		// 문제를 입력받아 map, 우선순위 큐에 모두 넣기.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			Problem p = new Problem(idx, level);
			map.put(idx, p);
			pq.add(p);
			reversedPq.add(p);
		}

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		String command;
		// 명령어를 입력받아 처리.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			command = st.nextToken();
			// 명령어에 따라 동작 처리.
			switch (command) {
			// add인 경우, map, 우선순위 큐에 모두 추가.
			case "add":
				int idx = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				Problem p = new Problem(idx, level);
				map.put(idx, p);
				pq.add(p);
				reversedPq.add(p);
				break;
			// recommend인 경우,
			case "recommend":
				int x = Integer.parseInt(st.nextToken());
				// 1이라면 어려운 문제 순이므로 pq의 peek 출력.
				if (x == 1) {
					Problem hardest = pq.peek();
					sb.append(hardest.idx).append("\n");
				} else { // -1이라면 쉬운 문제 순이므로 reversedPq의 peek 출력.
					Problem easiest = reversedPq.peek();
					sb.append(easiest.idx).append("\n");
				}
				break;
			// solved인 경우, map에서 제거 후, 반환된 값을 이용해서 우선순위 큐 모두에서 제거.
			case "solved":
				int P = Integer.parseInt(st.nextToken());
				Problem removedP = map.remove(P);
				pq.remove(removedP);
				reversedPq.remove(removedP);
				break;

			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}

	// Problem 클래스 정의. map, 우선순위 큐에서 원활하게 사용하고자 정의.
	public static class Problem {
		int idx;
		int level;

		public Problem(int idx, int level) {
			super();
			this.idx = idx;
			this.level = level;
		}

	}

}
