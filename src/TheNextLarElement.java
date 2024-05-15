import java.util.Arrays;
import java.util.LinkedList;

/**
 * 题目描述
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 * <p>
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 * <p>
 * 示例
 * 输入: nums = [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * <p>
 * 输入: nums = [1,2,3,4,3]
 * 输出: [2,3,4,-1,4]
 * <p>
 * 提示
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 */
public class TheNextLarElement {
	public static void main(String[] args) {
		int[] nums = new int[]{100, 1, 11, 1, 120, 111, 123, 1, -1, -100};
		int n = nums.length;
		// 结果数组
		int[] nums2 = new int[n];
		// 给结果数组赋初始值 -1
		Arrays.fill(nums2, -1);
		LinkedList<Integer> list = new LinkedList<>();
		for (int j = 0; j < n * 2; j++) {
			// 需比较两次
			int num = nums[j % n];
			while (list.size() > 0) {
				// 比较栈顶元素是否小于下一元素值，若是将下一元素赋值到结果集中，移除该栈顶元素
				int i = list.getLast();
				if (nums[i] < num) {
					list.removeLast();
					nums2[i] = num;
				} else {
					break;
				}
			}
			// 只有第一遍循环才需要压入栈中
			if (j < n) {
				list.add(j);
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.print(nums2[i] + "   ");
		}
	}
}
