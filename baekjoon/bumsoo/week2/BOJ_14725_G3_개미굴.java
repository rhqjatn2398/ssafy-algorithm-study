import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static StringTokenizer st;

    static int n;
    static Node root;

    /**
     * 트리의 노드
     */
    static class Node {
        String name; // 먹이 이름
        TreeMap<String, Node> childs; // 연결된 아래층의 먹이들을 먹이 이름의 사전순으로 정렬하여 보관

        public Node(String name) {
            super();
            this.name = name;
            this.childs = new TreeMap<>();
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        Node root = new Node("root");

        for (int i = 0; i < n; i++) {
            Node curNode = root;
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 로봇 개미 한마리가 보내준 먹이 정보 개수 K

            for (int j = 0; j < k; j++) {
                String word = st.nextToken(); // 먹이 이름

                // 해당 굴, 해당 층에 먹이이름'word'가 이미 있는 경우 아래 층으로 이동
                if (curNode.childs.containsKey(word)) {
                    curNode = curNode.childs.get(word);
                    continue;
                }

                // 해당 굴, 해당 층에 먹이이름'word'가 없는 경우 먹이이름'word'를 생성하고 연결한 후 아래 층으로 이동
                curNode.childs.put(word, new Node(word));
                curNode = curNode.childs.get(word);
            }
        }

        // 결과 출력
        go(root, 0);

        bw.flush();

        return;
    }


    /**
     * DFS의 형태
     *
     * @param curNode 현재 처리중인 노드
     * @param depth   굴 깊이
     * @throws IOException
     */
    static void go(Node curNode, int depth) throws IOException {
        for (String key : curNode.childs.keySet()) {
            printDepth(depth);
            bw.append(key + '\n');
            go(curNode.childs.get(key), depth + 1);
        }
    }

    static void printDepth(int depth) throws IOException {
        for (int i = 0; i < depth; i++) {
            bw.append("--");
        }
    }

}
