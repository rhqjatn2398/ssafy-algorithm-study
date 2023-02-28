package algoStudy.week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Node implements Comparable<Node> {
	int vertex; // 정점번호
	Map<Node, Integer> childs = new TreeMap<Node, Integer>(); // 자식 노드 : 가중치

	public Node(int vNum) {
		vertex = vNum;
	}

	@Override
	public int compareTo(Node o) {
		if (this.vertex < o.vertex) {
			return -1;
		} else if (this.vertex == o.vertex) {
			return 0;
		} else {
			return 1;
		}
	}
}

public class Main_1167_트리의지름_G2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int V;
	static Node[] tree; // 정점 노드 배열
	static Node furthestNode; // 가장 먼 노드를 저장할 변수
	static boolean[] visited;
	static int distance, answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		// 입력
		V = Integer.parseInt(br.readLine());
		tree = new Node[V + 1];
		for (int i = 1; i <= V; i++) {
			tree[i] = new Node(i);
		}

		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int vNum = Integer.parseInt(st.nextToken());

			while (true) {
				int nextV = Integer.parseInt(st.nextToken());
				if (nextV == -1) {
					break;
				}
				int weight = Integer.parseInt(st.nextToken());

				tree[vNum].childs.put(tree[nextV], weight); // 주어진 정점번호에 해당하는 노드의 자식Map에 자식노드와 가중치 추가
			}
		}

		// 구현(시간초과)
//		for (int i = 1; i <= V; i++) {
//			if (isLeafNode(tree[i])) {
//				temp = 0;
//				visited = new boolean[V + 1];
//				dfs(i);
//			}
//		}

		/**
		 * 이문제는 모든 정점에 대해서 DFS를 호출하지 않고 DFS를 2번 호출해서 문제를 해결할 수 있다.
		 * 
		 * [트리의 지름 알고리즘]
		 * 
		 * 1. 임의의 정점 x에서 가장 멀리 떨어진 정점 u를 찾는다
		 * 
		 * 2. 1에서 찾은 정점 u에서 가장 멀리 떨어진 정점 v를 찾는다
		 * 
		 * 3. u - v가 트리의 지름이 된다.
		 */

		// 1.
		visited = new boolean[V + 1];
		distance = 0;
		dfs(1);

		// 2.
		visited = new boolean[V + 1];
		answer = Integer.MIN_VALUE;
		distance = 0;
		dfs(furthestNode.vertex);

		// 3.
		System.out.println(answer);
		br.close();
	}

	private static void dfs(int i) {
		// 방문
		visited[i] = true;
		for (Node childNode : tree[i].childs.keySet()) {
			int nextVertex = childNode.vertex;
			if (!visited[nextVertex]) {
				distance += tree[i].childs.get(childNode);

				if (answer < distance) {
					answer = Math.max(answer, distance);
					furthestNode = tree[nextVertex];
				}
				dfs(nextVertex);
				distance -= tree[i].childs.get(childNode);
			}
		}
	}

}