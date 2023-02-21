package com.ssafy.w02;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1941_G3_소문난칠공주 {
	// 4방 탐색을 위한 방향 배열.
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	// 5*5 배열의 조합을 구하기 위해 방문처리 할 때 쓰이는 배열.
	static boolean[] isUsed = new boolean[25];
	// 5*5 배열 중 구해진 7개의 조합 배열. 좌표값 저장.
	static Point[] princess = new Point[7];
	// 입력받을 5*5 배열.
	static char[][] board = new char[5][5];
	// 인접 여부 확인을 위해 dfs 처리 시 방문처리 할 2차원 배열.
	static int[][] checkBoard = new int[5][5];
	// 리턴할 '소문난 칠공주' 결성 가능한 모든 경우의 수.
	static int totalCnt = 0;

	public static void main(String[] args) {
		// 항상 입력이 5*5 배열의 문자 배열이므로 Scanner 사용.
		Scanner sc = new Scanner(System.in);
		// 입력 처리.
		for (int i = 0; i < 5; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < 5; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		// 5*5 배열 중 7개의 조합을 가져오는함수.
		getSeven(0, 0);

		// 리턴값 출력.
		System.out.println(totalCnt);
	}

	/**
	 * 5*5 배열 중 7개의 조합을 가져오는 함수.
	 * @param cnt : 현재까지 구해진 조합의 개수.
	 * @param start : 조합에서 중복 없이 가져오기 위해 처리할 변수.
	 */
	public static void getSeven(int cnt, int start) {
		// 7개의 조합을 모두 구했다면
		if (cnt == 7) {
			// checkBoard를 0으로 초기화.
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					checkBoard[i][j] = 0;
				}
			}
			// 7개의 조합 좌표에 해당하는 checkBoard에 1 대입. 
			for (int i = 0; i < 7; i++) {
				checkBoard[princess[i].x][princess[i].y] = 1;
			}

			// '이다솜파'가 적어도 4명 이상이고
			// 가로나 세로로 인접한 경우라면 
			if (checkSuper() && checkAdj()) {
				// 조건에 맞으므로 경우의 수 증가.
				totalCnt++;
			}
			return;
		}

		// 아직 7개의 조합이 구해지지 않은 경우
		
		// start부터 25까지 반복문을 돌면서
		for (int i = start; i < 25; i++) {
			// 7개의 조합에 현재 좌표 저장.
			// 0  1  2  3  4
			// 5  6  7  8  9
			// 10 11 12 13 14
			// 15 16 17 18 19
			// 20 21 22 23 24 
			// 5*5 배열을 위와 같이 생각할 때, 행은 i/5, 열은 i%5로 처리 가능. 
			princess[cnt] = new Point(i / 5, i % 5);
			// 중복없이 처리하기 위해 다음 뽑을 수에 i+1 넣어서 조합 재귀 호출.
			getSeven(cnt + 1, i + 1);
		}

	}

	/**
	 * 이다솜파가 적어도 4명 이상인지 확인하는 함수.
	 * @return
	 */
	public static boolean checkSuper() {
		// 임도연파의 학생 수.
		int yCnt = 0;
		// 뽑힌 7개의 좌표를 확인하면서
		for (int i = 0; i < 7; i++) {
			// 해당 좌표에 'Y' 임도연 파가 있다면 yCnt 1 증가.
			if (board[princess[i].x][princess[i].y] == 'Y') {
				yCnt++;
			}
			// 임도연파가 4명 이상이라면 false 리턴.
			if (yCnt > 3)
				return false;
		}
		// 임도연 파가 4명 이상이라면 false 리턴.
		if (yCnt > 3)
			return false;
		// 아니라면 이다솜파가 4명 이상이므로 true 리턴.
		return true;
	}

	// 인접 확인하는 함수.
	public static boolean checkAdj() {
		// 큐를 통해 인접 확인.
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(princess[0]);
		// 현재 1로 처리된 인접 영역이 몇 개인지 셀 변수.
		int cnt = 0;

		// 큐가 빌 때까지
		while (!queue.isEmpty()) {
			// 현재 좌표를 큐에서 빼내고.
			Point cur = queue.poll();

			// 방향 전환 시키면서
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				// 범위를 나간 경우 넘기기.
				if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
					continue;
				// 뽑힌 조합이 아닌 경우 넘기기.
				if (checkBoard[nx][ny] == 0)
					continue;
				// 현재 영역이 1이므로 cnt 1 증가.
				cnt++;

				// 방문했으므로 checkBoard 0으로 바꾸기.
				checkBoard[nx][ny] = 0;
				// 큐에 해당 좌표 넣어주기.
				queue.add(new Point(nx, ny));
			}
		}
		if (cnt < 7) {
			return false;
		}
		return true;
	}
}