import java.util.Scanner;

/**
 * 题目描述
 * 给定一个包含 0 和 1 的二维矩阵。
 *
 * 给定一个初始位置和速度，一个物体从给定的初始位置出发，在给定的速度下进行移动，遇到矩阵的边缘则发生镜面发射。
 *
 * 无论物体经过 0 还是 1，都不影响其速度。
 *
 * 请计算并给出经过 t 时间单位后，物体经过 1 点的次数。
 *
 * 矩阵以左上角位置为 [0, 0]（列(x)，行(y)），例如下面A点坐标为 [2, 1]（第二列，第一行）
 *
 * image
 *
 * 注意：
 *
 * 如果初始位置的点是 1，也计算在内
 * 时间的最小单位为 1，不考虑小于 1 个时间单位内经过的点
 * 输入描述
 * 第一行为初始信息
 *
 * <w><h><x><y><sx><sy><t>
 *
 * 第二行开始一共 h 行，为二维矩阵信息
 *
 * 其中：
 *
 * w，h 为矩阵的宽和高
 * x，y 为起始位置
 * sx，sy 为初始速度
 * t 为经过的时间
 * 所有输入都是有效的，数据范围如下：
 *
 * 0 < w < 100
 * 0 < h < 100
 * 0 ≤ x < w
 * 0 ≤ y < h
 * -1 ≤ sx ≤ 1
 * -1 ≤ sy ≤ 1
 * 0 ≤ t ＜100
 * 输出描述
 * 经过 1 的个数
 *
 * 注意初始位置也要计算在内
 *
 * 用例1
 * 输入
 * 12 7 2 1 1 -1 13
 * 001000010000
 * 001000010000
 * 001000010000
 * 001000010000
 * 001000010000
 * 001000010000
 * 001000010000
 * 输出
 * 3
 * 说明
 * 初始位置为（2,1），速度为（1,-1），那么13个时间单位后，经过点1的个数为3
 */
public class ReflectionCounting {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] s = sc.nextLine().split(" ");
		int[] aa = new int[s.length];
		for (int i = 0; i < aa.length; i++) {
			aa[i] = Integer.parseInt(s[i]);
		}
		int w = aa[0];
		int h = aa[1];
		int x = aa[2];
		int y = aa[3];
		int sx = aa[4];
		int sy = aa[5];
		int t = aa[6];

		int[][] range = new int[h][w];
		// 构建二维数组
		for (int i = 0; i < h; i++) {
			String line = sc.nextLine();
			char[] chars = line.toCharArray();
			for (int j = 0; j < chars.length; j++) {
				range[i][j] = chars[j] == '1' ? 1 : 0;
			}
		}

		int total = range[y][x];

		for (int i = 0; i < t; i++) {
			if (x + sx > w -1 || x + sx < 0) {
				sx = -sx;
			}

			if (y + sy > h -1 || y + sy < 0) {
				sy = -sy;
			}

			x = x + sx;
			y = y + sy;
			total = total + range[y][x];
		}

		System.out.println(total);

	}
}
