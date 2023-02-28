package algoStudy.week2;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1941_소문난칠공주_G3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[][] arr = new char[5][5];
	static Point[] cases = new Point[7]; // 경우의 수를 저장할 변수
	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };
	static boolean[][] isVisited = new boolean[5][5];
	static int[][] temp = new int[5][5];
	static int answer, studentCnt;

	public static void main(String[] args) throws Exception {
		// 입력
		for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		// 구현
		combination(0, 0);

		// 출력
		System.out.println(answer);
	}

	private static void combination(int cnt, int start) {
		// 기저 조건
		// 선택한 7명에 대해 temp 2차원 배열에 표시, 이후 dfs로 인접해있는지 확인
		if (cnt == 7) {
			int sCnt = 0;
			temp = new int[5][5];
			studentCnt = 0;

			for (int i = 0; i < 7; i++) {
				if (arr[cases[i].x][cases[i].y] == 'S') { // 다솜파 학생 카운트
					sCnt++;
				}
				temp[cases[i].x][cases[i].y] = 1; // 해당 위치의 학생이 선택되었음을 표시
			}

			if (sCnt >= 4) { // 이다솜파 4명 이상
				// dfs 돌렸을 때 인접한 학생 수가 7이 나오면 answer++
				isVisited = new boolean[5][5];
				int startI = cases[0].x;
				int startJ = cases[0].y;
				dfs(startI, startJ); // 다 인접해있는지 확인
				if (studentCnt == 7) {
					answer++;
				}
			}

			return;
		}
		// 25C7 조합 생성
		for (int i = start; i < 25; i++) {
			cases[cnt] = new Point(i / 5, i % 5);
			combination(cnt + 1, i + 1);
		}
	}

	private static void dfs(int i, int j) {
		isVisited[i][j] = true;
		studentCnt++;
		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];
			if (0 <= ni && ni < 5 && 0 <= nj && nj < 5 && !isVisited[ni][nj] && temp[ni][nj] == 1) {
				dfs(ni, nj);
			}
		}
	}

}
