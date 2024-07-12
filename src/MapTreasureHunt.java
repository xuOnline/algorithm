import java.util.Scanner;

/**
 * 题目描述
 * 小华按照地图去寻宝，地图上被划分成 m 行和 n 列的方格，横纵坐标范围分别是 [0, n-1] 和 [0, m-1]。
 * <p>
 * 在横坐标和纵坐标的数位之和不大于 k 的方格中存在黄金（每个方格中仅存在一克黄金），但横坐标和纵坐标之和大于 k 的方格存在危险不可进入。小华从入口 (0,0) 进入，任何时候只能向左，右，上，下四个方向移动一格。
 * <p>
 * 请问小华最多能获得多少克黄金？
 * <p>
 * 输入描述
 * 坐标取值范围如下：
 * <p>
 * 0 ≤ m ≤ 50
 * 0 ≤ n ≤ 50
 * k 的取值范围如下：
 * <p>
 * 0 ≤ k ≤ 100
 * 输入中包含3个字数，分别是m, n, k
 * <p>
 * 输出描述
 * 输出小华最多能获得多少克黄金
 * <p>
 * 用例1
 * 输入
 * 40 40 18
 * 输出
 * 1484
 */
public class MapTreasureHunt {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] split = sc.nextLine().split(" ");
		int line = Integer.parseInt(split[0]);
		int row = Integer.parseInt(split[1]);
		int k = Integer.parseInt(split[1]);
		// 初始化地图
		int[][] map = new int[line][row];
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < line; j++) {
				map[i][j] = i + j;
			}
		}
		int[][] used = new int[line][row];
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < line; j++) {
				used[i][j] = 0;
			}
		}
		// 
	}
}
