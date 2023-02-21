package com.ssafy.w02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1167_G2_트리의지름 {

	// 트리의 지름
	static int mx = Integer.MIN_VALUE;
	// 루트노드에서 가장 먼 자식 노드
	static int node;
	// 트리
	static ArrayList<Node>[] tree;
	// 방문 처리 위한 배열
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		
		// 트리 배열 할당.
		tree = new ArrayList[V + 1];
		for (int i = 1; i < V+1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int next = 0;
			// -1 전까지 입력 처리.
			while ((next = Integer.parseInt(st.nextToken())) != -1) {
				int node = next;
				int edge = Integer.parseInt(st.nextToken());
				// 현재 정점 위치에 연결된 정점과 간선 추가.
				tree[N].add(new Node(node, edge));
			}
		}
		// 방문 초기화 후 처음 위치에서 dfs 진행하여 최대 깊이 구하기.
		visited = new boolean[V + 1];
		dfs(1, 0);

		// 방문 초기화 후 가장 먼 노드에서 dfs 진행하여 해당 최대 깊이(트리의 지름) 구하기.
		visited = new boolean[V + 1];
		dfs(node, 0);

		System.out.println(mx);

	}

	/**
	 * dfs
	 * @param cur : 현재 위치
	 * @param depth : 깊이
	 */
	public static void dfs(int cur, int depth) {
		// 최댓값, 가장 먼 자식노드 갱신.
		if (depth > mx) {
			mx = depth;
			node = cur;
		}

		// 방문 처리.
		visited[cur] = true;
		
		// 현재 트리의 자식들 탐색.
		for (int i = 0; i < tree[cur].size(); i++) {
			Node node = tree[cur].get(i);
			// 방문한 경우 넘기기.
			if (visited[node.cur] == true)
				continue;
			// 방문하지 않은 경우이므로 방문처리 후 자식 노드에 대해 dfs 진행.
			// 방문 처리 복구.
			visited[node.cur] = true;
			dfs(node.cur, node.cost + depth);
			visited[node.cur] = false;
		}
	}
	
	/**
	 * 정점과 간선 정보를 갖는 노드 클래스.
	 * @author SSAFY
	 *
	 */
	static class Node {
		int cur;
		int cost;
		
		public Node(int cur, int cost) {
			this.cur = cur;
			this.cost = cost;
		}
	}
}