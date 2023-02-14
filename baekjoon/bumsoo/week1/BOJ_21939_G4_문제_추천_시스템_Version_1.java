import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static StringTokenizer st;

    static class Problem {
        int num;
        int difficulty;

        public Problem(int num, int difficulty) {
            super();
            this.num = num;
            this.difficulty = difficulty;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Problem other = (Problem) obj;
            return num == other.num;
        }

    }

    int n, m;
    TreeSet<Problem> problems;
    Map<Integer, Integer> difficulties;

    public static void main(String[] args) throws IOException {
        Main ps = new Main();

        ps.solution();
    }

    void solution() throws IOException {
        init();

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            difficulties.put(P, L);
            problems.add(new Problem(P, L));
        }

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            readCmd();
        }

    }

    void init() {
        problems = new TreeSet<>((p1, p2) -> {
            if (p1.difficulty == p2.difficulty) {
                return p2.num - p1.num;
            }

            return p2.difficulty - p1.difficulty;
        });

        difficulties = new HashMap<>();
    }

    void readCmd() throws IOException {
        st = new StringTokenizer(br.readLine());

        String cmd = st.nextToken();

        execute(cmd);
    }

    void execute(String cmd) {
        if (cmd.equals("add")) {
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            difficulties.put(P, L);
            problems.add(new Problem(P, L));
        } else if (cmd.equals("recommend")) {
            int x = Integer.parseInt(st.nextToken());

            if (x == 1) {
                System.out.println(problems.first().num);
            } else {
                System.out.println(problems.last().num);
            }
        } else if (cmd.equals("solved")) {
            int P = Integer.parseInt(st.nextToken());

            problems.remove(new Problem(P, difficulties.get(P)));
        }
    }

}
