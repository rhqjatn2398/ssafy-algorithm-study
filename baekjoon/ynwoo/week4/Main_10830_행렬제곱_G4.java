package algoStudy.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10830_행렬제곱_G4 {
	static int N;
	static long B;
	static int[][] matrix;
	static int[][] answer;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());

		matrix = new int[N][N];
		answer = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 원소가 1000이 될 수 있기 때문에 B가 1일 때(연산을 하지 않을 때) 0을 출력해주어야한다
		// 2 1
		// 1000 1000
		// 1000 1000
		// 0 0
		// 0 0
		if (B == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print((matrix[i][j] % 1000) + " ");
				}
				System.out.println();
			}
		} else {
			answer = getBSquare(B); // 행렬 제곱 연산
			// 출력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(answer[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	// 행렬의 제곱을 연산하는 함수
	private static int[][] getBSquare(long B) {
		if (B == 1) {
			return matrix;
		}
		// 시간초과 해결 방법: temp로 한 번만 연산하게 한다
		int[][] temp = getBSquare(B / 2);
		if (B % 2 == 0) {
			return multiply(temp, temp);
			// 시간초과
			// return multiply(getBSquare(B / 2), getBSquare(B / 2));
		} else {
			return multiply(multiply(temp, temp), matrix);
		}

	}

	// 행렬의 곱을 연산하는 함수
	private static int[][] multiply(int[][] m1, int[][] m2) {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int[] aArray = getRowArray(i, m1); // 행 구하기
				int[] bArray = getColArray(j, m2); // 열 구하기
				result[i][j] = getSpace(aArray, bArray);
			}
		}
		return result;
	}

	private static int[] getRowArray(int i, int[][] m1) {
		int[] result = new int[N];
		for (int j = 0; j < N; j++) {
			result[j] = m1[i][j];
		}
		return result;
	}

	private static int[] getColArray(int j, int[][] m2) {
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			result[i] = m2[i][j];
		}
		return result;
	}

	// 나머지(Modulo) 연산 분배법칙
	// (A + B) % p = ((A % p) + (B % p)) % p
	// (A * B) % p = ((A % p) * (B % p)) % p
	// (A - B) % p = ((A % p) - (B % p) + p) % p
	
	// 행렬의 한 칸 계산하는 함수
	private static int getSpace(int[] a, int[] b) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += (((a[i] % 1000) * (b[i] % 1000)) % 1000);
		}
		return sum % 1000;
	}
}
