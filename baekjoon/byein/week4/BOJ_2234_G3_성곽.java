package byein.week4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_G3_성곽 {

	static int N, M;
	static int[][] board;
	static int[][] roomNums;
	static boolean[][] visited;
	// 서 - 1 북 - 2 동 - 4 남 - 8
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Queue<Point> queue = new ArrayDeque<>();

		board = new int[M][N];
		roomNums = new int[M][N];
		visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 방번호에 따라 크기 담을 hashmap.
		HashMap<Integer, Integer> areaMap = new HashMap<>();
		// 현재 방.
		int roomIdx = 0;
		// 벽 뚫었을 때 합의 최대.
		int sumMx = Integer.MIN_VALUE;
		
		// bfs.
		// 모든 좌표에 대해 돌면서 방의 크기와 방의 개수 구함.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				queue.add(new Point(i, j));
				visited[i][j] = true;
				int area = 1; // 현재 방의 크기.
				while (!queue.isEmpty()) {
					Point cur = queue.poll();
					// 현재 방 번호 배열에 저장.
					roomNums[cur.x][cur.y] = roomIdx;
					for (int dir = 0; dir < 4; dir++) {
						// 이동 불가능한 경우 넘어감.
						if ((board[cur.x][cur.y] & 1 << dir) != 0) {
							continue;
						}
						// 배열 벗어나거나 방문한 경우도 넘어감.
						int nx = cur.x + dx[dir];
						int ny = cur.y + dy[dir];
						if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[nx][ny])
							continue;
						Point next = new Point(nx, ny);
						queue.add(next);
						visited[nx][ny] = true;
						area++; // 방 크기 증가.
					}
				}
				// 현재 방 번호에 크기 저장.
				areaMap.put(roomIdx++, area);
			}
		}

		// 방의 총 개수와 최대 방 크기 출력.
		System.out.println(areaMap.size());
		System.out.println(Collections.max(areaMap.values()));

		// bfs
		// 인접한 경우 방 크기 합했을 때 최대 구하기.
		visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				queue.add(new Point(i, j));
				visited[i][j] = true;
				while (!queue.isEmpty()) {
					Point cur = queue.poll();
					for (int dir = 0; dir < 4; dir++) {
						int nx = cur.x + dx[dir];
						int ny = cur.y + dy[dir];
						// 범위 벗어나거나 방문한 경우 넘어감.
						if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[nx][ny])
							continue;
						// 현재 방의 번호와 다음 방의 번호가 다르다면 벽을 뚫은 경우도 두 방의 합의 최대 갱신.
						if (roomNums[nx][ny] != roomNums[cur.x][cur.y]) {
							sumMx = Math.max(sumMx,
									areaMap.get(roomNums[nx][ny]) + areaMap.get(roomNums[cur.x][cur.y]));
						}
						Point next = new Point(nx, ny);
						queue.add(next);
						visited[nx][ny] = true;
					}
				}
			}
		}
		System.out.println(sumMx);
	}

}