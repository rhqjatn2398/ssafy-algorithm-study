package algoStudy.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13549_숨바꼭질3_G5 {
	static class Vertex implements Comparable<Vertex> {
		int v; // 현재 위치
		int distance; // 출발점에서 현재 위치까지의 최단 거리

		public Vertex(int v, int distance) {
			this.v = v;
			this.distance = distance;
		}

		// 우선순위 큐에서 최단 거리가 작은 Vertex 객체가 우선순위가 높도록 구현
		@Override
		public int compareTo(Vertex o) {
			if (this.distance < o.distance) {
				return -1;
			} else if (this.distance == o.distance) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	static int[] distance = new int[100001]; // 출발점에서 각 정점까지의 최단 거리
	static int INF = Integer.MAX_VALUE; // 초기 거리 값 무한으로 설정

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		int K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치

		// 다익스트라 알고리즘 실행
		dijkstra(N);

		// 출력
		System.out.println(distance[K]); // N에서 K로 가는 최단 경로
	}

	// Dijkstra 알고리즘 수행 과정
	// 1. 모든 정점의 distance를 무한대로 초기화한다.
	// 2. 출발정점 s에 대해 distance[s]를 0으로 저장한다.
	// 3. 출발 정점을 우선순위 큐에 삽입
	// 4. 우선순위 큐에서 최단거리 값이 가장 짧은 정점 poll
	// 5. 방문가능한 정점들에 대해(간선) 최단거리가 갱신되면 우선순위 큐에 추가
	// 6. 우선순위 큐에 남은 정점이 없을 때 까지(=모든 정점들에 대해) 4. 5. 반복
	private static void dijkstra(int N) {
		Arrays.fill(distance, INF); // 모든 정점의 거리 값을 무한대로 초기화
		distance[N] = 0; // 출발 지점의 거리 값을 0으로 저장

		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(N, distance[N])); // 출발 지점을 우선순위 큐에 삽입

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll(); // 우선순위 큐에서 최단 거리가 가장 짧은 Vertex 객체 추출
			int[] nextVs = { cur.v - 1, cur.v + 1, cur.v * 2 }; // 이동 가능한 위치들

			for (int i = 0; i < 3; i++) {
				int nextV = nextVs[i]; // 다음 위치
				int nextDistance = cur.distance; // 다음 위치까지의 거리 값

				// +1 -1로 움직일 때만 가중치가 1이므로
				if (i == 0 || i == 1) {
					nextDistance = cur.distance + 1;
				}

				// 범위 벗어나는 경우 무시
				if (nextV < 0 || nextV > 100000) {
					continue;
				}

				// 거리가 갱신되면 우선순위 큐에 추가
				if (nextDistance < distance[nextV]) {
					distance[nextV] = nextDistance;
					pq.offer(new Vertex(nextV, nextDistance));
				}
			}

		}

	}

}