import java.util.*;

class Solution {
    
    static int[] distS, distA, distB;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;
    
    // 다익스트라에 사용할 Node
    static class Node implements Comparable<Node>{
        int idx, cost;

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        distS = new int[n+1];
        distA = new int[n+1];
        distB = new int[n+1];
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];

        for (int i = 0;i<n+1;i++){
            graph[i] = new ArrayList();
            distS[i] = INF;
            distA[i] = INF;
            distB[i] = INF;
        }
        
        // 무향 그래프
        for (int i = 0;i<fares.length;i++){
            graph[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
            graph[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
        }
        
        // 시작점에서 출발해서 거리 구하고
        dijkstra(distS,s);
        
        // a에서 출발해서 거리 구하고
        dijkstra(distA,a);
        
        // b에서 출발해서 거리 구함.
        dijkstra(distB,b);
            
        // 정점을 거쳐서 최소 거리 계산. 
        for (int i = 1;i<=n;i++){
            if (answer > distS[i] + distA[i] + distB[i]){
                answer = distS[i] + distA[i] + distB[i];
            }
        }
        return answer;
    }
    
    /**
     * 다익스트라
     */
    public static void dijkstra(int[] dist, int start){
        Arrays.fill(dist, INF);
        dist[start] = 0;
        visited = new boolean[dist.length];
        
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.offer(new Node(start,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if (visited[cur.idx]) continue;
            visited[cur.idx] = true;
            
            for (Node next:graph[cur.idx]){
                if (!visited[next.idx] && dist[next.idx] > cur.cost + next.cost){
                    dist[next.idx] = cur.cost + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

    }
}
