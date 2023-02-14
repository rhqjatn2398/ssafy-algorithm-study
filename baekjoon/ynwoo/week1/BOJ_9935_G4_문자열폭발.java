package algoStudy.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935_문자열폭발_G4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String string = br.readLine();
		String explosionString = br.readLine();

		Stack<Character> stack = new Stack<>();
		Stack<Character> temp = new Stack<>();

		for (int i = string.length() - 1; i >= 0; i--) { // 역순으로 스택에 넣는다.
			stack.push(string.charAt(i));

			// 폭발문자열의 길이보다 스택의 사이즈가 크거나 같고, 이번에 들어온 문자가 폭발문자열의 맨 앞 문자라면!
			// -> 폭발문자열이 있을 가능성이 있다!
			if (stack.peek() == explosionString.charAt(0) && explosionString.length() <= stack.size()) {
				// 폭발문자열의 길이 만큼 스택에서 pop해서 폭발문자열과 같은지 비교, 같으면 그대로, 다르면 다시 stack에 push
				for (int j = 0; j < explosionString.length(); j++) {
					char c = stack.pop();
					temp.push(c);
					if (c != explosionString.charAt(j)) { // 폭발문자열과 같지 않음
						// 다시 스택에 push 해주고 break
						while (!temp.empty()) {
							stack.push(temp.pop());
						}
						break;
					}
				}
				temp.clear();
			}
			// System.out.println(stack);
		}

		if (stack.empty()) {
			System.out.println("FRULA");
		} else {
			// StringBuilder 사용
			StringBuilder sb = new StringBuilder();
			while (!stack.empty()) {
				sb.append(stack.pop());
			}
			System.out.println(sb.toString());
			
			// 시간초과 원인...
//			while(!stack.empty()) {
//				System.out.print(stack.pop());
//			}
		}
		
		br.close();
	}

}
