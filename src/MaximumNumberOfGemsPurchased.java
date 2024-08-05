import java.util.Scanner;

/**
 * 题目描述
 * 橱窗里有一排宝石，不同的宝石对应不同的价格，宝石的价格标记为 gems[i]
 *
 * 0 ≤ i < n
 * n = gems.length
 * 宝石可同时出售0个或多个，如果同时出售多个，则要求出售的宝石编号连续；
 *
 * 例如客户最大购买宝石个数为m，购买的宝石编号必须为：gems[i]，gems[i+1]，...，gems[i+m-1]
 *
 * 0 ≤ i < n
 * m ≤ n
 * 假设你当前拥有总面值为 value 的钱，请问最多能购买到多少个宝石，如无法购买宝石，则返回0。
 *
 * 输入描述
 * 第一行输入n，参数类型为int，取值范围：[0,10^6]，表示橱窗中宝石的总数量。
 *
 * 之后 n 行分别表示从第0个到第n-1个宝石的价格，即 gems[0] 到 gems[n-1] 的价格，类型为int，取值范围：(0,1000]。
 *
 * 之后一行输入v，类型为int，取值范围：[0,10^9]，表示你拥有的钱。
 *
 * 输出描述
 * 输出int类型的返回值，表示最大可购买的宝石数量。
 *
 * 用例1
 * 输入
 * 7
 * 8
 * 4
 * 6
 * 3
 * 1
 * 6
 * 7
 * 10
 * 输出
 * 3
 * 说明
 * gems = [8,4,6,3,1,6,7], value = 10
 *
 * 最多购买的宝石为gems[2]至gems[4]或者gems[3]至gems[5]
 *
 * 用例2
 * 输入
 * 0
 * 1
 * 输出
 * 0
 * 说明
 * gems = [], value = 1
 *
 * 因为没有宝石，所以返回0
 *
 * 用例3
 * 输入
 * 9
 * 6
 * 1
 * 3
 * 1
 * 8
 * 9
 * 3
 * 2
 * 4
 * 15
 * 输出
 * 4
 * 说明
 * gems = [6, 1, 3, 1, 8, 9, 3, 2, 4], value = 15
 *
 * 最多购买的宝石为gems[0]至gems[3]
 *
 * 用例4
 * 输入
 * 9
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 10
 * 输出
 * 9
 * 说明
 * gems = [1, 1, 1, 1, 1, 1, 1, 1, 1], value = 10
 *
 * 最多购买的宝石为gems[0]至gems[8]，即全部购买
 */
public class MaximumNumberOfGemsPurchased {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 宝石数量
		int quantity = sc.nextInt();
		int[] gems = new int[quantity];
		for (int i = 0; i < quantity; i++) {
			gems[i] = sc.nextInt();
		}
		// 拥有的钱
		int amount = sc.nextInt();
		// 记录最大数量
		int maxQuantity = 0;
		int start = 0;
		int end = 0;
		int currentAmount = 0;
		while (end < quantity) {
			// 当前金额加宝石金额
			currentAmount = currentAmount + gems[end];
			// 当前金额大于拥有的钱时，缩小窗口
			while (currentAmount > amount && start <= end) {
				currentAmount = currentAmount - gems[start];
				start++;
			}
			// 更新最大数量
			maxQuantity = Math.max(maxQuantity, (end - start + 1));
			end++;
		}

		System.out.print(maxQuantity);
	}
}
