import java.util.Scanner;

/**
 *题目描述
 * "吃货"和"馋嘴"两人到披萨店点了一份铁盘（圆形）披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。但是粗心的服务员将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
 *
 * 由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法：从"吃货"开始，轮流取披萨。除了第一块披萨可以任意选取外，其他都必须从缺口开始选。
 *
 * 他俩选披萨的思路不同。"馋嘴"每次都会选最大块的披萨，而且"吃货"知道"馋嘴"的想法。
 *
 * 已知披萨小块的数量以及每块的大小，求"吃货"能分得的最大的披萨大小的总和。
 *
 * 输入描述
 * 第 1 行为一个正整数奇数 N，表示披萨小块数量。
 *
 * 3 ≤ N < 500
 * 接下来的第 2 行到第 N + 1 行（共 N 行），每行为一个正整数，表示第 i 块披萨的大小
 *
 * 1 ≤ i ≤ N
 * 披萨小块从某一块开始，按照一个方向次序顺序编号为 1 ~ N
 *
 * 每块披萨的大小范围为 [1, 2147483647]
 * 输出描述
 * "吃货"能分得到的最大的披萨大小的总和
 */
// 0 1 2 3 4 5 
public class AllocationPizza {
	static long[][] cache;
	static long max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int total = Integer.parseInt(sc.nextLine());
		int[] pizza = new int[total];
		for (int i = 0; i < total; i++) {
			pizza[i] = Integer.parseInt(sc.nextLine());
		}
		cache = new long[total][total];
		max = 0;
		for (int i = 0; i < total; i++) {
			max = Math.max(max, dfs(i - 1, i + 1, pizza) + pizza[i]);
		}

		System.out.println(max);
	}

	public static long dfs(int l, int r, int[] pizza) {
		long result;
		l = checkIndex(l, pizza);
		r = checkIndex(r, pizza);
		if (pizza[l] > pizza[r]) {
			l = l - 1;
		} else {
			r = r + 1;
		}
		l = checkIndex(l, pizza);
		r = checkIndex(r, pizza);
		if (cache[l][r] > 0) {
			return cache[l][r];
		}
		if (l == r) {
			result = pizza[l];
			cache[l][r] = result;
		} else {
			result = Math.max(dfs(l - 1, r, pizza) + pizza[l], dfs(l, r + 1, pizza) + pizza[r]);
			cache[l][r] = result;
		}
		return result;
	}

	public static int checkIndex(int index, int[] pizza) {
		int length = pizza.length;
		if (index < 0) {
			index = length - 1;
		}
		if (index >= length) {
			index = 0;
		}

		return index;
	}


}


