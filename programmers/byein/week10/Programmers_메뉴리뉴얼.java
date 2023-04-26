import java.util.*;
import java.io.*;
class Solution {
    static char[] combStr;
    static HashMap<String, Integer> hm = new HashMap();
    static List<Map.Entry<String, Integer>> entryList = new LinkedList(hm.entrySet());
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> ansList = new ArrayList();
        for (int i = 0;i<course.length;i++){
            for (int j = 0;j<orders.length;j++){
                combStr = new char[course[i]];
                char[] tmp = orders[j].toCharArray();
                Arrays.sort(tmp);
                orders[j] = new String(tmp);
                comb(orders[j], course[i], 0,0);
            }
        }

        entryList = new LinkedList(hm.entrySet());
        entryList.sort((o1,o2)-> o1.getKey().length() == o2.getKey().length() ? hm.get(o2.getKey()) - hm.get(o1.getKey()) : o1.getKey().length() - o2.getKey().length());
        
        HashMap<String, Integer>[] hashMap = new HashMap[course.length];
        List<Map.Entry<String, Integer>>[] entryLists = new LinkedList[course.length];
        
        for (int i = 0;i<course.length;i++){
            hashMap[i] = new HashMap();
            entryLists[i] = new LinkedList();
        }
        
        for (int i = 0;i<course.length;i++){
            for(Map.Entry<String, Integer> entry : entryList){
                if (entry.getKey().length() == course[i]){
                    hashMap[i].put(entry.getKey(), entry.getValue());
                }
            }
        }
        
        for (int i = 0;i<course.length;i++){
            entryLists[i] = new LinkedList(hashMap[i].entrySet());
            int mxCnt = -1;
            entryLists[i].sort((o1,o2)-> hm.get(o2.getKey()) - hm.get(o1.getKey()));
            System.out.println(entryLists[i]);
            for (Map.Entry<String, Integer> entry : entryLists[i]){
                if (entry.getValue() >= mxCnt && entry.getValue() > 1){
                    ansList.add(entry.getKey());
                    mxCnt = entry.getValue();
                }
            }
        }
        String[] answer = new String[ansList.size()];
        answer = ansList.toArray(new String[ansList.size()]);
        
        Arrays.sort(answer);

        return answer;
    }
    
    static void comb(String s, int n, int start, int cnt){
        if (cnt == n){
            String str = String.valueOf(combStr);
            hm.put(str, hm.getOrDefault(str, 0) + 1);
            return;
        }
        for (int i = start;i<s.length();i++){
            combStr[cnt] = s.charAt(i);
            comb(s, n, i+1, cnt+1);
        }
    }
}
