import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	private static int n, m, maxHouseCnt, nowHouseCnt, areaSize;
	private static ArrayList<Point> houses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			maxHouseCnt = 0;
			houses = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					if (Integer.parseInt(st.nextToken()) == 1)
						houses.add(new Point(i, j)); // 집 좌표 저장하기
			}

			centerPoint();
			sb.append("#").append(t).append(" ").append(maxHouseCnt).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void centerPoint() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) { // (i,j) 을 마름모의 중심점으로 둘 때
				offerService(i, j);
			}
		}
	}

	private static void offerService(int x, int y) {
		areaSize = 1;
		for (int k = 0; k <= n; k++) { // k
			nowHouseCnt = 0;
			areaSize += k * 4; // 마름모 영역 크기
			
			// 집~마름모중심좌표(x,y) 의 거리가 k이내라면 서비스 영역에 포함된다.
			for (int i = 0; i < houses.size(); i++) 
				if (Math.abs(houses.get(i).x - x) + Math.abs(houses.get(i).y - y) <= k) nowHouseCnt++;

			if (nowHouseCnt > 0) {
				if ((nowHouseCnt * m) - areaSize >= 0) { // 손해를 보지 않는다면
					if (maxHouseCnt < nowHouseCnt) maxHouseCnt = nowHouseCnt;
					if (maxHouseCnt == houses.size())	return;
				}
			}
		}
	}
}
