import java.util.*;


class Solution {
   
    static boolean[] visited;
    static String[] regex;
    static HashSet<String> set;
    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet();
        int answer = 0;
        regex = new String[banned_id.length];
        visited = new boolean[user_id.length];
        for (int i = 0;i<banned_id.length;i++){
            regex[i] = "^" + banned_id[i].replaceAll("\\*", "\\.") + "$";
        }
        dfs(0, "", user_id);
        System.out.println(set);
        return set.size();
    }
    
    public void dfs(int idx, String result, String[] user_id){
        if (idx == regex.length){
            String[] str= result.split(" ");
            Arrays.sort(str);
            
            String sortedStr = "";
            for (int i = 0;i<str.length;i++){
                sortedStr += str[i];
            }
            set.add(sortedStr);
          return;  
        }
        
        for(int i = 0;i<user_id.length;i++){
            if (visited[i] || !user_id[i].matches(regex[idx])) continue;
            visited[i] = true;
            dfs(idx + 1, result + " " + user_id[i], user_id);
            visited[i] = false;
        }
    }
}
