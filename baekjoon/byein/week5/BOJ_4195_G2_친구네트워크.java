package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 유니온 파인드 이용.
 * @author byein
 *
 */
public class BOJ_4195_G2_친구네트워크 {
	static int[] parents, cnt;
	static int T, F;
	static String A, B;
	static HashMap<String, Integer> map = new HashMap<>();


	// 초기
	static void make(int n) {
		parents = new int[n];
		cnt = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
			cnt[i] = 1;
		}
	}

	// union
	static int union(int a, int b) {
		int aRoot = find(parents[a]);
		int bRoot = find(parents[b]);
		if (aRoot != bRoot) {
			parents[bRoot] = find(aRoot);
			cnt[aRoot] += cnt[bRoot];
			cnt[bRoot] = 1;
		}
		return cnt[aRoot];
	}

	// find
	static int find(int x) {
		if (x == parents[x])
			return x;
		return parents[x] = find(parents[x]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			F = Integer.parseInt(br.readLine());
			// 초기화. 최대 F*2개만큼 가능.
			make(F * 2);
			int idx = 0;
			map = new HashMap<>();
			for (int f = 0; f < F; f++) {
				st = new StringTokenizer(br.readLine());
				A = st.nextToken();
				B = st.nextToken();

				// A가 없으면 개수 증가.
				if (!map.containsKey(A)) {
					map.put(A, idx++);
				}
				// B가 없으면 개수 증가.
				if (!map.containsKey(B)) {
					map.put(B, idx++);
				}
				System.out.println(union(map.get(A), map.get(B)));
			}
		}
	}

}
