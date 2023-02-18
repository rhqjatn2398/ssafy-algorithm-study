package algoStudy.week2;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683_감시_G4 {
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] arr;
	//                       ←           ↑         →         ↓
	static int[][] di = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
	// 
	static int[][] directionInfos; // cctv종류에 따라 가능한 방향 정보들 저장
	static List<Point> coordinates = new ArrayList<Point>(); // cctv 좌표 위치 정보
	static List<Integer> cctvNums = new ArrayList<Integer>(); // cctv 종류 정보

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0 && arr[i][j] != 6) {
					coordinates.add(new Point(i, j));
					cctvNums.add(arr[i][j]);
				}
			}
		}
//		for (int i = 0; i < coordinates.size(); i++) {
//			bw.write(coordinates.get(i) + " " + cctvNums.get(i));
//			bw.write("\n");
//			bw.flush();
//		}

		// 구현
		directionInfos = new int[coordinates.size()][]; // cctv종류에 따라 가변적으로 저장
		generateCases(0);
		
		// 출력
		bw.write(answer + "");
		bw.close();
	}

	private static void generateCases(int cnt) {
		if (cnt == coordinates.size()) {
			// 기존 배열 카피, 선택된 방향에 대해서 계산
			int[][] copyArr = new int[N][M];
			for (int i = 0; i < N; i++) {
				copyArr[i] = arr[i].clone();
			}

			for (int i = 0; i < directionInfos.length; i++) {
				for (int j = 0; j < directionInfos[i].length; j++) {
					// cctv 작동시키기
					activateCCTV(i, directionInfos[i][j], copyArr); // i번째에 해당하는 cctv를 directionInfos[i][j] 방향으로 작동시킨다
				}

			}

			int blindSpotCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copyArr[i][j] == 0) {
						blindSpotCnt++;
					}
				}
			}
			// 어떤 cctv가 어떻게 작동했는지
//			for (int i = 0; i < directionInfos.length; i++) {
//				for (int j = 0; j < directionInfos[i].length; j++) {
//					System.out.print(
//							i + "번째 cctv인 " + cctvNums.get(i) + "번 cctv를 " + directionInfos[i][j] + "방향으로 작동시켰습니다. ");
//				}
//				System.out.println();
//			}
//			System.out.println();

			// 작동 결과 출력
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(copyArr[i][j] + " ");
//				}
//				System.out.println();
//			}

			answer = Math.min(answer, blindSpotCnt);
			return;
		}

		int cctvType = cctvNums.get(cnt);
		switch (cctvType) {
		case 1:
			directionInfos[cnt] = new int[1];
			directionInfos[cnt][0] = 0;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 1;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 2;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 3;
			generateCases(cnt + 1);
			break;
		case 2:
			directionInfos[cnt] = new int[2];
			directionInfos[cnt][0] = 0;
			directionInfos[cnt][1] = 2;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 1;
			directionInfos[cnt][1] = 3;
			generateCases(cnt + 1);
			break;
		case 3:
			directionInfos[cnt] = new int[2];
			directionInfos[cnt][0] = 1;
			directionInfos[cnt][1] = 2;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 2;
			directionInfos[cnt][1] = 3;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 3;
			directionInfos[cnt][1] = 0;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 0;
			directionInfos[cnt][1] = 1;
			generateCases(cnt + 1);
			break;
		case 4:
			directionInfos[cnt] = new int[3];
			directionInfos[cnt][0] = 0;
			directionInfos[cnt][1] = 1;
			directionInfos[cnt][2] = 2;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 1;
			directionInfos[cnt][1] = 2;
			directionInfos[cnt][2] = 3;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 2;
			directionInfos[cnt][1] = 3;
			directionInfos[cnt][2] = 0;
			generateCases(cnt + 1);
			directionInfos[cnt][0] = 3;
			directionInfos[cnt][1] = 0;
			directionInfos[cnt][2] = 1;
			generateCases(cnt + 1);
			break;
		case 5:
			directionInfos[cnt] = new int[4];
			directionInfos[cnt][0] = 0;
			directionInfos[cnt][1] = 1;
			directionInfos[cnt][2] = 2;
			directionInfos[cnt][3] = 3;
			generateCases(cnt + 1);
			break;

		}
	}

	// i번째에 해당하는 cctv를 di[directionIdx] 방향으로 작동시킨다
	private static void activateCCTV(int i, int directionIdx, int[][] arr) {
		// 사무실 범위를 벗어나거나 벽을 만날 때 까지 cctv를 비춘다.
		int currentI = coordinates.get(i).x;
		int currentJ = coordinates.get(i).y;
		while (true) {
			int ni = currentI + di[directionIdx][0];
			int nj = currentJ + di[directionIdx][1];
			if (0 <= ni && ni < N && 0 <= nj && nj < M && arr[ni][nj] != 6) { // cctv를 비출 수 있음
				arr[ni][nj] = cctvNums.get(i);
				currentI = ni;
				currentJ = nj;
			} else {
				break;
			}
		}

	}

}
