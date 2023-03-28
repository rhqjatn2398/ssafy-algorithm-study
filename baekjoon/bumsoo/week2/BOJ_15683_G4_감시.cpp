#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

struct CCTV {
	int type;
	int cntKinds; // 회전시 서로다른 경우의 수
	int kind; // 현재 설정된 시계방향 순서
	int y;
	int x;

	CCTV() {};
	CCTV(int type, int cntKinds, int kind, int y, int x) : type(type), cntKinds(cntKinds), kind(kind), y(y), x(x) {}
};

int n, m, cntCCTV;
int ans = 8 * 8;
int grid[8][8], temp[8][8];
int dy[4] = { 0, 1, 0, -1 };
int dx[4] = { 1, 0, -1, 0 };
int kinds[5]{ 4, 2, 4, 4, 1 };
vector<CCTV> cctvs;

bool inRange(int row, int col) {
	return 0 <= row && row < n && 0 <= col && col < m;
}

void watchLine(int row, int col, int dRow, int dCol) {
	// (row, col) 에서 dy, dx 방향으로 한 방향 감시
	while (true) {
		int nRow = row + dRow;
		int nCol = col + dCol;

		if (!inRange(nRow, nCol) || temp[nRow][nCol] == 6) {
			return;
		}

		if (temp[nRow][nCol] == 0) {
			temp[nRow][nCol] = -1; // -1 := 감시되고 있는 칸
		}

		row = nRow;
		col = nCol;
	}
}

void watch(CCTV cctv) {
	// CCTV type에 따라 다방향 감시
	int row = cctv.y;
	int col = cctv.x;
	int kind = cctv.kind;
	int cntKinds = cctv.cntKinds;

	switch (cctv.type)
	{
	case 1:
		watchLine(row, col, dy[kind], dx[kind]);
		break;
	case 2:
		watchLine(row, col, dy[kind], dx[kind]);
		watchLine(row, col, dy[(kind + 2) % 4], dx[(kind + 2) % 4]);
		break;
	case 3:
		watchLine(row, col, dy[kind], dx[kind]);
		watchLine(row, col, dy[(kind + 1) % 4], dx[(kind + 1) % 4]);
		break;
	case 4:
		watchLine(row, col, dy[kind], dx[kind]);
		watchLine(row, col, dy[(kind + 1) % 4], dx[(kind + 1) % 4]);
		watchLine(row, col, dy[(kind + 2) % 4], dx[(kind + 2) % 4]);
		break;
	case 5:
		watchLine(row, col, dy[kind], dx[kind]);
		watchLine(row, col, dy[(kind + 1) % 4], dx[(kind + 1) % 4]);
		watchLine(row, col, dy[(kind + 2) % 4], dx[(kind + 2) % 4]);
		watchLine(row, col, dy[(kind + 3) % 4], dx[(kind + 3) % 4]);
		break;
	}
}

int simulate() {
	copy(&grid[0][0], &grid[0][0] + 8 * 8, &temp[0][0]);

	for (CCTV cctv : cctvs) {
		watch(cctv);
	}

	int result = 0; // 사각지대의 크기
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (temp[i][j] == 0) {
				result++;
			}
		}
	}

	return result;
}

void go(int idx) {
	if (idx == cctvs.size()) {
		ans = min(ans, simulate());
		return;
	}

	// CCTV cctv = cctvs[idx]; shallow copy...
	CCTV &cctv = cctvs[idx];

	for (int i = 0; i < cctv.cntKinds; i++) {
		cctv.kind = i;
		go(idx + 1);
	}
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> grid[i][j];

			if (grid[i][j] == 0)
				continue;
			if (grid[i][j] < 6) {
				int type = grid[i][j];
				cctvs.push_back(CCTV(type, kinds[type - 1], 0, i, j));
			}
		}
	}

	go(0);

	cout << ans << '\n';

	return 0;
}