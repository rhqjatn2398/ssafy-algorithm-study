package bumsoo.week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_4195_G2_친구_네트워크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int f;
    static final int MAX = 200_000;
    static Map<String, Integer> nameToIdx;
    static DisSet disSet;

    static class DisSet {
        int[] parents;
        int[] ranks;
        int[] size;

        public DisSet() {
            this.parents = new int[MAX];
            this.ranks = new int[MAX];
            this.size = new int[MAX];

            for (int i = 0; i < MAX; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int idx) {
            if (parents[idx] == idx) {
                return idx;
            }

            return parents[idx] = find(parents[idx]);
        }

        public boolean union(int a, int b) {
            int setA = find(a);
            int setB = find(b);

            if (setA == setB) {
                return false;
            }

            if (ranks[setA] > ranks[setB]) {
                parents[setB] = setA;
                size[setA] += size[setB];
            } else if (ranks[setA] < ranks[setB]) {
                parents[setA] = setB;
                size[setB] += size[setA];
            } else {
                parents[setB] = setA;
                ranks[setA]++;
                size[setA] += size[setB];
            }

            return true;
        }

        public int getSize(int idx) {
            int set = find(idx);

            return size[set];
        }
    }

    public static void main(String[] args) throws Exception {
        int testNum = Integer.parseInt(br.readLine());
        for (int testCnt = 1; testCnt <= testNum; testCnt++) {
            solution();
        }

        bw.flush();
    }

    static void solution() throws Exception {
        f = Integer.parseInt(br.readLine());

        nameToIdx = new HashMap<>();
        disSet = new DisSet();

        int idx = 0;
        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(br.readLine());

            String name1 = st.nextToken();
            String name2 = st.nextToken();
            if (!nameToIdx.containsKey(name1)) {
                nameToIdx.put(name1, idx++);
            }

            if (!nameToIdx.containsKey(name2)) {
                nameToIdx.put(name2, idx++);
            }

            disSet.union(nameToIdx.get(name1), nameToIdx.get(name2));

            bw.write(Integer.toString(disSet.getSize((nameToIdx.get(name1)))));
            bw.newLine();
        }
    }

}
