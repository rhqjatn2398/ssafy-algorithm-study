import java.util.*;

class Solution {
	public String solution(String play_time, String adv_time, String[] logs) {
		// 초로 변환
		int totalSec = str2sec(play_time);
		int advSec = str2sec(adv_time);
		// index초에 영상을 보고 있던 시청자 수
		int[] viewersPerSec = new int[totalSec + 1];
		for (String log : logs) {
			String[] tmp = log.split("-");
			int s = str2sec(tmp[0]);
			int e = str2sec(tmp[1]);
			// 0초~5초까지 시청했다면 재생시간은 5초이다(0,1,2,3,4초)
			for (int time = s; time < e; time++) {
				viewersPerSec[time]++;
			}
		}
		// curTotalTime : 0초~광고 끝날 때 까지 시청자들의 누적 재생시간
		int startTime = 0;
		long curTotalTime = 0;
		for (int i = 0; i < advSec; i++) {
			curTotalTime += viewersPerSec[i]; 
		}
		long max = curTotalTime;

		// t: 광고가 시작 가능한 초
		for (int t = 1; t <= totalSec - advSec; t++) {
			curTotalTime += viewersPerSec[t + advSec - 1];
			curTotalTime -= viewersPerSec[t - 1];
			if (max < curTotalTime) {
				max = curTotalTime;
				startTime = t;
			}
		}
		return sec2str(startTime);
	}

	public int str2sec(String play_time) {
		String[] tmp = play_time.split(":");
		return Integer.parseInt(tmp[0]) * 60 * 60 + Integer.parseInt(tmp[1]) * 60 + Integer.parseInt(tmp[2]);
	}

	public String sec2str(int play_time) {
		String h = String.format("%02d", play_time / 3600);
		play_time %= 3600;
		String m = String.format("%02d", play_time / 60);
		play_time %= 60;
		String s = String.format("%02d", play_time);
		return h + ":" + m + ":" + s;
	}
}