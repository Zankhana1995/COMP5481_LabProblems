import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Lab6 {
	static int vertices =0;
	static ArrayList<String> visited = new ArrayList<>();
	static HashMap<String, ArrayList<String>> map = new HashMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		vertices = Integer.parseInt(sc.nextLine());
		int NumberOfEdges = Integer.parseInt(sc.nextLine());
		Lab6 graph = new Lab6();
		
		for (int i = 0; i < NumberOfEdges; i++) {
			String[] arr = sc.nextLine().split("\\s+");
			graph.addEdge(arr[0], arr[1], map);
		}
		
		graph.findConnectedComponenets(map);
		sc.close();
	}

	private void findConnectedComponenets(HashMap<String, ArrayList<String>> map) {
		int component = 0;
		Iterator itr = map.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry pair = (Map.Entry) itr.next();
			// or Map.Entry<String,ArrayList<String>> pair
			String str = (String) pair.getKey();
			if(!visited.contains(str))
			{
				DFSRecursion(str);
				component++;
			}
		}
		int diff = vertices - visited.size();
		component+=diff;
		if (component > 1) {
			System.out.print("0 " + component);
		} else {
			System.out.print("1 " + component);
		}
	}

	private void DFSRecursion(String str) {
		visited.add(str);
		List<String> values = map.get(str);
		Iterator<String> itr = values.listIterator();
		while (itr.hasNext()) {
			String n = itr.next();
			if (!visited.contains(n)) {
				DFSRecursion(n);
			}
		}
	}

	void addEdge(String a, String b, HashMap<String, ArrayList<String>> map) {
		if (map.containsKey(a)) {
			ArrayList<String> list = map.get(a);
			list.add(b);
			map.put(a, list);
		} else {
			ArrayList<String> list = new ArrayList<>();
			list.add(b);
			map.put(a, list);
		}
			
		if (map.containsKey(b)) {
			ArrayList<String> list = map.get(b);
			list.add(a);
			map.put(b, list);
		} else {
			ArrayList<String> list = new ArrayList<>();
			list.add(a);
			map.put(b, list);
		}
		
	}
}
