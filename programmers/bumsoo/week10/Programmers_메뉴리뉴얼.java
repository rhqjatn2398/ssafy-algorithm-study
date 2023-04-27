import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Solution {

    public static void main(String[] args) {
        new Solution().solution(new String[] {"XYZ", "XWY", "WXA"}, new int[] {2, 3, 4});
    }

    Map<String, Integer> count;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        count = new HashMap();

        // 가능한 코스 조합을 생성한다
        // orders 배열의 각 원소인 문자열에는 같은 알파벳이 중복해서 들어있지 않기 때문에 Map을 이용해서 카운팅 해주면 몇 명이 해당 조합으로 주문했는지 쉽게 구할 수 있음
        for (String order : orders) {
            dfs(0, 0, order, new StringBuilder());
        }

        for (int size : course) {
            int maxCnt = 0;
            for (Entry<String, Integer> entry : count.entrySet()) {
                if (entry.getKey().length() == size && maxCnt < entry.getValue()) {
                    maxCnt = entry.getValue();
                }
            }

            // 두 명이상의 손님으로부터 주문된 조합이 아니면
            if (maxCnt <= 1) {
                continue;
            }

            // 같은 길이의 코스가 여러 개 있을 수 있음
            for (Entry<String, Integer> entry : count.entrySet()) {
                if (entry.getKey().length() == size && maxCnt == entry.getValue()) {
                    answer.add(entry.getKey());
                }
            }

        }

        Collections.sort(answer);

        return answer.toArray(new String[answer.size()]);
    }

    private void dfs(int idx, int cnt, String origin, StringBuilder sb) {
        if (2 <= cnt && cnt <= 10) {
            // 정답후보
            // 정렬해야 같은 조합임을 알 수 있음
            char[] chars = sb.toString().toCharArray();
            Arrays.sort(chars);
            String str = new String(chars);

            count.put(str, count.getOrDefault(str, 0) + 1);
        } else if (cnt > 10) {
            return;
        }

        for (int i = idx; i < origin.length(); i++) {
            sb.append(origin.charAt(i));
            dfs(i + 1, cnt + 1, origin, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}