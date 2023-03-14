package algoStudy.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1766_문제집_G2 {
	static int N, M;
	static List<Integer>[] lists; // 인접 리스트(인접 행렬 메모리초과)
	static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	static int[] inDegree; // 진입 차수

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lists = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			lists[i] = new ArrayList<Integer>();
		}
		inDegree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			lists[A].add(B);
			inDegree[B]++; // 진입차수 저장
		}

		// 위상 정렬 구현
		bfs();

		// 출력
		System.out.println(sb);

	}

	private static void bfs() {
		// 우선순위 큐 (3. 가능하면 쉬운 문제부터 풀어야 한다는 조건)
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		boolean[] visited = new boolean[N + 1]; // 방문 여부

		// 1. 진입 차수가 0인 노드(시작점)를 큐에 모두 넣는다.
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0 && !visited[i]) {
				queue.add(i);
				visited[i] = true;
			}
		}

		// 큐가 공백 큐 상태가 될 때 까지 2~3 작업 반복
		while (!queue.isEmpty()) {
			// 2. 큐에서 진입 차수가 0인 노드를 꺼내어
			int cur = queue.poll();
			sb.append(cur).append(" ");
			// 2-1. 자신과 인접한 노드의 간선을 제거한다.
			for (int j : lists[cur]) {
				inDegree[j]--; // --> 인접한 노드의 진입 차수를 1감소시킨다.

				// 3. 간선 제거 후 진입 차수가 0이 된 노드를 큐에 넣는다.
				if (inDegree[j] == 0 && !visited[j]) {
					queue.add(j);
					visited[j] = true;
				}
			}
		}
	}

}