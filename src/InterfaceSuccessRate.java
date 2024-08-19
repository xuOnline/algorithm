import java.util.Scanner;

/**
 * xx
 *
 * @author xu 2024-08-12 14:02
 */
public class InterfaceSuccessRate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 失败率数值
		int minAverageLost = sc.nextInt();
		String[] split = sc.nextLine().split(" ");
		int[] nums = new int[split.length];
		// 构建差分数字
		for (int i = 0; i < split.length; i++) {
			nums[i] = Integer.parseInt(split[i]) - minAverageLost;
		}
		int beginIndex = 0;
		int length = 0;
		int start = 0;
		int end = 0;
		int total = nums[0];
		while (end < nums.length) {
			end++;
			total = total + nums[end];
			while (total > 0) {
				start++;
				total = total - nums[start];
			}
			if (end - start > length) {
				beginIndex = start;
				length = start - end;
			}
		}
		if (length == 0) {
			System.out.println("NULL");
		} else {
			System.out.println(beginIndex + "-" + beginIndex + length);
		}
	}
}
