import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;

public class Lab8 {
	static int vertices;
	static int edges;
	static List<String> visited = new ArrayList<>();
	static List<String> visitedTranspose = new ArrayList<>();
	static Map<String, List<String>> Originalmap = new HashMap<>();
	static Map<String, List<String>> transposemap = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		vertices = Integer.parseInt(sc.nextLine());
		edges = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < edges; i++) {
			String arr[] = sc.nextLine().split("\\s+");
			addEdge(arr[0], arr[1]);
		}
		printSSC();
		sc.close();
	}

	private static void printSSC() {
		Stack<String> s = new Stack<>();
		for (Entry<String, List<String>> e : Originalmap.entrySet()) {
			if (!visited.contains(e.getKey())) {
				fillOrder(s, e.getKey());
			}
		}
		getTranspose();
	//	System.out.println("stack "+ s);
		while (!s.isEmpty()) {
			String stri = s.pop();
			if (!visitedTranspose.contains(stri)) {
				List<String> l = new ArrayList<>();
				DFSRec(stri, l);
				Collections.sort(l);	
				System.out.println(l);
			}
		}
	}

	private static void getTranspose() {
		Iterator<Entry<String, List<String>>> itr = Originalmap.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, List<String>> pair = (Map.Entry<String, List<String>>) itr.next();
			List<String> values = pair.getValue();
			for (String string : values) {
				addEdgeToTranspose(string, pair.getKey());
			}
		}
	}

	private static void fillOrder(Stack<String> s, String key) {
		visited.add(key);
		if (Originalmap.get(key) != null) {
			List<String> values = Originalmap.get(key);
			Iterator<String> itr = values.listIterator();
			while (itr.hasNext()) {
				String n = itr.next();
				if (!visited.contains(n)) {
					fillOrder(s, n);
				}
			}
			s.push(key);
		} else {
			s.push(key);
		}
	}

	private static void DFSRec(String str, List<String> l) {
		visitedTranspose.add(str);
		l.add(str);
		if (transposemap.get(str) != null) {
			List<String> values = transposemap.get(str);
			Iterator<String> itr = values.listIterator();
			while (itr.hasNext()) {
				String n = itr.next();
				if (!visitedTranspose.contains(n)) {
					DFSRec(n, l);
				}
			}
		}
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

	private static void addEdgeToTranspose(String a, String b) {
		if (transposemap.containsKey(a)) {
			List<String> list = transposemap.get(a);
			list.add(b);
			transposemap.put(a, list);
		} else {
			List<String> list = new ArrayList<>();
			list.add(b);
			transposemap.put(a, list);
		}
	}
}
