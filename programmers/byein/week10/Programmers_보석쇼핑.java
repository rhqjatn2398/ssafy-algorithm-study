import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        int len = Integer.MAX_VALUE;
        HashMap<String, Integer> hMap = new HashMap();
        HashMap<String, Integer> map = new HashMap();

        for (String gem:gems){
            hMap.put(gem, hMap.getOrDefault(gem, 0) + 1);
        }
        int size = hMap.size();
        
        int left = 0, right = 0;
        while(left <= right && right <= gems.length){
            if (map.size() == size){
                left++;
                
                if (right - left + 1 < len || (right - left + 1 == len && left < answer[0])){
                    len = right - left + 1;
                    answer[0] = left;
                    answer[1] = right;
                }
                int val = map.get(gems[left-1]) - 1;
                if (val > 0){
                    map.put(gems[left-1], map.get(gems[left-1]) - 1);
                }else{
                    map.remove(gems[left-1]);
                }
            }
            else {
                if (right == gems.length){
                    left++;
                }else{
                    map.put(gems[right], map.getOrDefault(gems[right], 0) + 1); 
                    right++;
                }
            }
        }
        return answer;
    }
}