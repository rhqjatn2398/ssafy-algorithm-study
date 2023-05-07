import java.util.*;

//0     o -> 후보 키 가능 isUsed = [[0]]  
//1     x
//2     x
//3     x
//0 1   x (0이 이미 있기 때문)
//0 2   x (0이 이미 있기 때문)
//0 3   x (0이 이미 있기 때문)
//1 2   o -> 후보 키 가능 isUsed = [[0], [1,2]]
//1 3   x
//2 3   x
//0 1 2 x (1,2가 이미 있기 때문)
//0 1 3 x
//0 2 3 x
//1 2 3 x (1,2가 이미 있기 때문)
//0 1 2 x
class Solution {
	static boolean[] selected;
	static int rowCnt, colCnt;
	static List<List<Integer>> isUsed = new ArrayList<>();
	static int answer;

	public int solution(String[][] relation) {
		rowCnt = relation.length; // 행 길이
		colCnt = relation[0].length; // 열 길이

		// column의 조합
		for (int r = 1; r <= colCnt; r++) {
			// cCr 뽑기
			selected = new boolean[colCnt];
			comb(0, 0, colCnt, r, relation);
		}
		return answer;
	}

	public void comb(int cnt, int start, int n, int r, String[][] relation) {
		if (cnt == r) {
			Set<List> set = new HashSet<>();
			// key 조합
			List<Integer> idxList = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (selected[j]) {
					idxList.add(j);
				}
			}
			// 유일성 만족 여부 판별
			for (int i = 0; i < rowCnt; i++) {
				List<String> list = new ArrayList<>();
				for (int col : idxList) {
					list.add(relation[i][col]);
				}
				set.add(list);
			}
			if (set.size() == rowCnt) {

				// 최소성 만족 여부 판별
				// 현재 key 조합안에 이미 사용된 후보키 조합이 들어있는지 체크
				if (!isAlreadyUsed(idxList)) {
					answer++;
					isUsed.add(idxList);
				}
			}
			return;
		}

		for (int i = start; i < n; i++) {
			if (selected[i]) {
				continue;
			}
			selected[i] = true;
			comb(cnt + 1, i + 1, n, r, relation);
			selected[i] = false;
		}

	}

	public boolean isAlreadyUsed(List<Integer> idxList) {
		// 이미 카운트한 후보키들에 대해서
		for (List list : isUsed) {
			int cnt = 0;

			for (int i = 0; i < list.size(); i++) {
				// 해당 key 조합안에 후보키가 포함되어 있는지 체크
				for (int idx : idxList) {
					if ((Integer) list.get(i) == idx) {
						cnt++;
						break;
					}
				}
			}
			if (cnt == list.size()) {
				return true;
			}
		}
		return false;
	}
}