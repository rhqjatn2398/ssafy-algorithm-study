import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_1644_G3_소수의연속합 {

	static int N, cnt; // 입력값과 출력할 답
	static boolean[] isPrime; // 소수인지 아닌지 정보를 담을 배열
	static ArrayList<Integer> primeNums = new ArrayList<>(); // 소수만 담을 리스트

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		initPrime(); // 소수 판별하고 리스트에 소수만 담기.


		// 투 포인터. st, en
		int st = 0, en = 0, sum = 0;

		// 무한루프 돌면서
		while (true) {
			// 만약 합이 N보다 크거나 같으면 st에 있는 소수 빼기.
			if (sum >= N)
				sum -= primeNums.get(st++);
			// 만약 en이 primeNums 크기를 넘어가면 빠져나오기
			else if (en >= primeNums.size())
				break;
			// 만약 합이 N보다 작다면 en에 있는 소수 더하기.
			else if (sum < N)
				sum += primeNums.get(en++);
			// 합이 N이라면 cnt 증가.
			if (sum == N)
				cnt++;
		}
		System.out.println(cnt);
	}

	/**
	* 소수인지 아닌지 판별(에라토스테네스의 체) 하고 리스트에 소수만 넣는 함수.
	* 	
	**/
	static void initPrime() {
		isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true); // true로 초기화
		isPrime[0] = isPrime[1] = false; // 처음 0,1은 소수가 아니므로 false로 초기화
		// 2부터 N까지의 수 중 소수가 가능한 수는 N^(1/2)까지의 수에서 나눠지는 수가 없는 경우.
		for (int i = 2; i * i <= N; i++) {
			// 이미 소수가 아닌 경우는 체크된 경우이므로 소수인 경우만 확인.
			if (isPrime[i]) {
				// i의 모든 배수를 false로 처리. j가 i가 아니라 i*i로 초기화되어 소수는 처리X.
				for (int j = i * i; j <= N; j += i) {
					isPrime[j] = false;
				}
			}
		}
		// 소수인 경우 리스트에 넣기.
		for (int i = 0; i <= N; i++) {
			if (isPrime[i]) {
				primeNums.add(i);
			}
		}
	}
}