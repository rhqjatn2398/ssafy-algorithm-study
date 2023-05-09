import java.util.*;
import java.util.Map.*;

class Solution {
    // 가능한 누적합의 최대치는 약 1000억으로 예상됨
    long[] suffix;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = toSec(play_time);
        int advTime = toSec(adv_time);
        
        suffix = new long[playTime + 2];
        
        for (int i = 0; i < logs.length; i++) {
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            int startTime = toSec(st.nextToken());
            int endTime = toSec(st.nextToken());
            
            suffix[startTime] += 1;
            // 동영상 재생시간 = 재생이 종료된 시각 - 재생이 시작된 시각 
            // 즉, 재생이 종료된 시각은 포함하지 않음
            // 포함했다면 suffix[endTime + 1] -= 1; 이 됨
            suffix[endTime] -= 1; 
        }
        
        // i초에 몇명이 보고 있는가를 계산
        for (int i = 0; i < playTime; i++) {
            suffix[i + 1] += suffix[i];
        }
        
        // 문제에서 요구하는 누적 시청 시간을 0~i 구간에 대해 구한다
        for (int i = 0; i < playTime; i++) {
            suffix[i + 1] += suffix[i];
        }
        
        long maxWatchTime = 0;
        int answerSec = 0;
        // 누적합으로 O(1) 만에 특정 구간의 누적 시청 시간을 계산할 수 있다
        for (int i = 0; i <= playTime - advTime; i++) {
            int start = i;
            int end = start + advTime;
            
            long accumulativeWatchTime = suffix[end - 1];
            if (start - 1 >= 0) {
                accumulativeWatchTime -= suffix[start - 1];   
            }
             
            if (maxWatchTime < accumulativeWatchTime) {
                maxWatchTime = accumulativeWatchTime;
                answerSec = start;
            }
        }
        
        return toStr(answerSec);
    }
    
    public int toSec(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        int hours = Integer.parseInt(st.nextToken()) * 60 * 60;
        int minutes = Integer.parseInt(st.nextToken()) * 60;
        int seconds = Integer.parseInt(st.nextToken());
        
        return hours + minutes + seconds;
    }
    
    public String toStr(int sec) {
        int hours = sec / (60 * 60);
        sec %= (60 * 60);
        int minutes = sec / 60;
        sec %= 60;
        int seconds = sec;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
