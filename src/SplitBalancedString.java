import java.util.*;

/**
 * 题目描述
 * 均衡串定义：字符串中只包含两种字符，且这两种字符的个数相同。
 * <p>
 * 给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
 * <p>
 * 约定：字符串中只包含大写的 X 和 Y 两种字符。
 * <p>
 * 输入描述
 * 输入一个均衡串。
 * <p>
 * 字符串的长度：[2， 10000]。
 * 给定的字符串均为均衡字符串
 * 输出描述
 * 输出可分割成新的均衡子串的最大个数。
 * <p>
 * 备注
 * 分割后的子串，是原字符串的连续子串
 * <p>
 * 用例1
 * 输入
 * XXYYXY
 * 输出
 * 2
 * 说明
 * XXYYXY可分割为2个均衡子串，分别为：XXYY、XY
 */
public class SplitBalancedString {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < line.length(); i++) {
			for (int j = i + 1; j <= line.length(); j++) {
				// 获取字串
				String substring = line.substring(i, j);
				if(balance(substring)){
					result.add(substring);
					i = j;
				}
			}
		}

		System.out.println(result.size());
	}

	public static boolean balance(String s) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ('X' == c) {
				x++;
			} else {
				y++;
			}
		}
		return x == y;
	}

}
/**
 * 标准答案
 * import java.util.Scanner;
 *
 * public class Main {
 *   public static void main(String[] args) {
 *     Scanner sc = new Scanner(System.in);
 *
 *     String s = sc.nextLine();
 *
 *     int countX = 0;
 *     int countY = 0;
 *
 *     int ans = 0;
 *
 *     for (int i = 0; i < s.length(); i++) {
 *       if (s.charAt(i) == 'X') {
 *         countX++;
 *       } else {
 *         countY++;
 *       }
 *
 *       if (countX == countY) {
 *         ans++;
 *       }
 *     }
 *
 *     System.out.println(ans);
 *   }
 * }
 */