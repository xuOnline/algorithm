import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 幼儿园组织活动，老师布置了一个任务：
 *
 * 每个小朋友去了解与自己同一个小区的小朋友还有几个。
 *
 * 我们将这些数量汇总到数组 garden 中。
 *
 * 请根据这些小朋友给出的信息，计算班级小朋友至少来自几个小区？
 *
 * 输入描述
 * 输入：garden[] = {2, 2, 3}
 *
 * 输出描述
 * 输出：7
 *
 * 备注
 * garden 数组长度最大为 999
 * 每个小区的小朋友数量最多 1000 人，也就是 garden[i] 的范围为 [0, 999]
 * 用例1
 * 输入
 * 2 2 3
 * 输出
 * 7
 * 说明
 * 第一个小朋友反馈有两个小朋友和自己同一小区，即此小区有3个小朋友。
 *
 * 第二个小朋友反馈有两个小朋友和自己同一小区，即此小区有3个小朋友。
 *
 * 这两个小朋友，可能是同一小区的，且此小区的小朋友只有3个人。
 *
 * 第三个小区反馈还有3个小朋友与自己同一小区，则这些小朋友只能是另外一个小区的。这个小区有4个小朋友。
 */
public class ChildrenComeFrom {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] numArray = sc.nextLine().split(" ");
		int[] nums = new int[numArray.length];
		for (int i = 0; i < numArray.length; i++) {
			nums[i] = Integer.parseInt(numArray[i]);
		}
		// 先对数组进行排序
		Arrays.sort(nums);
		int start = 0;
		int end = 0;
		int total = 0;
		while (end < nums.length) {
			if (nums[end] != nums[start] || nums[start] < end - start) {
				total = total + nums[start] + 1;
				start = end;
			}
			if (end == nums.length - 1) {
				total = total + nums[start] + 1;
			}
			end++;
		}

		System.out.println(total);
	}
}
