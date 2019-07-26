
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Lab10 {
	static int vertices;
	static int edges;
	static Map<String, List<String>> Originalmap = new HashMap<>();
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		vertices = Integer.parseInt(sc.nextLine());
		edges = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < edges; i++) {
			String arr[] = sc.nextLine().split("\\s+");
			addEdge(arr[0], arr[1]);
		}
		int noOfTest = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < noOfTest; i++) {
			String arr[] = sc.nextLine().split("\\s+");
			List<String> visited = new ArrayList<>();
			if (checkEdge(arr[0], arr[1], visited)) {
				sb.append("YES ");
			} else {
				sb.append("NO ");
			}
			visited = null;
		}
		System.out.println(sb.toString().trim());
		sc.close();
	}

	private static boolean checkEdge(String src, String dest, List<String> visited) {
		visited.add(src);
		LinkedList<String> q = new LinkedList<>();
		q.add(src);
		Iterator<String> i;
		while (q.size() != 0) {
			src = q.poll();
			List<String> list = Originalmap.get(src);
			String n;
			if (list != null) {
				i = list.listIterator();
				while (i.hasNext()) {
					n = i.next();
					if (n.equals(dest)) {
						return true;
					}
					if (!visited.contains(n)) {
						visited.add(n);
						q.add(n);
					}
				}
			}
		}
		return false;
	}

	private static void addEdge(String a, String b) {
		if (Originalmap.containsKey(a)) {
			List<String> list = Originalmap.get(a);
			list.add(b);
			Originalmap.put(a, list);
		} else {
			List<String> list = new ArrayList<>();
			list.add(b);
			Originalmap.put(a, list);
		}
	}
}
