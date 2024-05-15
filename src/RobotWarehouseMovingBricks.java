import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第 i 堆砖中有 bricks[i] 块砖头，要求在 8 小时内搬完。
 * <p>
 * 机器人每小时能搬砖的数量取决于有多少能量格，机器人一个小时中只能在一个仓库中搬砖，
 * 机器人的能量格只在这一个小时有效，为使得机器人损耗最小化，应尽量减小每次补充的能量格数。
 * <p>
 * 为了保障在 8 小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
 * <p>
 * 无需考虑机器人补充能力格的耗时；
 * 无需考虑机器人搬砖的耗时；
 * 机器人每小时补充能量格只在这一个小时中有效；
 * 输入描述
 * 第一行为一行数字，空格分隔
 * <p>
 * 输出描述
 * 机器人每小时最少需要充的能量格，若无法完成任务，输出 -1
 * <p>
 * 用例1
 * 输入
 * 30 12 25 8 19
 * 输出
 * 15
 * 用例2
 * 输入
 * 10 12 25 8 19 8 6 4 17 19 20 30
 * 输出
 * -1
 * 说明
 * 砖的堆数为12堆存放在12个仓库中，机器人一个小时内只能在一个仓库搬砖，不可能完成任务。
 */
public class RobotWarehouseMovingBricks {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ss = sc.nextLine();
		List<Integer> numbers = Arrays.stream(ss.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
		if (numbers.size() > 8) {
			System.out.print(-1);
		}
		Integer max = numbers.stream().max((x, y) -> x - y).orElse(0);
		if (numbers.size() == 8) {
			System.out.print(max);
		}
		Integer result = 0;
		for (Integer number : numbers) {
			result = result + number;
		}
		// 每小时最少需要的能量块
		Integer min = (result / 8);
		int ans = max;
		// 二分法
		while (min <= max) {
			// 取中间值
			int mid = (min + max) >> 1;

			if (check(mid, numbers)) {
				// 如果每小时充mid格能量，就能在8小时内，搬完所有砖头，则mid就是一个可能解
				ans = mid;
				// 但mid不一定是最优解，因此继续尝试更小的能量块
				max = mid - 1;
			} else {
				// 如果每小时充mid能量块，无法在8小时能完成工作，则说明每天能量块充少了，下次应该尝试充更多能量块
				min = mid + 1;
			}
		}
		System.out.println(ans);
	}

	public static boolean check(int enery, List<Integer> numbers) {
		int total = 0;
		for (Integer num : numbers) {
			total = total + num / enery + (num % enery == 0 ? 0 : 1);
			if (total > 8) {
				return false;
			}
		}
		return true;
	}

}
