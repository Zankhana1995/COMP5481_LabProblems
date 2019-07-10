import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Node7 {
	String data;
	Node7 left, right;

	public Node7(String data) {
		this.data = data;
		left = right = null;
	}
}

public class Lab7 {
	Node7 root;
	public static StringBuilder preOrderString = new StringBuilder();
	Map<String, ArrayList<String>> map = new HashMap<>();
	StringBuilder sb = new StringBuilder();
	public static ArrayList<String> allnodes = new ArrayList<>();

	public static void main(String[] args) {
		Lab7 lab7 = new Lab7();
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		ArrayList<String> dataset = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			dataset.add(sc.nextLine());
		}

		int m = Integer.parseInt(sc.nextLine());
		List<String> relations = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			relations.add(sc.nextLine());
		}
		String[] rootdata = dataset.get(0).split(" ");
		lab7.root = new Node7(rootdata[0]);
		allnodes.add(lab7.root.data);
		lab7.addToMap(dataset, lab7.map);
		lab7.makeTree(lab7.root, lab7.map);
		for (String str : relations) {
			String[] datass = str.split("\\s+");
			if (datass[1].equals("child")) {
				boolean res = lab7.checkChild(datass, lab7.map);
				if (res == true)
					lab7.sb.append("T ");
				else
					lab7.sb.append("F ");
			} else if (datass[1].equals("parent")) {
				boolean res = lab7.checkParent(datass, lab7.map);
				if (res == true)
					lab7.sb.append("T ");
				else
					lab7.sb.append("F ");
			} else if (datass[1].equals("sibling")) {
				boolean res = lab7.checkSibling(datass, lab7.map);
				if (res == true)
					lab7.sb.append("T ");
				else
					lab7.sb.append("F ");
			} else if (datass[1].equals("descendant")) {
				String target = datass[0];
				String ancestor = datass[2];
				ArrayList<String> childList = new ArrayList<>();
				lab7.checkDec(lab7.root, ancestor, childList);
				if (childList.contains(target))
					lab7.sb.append("T ");
				else
					lab7.sb.append("F ");
			} else if (datass[1].equals("ancestor")) {
				String target = datass[2];
				String ancestor = datass[0];
				ArrayList<String> childList = new ArrayList<>();
				lab7.checkDec(lab7.root, ancestor, childList);
				if (childList.contains(target))
					lab7.sb.append("T ");
				else
					lab7.sb.append("F ");
			}
		}
		System.out.println(lab7.sb.toString().trim());
		lab7.preOrder(lab7.root);
		System.out.println(preOrderString.toString().trim());
		sc.close();
	}

	private void checkDec(Node7 root, String ancestor, ArrayList<String> childList) {

		if (root == null) {
			return;
		}
		if (root.data.equals(ancestor)) {
			checkdecedant(root, childList);
			return;
		}
		checkDec(root.left, ancestor, childList);
		checkDec(root.right, ancestor, childList);
	}

	private void checkdecedant(Node7 root, ArrayList<String> childList) {
		if (root == null) {
			return;
		}
		childList.add(root.data);
		checkdecedant(root.left, childList);
		checkdecedant(root.right, childList);
	}
	//
	// private boolean checkancestor(String target, String ancestor, Node7 root,
	// ArrayList<String> ancestorList) {
	//
	// if (root == null) {
	// return false;
	// }
	// if (root.data.equals(target)) {
	// // ancestorList.add(ancestor);
	// return true;
	// } else if (checkancestor(target, ancestor, root.left, ancestorList)) {
	// ancestorList.add(root.data);
	// } else if (checkancestor(target, ancestor, root.right, ancestorList)) {
	// ancestorList.add(root.data);
	// }
	// if (ancestorList.contains(ancestor)) {
	// return true;
	// } else
	// return false;
	// }

	private boolean checkSibling(String[] datass, Map<String, ArrayList<String>> map) {
		ArrayList<String> siblings = new ArrayList<>();
		siblings.add(datass[0]);
		siblings.add(datass[2]);
		Collections.sort(siblings);
		boolean flag = false;
		for (ArrayList<String> arrays : map.values()) {
			if (arrays.size() == 2) {
				Collections.sort(arrays);
				if (arrays.equals(siblings)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	private boolean checkParent(String[] datass, Map<String, ArrayList<String>> map) {
		if (!map.containsKey(datass[0])) {
			return false;
		}
		ArrayList<String> child = map.get(datass[0]);
		if (child.contains(datass[2])) {
			return true;
		} else
			return false;

	}

	private boolean checkChild(String[] datass, Map<String, ArrayList<String>> map) {
		if (!map.containsKey(datass[2])) {
			return false;
		}
		ArrayList<String> child = map.get(datass[2]);
		if (child.contains(datass[0])) {
			return true;
		} else
			return false;

	}

	private void preOrder(Node7 root) {
		if (root == null) {
			return;
		}
		preOrderString.append(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	private void makeTree(Node7 root, Map<String, ArrayList<String>> map) {
		if (root == null) {
			return;
		}
		String rootData = root.data;
		if (map.containsKey(rootData)) {
			ArrayList<String> child = map.get(rootData);
			if (child.size() == 1) {
				root.left = new Node7(child.get(0));
			} else {
				root.left = new Node7(child.get(0));
				root.right = new Node7(child.get(1));
			}
			makeTree(root.left, map);
			makeTree(root.right, map);
		} else {
			return;
		}
	}

	private void addToMap(List<String> dataset, Map<String, ArrayList<String>> map) {
		for (String str : dataset) {
			String[] data = str.split(" ");
			if (allnodes.contains(data[0])) {
				if (map.containsKey(data[0])) {
					if (!allnodes.contains(data[0])) {
						allnodes.add(data[0]);
					}
					if (!allnodes.contains(data[1])) {
						allnodes.add(data[1]);
					}
					ArrayList<String> list = map.get(data[0]);
					list.add(data[1]);
					map.put(data[0], list);
				} else {
					if (!allnodes.contains(data[0])) {
						allnodes.add(data[0]);
					}
					if (!allnodes.contains(data[1])) {
						allnodes.add(data[1]);
					}
					ArrayList<String> list = new ArrayList<>();
					list.add(data[1]);
					map.put(data[0], list);
				}
			}
		}
	}
}
