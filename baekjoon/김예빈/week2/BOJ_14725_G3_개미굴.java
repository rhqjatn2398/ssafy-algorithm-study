package com.ssafy.w02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_14725_G3_개미굴 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 노드 클래스를 통해 루트 할당.
		Node root = new Node();

		int N = Integer.parseInt(st.nextToken());

		// 입력 줄 수 만큼
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());

			// 현재 노드.
			Node cur = root;

			// 각 줄에서 먹이 정보가 주어지는 동안
			for (int j = 0; j < K; j++) {
				String s = st.nextToken();

				// 현재 노드의 자식노드 중에서 같은 먹이 정보를 가진 노드가 있지 않다면
				if (!cur.child.containsKey(s)) {
					// 현재 노드의 자식 노드로 먹이값을 키로 갖는 새로운 노드 할당.
					cur.child.put(s, new Node());
				}
				// 먹이 정보가 이미 있다면 해당 노드를 현재 노드로 갱신.
				cur = cur.child.get(s);
			}
		}
		// dfs 탐색.
		dfs(root, 0);

	}

	/**
	 * dfs
	 * 
	 * @param cur   : 현재 노드
	 * @param depth : 깊이
	 */
	public static void dfs(Node cur, int depth) {
		// 현재 노드의 자식들의 keySet을 받아 배열로 반환.
		Object[] key = cur.child.keySet().toArray();
		// 자식들의 key 즉, 자식 먹이들 순회.
		for (Object s : key) {
			// 깊이만큼 앞에 prefix로 "--" 출력.
			for (int i = 0; i < depth; i++) {
				System.out.print("--");
			}
			// 먹이 출력.
			System.out.println(s);
			// 현재 노드를 자식노드로 하고 깊이를 1 증가시켜 dfs 실행.
			dfs(cur.child.get(s), depth + 1);
		}

	}

	/**
	 * 트리맵을 갖는 노드 클래스.
	 * 
	 * @author SSAFY
	 *
	 */
	static class Node {
		TreeMap<String, Node> child = new TreeMap<>();
	}
}
