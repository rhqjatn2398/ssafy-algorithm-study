package algoStudy.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Node {
	int key;
	Node left, right;

	public Node(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}

}

public class Main_5639_이진검색트리_G5 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node root = null;
		String input;
		int n;
		// 입력이 없을 때 까지
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			n = Integer.parseInt(input);
			root = insert(root, n); // 삽입
		}

		// 순회 결과 출력
		traversal(root);
		// 스택을 사용해 구현
		// traversalUsingStack(root);
	}

	// 재귀로 구현한 노드 삽입
	// node를 루트로 하는 서브트리에 n을 삽입하고 서브트리의 루트노드를 리턴
	private static Node insert(Node node, int n) {
		// 1. 노드가 null인 경우 -> 서브트리가 비어있는 경우
		// n을 추가하면 노드가 n 한개만 있는 서브트리가 생기기 때문에 n노드를 만들고 리턴
		if (node == null) {
			return new Node(n);
		}

		// 2. 노드의 왼쪽 자식에 n이 들어가야 하는 경우(현재 노드의 키보다 작으면 왼쪽 서브트리에 추가)
		// 왼쪽 서브트리에 n을 삽입하고 그 왼쪽 서브트리의 루트노드를 node의 왼쪽 자식으로 만들고 node 그대로 리턴
		if (n < node.key) { // 현재 노드의 키보다 작으면 왼쪽 서브트리에 추가
			node.left = insert(node.left, n);
		} else if (node.key < n) { // 현재 노드의 키보다 크면 오른쪽 서브트리에 추가
			node.right = insert(node.right, n);
		}
		return node; // 중복 키는 무시
	}

	private static void traversal(Node node) {
		if (node == null) {
			return;
		}
		// System.out.println(node.key); // 전위 순회
		traversal(node.left);
		// System.out.println(node.key); // 중위 순회
		traversal(node.right);
		System.out.println(node.key); // 후위 순회
	}

	private static void traversalUsingStack(Node node) {
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> print = new Stack<Node>(); // 방문순서를 저장할 스택
		stack.push(node);
		while (!stack.isEmpty()) {
			Node top = stack.pop();
			print.push(top);

			if (top.left != null) {
				stack.push(top.left);
			}

			if (top.right != null) {
				stack.push(top.right);
			}

		}
		while (!print.isEmpty()) {
			Node top = print.pop();
			System.out.println(top.key);
		}
	}
}
