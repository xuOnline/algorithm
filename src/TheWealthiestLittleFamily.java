import java.util.Arrays;
import java.util.Scanner;

/**
 * 在一颗树中，每个节点代表一个家庭成员，节点的数字表示只其个人的财富值，一个节点及其直接相连的子节点被定义为一个小家庭。
 * 现给你一颗树，请计算出最富裕的小家庭的财富和。
 * 输入描述
 * 第一行为一个数 N，表示成员总数，成员编号 1~N。1≤N≤1000第二行为 N 个空格分隔的数，
 * 表示编号 1~N 的成员的财富值。0≤财富值≤1000000接下来 N-1 行，每行两个空格分隔的整数(N1,N2)，表示 N1 是 N2 的 父节点Q
 *
 * <a href="https://blog.csdn.net/qfc_128220/article/details/134383453" />
 */
public class TheWealthiestLittleFamily {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		// 这里wealth长度定义为n+1，是为了让wealth数组索引对应成员编号 1~N
		long[] wealth = new long[n + 1];
		long[] family = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			wealth[i] = sc.nextInt();
			family[i] = wealth[i];
		}

		for (int i = 0; i < n - 1; i++) {
			int fa = sc.nextInt();
			int ch = sc.nextInt();
			family[fa] += wealth[ch];
		}

		System.out.println(Arrays.stream(family).max().orElse(0));
	}
}
