import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static StringTokenizer st;

    char[] input;
    String explosive;
    Deque<Character> stack;

    public static void main(String[] args) throws IOException {
        Main ps = new Main();

        ps.solution();
    }

    void printResult() throws IOException {
        if (stack.isEmpty()) {
            System.out.println("FRULA");
            return;
        }

        while (!stack.isEmpty()) {
            bw.append(stack.pop());
        }

        bw.flush();
    }

    void explode() {
        for (int i = 0; i < explosive.length(); i++) {
            stack.pop();
        }
    }

    boolean comp() {
        Iterator<Character> iter = stack.iterator();
        for (int i = 0; i < explosive.length(); i++) {
            if (iter.next() != explosive.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    void solution() throws IOException {
        input = br.readLine().toCharArray();
        explosive = br.readLine();
        stack = new ArrayDeque<>();

        /**
         * i := input 배열의 index
         */
        for (int i = input.length - 1; i >= 0; i--) {
            stack.push(input[i]);
            if (stack.size() < explosive.length()) {
                continue;
            }

            // explosive 배열과 길이가 같거나 커졌으므로 같은 문자열인지 비교할 수 있다
            if (comp()) { // 비교해서 같으면
                explode();
            }
        }

        printResult();
    }
}
