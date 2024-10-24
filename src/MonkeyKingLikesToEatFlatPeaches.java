import java.util.Arrays;
import java.util.Scanner;


/**
 * 题目描述
 * 孙悟空爱吃蟠桃，有一天趁着蟠桃园守卫不在来偷吃。已知蟠桃园有 N 棵桃树，每颗树上都有桃子，守卫将在 H 小时后回来。
 * <p>
 * 孙悟空可以决定他吃蟠桃的速度K（个/小时），每个小时选一颗桃树，并从树上吃掉 K 个，如果树上的桃子少于 K 个，则全部吃掉，并且这一小时剩余的时间里不再吃桃。
 * <p>
 * 孙悟空喜欢慢慢吃，但又想在守卫回来前吃完桃子。
 * <p>
 * 请返回孙悟空可以在 H 小时内吃掉所有桃子的最小速度 K（K为整数）。如果以任何速度都吃不完所有桃子，则返回0。
 * <p>
 * 输入描述
 * 第一行输入为 N 个数字，N 表示桃树的数量，这 N 个数字表示每颗桃树上蟠桃的数量。
 * <p>
 * 第二行输入为一个数字，表示守卫离开的时间 H。
 * <p>
 * 其中数字通过空格分割，N、H为正整数，每颗树上都有蟠桃，且 0 < N < 10000，0 < H < 10000。
 * <p>
 * 输出描述
 * 吃掉所有蟠桃的最小速度 K，无解或输入异常时输出 0。
 */
public class MonkeyKingLikesToEatFlatPeaches {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] split = sc.nextLine().split(" ");
		int hour = Integer.parseInt(sc.nextLine());

		int[] peach = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			Integer cur = Integer.parseInt(split[i]);
			peach[i] = cur;
		}

		System.out.println(getResult(hour, peach));
	}

	private static int getResult(int hour, int[] peach) {
		if (peach.length > hour) {
			return 0;
		}

		int max = Arrays.stream(peach).max().orElse(0);
		if (peach.length == hour) {
			return max;
		}
		int min = 1;
		int ans = max;
		while (min <= max) {
			int mid = (max + min) / 2;
			if (consume(mid, hour, peach)) {
				ans = mid;
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}


		return ans;
	}

	public static boolean consume(int speed, int hour, int[] peach) {

		int curHour = 0;
		for (int i = 0; i < peach.length; i++) {
			int cur = peach[i];
			if (cur % speed == 0) {
				curHour = curHour + cur / speed;
			} else {
				curHour = curHour + cur / speed + 1;
			}

			if (curHour > hour) {
				return false;
			}
		}

		return true;
	}

}
