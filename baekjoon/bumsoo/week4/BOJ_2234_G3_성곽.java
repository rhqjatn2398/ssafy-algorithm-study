import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2234_G3_성곽 {

    public class Main {

        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;

        static int n, m;
        static int[][] grid, nums;
        static boolean[][] visited;
        static int[] rooms;
        static List<Point>[] adjPoints;
        static int[] dy = {0, -1, 0, 1};
        static int[] dx = {-1, 0, 1, 0};

        public static void main(String[] args) throws Exception {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            grid = new int[m][n];
            nums = new int[m][n];
            visited = new boolean[m][n];
            rooms = new int[2501];
            adjPoints = new List[2501];
            for (int i = 0; i < 2501; i++) {
                adjPoints[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int roomCnt = 0;
            int maxArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        roomCnt++;
                        visited[i][j] = true;
                        nums[i][j] = roomCnt;
                        rooms[roomCnt] = go(i, j, roomCnt);
                        maxArea = Math.max(maxArea, rooms[roomCnt]);
                    }
                }
            }

            int maxAreaOneWall = 0;
            for (int roomNum = 1; roomNum <= roomCnt; roomNum++) {
                for (Point point : adjPoints[roomNum]) {
                    int adjNum = nums[point.y][point.x];

                    if (adjNum == roomNum) {
                        continue;
                    }

                    int candidate = rooms[roomNum] + rooms[adjNum];
                    maxAreaOneWall = Math.max(maxAreaOneWall, candidate);
                }
            }

            System.out.println(roomCnt + "\n" + maxArea + "\n" + maxAreaOneWall);
        }

        static int go(int row, int col, int num) {
            int result = 1;

            for (int i = 0; i < 4; i++) {
                int nRow = row + dy[i];
                int nCol = col + dx[i];

                int cur = grid[row][col];
                if (inRange(nRow, nCol) && !visited[nRow][nCol]) {
                    if ((cur & (1 << i)) != 0) {
                        // 벽 너머의 좌표추가 같은 방이 될 좌표도 추가 될 수 있음
                        adjPoints[num].add(new Point(nCol, nRow));
                    } else {
                        // 벽이 아니라면 같은 방
                        visited[nRow][nCol] = true;
                        nums[nRow][nCol] = num;
                        result += go(nRow, nCol, num);
                    }
                }
            }

            return result;
        }

        static boolean inRange(int row, int col) {
            return 0 <= row && row < m && 0 <= col && col < n;
        }
    }
}