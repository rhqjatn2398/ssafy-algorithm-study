import java.util.*;

class Programmers_메뉴리뉴얼 {
	static Map<String, Integer> courseMap; // key: 코스요리 메뉴 구성, value: 주문 횟수
	static char[] c, menus;

	public String[] solution(String[] orders, int[] course) {
		List<String> list = new ArrayList<>(); // 가장 많이 함께 주문된 단품메뉴 조합

		for (int i = 0; i < course.length; i++) { // 단품 메뉴 개수[2,3,4]
			courseMap = new HashMap<>();
			c = new char[course[i]]; // 조합을 저장할 배열

			for (String order : orders) {
				// 단품메뉴개수 C course[i] 조합 뽑아서 courseMap에 추가
				menus = order.toCharArray();
				Arrays.sort(menus);
				comb(0, 0, menus.length, course[i]);
			}

			if (courseMap.values().size() > 0) { // 메뉴구성이 가능하고
				int maxValue = Collections.max(courseMap.values());
				if (maxValue >= 2) { // 최소 2명 이상의 손님으로 부터 주문되었다면
					for (String key : courseMap.keySet()) {
						if (courseMap.get(key) == maxValue) {
							list.add(key); // 메뉴 후보에 포함
						}
					}
				}
			}
		}
		Collections.sort(list);

		return list.toArray(new String[0]);
	}

	public void comb(int cnt, int start, int n, int r) {
		if (cnt == r) {
			courseMap.put(String.valueOf(c), courseMap.getOrDefault(String.valueOf(c), 0) + 1);
			return;
		}
		for (int i = start; i < n; i++) {
			c[cnt] = menus[i];
			comb(cnt + 1, i + 1, n, r);
		}
	}
}