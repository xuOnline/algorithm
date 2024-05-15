import java.time.temporal.Temporal;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * xx
 *
 * @author xu 2024-05-15 14:40
 */
public class SymbolicOperation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ss = sc.nextLine();
		// 数字栈
		LinkedList<Integer> numStack = new LinkedList<Integer>();
		// 操作符栈
		LinkedList<Character> operationStack = new LinkedList<Character>();
		char[] ssChar = ss.toCharArray();

		StringBuilder bu = new StringBuilder();
		for (int i = 0; i < ssChar.length; i++) {
			char c = ssChar[i];
			if (c >= '0' && c <= '9') {
				// 一次性把数字遍历完
				while (c >= '0' && c <= '9') {
					bu.append(c);
					i++;
					if (i >= ssChar.length) {
						break;
					}
					c = ssChar[i];
				}
				numStack.addLast(Integer.parseInt(bu.toString()));
				bu = new StringBuilder();
			}

			if (c == '(') {
				operationStack.addLast(c);
			}
			// 有括号时，优先计算括号里面的内推
			if (c == ')') {
				while (operationStack.getLast() != '(') {
					operation(numStack, operationStack);
				}
				operationStack.removeLast();
			}
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				// 乘法与除法的优先级高需要先运算
				if (operationStack.size() > 0 && (operationStack.getLast() == '*' || operationStack.getLast() == '/')) {
					operation(numStack, operationStack);
				}
				operationStack.addLast(c);
			}
		}


		while (numStack.size() != 1) {
			operation(numStack, operationStack);
		}

		System.out.print(numStack.getLast());
	}

	public static void operation(LinkedList<Integer> numStack, LinkedList<Character> operationStack) {
		Integer num1 = numStack.removeLast();
		Integer num2 = numStack.removeLast();
		Character operate = operationStack.removeLast();
		Integer result;
		switch (operate) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				result = num1 / num2;
				break;
			default:
				result = 0;
		}
		numStack.addLast(result);
	}

}
