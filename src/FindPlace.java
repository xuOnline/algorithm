import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 小朋友出操，按学号从小到大排成一列；
 * <p>
 * 小明来迟了，请你给小明出个主意，让他尽快找到他应该排的位置。
 * <p>
 * 算法复杂度要求不高于nLog(n)；学号为整数类型，队列规模 ≤ 10000；
 * <p>
 * 输入描述
 * 第一行：输入已排成队列的小朋友的学号（正整数），以空格隔开，例如：
 * <p>
 * 93 95 97 100 102 123 155
 * <p>
 * 第二行：小明学号，如：
 * <p>
 * 110
 */
public class FindPlace {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String allPerson = sc.nextLine();
		int height = sc.nextInt();
		// 将字符串转成整形数组
		String[] heightStringArray = allPerson.split(" ");
		int[] heightArray = new int[heightStringArray.length];
		for (int i = 0; i < heightStringArray.length; i++) {
			String s = heightStringArray[i];
			heightArray[i] = Integer.parseInt(s);
		}
		int start = 0;
		int end = heightArray.length;
		// 二分法搜索，注意边界值是起点小于等于终点
		while (start <= end) {
			int mid = (start + end) / 2;
			if (height < heightArray[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(start + 1);
	}
}
