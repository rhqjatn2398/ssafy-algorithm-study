import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_2812_G3_크게_만들기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k;
    static char[] numStr;
    static ArrayDeque<Integer> stack;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        numStr = br.readLine().toCharArray();

        stack = new ArrayDeque();

        go();

        StringBuilder sb = new StringBuilder();
        Iterator iter = stack.descendingIterator();
        int cnt = 0;
        // n - k 자리 까지만 출력
        while (cnt < n - k) {
            sb.append(iter.next());
            cnt++;
        }

        System.out.println(sb.toString());
    }

    static void go() {
        int cnt = 0; // 제거한 숫자 갯수

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() < numStr[i] - '0' && cnt < k) {
                stack.pop();
                cnt++;
            }

            stack.push(numStr[i] - '0');
        }
    }
}