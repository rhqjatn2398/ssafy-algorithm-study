import java.util.*;

class Solution {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		int minLength = Integer.MAX_VALUE;

		Set<String> set = new HashSet<>(Arrays.asList(gems));
		Map<String, Integer> jMap = new HashMap<>();

		int n = gems.length; // 보석 전체 개수
		int numOfJewelryTypes = set.size(); // 보석 종류 개수
		int left = 0;
		int right = 0;

		while (true) {
			if (jMap.size() == numOfJewelryTypes) {
				jMap.put(gems[left], jMap.get(gems[left]) - 1); // 왼쪽 끝 보석 빼주기
				if (jMap.get(gems[left]) == 0) {
					jMap.remove(gems[left]);
				}
				left++;
			} else {
				if (right == n) {
					break;
				}
				// 오른쪽 보석 넣어주기
				jMap.put(gems[right], jMap.getOrDefault(gems[right], 0) + 1);
				right++;
			}
			// 모든 보석을 하나이상 포함하는 가장 짧은 구간 찾기
			if (jMap.size() == numOfJewelryTypes && minLength > right - left) {
				minLength = right - left;
				answer[0] = left + 1;
				answer[1] = right;
			}
		}

		return answer;
	}
}