import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);
    static StringTokenizer st;

    static class Bst {
        Node root;

        void insert(int data) {
            if (root == null) {
                root = new Node(data);
                return;
            }

            Node curNode = root;
            while (true) {
                if (data >= curNode.data) {
                    if (curNode.right == null) {
                        curNode.right = new Node(data);
                        return;
                    }

                    curNode = curNode.right;
                } else {
                    if (curNode.left == null) {
                        curNode.left = new Node(data);
                        return;
                    }

                    curNode = curNode.left;
                }
            }
        }

        void postOrder(Node cur) throws Exception {
            if (cur == null) {
                return;
            }

            postOrder(cur.left);
            postOrder(cur.right);
            bw.append(cur.data + "\n");
        }
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            super();
            this.data = data;
        }

    }

    static Bst bst;

    public static void main(String[] args) throws Exception {
        bst = new Bst();
        String line;
        while ((line = br.readLine()) != null) {
            int num = Integer.parseInt(line);

            bst.insert(num);
        }

        bst.postOrder(bst.root);
        bw.flush();
    }
}
