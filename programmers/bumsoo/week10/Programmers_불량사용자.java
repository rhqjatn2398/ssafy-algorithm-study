import java.util.*;
import java.util.Map.*;

class Solution {

    Set<String> set;
    int ans = 0;

    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet();

        dfs(0, user_id, new boolean[user_id.length], banned_id);

        return set.size();
    }

    private void dfs(int cnt, String[] user_id, boolean[] used, String[] banned_id) {
        if (cnt == banned_id.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < used.length; i++) {
                if (used[i]) {
                    sb.append(user_id[i]);
                }
            }

            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (!used[i] && isMatched(user_id[i], banned_id[cnt])) {
                used[i] = true;
                dfs(cnt + 1, user_id, used, banned_id);
                used[i] = false;
            }
        }
    }

    private boolean isMatched(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }

        for (int i = 0; i < bannedId.length(); i++) {
            if (bannedId.charAt(i) == '*') {
                continue;
            }

            if (bannedId.charAt(i) != userId.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}