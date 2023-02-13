package baekjoon.김예빈.week01.BOJ_9935_문자열_폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		Stack<Character> stack = new Stack<>(); // 스택 사용.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String string = br.readLine();
		String bomb = br.readLine();
		StringBuilder sb = new StringBuilder();
		// 문자열 순회.
		for (int i = 0; i < string.length(); i++) {
			// 스택에 현재 문자열 추가.
			stack.add(string.charAt(i));
			// 스택의 사이즈가 폭발 문자열보다 작다면 
			// 폭발문자열이 있을 수 없으므로 예외 처리.
			if (stack.size() >= bomb.length()) {
				// 폭발 문자열인지 아닌지 판단하는 값을 저장하는 변수.
				boolean isBomb = true;
				// 폭발 문자열만큼 돌면서 폭발 문자열과 스택에 저장된 값이 서로 같다면 
				// 폭발문자열이 아니므로 isBomb을 false로 처리 후 반복문 빠져나옴.
				for (int j = 0; j < bomb.length(); j++) {
					if (bomb.charAt(j) != stack.get(stack.size() - bomb.length() + j)) {
						isBomb = false;
						break;
					}
				}
				// 폭발 문자열이라면 폭발 문자열 길이만큼 스택에서 pop.
				if (isBomb) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		for (int i = 0; i < stack.size(); i++) {
			sb.append(stack.get(i));
		}
		// 공백이면 FRULA 아니면 남은 문자열 출력.
		System.out.println(sb.toString() == "" ? "FRULA" : sb.toString());
	}
}
