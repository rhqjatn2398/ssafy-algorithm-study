class Solution {
    
    int[][] grid;
    int m, n, cntToFill;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        m = key.length;
        n = lock.length;
        grid = new int[2 * m + n - 2][2 * m + n - 2];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[m - 1 + i][m - 1 + j] = lock[i][j];
                
                if (lock[i][j] == 0) {
                    cntToFill++;
                }
            }
        }
        
        for (int i = 0; i < 4; i++) {
            key = rotate90(key);
            
            if (go(key, lock)) {
                answer = true;
                break;
            }
        }
        
        return answer;
    }
    
    int[][] rotate90(int[][] key) {
        int[][] tmp = new int[m][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = key[m - j - 1][i];
            }
        }
        
        return tmp;
    }
    
    boolean go(int[][] key, int[][] lock) {
        for (int i = 0; i < m + n - 1; i++) {
            for (int j = 0; j < m + n - 1; j++) {
                if (check(key, i, j)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    boolean check(int[][] key, int row, int col) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (!inLockRange(row + i, col + j)) {
                    continue;
                }
                
                // 돌기 and 돌기. 그냥 불가능한 경우
                if (grid[row + i][col + j] == 1 && key[i][j] == 1) {
                    return false;
                }
                
                if (grid[row + i][col + j] == 0 && key[i][j] == 1) {
                    cnt++;
                }
            }
        }
        
        return cnt == cntToFill;
    }
    
    boolean inLockRange(int row, int col) {
        return m - 1 <= row && row < m + n - 1 && m - 1 <= col && col < m + n - 1;
    }
}
