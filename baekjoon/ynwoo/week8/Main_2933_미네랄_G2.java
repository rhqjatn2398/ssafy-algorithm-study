package algoStudy.week8;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2933_미네랄_G2 {
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static boolean[][] visited; // bfs 방문 배열
	static char[][] board; // 동굴 배열
	static int r, c; // 동굴 크기
	static int n; // 막대 던진 횟수

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		board = new char[r][c];
		for (int i = 0; i < r; i++) {
			board[i] = br.readLine().toCharArray();
		}
		n = Integer.parseInt(br.readLine());
		int[] sticks = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			sticks[i] = Integer.parseInt(st.nextToken());
		}

		// 구현
		// 땅에 붙어있는 미네랄이 1개(i 인덱스가 r-1)라도 있으면 그냥 continue
		// 근데 땅에 붙어있는 미네랄이 없으면 그 클러스터에서 가장 작은 j값과 큰 j값을 구한다.
		// 그 범위 안에서 i를 r-1 부터 0까지 돌리는데
		// 찾은 높이와 방문되지 않은 x의 위치 를 빼주고, 그 값이 현재 최소값보다 작으면 업데이트 해서 저장
		// 다 돌았을 때 최소값이 떨어져야하는 높이
		// 다시 j 범위 안에서 돌면서 방문된(떨어져야하는) x찾으면 그 높이만큼 떨궈주기
		for (int i = 0; i < n; i++) {
			int R = r - sticks[i]; // 인덱스
			int C = -1;
			if (i % 2 == 0) { // 왼쪽에서 던지기
				for (int j = 0; j < c; j++) {
					if (board[R][j] == 'x') {
						board[R][j] = '.'; // 미네랄 깬 위치 저장
						C = j;
						break;

					}
				}
			} else { // 오른쪽에서 던지기
				for (int j = c - 1; j >= 0; j--) {
					if (board[R][j] == 'x') {
						board[R][j] = '.'; // 미네랄 깬 위치 저장
						C = j;
						break;
					}
				}
			}
			// 미네랄을 깬 경우
			if (C != -1) {
				// 없어진 미네랄 주변 4방향에서 x이면 bfs 돌리기
				for (int d = 0; d < 4; d++) {
					int ni = R + di[d];
					int nj = C + dj[d];
					if (0 > ni || r <= ni || 0 > nj || c <= nj) { // 범위 아웃
						continue;
					}
					if (board[ni][nj] == '.') {
						continue;
					}

					// x이면 bfs
					visited = new boolean[r][c]; // 방문 배열 초기화(공중에 떠 있는 클러스터만 방문으로 체크 해준다.)
					int[] temp = bfs(ni, nj);
					if (temp == null) { // 바닥에 붙어있는 미네랄
						continue;
					}

					// 공중에 떠있는 클러스터 발견!
					int leftJ = temp[0];
					int rightJ = temp[1];

					int floor = r; // 기존 바닥
					int h = -1; // 가장 아래쪽 미네랄의 인덱스 저장할 변수
					int distance = Integer.MAX_VALUE; // 떨어질 간격 구하기
					for (int k = leftJ; k <= rightJ; k++) {
						for (int i2 = r - 1; i2 >= 0; i2--) {
							if (board[i2][k] == 'x' && !visited[i2][k]) {
								floor = i2; // 바닥 업데이트
							}
							if (board[i2][k] == 'x' && visited[i2][k]) { // 떨어질 클러스터에 포함된 x 찾음
								h = i2;
							}
							if (h != -1) { // 거리 구하기
								// 최소값 구하기
								distance = Math.min(distance, floor - h - 1);
								h = -1;
								floor = r; // 변수 초기화
							}
						}
					}

					// distance 만큼 내리기
					for (int k = leftJ; k <= rightJ; k++) {
						for (int i2 = r - 1; i2 >= 0; i2--) {
							if (board[i2][k] == 'x' && visited[i2][k]) {
								board[i2 + distance][k] = 'x';
								board[i2][k] = '.';
							}
						}
					}
				}
			}
		}

		// 출력
		for (int a = 0; a < r; a++) {
			for (int b = 0; b < c; b++) {
				System.out.print(board[a][b]);
			}
			System.out.println();
		}
	}

	private static int[] bfs(int i, int j) {
		int leftJ = Integer.MAX_VALUE;
		int rightJ = Integer.MIN_VALUE;
		Queue<Point> queue = new ArrayDeque<>();

		visited[i][j] = true;
		queue.offer(new Point(i, j));

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			if (leftJ > cur.y) {
				leftJ = cur.y;
			}
			if (rightJ < cur.y) {
				rightJ = cur.y;
			}
			// 바닥에 붙어있는 미네랄이다.
			if (cur.x == r - 1) {
				return null;
			}
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];

				if (0 > ni || r <= ni || 0 > nj || c <= nj) { // 범위 아웃
					continue;
				}
				if (board[ni][nj] == '.') {
					continue;
				}

				if (!visited[ni][nj]) {
					visited[ni][nj] = true;
					queue.offer(new Point(ni, nj));
				}
			}
		}
		// 떨어질 클러스터
		return new int[] { leftJ, rightJ }; // 떨어질 클러스터의 맨 왼쪽 j 인덱스, 맨 오른쪽 j 인덱스 리턴
	}
}