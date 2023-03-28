package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766_G2_문제집 {

	static int N, M, A, B;

	static ArrayList<Integer>[] adjList; // 인접 리스트 활용.
	static int[] inDegree; // 진입차수 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 1-index 사용을 위해 배열 N+!로 초기화.
		adjList = new ArrayList[N + 1];
		inDegree = new int[N + 1];
		// 인접리스트는 사용 전 초기화 필요!
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 모든 간선을 돌면서 인접리스트에 간선 정보를 넣고, 진입차수 증가 시 추가.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			adjList[A].add(B);
			++inDegree[B];
		}

		// 위상정렬된 결과를 담을 리스트.
		ArrayList<Integer> orderList = topologySort();
		// 위상정렬 결과의 크기가 N이라면 제대로 정렬된 것으로 출력.
		if (orderList.size() == N) {
			for (int o : orderList) {
				System.out.print(o+" ");
			}
			System.out.println();
		}
	}

	// 쉬운 문제부터 풀기. 어떻게 구현? PriorityQueue 사용.
	/**
	* 위상정렬 함수.
	* 
	*/
	private static ArrayList<Integer> topologySort() {
		// 결과를 담을 리스트.
		ArrayList<Integer> orderList = new ArrayList<>();
		// 우선순위 큐 사용.
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		// 정점을 돌면서 진입차수가 0이라면 큐에 넣기.
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}

		// 큐가 빌 때까지
		while (!queue.isEmpty()) {
			// 결과 리스트에 현재 큐의 top을 넣기.
			int cur = queue.poll();
			orderList.add(cur);
			// 현재 정점과 인접한 정점들 중에서
			for (int next : adjList[cur]) {
				// 진입차수가 0인 걸 큐에 넣고 진입차수 감소
				if (--inDegree[next] == 0)
					queue.offer(next);
			}
		}
		return orderList;
	}

}