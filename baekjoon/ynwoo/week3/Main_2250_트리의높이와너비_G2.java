package algoStudy.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2250_트리의높이와너비_G2 {
	static class Node {
		int key;
		int col, level;
		Node parent, left, right;

		public Node(int key) {
			this.key = key;
			this.parent = null;
			this.left = null;
			this.right = null;
		}

	}

	static Node[] nodes;
	static int N, maxLevel = Integer.MIN_VALUE;
	static int currentlevel, maxWidth = Integer.MIN_VALUE;
	static int cnt = 1;
	static int[][] levelWidth; // 각 레벨의 맨 왼쪽 노드와 오른쪽 노드의 col 값 저장

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int key, leftKey, rightKey;
		N = Integer.parseInt(br.readLine());

		nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			key = Integer.parseInt(st.nextToken());
			leftKey = Integer.parseInt(st.nextToken());
			rightKey = Integer.parseInt(st.nextToken());
			if (leftKey != -1) {
				nodes[key].left = nodes[leftKey];
				nodes[leftKey].parent = nodes[key];
			}
			if (rightKey != -1) {
				nodes[key].right = nodes[rightKey];
				nodes[rightKey].parent = nodes[key];
			}
		}
		// 루트 찾기
		Node root = null;
		for (int i = 1; i <= N; i++) {
			if (nodes[i].parent == null) {
				root = nodes[i];
				break;
			}
		}
		getNodeLevel(root, 1);
		// System.out.println(maxLevel);
		levelWidth = new int[maxLevel + 1][2];
		traversal(root); // 열 구하기

		for (int i = 1; i <= maxLevel; i++) {
			// System.out.println(levelWidth[i][1] - levelWidth[i][0] + 1);
			if (maxWidth < levelWidth[i][1] - levelWidth[i][0] + 1) {
				currentlevel = i;
				maxWidth = levelWidth[i][1] - levelWidth[i][0] + 1;
			}
			// System.out.println(Arrays.toString(levelWidth[i]));
		}
		System.out.println(currentlevel + " " + maxWidth);
	}

	// 특정 노드의 왼쪽 자식노드의 열, 오른쪽 자식노드의 열을 저장하는 함수
	private static void traversal(Node node) {
		if (node == null) {
			return;
		}
		traversal(node.left);
		node.col = cnt++;

		if (levelWidth[node.level][0] == 0) { // 아직 왼쪽 없음
			levelWidth[node.level][0] = node.col;
			if (node.level == 1) {
				levelWidth[node.level][1] = node.col;
			}
		} else {
			levelWidth[node.level][1] = node.col;
		}

		traversal(node.right);
	}

	// 특정 노드를 루트로 하는 트리의 노드의 개수를 리턴하는 함수
	private static void getNodeLevel(Node node, int level) {
		if (node == null) {
			return;
		}
		getNodeLevel(node.left, level + 1);
		getNodeLevel(node.right, level + 1);
		node.level = level;
		maxLevel = Math.max(maxLevel, node.level);
		return;
	}

}
