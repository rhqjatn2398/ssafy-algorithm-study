import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] map = new int[(board.length + 1)][(board[0].length + 1)];
        for (int i =0 ;i<skill.length;i++){
            if (skill[i][0] == 1){
                map[skill[i][1]][skill[i][2]] -= skill[i][5];
                map[skill[i][3] + 1][skill[i][4] + 1] -= skill[i][5];
                map[skill[i][1]][skill[i][4] + 1] += skill[i][5];
                map[skill[i][3] + 1][skill[i][2]] += skill[i][5];
            }else if (skill[i][0] == 2){
                map[skill[i][1]][skill[i][2]] += skill[i][5];
                map[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5];
                map[skill[i][1]][skill[i][4] + 1] -= skill[i][5];
                map[skill[i][3] + 1][skill[i][2]] -= skill[i][5];
            }
        }
        
        for (int i = 1;i<map.length;i++){
            for (int j = 0;j<map[i].length;j++){
                map[i][j] += map[i-1][j];
            }
        }
        
         for (int i = 0;i<map.length;i++){
            for (int j = 1;j<map[i].length;j++){
                map[i][j] += map[i][j-1];
            }
        }
        
        for (int i = 0;i<board.length;i++){
            for (int j = 0;j<board[i].length;j++){
                board[i][j] += map[i][j];
            }
        }
        
        for (int i = 0;i<board.length;i++){
            for (int j = 0;j<board[i].length;j++){
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
//     public int solution(int[][] board, int[][] skill) {
//         int answer = 0;
//         for (int i =0 ;i<skill.length;i++){
//             if (skill[i][0] == 1){
//                 for (int r = skill[i][1]; r <= skill[i][3]; r++){
//                     for (int c = skill[i][2]; c <= skill[i][4]; c++){
//                         board[r][c] -= skill[i][5];
//                     }
//                 }
//             }else if (skill[i][0] == 2){
//                 for (int r = skill[i][1]; r <= skill[i][3]; r++){
//                     for (int c = skill[i][2]; c <= skill[i][4]; c++){
//                         board[r][c] += skill[i][5];
//                     }
//                 }
//             }
//         }
        
//         for (int i = 0;i<board.length;i++){
//             for (int j = 0;j<board[0].length;j++){
//                 if (board[i][j]>0){
//                     answer++;
//                 }
//             }
//         }
//         return answer;
//     }
}
