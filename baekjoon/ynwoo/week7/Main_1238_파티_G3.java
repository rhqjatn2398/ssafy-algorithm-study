import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1238_파티_G3 {
    static class Vertex implements Comparable<Vertex> {
        int v;
        int totalDistance;

        public Vertex(int v, int total) {
            this.v = v;
            this.totalDistance = total;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.totalDistance, o.totalDistance);
        }

    }

    static class Edge {
        int t, end;

        public Edge(int t, int end) {
            this.t = t; // 가중치
            this.end = end;
        }
    }

    static ArrayList<Edge>[] graph;

    static int[] totalTime, distance;
    static int N;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        totalTime = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Edge>();

        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(t, e));
        }

        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            }

            dijkstra(i, X); // i에서 X까지 가는데 걸리는 최단 거리 구하기
            totalTime[i] += distance[X];
        }
        //System.out.println(Arrays.toString(totalTime));

        dijkstra2(X);
        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            }
            totalTime[i] += distance[i];
        }
        //System.out.println(Arrays.toString(totalTime));
        int answer = Integer.MIN_VALUE;
        for (int value : totalTime) {
            answer = Integer.max(answer, value);
        }
        System.out.println(answer);
    }

    private static void dijkstra2(int start) {
        distance = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(distance, INF);

        PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
        distance[start] = 0;
        pQueue.offer(new Vertex(start, distance[start]));

        Vertex current = null;

        while (!pQueue.isEmpty()) {

            // step1 : 미방문 정점들 중 최소가중치의 정점 선택
            current = pQueue.poll();
            if (visited[current.v])
                continue;

            visited[current.v] = true; // 선택 정점 방문 처리

            // step2: current정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
            for (Edge e : graph[current.v]) {
                if (!visited[e.end] && distance[e.end] > current.totalDistance + e.t) {
                    distance[e.end] = current.totalDistance + e.t;
                    pQueue.offer(new Vertex(e.end, distance[e.end]));
                }
            }
        }
    }

    private static void dijkstra(int start, int end) {
        distance = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(distance, INF);

        PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
        distance[start] = 0;
        pQueue.offer(new Vertex(start, distance[start]));

        Vertex current = null;

        while (!pQueue.isEmpty()) {

            // step1 : 미방문 정점들 중 최소가중치의 정점 선택
            current = pQueue.poll();
            if (visited[current.v])
                continue;

            visited[current.v] = true; // 선택 정점 방문 처리
            if (current.v == end)
                break; // 선택 정점이 도착정점이면 탈출.

            // step2: current정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
            for (Edge e : graph[current.v]) {
                if (!visited[e.end] && distance[e.end] > current.totalDistance + e.t) {
                    distance[e.end] = current.totalDistance + e.t;
                    pQueue.offer(new Vertex(e.end, distance[e.end]));
                }
            }
        }

    }

}