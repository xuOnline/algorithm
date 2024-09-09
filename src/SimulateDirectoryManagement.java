import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *题目描述
 * 实现一个模拟目录管理功能的软件，输入一个命令序列，输出最后一条命令运行结果。
 *
 * 支持命令：
 *
 * 创建目录命令：mkdir 目录名称，如 mkdir abc 为在当前目录创建abc目录，如果已存在同名目录则不执行任何操作。此命令无输出。
 * 进入目录命令：cd 目录名称，如 cd abc 为进入abc目录，特别地，cd .. 为返回上级目录，如果目录不存在则不执行任何操作。此命令无输出。
 * 查看当前所在路径命令：pwd，输出当前路径字符串。
 * 约束：
 *
 * 目录名称仅支持小写字母；mkdir 和 cd 命令的参数仅支持单个目录，如：mkdir abc 和 cd abc；不支持嵌套路径和绝对路径，如 mkdir abc/efg，cd abc/efg，mkdir /abc/efg，cd /abc/efg 是不支持的。
 * 目录符号为/，根目录/作为初始目录。
 * 任何不符合上述定义的无效命令不做任何处理并且无输出。
 * 输入描述
 * 输入 N 行字符串，每一行字符串是一条命令。
 *
 * 输出描述
 * 输出最后一条命令运行结果字符串。
 *
 * 备注
 * 命令行数限制100行以内，目录名称限制10个字符以内。
 *
 * 用例1
 * 输入
 * mkdir abc
 * cd abc
 * pwd
 * 输出
 * /abc/
 * 说明
 * 在根目录创建一个abc的目录并进入abc目录中查看当前目录路径，输出当前路径/abc/。
 */
public class SimulateDirectoryManagement {
	public static class Node {

		String path;

		Node pre;

		List<Node> next;


		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public Node getPre() {
			return pre;
		}

		public void setPre(Node pre) {
			this.pre = pre;
		}

		public List<Node> getNext() {
			return next;
		}

		public void setNext(List<Node> next) {
			this.next = next;
		}

		public Node(String path, Node pre, List<Node> next) {
			this.path = path;
			this.pre = pre;
			this.next = next;
		}


	}


	public static void main(String[] args) {
		Node root = new Node("/", null, null);
		Scanner sc = new Scanner(System.in);
		String last = null;
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.startsWith("mkdir")) {
				Node next = new Node(s.split(" ")[1] + "/", root, null);
				if (root.getNext() != null) {
					root.getNext().add(next);
				} else {
					List<Node> nodeList = new ArrayList<>();
					nodeList.add(next);
					root.setNext(nodeList);
				}
			}
			if (s.startsWith("cd") && !s.contains("/")) {
				if (s.split(" ")[1].equals("..")) {
					if (root.pre != null) {
						root = root.pre;
					}
				} else {
					String path =  s.split(" ")[1] + "/" ;
					List<Node> nodeList = root.getNext();
					if (nodeList != null) {
						for (Node node : nodeList) {

							if (path.equals(node.path)) {
								root = node;
							}
						}
					}
				}
			}

			last = s;
		}

		if (last.startsWith("pwd")) {
			String path = root.path;
			while (root.pre != null) {
				root = root.pre;
				path = root.path + path;
			}
			System.out.println(path);
		}
	}
}
