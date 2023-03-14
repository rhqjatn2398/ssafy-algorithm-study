package bumsoo.week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766_G2_문제집 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m;
    static List<Integer>[] adj;
    static int[] inDegree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        inDegree = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // DAG (Directed Acyclic Graph) 구성
            adj[from].add(to);
            inDegree[to]++;
        }

        topologySort();

        bw.flush();
    }

    static void topologySort() throws Exception {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                visited[i] = true;
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            bw.write(Integer.toString(cur) + " ");

            for (int adjNum : adj[cur]) {
                inDegree[adjNum]--;

                if (!visited[adjNum] && inDegree[adjNum] == 0) {
                    visited[adjNum] = true;
                    pq.add(adjNum);
                }
            }
        }
    }
}
