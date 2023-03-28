import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1493_G3_박스_채우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int length, width, height, n;
    static long ans;
    static int[] cubes;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        n = Integer.parseInt(br.readLine());

        cubes = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int kind = Integer.parseInt(st.nextToken());
            cubes[kind] = Integer.parseInt(st.nextToken());
        }

        go(length, width, height);

        System.out.println(ans < 0 ? -1 : ans);
    }

    static void go(int l, int w, int h) {
        // 주어진 부분을 모두 채운 경우
        if (l == 0 || w == 0 || h == 0 || ans < 0) {
            return;
        }

        for (int i = n - 1; i >= 0; i--) {
            int side = (int) Math.pow(2, i);

            if (cubes[i] > 0 && l >= side && w >= side && h >= side) {
                cubes[i]--;
                ans++;
                go(l - side, w, h);
                go(side, w, h - side);
                go(side, w - side, side);
                return;
            }
        }

        // 주어진 부분을 채울 수 없는 경우
        ans = Long.MIN_VALUE;
        return;
    }
}

