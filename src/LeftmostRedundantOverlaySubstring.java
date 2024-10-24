import java.util.HashMap;
import java.util.Scanner;

/**
 * 题目描述
 * 给定两个字符串 s1 和 s2 和正整数 k，其中 s1 长度为 n1，s2 长度为 n2。
 * <p>
 * 在 s2 中选一个子串，若满足下面条件，则称 s2 以长度 k 冗余覆盖 s1
 * <p>
 * 该子串长度为 n1 + k
 * <p>
 * 该子串中包含 s1 中全部字母
 * <p>
 * 该子串每个字母出现次数不小于 s1 中对应的字母
 * <p>
 * 给定 s1，s2，k，求最左侧的 s2 以长度 k 冗余覆盖 s1 的子串的首个元素的下标，如果没有返回-1。
 * <p>
 * 举例：
 * <p>
 * s1 = "ab"
 * <p>
 * s2 = "aabcd"
 * <p>
 * k = 1
 * <p>
 * 则子串 “aab” 和 "abc" 均满足此条件，由于 "aab" 在 "abc" 的左侧，"aab" 的第一个元素下部为 0，因此输出 0
 * <p>
 * 输入描述
 * 输入三行，第一行为 s1，第二行为 s2，第三行为 k
 * <p>
 * s1 和 s2 只包含小写字母
 * 输出描述
 * 最左侧的 s2 以长度 k 冗余覆盖 s1 的子串首个元素下标，如果没有返回 -1。
 * <p>
 * 备注
 * 0 ≤ len(s1) ≤ 1000000
 * 0 ≤ len(s2) ≤ 20000000
 * 0 ≤ k ≤ 1000
 */
public class LeftmostRedundantOverlaySubstring {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		int k = Integer.parseInt(sc.nextLine());
		int length = k + s1.length();

		HashMap<Character, Integer> s1Map = new HashMap<>();
		HashMap<Character, Integer> s2Map = new HashMap<>();
		int allMatch = 0;
		int start = 0;
		int end = 0;
		char[] chars = s1.toCharArray();
		for (char aChar : chars) {
			s1Map.put(aChar, s1Map.getOrDefault(aChar, 0) + 1);
		}
		int result = -1;
		while (end < s2.length()) {
			char cur = s2.charAt(end);
			s2Map.put(cur, s2Map.getOrDefault(cur, 0) + 1);
			if (s2Map.get(cur).equals(s1Map.get(cur))) {
				allMatch++;
			}
			while (end - start >= length) {
				char str = s2.charAt(start);
				if (s2Map.get(str).equals(s1Map.get(str))) {
					allMatch--;
				}
				s2Map.put(str, s2Map.get(str) - 1);
				start++;
			}

			if (allMatch == s1Map.size()) {
				result = start;
				break;
			}

			end++;
		}
		System.out.println(result);
	}
}
