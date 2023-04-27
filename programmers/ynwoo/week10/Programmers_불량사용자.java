
// 1. banned_id 배열의 길이만큼 user_id 배열에서 순열 생성
// 2. 생성된 순열과 banned_id 배열이 매칭되는지 체크
// 3. 매칭이 된다면 해당 순열을 정렬하여 문자열로 변환(아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은 것으로 처리해주어야 하기 때문)
// ex. frodo crodo abc123 과 crodo frodo abc123
// 4. 해당 문자열이 list에 있는지 확인 후 없으면 추가
// 5. list의 크기 = 가능한 경우의 수
// 시간복잡도: nPr * r * 8 -> O(n!)
import java.util.*;

class Solution {
	static String[] candidates; // 후보 순열을 저장 배열
	static List<String> cases = new ArrayList<>(); // 가능한 순열(배열)을 string으로 변환한 값을 저장할 list

	public int solution(String[] user_id, String[] banned_id) {
		candidates = new String[banned_id.length];
		// user_id배열에서 banned_id배열의 길이만큼 순열 생성: nPr
		permutation(0, 0, user_id, banned_id);

		return cases.size(); // 가능한 경우의 수
	}

	public void permutation(int cnt, int flag, String[] user_id, String[] banned_id) {
		if (cnt == banned_id.length) {
			// 후보 user_id 배열과 banned_id 배열 매칭
			for (int i = 0; i < candidates.length; i++) {
				// 길이가 다르면 매칭x
				if (candidates[i].length() != banned_id[i].length()) {
					return;
				}
				// 문자열 비교
				for (int j = 0; j < candidates[i].length(); j++) {
					if (candidates[i].charAt(j) == banned_id[i].charAt(j) || banned_id[i].charAt(j) == '*') {
						continue;
					}
					// 해당 인덱스에서의 문자가 같지않거나 banned_id에서의 문자가 *이 아니라면 매칭x
					return;
				}
			}
			// 매칭이 되는 경우

			String[] temp = candidates.clone();
			Arrays.sort(temp);
			if (!cases.contains(Arrays.toString(temp))) {
				cases.add(Arrays.toString(temp));
			}
			return;
		}

		for (int i = 0; i < user_id.length; i++) {
			if ((flag & (1 << i)) != 0) {
				continue;
			}
			candidates[cnt] = user_id[i];
			permutation(cnt + 1, flag | (1 << i), user_id, banned_id);
		}
	}
}