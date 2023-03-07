package byein.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10830_G4_행렬제곱 {

	static int N;
	static long B;
	static int[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());

		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		// 행렬 제곱.
		int [][]ans = pow(matrix, B);
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
	}

	/**
	 * 분할정복.
	 * @param A
	 * @param n
	 * @return
	 */
	static int[][] pow(int[][] A, long n) {
		// n이 1이면 A 리턴.
		if (n == 1) {
			return A;
		}
		// 현재 행렬은 A^(n/2).
		int[][] x = pow(A, n / 2);
		// 행렬 곱 연산으로 제곱 처리.
		x = multiply(x, x);
		// 홀수이면 현재 행렬 곱과 기존 행렬 곱.
		if (n % 2 == 1) {
			x = multiply(x, matrix);
		}
		return x;
	}

	/**
	 * 행렬 곱 연산.
	 * @param a
	 * @param b
	 * @return
	 */
	static int[][] multiply(int[][] a, int[][] b) {
		int[][] ret = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					ret[i][j] += a[i][k] * b[k][j];
				}
				ret[i][j] %= 1000;
			}
		}

		return ret;
	}

}
