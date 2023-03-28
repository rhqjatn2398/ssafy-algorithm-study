package bumsoo.week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3020_G5_개똥벌레 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, h, ans, cnt;
    static int[] topDown;
    static int[] bottomUp;
    static int[] suffixBottomUp;
    static int[] suffixTopDown;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        bottomUp = new int[h + 1];
        topDown = new int[h + 1];
        suffixBottomUp = new int[h + 1];
        suffixTopDown = new int[h + 1];

        for (int i = 0; i < n / 2; i++) {
            bottomUp[Integer.parseInt(br.readLine())]++;
            topDown[Integer.parseInt(br.readLine())]++;
        }

        for (int i = 1; i <= h; i++) {
            suffixBottomUp[i] = suffixBottomUp[i - 1] + bottomUp[i];
            suffixTopDown[i] = suffixTopDown[i - 1] + topDown[i];
        }

        ans = Integer.MAX_VALUE;
        for (int i = 1; i <= h; i++) {
            int crushCnt = 0;

            crushCnt += suffixBottomUp[h] - suffixBottomUp[i - 1];
            crushCnt += suffixTopDown[h] - suffixTopDown[h - i]; // i := 바닥에서부터 i번째 나는 구간 이므로 i.5 높이를 지나감

            if (crushCnt < ans) {
                ans = crushCnt;
                cnt = 1;
            } else if (crushCnt == ans) {
                cnt++;
            }
        }

        System.out.println(ans + " " + cnt);
    }
}
