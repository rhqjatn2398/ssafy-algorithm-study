package algoStudy.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195_친구네트워크_G2 {
	// key  : 한 친구의 이름
	// value: 그 친구가 속한 친구그룹의 대표 친구
	static Map<String, String> friends;
	// key  : 한 친구의 이름
	// value: 그 친구를 대표로 하는 친구그룹의 수
	static Map<String, Integer> friendsCnt;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 0; testCase < T; testCase++) {
			int F = Integer.parseInt(br.readLine());
			friends = new HashMap<String, String>();
			friendsCnt = new HashMap<String, Integer>();

			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				makeSet(s1);
				makeSet(s2);

				union(s1, s2);

				// 친구그룹의 수 출력
				sb.append(friendsCnt.get(findSet(s1))).append('\n');

				// 시간초과 코드 : 매번 반복문을 돌면서 같은 집합인지 확인
				// 최악의 경우(매 입력마다 매번 다른 사람이 들어오는 경우)
				// 입력에 대해 각각 2 + 4 + 6 + 8 + ... + 200,000 번 반복
				// 연산 횟수 = 200,002 * 100,000 / 2 = 약 100억 -> 당연히 시간초과..!
				// int cnt = 0;
				// String s1Root = findSet(s1);
//				for (String key : friends.keySet()) {
//					if (findSet(key).equals(s1Root)) {
//						cnt++;
//					}
//				}
//				sb.append(cnt).append('\n');
			}
		}
		// 출력
		System.out.println(sb);

	}

	// s라는 이름을 가진 친구를 포함하는 새로운 집합을 생성한다.
	private static void makeSet(String s) {
		if (friends.get(s) == null) { // 기존에 입력된 이름이 아니라면 새로 추가
			friends.put(s, s);
			friendsCnt.put(s, 1); // 친구 수 초기화
		}
	}

	// s라는 이름을 가진 친구를 포함하는 집합의 대표 친구를 찾는다.
	private static String findSet(String s) {
		// 기저 조건
		if (s.equals(friends.get(s))) {
			return s;
		}
		String temp = findSet(friends.get(s)); // s의 대표친구에 대해 findSet연산 수행
		friends.put(s, temp); // 최적화 - 경로 압축(Path Compression)
		return temp;
	}

	// s1과 s2를 포함하는 두 집합을 통합한다.
	private static void union(String s1, String s2) {
		String s1Root = findSet(s1);
		String s2Root = findSet(s2);
		if (s1Root.equals(s2Root)) { // 이미 같은 집합
			return;
		}
		// s2를 포함하는 집합의 대표친구(s2Root)의 대표친구로 s1집합의 대표 친구(s1Root)를 저장
		friends.put(s2Root, s1Root);

		// 시간초과 해결
		int s1Cnt = friendsCnt.get(s1Root);
		int s2Cnt = friendsCnt.get(s2Root);

		friendsCnt.put(s1Root, s1Cnt + s2Cnt);
	}

}