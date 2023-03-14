package bumsoo.week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2042_G1_구간_합_구하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class SegmentTree {
        long[] tree;

        public SegmentTree(int size) {
            this.tree = new long[size + 1];
        }

        long init(long[] input, int node, int start, int end) {
            if (start == end) {
                return tree[node] = input[start];
            }

            return tree[node] =
                    init(input, node * 2, start, (start + end) / 2) + init(input, node * 2 + 1, (start + end) / 2 + 1,
                            end);
        }

        long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            return sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end,
                    left, right);
        }

        void update(int node, int start, int end, int index, long diff) {
            if (index < start || end < index) {
                return;
            }

            tree[node] += diff;

            if (start != end) {
                update(node * 2, start, (start + end) / 2, index, diff);
                update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
            }
        }
    }

    static int n, m, k;
    static long[] input;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        input = new long[n + 1];

        int size = (int) Math.pow(2, Math.ceil(log2(input.length)) + 1) - 1;
        SegmentTree segTree = new SegmentTree(size);

        for (int i = 1; i <= n; i++) {
            input[i] = Long.parseLong(br.readLine());
        }

        segTree.init(input, 1, 1, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            long sum = -1;
            if (a == 1) {
                long diff = c - input[b];
                input[b] = c;
                segTree.update(1, 1, n, b, diff);
            } else {
                sum = segTree.sum(1, 1, n, b, (int) c);
                bw.write(Long.toString(sum));
                bw.newLine();
            }
        }

        bw.flush();
    }

    static double log2(int x) {
        return Math.log(x) / Math.log(2);
    }
}
