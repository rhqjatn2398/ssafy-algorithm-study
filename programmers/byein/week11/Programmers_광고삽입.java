import java.util.*;
import java.awt.Point;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int pt = convertToSecond(play_time);
        int at = convertToSecond(adv_time);
        int[] times = new int[360_000];
        Point[] logsTime = new Point[logs.length];
        Arrays.fill(logsTime, new Point());

        for (int i = 0; i < logsTime.length; i++) {
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            int startTime = convertToSecond(st.nextToken());
            int endTime = convertToSecond(st.nextToken());
            for (int j = startTime;j<endTime;j++){
                times[j]++;
            }
        }
        long sum = 0;
        for (int i = 0; i < at; i++) {
            sum += times[i]; 
        }
        long maxSum = sum;
        int startTime = 0;
        for (int i = at; i < pt; i++) {
            sum += times[i] - times[(i - at)];
            if (sum > maxSum) {
                startTime = (i - at + 1);
                maxSum = sum;
            }
           
        }

        return convertToFormat(startTime);
    }
    
    static int convertToSecond(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        int ret = Integer.parseInt(st.nextToken()) * 3600 + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        return ret;
    }
    
    
    static String convertToFormat(int time) {
        String ret = "";
        int hour = 0, minute = 0, second = 0;
        if (time >= 3600) {
            hour = time / 3600;
            time %= 3600;
        }
        if (time >= 60) {
            minute = time / 60;
            time %= 60;
        }
        second = time;
        if (hour <= 9) {
            ret += '0';
        }
        ret += Long.toString(hour)+':';
        if (minute <= 9) {
            ret += '0';
        }
        ret += Long.toString(minute) + ':';
        if (second <= 9) {
            ret += '0';
        }
        ret += Long.toString(second);
        return ret;
    }

}
