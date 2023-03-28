package byein.week3;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 풀이.
 * 
 * 먼저 전위순회 방법으로 각 노드가 몇 번 열에 존재하는지 파악. 그 후 BFS를 통해 한 차수에 최대 너비 파악.
 * 
 * @author SSAFY
 *
 */
public class BOJ_2250_G2_트리의높이와너비 {
	// 노드 클래스.
	static class Node {
		int data;
		int left;
		int right;
		int parent;

		public Node(int data, int left, int right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
			this.parent = -1;
		}

	}

	static int N, rootIdx, point;
	static int[] treeDepthLeft, treeDepthRight, treeDepthGap;
	static ArrayList<Point> list = new ArrayList<>();
	static int[] vInput, leftDataInput, rightDataInput;
	static List<Node> tree = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		// 각 레벨 별 가장 왼쪽에 있는 노드를 담을 배열.
		treeDepthLeft = new int[N + 1];
		// 각 레벨 별 가장 오른쪽에 있는 노드를 담을 배열.
		treeDepthRight = new int[N + 1];
		// 각 레벨 별 오른쪽 노드 데이터 - 왼쪽 노드 데이터 + 1 값을 담을 배열. 
		treeDepthGap = new int[N + 1];

		vInput = new int[N];
		leftDataInput = new int[N];
		rightDataInput = new int[N];
		// 배열 초기화.
		for (int i = 0; i <= N; i++) {
			treeDepthLeft[i] = Integer.MAX_VALUE;
			treeDepthRight[i] = -1;
			treeDepthGap[i] = -1;
		}

		// 루트노드가 1이 아니므로 모든 데이터를 우선 자식 없이 모두 생성.
		list.add(new Point(-1, 0));
		for (int i = 0; i <= N; i++) {
			tree.add(new Node(i, -1, -1));
		}
		// 입력을 받으면서 데이터의 왼쪽에 왼쪽 자식, 오른쪽에 오른쪽 자식 연결.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int leftData = Integer.parseInt(st.nextToken());
			int rightData = Integer.parseInt(st.nextToken());

			tree.get(V).left = leftData;
			tree.get(V).right = rightData;
			// 자식이 -1이 아니면 모든 자식의 부모로 현재 노드 데이터 설정.
			if (leftData != -1) {
				tree.get(leftData).parent = V;
			}
			if (rightData != -1) {
				tree.get(rightData).parent = V;
			}
		}

		// 부모가 -1인 경우를 루트노드로 설정.
		for (int i = 1; i <= N; i++) {
			if (tree.get(i).parent == -1) {
				rootIdx = i;
				break;
			}

		}

		// 루트노드부터 중위 순회.
		inOrder(rootIdx, 1);

		int mxIdx = -1, mx = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			treeDepthGap[i] = treeDepthRight[i] - treeDepthLeft[i] + 1;
			if (mx < treeDepthGap[i]) {
				mx = treeDepthGap[i];
				mxIdx = i;
			}
		}

		System.out.println(mxIdx + " " + mx);
	}

	/**
	 * 중위 순회 함수.
	 * @param idx : 현재 노드 데이터.
	 * @param level : 현재 노드 레밸.
	 */
	static void inOrder(int idx, int level) {
		Node node = tree.get(idx);

		// 왼쪽 자식.
		if (node.left != -1) {
			inOrder(node.left, level + 1);
		}

		// 현재 노드.
		// 각 레벨별 가장 왼쪽, 오른쪽에 있는 값 갱신.
		treeDepthLeft[level] = Math.min(treeDepthLeft[level], point);
		treeDepthRight[level] = point;
		point++;

		// 오른쪽 자식.
		if (node.right != -1) {
			inOrder(node.right, level + 1);
		}
	}

}
