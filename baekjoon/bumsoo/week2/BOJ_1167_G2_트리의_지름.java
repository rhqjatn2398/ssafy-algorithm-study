import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static StringTokenizer st;

    int v;
    long ans;
    List<List<AdjElement>> adj;
    boolean visited[];

    class AdjElement {
        int vertex;
        int dist;

        public AdjElement(int vertex, int dist) {
            super();
            this.vertex = vertex;
            this.dist = dist;
        }

    }

    class Result {
        int vertex;
        long dist;

        public Result(int vertex, long dist) {
            super();
            this.vertex = vertex;
            this.dist = dist;
        }

    }

    public static void main(String[] args) throws Exception {
        Main m = new Main();

        m.solution();
    }

    void solution() throws IOException {
        v = Integer.parseInt(br.readLine());

        adj = new ArrayList<List<AdjElement>>(v + 1);
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[v + 1];

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());

            while (true) {
                int dst = Integer.parseInt(st.nextToken());
                if (dst == -1) {
                    break;
                }

                int dist = Integer.parseInt(st.nextToken());

                adj.get(src).add(new AdjElement(dst, dist));
            }
        }

        // 입력 끝------------------------------------------

        // 트리의 한쪽 끝 정점을 구한다
        Result firstMaxResult = findMaxDistVertex(1);
        // 한쪽 끝 정점에서 가장 먼 정점과 거리를 구한다
        Result secondMaxResult = findMaxDistVertex(firstMaxResult.vertex);

        // 그 거리가 트리의 지름이다
        System.out.println(secondMaxResult.dist);
    }

    Result findMaxDistVertex(int startV) {
        Arrays.fill(visited, false);
        visited[startV] = true;
        long[] dists = new long[v + 1];

        // DFS로 dists 배열에 startV 정점으로부터의 거리를 저장
        go(dists, startV, 0);

        int maxInd = -1;
        long maxValue = -1;
        for (int i = 1; i <= v; i++) {
            if (dists[i] > maxValue) {
                maxValue = dists[i];
                maxInd = i;
            }
        }

        return new Result(maxInd, maxValue);
    }

    void go(long[] dists, int cur, long curDist) {
        dists[cur] = curDist;

        for (AdjElement e : adj.get(cur)) {
            if (visited[e.vertex]) {
                continue;
            }

            visited[e.vertex] = true;
            go(dists, e.vertex, curDist + e.dist);
        }
    }
}
