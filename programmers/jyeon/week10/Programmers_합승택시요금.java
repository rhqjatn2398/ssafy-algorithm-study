import java.util.*;

class Solution {

    static final int MAX = 20000001;
    static ArrayList<Node>[] graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX;
        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int[] fare : fares){
            graph[fare[0]].add(new Node(fare[1], fare[2]));
            graph[fare[1]].add(new Node(fare[0], fare[2]));
        }

        int[] startA = dijkstra(a,n);
        int[] startB = dijkstra(b,n);
        int[] start = dijkstra(s,n);

        for(int i=1; i<=n; i++){ // S->i까지 경유하고 i->a & i->b 까지 따로간다.
            answer = Math.min(answer, start[i] + startA[i] + startB[i]);
        }

        return answer;
    }

    public int[] dijkstra(int start, int n){
        int[] dist = new int[n+1]; // dist[i] : start->i 까지의 거리
        Arrays.fill(dist, MAX);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            for(Node node : graph[now.x]){
                if (dist[node.x] > now.dist + node.dist){
                    dist[node.x] = now.dist + node.dist;
                    q.add(new Node(node.x, now.dist + node.dist));
                }
            }
        }
        return dist;
    }

    class Node implements Comparable<Node>{
        int x;
        int dist;

        Node(int x, int dist){
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node e){
            return this.dist - e.dist;
        }
    }
}
