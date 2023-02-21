package algoStudy.week1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3190_뱀_G4 {
	static int[][] board; // 사과의 위치(1로 표시)와 뱀의 위치(2로 표시)를 표시할 2차원 배열
	// 큐 그림 <-----------
	// 뱀 꼬리 ----------- 뱀 머리
	//
	// 뱀 꼬리 ----------- 뱀 머리
	static Queue<Point> snake = new LinkedList<>(); // 뱀의 위치정보를 가지는 큐
	static int snakeHeadI, snakeHeadJ; // 뱀의 머리 위치를 표시할 인덱스
	static int[][] direction = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 방향정보(동 남 서 북)
	static int currentDirectionIndex; // 현재 방향
	static int time = 0; // 시간

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		board = new int[n][n];

		snakeHeadI = 0;
		snakeHeadJ = 0; // 초기 뱀 머리 위치
		currentDirectionIndex = 0; // 초기 방향

		board[snakeHeadI][snakeHeadJ] = 2; // 보드에 뱀 표시
		snake.add(new Point(snakeHeadI, snakeHeadJ)); // 몸통 추가

		int k = Integer.parseInt(br.readLine()); // 사과의 개수
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			board[row - 1][col - 1] = 1; // 사과 위치 표시
		}

		int l = Integer.parseInt(br.readLine()); // 방향 변환 정보 수
		label: for (int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다
			char c = st.nextToken().charAt(0);

			// 구현
			while (time != x) {
				int ni = snakeHeadI + direction[currentDirectionIndex][0]; // 이동한 뱀의 머리 위치
				int nj = snakeHeadJ + direction[currentDirectionIndex][1];
				if (0 <= ni && ni < n && 0 <= nj && nj < n && board[ni][nj] != 2) { // 머리가 새롭게 이동한 곳이 범위 안이면서 2(뱀의 몸통)가
																					// 아니어야함
					time++;
					snakeHeadI = ni; // 머리 위치 업데이트
					snakeHeadJ = nj;

					if (board[ni][nj] == 0) { // 사과를 못먹음
						Point tail = snake.poll(); // 꼬리칸 없애줌
						board[tail.x][tail.y] = 0;
					}

					Point newHead = new Point(snakeHeadI, snakeHeadJ);
					snake.add(newHead); // 새로운 머리 추가
					board[newHead.x][newHead.y] = 2;
				} else {
					break label;
				}
			}
			if (c == 'L') {// 왼쪽 90도 회전
				currentDirectionIndex = (currentDirectionIndex - 1 >= 0) ? currentDirectionIndex - 1 : 3;
			} else { // 오른쪽 90도 회전
				currentDirectionIndex = (currentDirectionIndex + 1 < 4) ? currentDirectionIndex + 1 : 0;
			}
		}
		// 한번 틀렸던 이유
		// 방향 전환을 다 끝낼때까지 뱀이 살아있으면 뱀이 죽을 때까지 추가로 시간 카운트
		while (true) {
			int ni = snakeHeadI + direction[currentDirectionIndex][0];
			int nj = snakeHeadJ + direction[currentDirectionIndex][1];
			if (0 <= ni && ni < n && 0 <= nj && nj < n && board[ni][nj] != 2) { // 새롭게 이동한 곳이 범위 안이면서 2가 아니어야함
				time++;
				snakeHeadI = ni;
				snakeHeadJ = nj;

				if (board[ni][nj] == 0) { // 사과를 못먹음
					Point tail = snake.poll(); // 꼬리칸 없애줌
					board[tail.x][tail.y] = 0;
				}

				Point newHead = new Point(snakeHeadI, snakeHeadJ);
				;
				snake.add(newHead); // 새로운 머리 추가
				board[newHead.x][newHead.y] = 2;
			} else {
				break;
			}
		}

		System.out.println(time + 1);
		br.close();
	}
}
