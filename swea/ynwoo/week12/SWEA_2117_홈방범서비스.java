package algo2;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class SWEA_2117_홈방범서비스 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int houseCnt = 0;
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						houseCnt++;
					}
				}
			}

			int k = 1;
			int maxCnt = 0;
			while (k * k + (k - 1) * (k - 1) <= houseCnt * m) {
				// k에 해당하는 구역 set으로 추가
				Set<Point> area = getArea(n, k);
				// 2중 for문 돌면서 범위 안 && 1이면 개수에 추가
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						int curHouseCnt = 0;
						for (Point point : area) {
							int ni = i + point.x;
							int nj = j + point.y;
							// i,j + area[] : 범위 안 + 1이면 curHouseCnt증가
							if (ni < 0 || ni >= n || nj < 0 || nj >= n) {
								continue;
							}
							if (map[ni][nj] == 1) {
								curHouseCnt++;
							}
						}
						// cnt * m 이 k계산 값보다 크거나 같으면 cnt 최대값 비교후 갱신
						if (curHouseCnt * m >= k * k + (k - 1) * (k - 1)) {
							maxCnt = Math.max(maxCnt, curHouseCnt);
						}
					}
				}
				k++;
			}

			System.out.printf("#%d %d\n", test_case, maxCnt);
		}
	}

	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	private static Set<Point> getArea(int n, int k) {
		int depth = 1;
		Set<Point> area = new HashSet<>();
		Point startPoint = new Point(0, 0);

		Queue<Point> queue = new ArrayDeque<>();
		queue.add(startPoint);
		area.add(startPoint); // visit
		while (!queue.isEmpty() && depth < k) {
			int size = queue.size();
			while (--size >= 0) {
				Point cur = queue.poll();
				// 4방향
				for (int d = 0; d < 4; d++) {
					int ni = cur.x + di[d];
					int nj = cur.y + dj[d];
					Point newPoint = new Point(ni, nj);
					if (!area.contains(newPoint)) {
						queue.add(newPoint);
						area.add(newPoint);
					}
				}
			}
			depth++;
		}

		return area;
	}

}
