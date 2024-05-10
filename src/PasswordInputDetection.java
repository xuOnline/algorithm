import java.util.LinkedList;
import java.util.Scanner;

/**
 * 给定用户密码输入流 input，输入流中字符 '<' 表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
 *
 * 密码安全要求如下：
 *
 * 密码长度 ≥ 8；
 * 密码至少需要包含 1 个大写字母；
 * 密码至少需要包含 1 个小写字母；
 * 密码至少需要包含 1 个数字；
 * 密码至少需要包含 1 个字母和数字以外的非空白特殊字符；
 * 注意空串退格后仍然为空串，且用户输入的字符串不包含 '<' 字符和空白字符。
 * <a href="https://blog.csdn.net/qfc_128220/article/details/134454085" />
 */
public class PasswordInputDetection {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		LinkedList<Character> stack = new LinkedList<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '<') {
				if (stack.isEmpty()) continue;
				stack.removeLast();
			} else {
				stack.addLast(c);
			}
		}

		int upper = 0;
		int lower = 0;
		int number = 0;
		int non_letter_number = 0;

		StringBuilder password = new StringBuilder();
		for (Character c : stack) {
			password.append(c);

			if (c >= 'a' && c <= 'z') {
				lower++;
			} else if (c >= 'A' && c <= 'Z') {
				upper++;
			} else if (c >= '0' && c <= '9') {
				number++;
			} else {
				non_letter_number++;
			}
		}

		if (password.length() >= 8
				&& lower >= 1
				&& upper >= 1
				&& number >= 1
				&& non_letter_number >= 1) {
			password.append(",true");
		} else {
			password.append(",false");
		}

		System.out.println(password);
	}
}
