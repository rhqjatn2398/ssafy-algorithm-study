package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2042_G1_구간합구하기 {

	static int N, M, K, a, b;
	static long c;
	static long[] nums;

	/**
	 * 세그먼트 트리 만들기.
	 * @author byein
	 *
	 */
	static class SegmentTree {
		long tree[];
		int treeSize;

		// 생성 시 트리 개수가 최대 2^(h+1).
		public SegmentTree(int arrSize) {

			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];
		}

		// 초기화로 세그먼트 트리 노드에 합 구하기.
		public long init(long nums[], int idx, int start, int end) {
			if (start == end) {
				return tree[idx] = nums[start];
			}
			return tree[idx] = init(nums, idx * 2, start, (start + end) / 2)
					+ init(nums, idx * 2 + 1, (start + end) / 2 + 1, end);
		}

		// 수정 시 갱신
		public void update(int cur, int start, int end, int idx, long diff) {
			if (idx < start || idx > end)
				return;
			tree[cur] += diff;
			if (start != end) {
				update(cur * 2, start, (start + end) / 2, idx, diff);
				update(cur * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
			}
		}

		// 합 구하기.
		public long sum(int idx, int start, int end, int left, int right) {
			if (left > end || right < start)
				return 0;

			if (left <= start && end <= right) {
				return tree[idx];
			}

			return sum(idx * 2, start, (start + end) / 2, left, right)
					+ sum(idx * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}

		@Override
		public String toString() {
			return "SegmentTree [tree=" + Arrays.toString(tree) + ", treeSize=" + treeSize + "]";
		}

		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N + 1];
		nums[0] = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			nums[i] = Long.parseLong(st.nextToken());
		}
		SegmentTree tree = new SegmentTree(N);
		tree.init(nums, 1, 1, N);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if (a == 1) {
				tree.update(1, 1, N, b, c - nums[b]);
				nums[b] = c;
			} else if (a == 2) {
				System.out.println(tree.sum(1, 1, N, b, (int) c));
			}
		}
	}

}
