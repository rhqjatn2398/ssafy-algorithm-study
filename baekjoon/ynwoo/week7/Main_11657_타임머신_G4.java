import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11657_타임머신_G4 {
    static class Edge {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static ArrayList<Edge> edgeList = new ArrayList<>();
    static long[] distance;
    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(a, b, c));
        }

        // bellmanFord(n, 1); // 시작 지점
        // System.out.println(Arrays.toString(distance));

        if (bellmanFord(n, 1)) {
            for (int i = 2; i <= n; i++) {
                System.out.println(distance[i] != INF ? distance[i] : -1);
            }
        } else {
            System.out.println(-1);
        }
    }

    private static boolean bellmanFord(int n, int start) {
        distance = new long[n + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        for (int i = 1; i < n; i++) {
            for (Edge e :
                    edgeList) {
                int u = e.start;
                int z = e.end;
                if (distance[u] != INF && distance[z] > distance[u] + e.weight)
                    distance[z] = distance[u] + e.weight;
            }
        }

        for (Edge e :
                edgeList) {
            int u = e.start;
            int z = e.end;

            if (distance[u] != INF && distance[z] > distance[u] + e.weight) {
                // 음의 싸이클 발생
                return false;
            }
        }
        return true;
    }
}
