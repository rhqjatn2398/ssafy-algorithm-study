import java.util.*;

class Solution {
    static HashSet<HashSet<String>> answer;
    public int solution(String[] user_id, String[] banned_id) {
        answer = new HashSet<>();
        dfs(new LinkedHashSet<>(), user_id, banned_id);
        return answer.size();
    }
    
    static void dfs(LinkedHashSet<String> hs, String[] user_id, String[] banned_id){
        if (hs.size() == banned_id.length){ // 불량 사용자 사이즈만큼 뽑았다면
            if (check(hs, banned_id)){
                 answer.add(new HashSet<>(hs));
            }
            return;
        }
        
        for(String userId : user_id){
            if (hs.add(userId)){ // 중복되지 않은 사용자라면 뽑는다
                dfs(hs, user_id, banned_id);
                hs.remove(userId);
            }
        }
    }
    
    static boolean check(LinkedHashSet<String> hs, String[] banned_id) {
        int idx = 0;
        for (String userID : hs) {
            String banID = banned_id[idx++];
            if (userID.length() != banID.length()) return false;
            for (int i = 0; i < banID.length(); i++) {
                if (banID.charAt(i) == '*') continue;
                if (userID.charAt(i) != banID.charAt(i)) return false;
            }
        }
        return true;
    }
}
