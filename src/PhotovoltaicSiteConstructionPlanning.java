import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 祖国西北部有一片大片荒地，其中零星的分布着一些湖泊，保护区，矿区；
 * <p>
 * 整体上常年光照良好，但是也有一些地区光照不太好。
 * <p>
 * 某电力公司希望在这里建设多个光伏电站，生产清洁能源对每平方公里的土地进行了发电评估，其中不能建设的区域发电量为 0 kw，可以发电的区域根据光照，地形等给出了每平方公里年发电量 x 千瓦。
 * <p>
 * 我们希望能够找到其中集中的矩形区域建设电站，能够获得良好的收益。
 * <p>
 * 输入描述
 * 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长，最低要求的发电量。
 * <p>
 * 之后每行为调研区域每平方公里的发电量。
 * <p>
 * 例如，输入为：
 * <p>
 * 2 5 2 6
 * 1 3 4 5 8
 * 2 3 6 7 1
 * <p>
 * 表示调研的区域大小为长 2 宽 5 的矩形，我们要建设的电站的边长为 2，建设电站最低发电量为 6
 * <p>
 * 输出描述
 * 输出为这样的区域有多少个？
 * <p>
 * 上述输入长度为 2 的正方形满足发电量大于等于 6 的区域有 4 个。则输出为：
 * <p>
 * 4
 */
public class PhotovoltaicSiteConstructionPlanning {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int line = sc.nextInt();
		int row = sc.nextInt();
		int length = sc.nextInt();
		int req = sc.nextInt();
		sc.nextLine();
		int[][] area = new int[line][row];
		for (int i = 0; i < line; i++) {
			String[] ss = sc.nextLine().split(" ");
			for (int j = 0; j < row; j++) {
				int current = Integer.parseInt(ss[j]);
				int pre = 0;
				if (i > 0 && j > 0) {
					pre = area[i - 1][j - 1];
				}
				if (i > 0) {
					current = current + area[i - 1][j];
				}
				if (j > 0) {
					current = current + area[i][j - 1];
				}
				current = current - pre;
				area[i][j] = current;
			}
		}
		int total = 0;
		for (int i = length - 1; i < line; i++) {
			for (int j = length - 1; j < row; j++) {
				if (area[i][j] >= req) {
					total++;
				}
			}
		}
		System.out.println(total);
	}
}