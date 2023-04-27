import java.util.*;

class Solution {
    static HashMap<String, Integer> map;
    static HashMap<Integer, ArrayList<String>> courseMenu;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> tmp = new ArrayList<>();
        map = new HashMap<>();
        courseMenu = new HashMap<>();
        for(String order : orders) { 
            // 문자열 정렬하기
            char[] StringToChar = order.toCharArray();
            Arrays.sort(StringToChar);
            String sortedOrder = new String(StringToChar);
            // 부분집합 구하기
            subset(0,order.length(), sortedOrder, "");
        }
        for(int c : course) {
            ArrayList<String> list = new ArrayList<>();
            courseMenu.put(c, list);
        }
        
        for(String s : map.keySet()){
            int length = s.length();
            int cnt = map.get(s);
            if (courseMenu.containsKey(length) && cnt >= 2){
                ArrayList<String> list = courseMenu.get(length);
                if (list.size() > 0){  // lengh개 코스
                    if (map.get(list.get(0)) < cnt){
                        list.clear();
                        list.add(s);
                    } 
                    else if (map.get(list.get(0)) == cnt){
                        list.add(s);
                    }
                } else{
                    list.add(s);
                }
                courseMenu.put(length, list);
            }
         }
       
        for(int c : course){
            for(String s : courseMenu.get(c)){
                tmp.add(s);
            }
        }

        answer = new String[tmp.size()];
        tmp.toArray(answer);
        Arrays.sort(answer);
        
         return answer;
    }
    
    static void subset(int idx, int size, String order, String now){
        if (idx==size) return;

        String tmp = now + Character.toString(order.charAt(idx));
        map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        subset(idx+1, size, order, tmp);
        subset(idx+1, size, order, now);
        
    }
}
