import java.util.*;

class Solution {

    int answer;
    Set<String> candidateKeys;

    public int solution(String[][] relation) {
        candidateKeys = new HashSet();

        for (int len = 1; len <= relation[0].length; len++) {
            go(0, 0, new int[len], len, relation);
        }

        return answer;
    }

    private void go(int idx, int cnt, int[] cur, int len, String[][] relation) {
        if (cnt == len) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cur.length; i++) {
                sb.append(cur[i]);
            }
            String curKey = sb.toString();

            for (String candidateKey : candidateKeys) {
                boolean minimality = false;
                for (char e : candidateKey.toCharArray()) {
                    // 후보키와 완벽히 겹치지 않음
                    if (curKey.indexOf(e) < 0) {
                        minimality = true;
                        break;
                    }
                }

                // 최소성 만족 안함
                if (!minimality) {
                    return;
                }
            }

            Set<String> set = new HashSet();

            for (int i = 0; i < relation.length; i++) {
                sb = new StringBuilder();

                for (int j = 0; j < cur.length; j++) {
                    sb.append(relation[i][cur[j]]);
                }

                set.add(sb.toString());
            }

            // 유일성 만족함. 후보 키다.
            if (set.size() == relation.length) {
                candidateKeys.add(curKey);

                answer++;
            }

            return;
        }

        for (int i = idx; i < relation[0].length; i++) {
            cur[cnt] = i;
            go(i + 1, cnt + 1, cur, len, relation);
        }
    }
}