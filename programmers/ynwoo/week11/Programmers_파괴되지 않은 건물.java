import java.util.*;

class Solution {
	public int solution(int[][] board, int[][] skill) {
		// 최악의 경우,
		// board[1000][1000]
		// skill.length <= 250,000
		// 250,000 * 1,000 * 1,000 => 2천5백억(시간초과)

		// 풀이 설명: https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
		int answer = 0;
		int skillLength = skill.length;
		int[][] temp = new int[board.length + 1][board[0].length + 1];
		// O(k)
		for (int l = 0; l < skillLength; l++) {
			int type = skill[l][0];
			int si = skill[l][1];
			int sj = skill[l][2];
			int ei = skill[l][3] + 1;
			int ej = skill[l][4] + 1;
			int amount = skill[l][5];

			if (type == 1) { // 적의 공격
				temp[si][sj] -= amount;
				temp[ei][ej] -= amount;
				temp[si][ej] += amount;
				temp[ei][sj] += amount;
			} else { // 아군의 회복
				temp[si][sj] += amount;
				temp[ei][ej] += amount;
				temp[si][ej] -= amount;
				temp[ei][sj] -= amount;
			}
		}

		// 누적합( O(N*M) + O(N*M) )
		// 위에서 아래로 누적합
		for (int j = 0; j < temp[0].length; j++) {
			for (int i = 1; i < temp.length; i++) {
				temp[i][j] += temp[i - 1][j];
			}
		}
		// 왼쪽에서 오른쪽 누적합
		for (int i = 0; i < temp.length; i++) {
			for (int j = 1; j < temp[0].length; j++) {
				temp[i][j] += temp[i][j - 1];
			}
		}

		// board와 합치기
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] += temp[i][j];
			}
		}

		// 0보다 큰 정수의 개수 구하기
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] > 0) {
					answer++;
				}
			}
		}

		return answer;
	}
}