import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法:给出数字个数n(0<n≤999)和行数m(0<m≤999)，从左上角的1开始，按照顺时针螺旋向内写方式，依次写出2,3.....,n，最终形成一个 m 行矩阵。
 * 小明对这个矩阵有些要求:
 * 1.每行数字的个数一样多
 * 2.列的数量尽可能少
 * 3.填充数字时优先填充外部
 * 4.数字不够时，使用单个*号占位
 * 输入描述
 * 两个整数，空格隔开，依次表示 n、m
 * 输出描述
 * 符合要求的唯一矩阵
 * <a href="https://fcqian.blog.csdn.net/article/details/135085069" />
 */

public class SpiralDigitalMatrix {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 需要在螺旋矩阵中填入 1 ~ n 数字
		int n = sc.nextInt();

		// 螺旋矩阵行数
		int m = sc.nextInt();

		// 螺旋矩阵列数
		int k = (int) Math.ceil(n * 1.0 / m);

		// 螺旋矩阵
		int[][] matrix = new int[m][k]; // 由于需要填入1~n数字，因此这里未填值的位置值默认初始化为0

		// 当前要填入的值
		int step = 1;

		// 当前要填入的值的位置
		int x = 0;
		int y = 0;

		// 如果填入的值 > n，则停止填值，否则继续填
		while (step <= n) {
			// 正序填入第x行，即：行号不变，列号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
			while (y < k && matrix[x][y] == 0 && step <= n) matrix[x][y++] = step++;
			// 正序填完第x行后，y处于末尾越界位置，因此y需要退一步
			y -= 1;
			// 正序填完第x行来到第x行的末尾，即第y列，按照螺旋矩阵顺序，应该从第x+1行开始正序填值第y列
			x += 1;

			// 正序填入第y列，即：列号不变，行号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
			while (x < m && matrix[x][y] == 0 && step <= n) matrix[x++][y] = step++;
			x -= 1;
			y -= 1;

			// 倒序填入第x行，即：行号不变，列号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
			while (y >= 0 && matrix[x][y] == 0 && step <= n) matrix[x][y--] = step++;
			y += 1;
			x -= 1;

			// 倒序填入第y列，即：列号不变，行号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
			while (x >= 0 && matrix[x][y] == 0 && step <= n) matrix[x--][y] = step++;
			x += 1;
			y += 1;
		}

		// 打印螺旋矩阵字符串
		for (int i = 0; i < m; i++) {
			StringJoiner row = new StringJoiner(" ");
			for (int j = 0; j < k; j++) {
				if (matrix[i][j] == 0) {
					row.add("*");
				} else {
					row.add(matrix[i][j] + "");
				}
			}
			System.out.println(row);
		}
	}
}
