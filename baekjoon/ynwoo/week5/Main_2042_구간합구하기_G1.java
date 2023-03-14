package algoStudy.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2042_구간합구하기_G1 {
	static int N, M, K;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;

		long[] tree = new long[1 << h];

		init(arr, tree, 1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) { // 변경
				updateTree(arr, tree, 1, 0, N - 1, b - 1, c);
			} else { // b번째 ~ c번째 합 출력
				sb.append(query(tree, 1, 0, N - 1, b - 1, c - 1)).append("\n");
			}
		}
		// 출력
		System.out.println(sb);
	}

	private static void init(long[] arr, long[] tree, int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}
		init(arr, tree, node * 2, start, (start + end) / 2);
		init(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	private static long query(long[] tree, int node, int start, int end, int left, long right) {
		if (left > end || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		long lsum = query(tree, node * 2, start, (start + end) / 2, left, right);
		long rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);

		return lsum + rsum;
	}

	private static void updateTree(long[] arr, long[] tree, int node, int start, int end, int idx, long value) {
		if (idx < start || idx > end) {
			return;
		}
		if (start == end) {
			arr[idx] = value;
			tree[node] = value;
			return;
		}
		updateTree(arr, tree, node * 2, start, (start + end) / 2, idx, value);
		updateTree(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, value);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
}