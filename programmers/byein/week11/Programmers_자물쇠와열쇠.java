class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int kLen = key.length; // 키 크기
        int lLen = lock.length; // 자물쇠 크기
        
        int newLockSize = lock.length + key.length * 2 - 2; // 키는 자물쇠의 밖으로 나갈 수 있으므로 확장시킬 자물쇠 크기는 자물쇠 크기에서 키 크기만큼 상하 좌우 각각 2군데씩 확장된다. 또한, 키가 자물쇠에 하나라도 올라가 있어야 하므로, 2를 빼줘야 한다.
        int[][] expandedLock = new int[newLockSize][newLockSize]; // 확장시킬 자물쇠
        
        // 자물쇠 중심에 기존 자물쇠 복사
        for (int i = 0;i<lLen;i++){
            for (int j = 0;j<lLen;j++){
                expandedLock[i+kLen-1][j+kLen-1] = lock[i][j];
            }
        }
        
        // 값이 바뀔 새 자물쇠
        int[][] newLock = new int[newLockSize][newLockSize];
        
        
        for (int k = 0; k < 4; k++){ // 회전 횟수
            for (int i = 0;i<lLen + kLen-1;i++){ 
                for (int j =0;j<lLen+kLen-1;j++){
                    // newLock에 복사
                    for (int tmp = 0;tmp<newLockSize;tmp++){
                        newLock[tmp] = expandedLock[tmp].clone();
                    }
                    
                    // newLock에 키 더함.
                    for (int r = 0; r < kLen; r++){
                        for (int c = 0; c < kLen; c++){
                            newLock[r+i][c+j] +=  key[r][c];
                        }
                    }
                    
                    // 자물쇠와 키가 맞으면 true 리턴
                    if (isMatch(kLen, lLen, newLock)){
                        return true;
                    }
                }
            }
            // 키 회전시키기
            key = rotate(kLen, key);
        }
        
        return false;
    }
    
    // 00 01 02
    // 10 11 12
    // 20 21 22

    // 20 10 00
    // 21 11 01
    // 22 12 02
    
    // 키 회전시키는 함수
    static int[][] rotate(int len, int[][] key){
        // 회전할 키
        int[][] newKey = new int[len][len];
        
        // 키 회전
        for (int i = 0;i<len;i++){
            for (int j = 0;j<len;j++){
                newKey[j][len-1-i] = key[i][j];
            }
        }
        
        // 회전된 키 리턴
        return newKey;
    }
    
    // 자물쇠와 키가 맞는지 체크
    static boolean isMatch(int kLen, int lLen, int[][] board){
        // 자물쇠 중심 부분은 키가 더해진 값으로 1이 아니라면 맞지 않는 것
        for (int i = kLen-1;i<kLen-1+lLen;i++){
            for (int j = kLen-1;j<kLen-1+lLen;j++){
                if (board[i][j] != 1){
                    return false;
                }
            }
        }
        // 맞는 경우
        return true;
    }
}
