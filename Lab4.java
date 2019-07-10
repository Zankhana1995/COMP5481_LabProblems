import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//4 2 6 3 5 7 1 -1
//7 5 8 3 12 23 9 27 55 33 2 -1
//5 2 12 1 3 9 17 15 19 -1
class Node4 {
	int data;
	Node4 left;
	Node4 right;

	Node4(int data) {
		this.data = data;
		left = right = null;
	}
}

public class Lab4 {
	Node4 root;
	static StringBuilder sb = new StringBuilder();
	static boolean flag = false;

	public static void main(String[] args) {
		Lab4 tree = new Lab4();
		Scanner sc = new Scanner(System.in);

		List<Integer> list = new ArrayList<>();
		String str = sc.nextLine();
		String[] arr = str.split("\\s+");
		for (int i = 0; i < arr.length; i++) {
			list.add(Integer.parseInt(arr[i]));
		}
		tree.root = new Node4(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			if (!(list.get(i) == -1)) {
				addIntoTree(tree.root, list.get(i));
			} else {
				break;
			}
		}
		checkHeight(tree.root);
		if (flag == true) {
			printPreOrder(tree.root);
			System.out.print(sb.toString().trim());
		} else {
			System.out.print("NOT");
		}
		sc.close();
	}

	private static void checkHeight(Node4 root) {
		if(root==null)
		{
			return;
		}
		int leftH = checkLeftHeight(root);
		int rightH = checkRightHeight(root);
		if (leftH - rightH == 0 || leftH - rightH == 1 || leftH - rightH == -1) {
			flag = true;
		} else {
			flag = false;
			return;
		}
		checkHeight(root.left);
		checkHeight(root.right);

	}

	private static int checkRightHeight(Node4 root) {
		int rightheight = 1;
		if (root == null) {
			return 0;
		}
		rightheight += checkRightHeight(root.right);
		return rightheight;
	}

	private static int checkLeftHeight(Node4 root) {
		int leftheight = 1;

		if (root == null) {
			return 0;
		}
		leftheight += checkLeftHeight(root.left);
		return leftheight;
	}

	private static void printPreOrder(Node4 root) {
		if (root == null) {
			return;
		}
		sb.append(root.data + " ");
		// System.out.print(root.data + " ");
		printPreOrder(root.left);
		printPreOrder(root.right);
	}

	private static void addIntoTree(Node4 root, int element) {
		if (element == -1) {
			return;
		} else {
			Node4 newNode = new Node4(element);
			if (root.left == null) {
				if (root.data > element) {
					root.left = newNode;
				}
			}
			if (root.right == null) {
				if (root.data < element) {
					root.right = newNode;
				}
			}
			if (root.data > element) {
				addIntoTree(root.left, element);

			} else if (root.data < element) {
				addIntoTree(root.right, element);

			}
		}
	}
}
