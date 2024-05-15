import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 提取字符串中的最长合法简单数学表达式，字符串长度最长的，并计算表达式的值。如果没有，则返回 0 。
 * <p>
 * 简单数学表达式只能包含以下内容：
 * <p>
 * 0-9数字，符号+-*
 * 说明：
 * <p>
 * 所有数字，计算结果都不超过long
 * 如果有多个长度一样的，请返回第一个表达式的结果
 * 数学表达式，必须是最长的，合法的
 * 操作符不能连续出现，如 +--+1 是不合法的
 * 输入描述
 * 字符串
 * <p>
 * 输出描述
 * 表达式值
 * <p>
 * 用例1
 * 输入
 * 1-2abcd
 * 输出
 * -1
 */
public class SimpleMathematicalExpression {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String aa = sc.nextLine();
		char[] aaChar = aa.toCharArray();
		int length = aaChar.length;
		int start = 0;
		int end = 0;
		List<String> result = new ArrayList<>();
		List<Character> set = new ArrayList<>();
		set.add('+');
		set.add('-');
		set.add('*');
		while (end < length && start < length) {
			
			// 判断缩小窗口
			if ((set.contains(aaChar[end]) && (end + 1 >= length || set.contains(aaChar[end + 1])))) {
				result.add(aa.substring(start, end));
				start = end + 2;
			}
			// 扩大窗口
			end++;
		}

		System.out.println(result);
	}
}
