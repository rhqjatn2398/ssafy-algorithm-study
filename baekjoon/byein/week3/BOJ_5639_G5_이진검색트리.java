package byein.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5639_G5_이진검색트리 {

	/**
	 * 트리를 구성할 노드 클래스
	 * 
	 * @author SSAFY
	 *
	 */
	static class Node {
		// 정점
		int v;
		// 왼쪽 자식
		Node left;
		// 오른쪽 자식
		Node right;

		// 루트 노드 생성자
		public Node(int v) {
			this.v = v;
			this.left = null;
			this.right = null;
		}

		// 자식 노드들 생성자
		public Node(int v, Node parent) {
			super();
			this.v = v;
			this.left = null;
			this.right = null;
		}

		// 노드 추가 메소드
		public static Node insertNode(Node node, int val) {
			Node cur = null;
			// 현재 노드가 널이면 루트 노드로 판단 하여 새로운 노드 리턴
			if (node == null) {
				return new Node(val);
			}

			if (node.v > val) {
				// 왼쪽 자식
				cur = insertNode(node.left, val);
				node.left = cur;
			} else {
				// 오른쪽 자식
				cur = insertNode(node.right, val);
				node.right = cur;
			}
			return node;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int root = Integer.parseInt(br.readLine());
		// 루트노드 생성자로 트리 생성
		Node tree = new Node(root);
		// 현재 노드 담기
		Node cur = tree;
		String s;
		// 입력이 있는 동안
		while ((s = br.readLine()) != null) {
			int val = Integer.parseInt(s);
			tree.insertNode(cur, val);
		}

		postOrder(tree);
	}

	/**
	 * 후위 순회 출력
	 * 
	 * @param cur
	 */
	private static void postOrder(Node cur) {
		// 리프 노드면 종료.
		if (cur == null) {
			return;
		}
		// 왼쪽-오른쪽-현재 순 순회
		postOrder(cur.left);
		postOrder(cur.right);
		System.out.println(cur.v);
	}

}
