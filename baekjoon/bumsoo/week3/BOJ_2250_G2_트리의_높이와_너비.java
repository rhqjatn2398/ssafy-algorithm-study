import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);
    static StringTokenizer st;

    static class Node {
        int data;
        int col;
        int left;
        int right;

        public Node(int data, int left, int right) {
            super();
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

    static int n, maxLevel, maxWidth;
    static Node[] nodes;
    static int[] parent;
    static int col = 1;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        nodes = new Node[n + 1];
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            nodes[num] = new Node(num, left, right);

            if (left != -1) {
                parent[left] = num;
            }

            if (right != -1) {
                parent[right] = num;
            }
        }

        int rootNum = findRoot();
        inOrder(rootNum);
        go(rootNum);

        System.out.println(maxLevel + " " + maxWidth);
    }

    /**
     * 임의의 정점에서 루트노드일 때까지 타고 올라감 단, 입력 정점 번호가 1~N 이므로 항상 존재하는 1번 정점에서 시작
     *
     * @return 루트노드의 번호
     */
    static int findRoot() {
        int curNum = 1;
        while (true) {
            if (parent[curNum] == 0) {
                return curNum;
            }

            curNum = parent[curNum];
        }
    }

    /**
     * 중위순회로 노드의 열 번호를 구함
     *
     * @param idx
     */
    static void inOrder(int idx) {
        if (idx == -1) {
            return;
        }

        inOrder(nodes[idx].left);
        nodes[idx].col = col++;
        inOrder(nodes[idx].right);
    }

    /**
     * BFS로 레벨 별 탐색 트리이므로 방문체크 불필요
     */
    static void go(int rootNum) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(rootNum);

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size(); // 같은 레벨인 정점의 개수를 구함

            Deque<Integer> deque = new ArrayDeque<>(); // 같은 레벨인 모든 정점들의 열 번호를 저장
            for (int i = 0; i < size; i++) { // // 같은 레벨인 정점의 개수만큼 꺼냄
                int cur = queue.poll();

                deque.add(nodes[cur].col);

                int left = nodes[cur].left;
                if (left != -1) {
                    queue.add(left);
                }

                int right = nodes[cur].right;
                if (right != -1) {
                    queue.add(right);
                }
            }

            int width = deque.getLast() - deque.getFirst() + 1; // 왼쪽 자식부터 Queue에 삽입하므로 꺼낼 때도 왼쪽 정점부터 꺼냄(최우측 - 최좌측 + 1)
            if (width > maxWidth) {
                maxWidth = width;
                maxLevel = level;
            }

            level++;
        }

    }
}
