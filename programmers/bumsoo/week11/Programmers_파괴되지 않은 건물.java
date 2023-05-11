class Solution {
    
    int[][] suffix;
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        suffix = new int[board.length + 1][board[0].length + 1];
        
        for (int i = 0; i < skill.length; i++) {
            int[] cur = skill[i];
            
            int type = cur[0];
            int r1 = cur[1];
            int c1 = cur[2];
            int r2 = cur[3];
            int c2 = cur[4];
            int degree = cur[5];
            
            // 공격 or 회복
            degree = type == 1 ? -degree : degree;
            
            // imos
            suffix[r1][c1] += degree;
            suffix[r1][c2 + 1] -= degree;
            suffix[r2 + 1][c1] -= degree;
            suffix[r2 + 1][c2 + 1] += degree;
        }
        
        for (int i = 0; i < suffix.length - 1; i++) {
            for (int j = 0; j < suffix[0].length - 1; j++) {
                suffix[i][j + 1] += suffix[i][j];
            }
        }
        
        for (int i = 0; i < suffix[0].length - 1; i++) {
            for (int j = 0; j < suffix.length - 1; j++) {
                suffix[j + 1][i] += suffix[j][i];
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (suffix[i][j] + board[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        
        return answer;
    }
}
