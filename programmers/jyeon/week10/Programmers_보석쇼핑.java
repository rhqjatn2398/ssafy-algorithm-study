import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int len = Integer.MAX_VALUE;
        HashSet<String> hs = new HashSet<>(Arrays.asList(gems)); // 모든 보석 종류
        HashMap<String, Integer> map = new HashMap<>(); //
        int size = hs.size(); // 모든 종류의 보석 개수
        int left = 0;
        int right = 0;
        int n = gems.length;
        map.put(gems[0], 1);
        while(left<=right && right<n){
            if (map.size() == size){ // 모든 종류의 보석을 담았다면 짧은 구간을 찾는다.
                if (len > right-left+1){ // 구간 길이 갱신하기
                    answer[0] = left+1; 
                    answer[1] = right+1;
                    len = right-left+1;
                }
                
                map.put(gems[left], map.get(gems[left])-1); // left에 해당하는 보석 개수 감소시키기
                if (map.get(gems[left]) == 0){ // 0이라면 보석을 제거한다
                    map.remove(gems[left]); 
                }
                left++;
               
            } else{
                if (right==n-1) break;
                right++;
                map.put(gems[right], map.getOrDefault(gems[right],0) + 1); // 증가한 right에 해당하는 보석 추가하기
            }
        }
        
        
        return answer;
    }
}
