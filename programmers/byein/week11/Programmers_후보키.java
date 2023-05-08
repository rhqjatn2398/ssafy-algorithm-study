import java.util.*;

class Solution {
    static ArrayList<HashSet<Integer>> candidate = new ArrayList();
    public int solution(String[][] relation) {
        candidate = new ArrayList();
        // 후보키 개수
        for (int i = 0;i<relation.length;i++){
            comb(relation, new HashSet(), i, 0, 0);
        }
        return candidate.size();
    }
    
    static void comb(String[][] relation, HashSet<Integer> keySet, int size, int idx, int cnt){
        if (cnt == size){
            // 최소성 확인
            for (HashSet<Integer> key:candidate){
                if (keySet.containsAll(key)){
                    return;
                }
            }
            if (isUnique(keySet, relation)){
                candidate.add(keySet);
            }
        }
        for (int i = idx;i<relation[0].length;i++){
            HashSet<Integer> newSet = new HashSet(keySet);
            newSet.add(i);
            comb(relation, newSet, size, i+1, cnt+1);
        }
    }
    
    // 유일성 확인
    static boolean isUnique(HashSet<Integer> keySet, String[][] relation){
        HashSet<String> s = new HashSet();
        for (int i = 0; i < relation.length; i++){
            String key = "";
            for (int j : keySet){
                key += relation[i][j];
            }
            System.out.println(key);
            if (s.contains(key)){
                return false;
            }else{
                s.add(key);
            }
        }
        return true;
    }
}
