package baekjoon.김예빈.week01.BOJ_3190_뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	// 방향
	public static int[] dx = { 0, 1, 0, -1 };
	public static int[] dy = { 1, 0, -1, 0 };

	// 보드
	static int[][] board;
	// 해당 칸을 지나는 시간
	static int[][] time;

	// 이동하는 시간
	static int[] moveCnt;
	// 바꾸는 방향
	static char[] moveDir;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		// 사과가 1인덱스로 되어 보드도 1인덱스로 처리. 따라서 N+2로 배열 크기 지정.
		board = new int[N + 2][N + 2];
		time = new int[N + 2][N + 2];

		// 뱀을 앞 뒤로 늘리고 줄여야 하므로 덱 사용.
		Deque<Pair> deq = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 사과의 개수

		// 사과가 있는 곳은 board를 1로 바꿈.
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x][y] = 1;
		}

		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); // 방향 변환 횟수
		moveCnt = new int[L];
		moveDir = new char[L];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			String D = st.nextToken();

			moveCnt[i] = X; // 이동 시간
			moveDir[i] = D.charAt(0); // 변환할 방향
		}
		// 방향 변환에 사용할 변수. dir로 dx, dy 변경.
		int dir = 0;
		// 방향 변환 횟수 카운트.
		int l = 0;
		// 덱에 초기 위치(1,1) 넣어 시작.
		deq.addFirst(new Pair(1, 1));
		// 무한루프를 돌다가 조건 만족 시 종료.
		while (true) {
			// 현재 뱀의 머리가 있는 위치
			int curHeadX = deq.getFirst().x;
			int curHeadY = deq.getFirst().y;
			// 현재 뱀의 꼬리가 있는 위치
			int curTailX = deq.getLast().x;
			int curTailY = deq.getLast().y;
			// 현재 뱀의 머리가 있는 곳의 board를 2로 바꿔줌.
			board[curHeadX][curHeadY] = 2;

			// 방향 변환 카운트가 방향 변환 횟수보다 적다면
			// 이동 횟수가 현재 위치의 시간과 같다면 방향 전환
			if (l < L && moveCnt[l] == time[curHeadX][curHeadY]) {
				// D면 오른쪽 이동.
				if (moveDir[l] == 'D') {
					dir++;
				} else {
					// 아니면 왼쪽 이동.
					dir--;
				}
				// 방향 전환 후 l 증가.
				l++;
			}
			// dir이 0보다 작아지면 3으로, 3보다 커지면 0으로 설정하여 원형 형태로 dir 변경.
			if (dir < 0) {
				dir = 3;
			} else if (dir > 3) {
				dir = 0;
			}
			// 다음 뱀의 머리가 위치하는 곳은 현재 위치에 방향을 고려하여 설정.
			int nextHeadX = curHeadX + dx[dir % 4];
			int nextHeadY = curHeadY + dy[dir % 4];

			// 다음 뱀의 머리가 위치하는 곳이 보드를 넘어가거나 뱀의 몸에 부딪히는 경우 종료 조건.
			// 현재 뱀의 머리가 위치하는 곳에서 1 더한 값을 출력 후 종료.
			if (nextHeadX <= 0 || nextHeadY <= 0 || nextHeadX > N || nextHeadY > N
					|| board[nextHeadX][nextHeadY] == 2) {
				System.out.println(time[curHeadX][curHeadY] + 1);
				return;
			}

			// 덱 앞에 다음 머리가 위치하는 곳을 추가.
			deq.addFirst(new Pair(nextHeadX, nextHeadY));

			// 다음 꼬리가 위치하는 곳.
			int nextTailX = curTailX;
			int nextTailY = curTailY;
			// 다음 머리가 위치하는 곳이 사과가 있는 곳이라면
			if (board[nextHeadX][nextHeadY] == 1) {
				// 뱀의 몸통이 존재하는 곳이므로 보드의 해당 위치를 2로 바꿔줌.
				board[nextHeadX][nextHeadY] = 2;
			} else {
				// 사과가 없다면 뱀의 꼬리가 줄어들기 때문에 덱에서 마지막 값을 제거하고
				// 제거된 위치의 보드를 0으로 설정.
				Pair last = deq.pollLast();
				nextTailX = last.x;
				nextTailY = last.y;
				board[nextTailX][nextTailY] = 0;
			}
			// 다음 머리가 있는 곳의 시간은 현재 머리가 있는 곳의 시간에서 1초 더해진 값.
			time[nextHeadX][nextHeadY] = time[curHeadX][curHeadY] + 1;
		}
	}

	// 덱에서 수월하게 x,y에 접근하기 위한 클래스
	public static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
