import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static StringTokenizer st;

    int n;
    PriorityQueue<Integer> lpq;
    PriorityQueue<Integer> rpq;

    public static void main(String[] args) throws IOException {
        Main ps = new Main();

        ps.solution();
    }

    void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        lpq = new PriorityQueue<>(Collections.reverseOrder());
        rpq = new PriorityQueue<>();


        int input = Integer.parseInt(br.readLine());
        int curMid = input;
        lpq.add(input);
        System.out.println(curMid);

        for (int i = 1; i < n; i++) {
            input = Integer.parseInt(br.readLine());

            if (lpq.size() == rpq.size()) {
                if (input <= curMid) {
                    lpq.add(input);
                    curMid = lpq.peek();
                } else {
                    rpq.add(input);
                    curMid = rpq.peek();
                }
            } else if (lpq.size() < rpq.size()) {
                if (input <= curMid) {
                    lpq.add(input);
                    curMid = lpq.peek();
                } else {
                    lpq.add(rpq.poll());
                    rpq.add(input);
                    curMid = lpq.peek();
                }
            } else if (lpq.size() > rpq.size()) {
                if (input <= curMid) {
                    rpq.add(lpq.poll());
                    lpq.add(input);
                    curMid = lpq.peek();
                } else {
                    rpq.add(input);
                    curMid = lpq.peek();
                }
            }

            bw.append(Integer.toString(curMid) + '\n');
        }
        bw.flush();
    }
}
