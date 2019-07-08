import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/*
2
abcd
z z. z,z. z z. z,z.
1

3
a a. a,a.
bc bc
abcd abcd abcd
2
 */
public class Lab2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> map = new HashMap<>();
		int p = Integer.parseInt(sc.nextLine());
		List<String> inputs = new ArrayList<>();
		for (int i = 0; i < p; i++) {
			inputs.add(sc.nextLine());
		}
		int ngram = Integer.parseInt(sc.nextLine());
		System.out.println(inputs);
		for (String str : inputs) {
			if (ngram == 1 && str.length() >= 1) {
				for (int i = 0; i < str.length(); i++) {
					if (!Character.toString(str.charAt(i)).equals(".") && !Character.toString(str.charAt(i)).equals(",")
							&& !Character.toString(str.charAt(i)).equals(" ")) {
						if (map.containsKey(Character.toString(str.charAt(i)))) {
							int a = map.get(Character.toString(str.charAt(i)));
							map.put(Character.toString(str.charAt(i)), a + 1);
						} else {
							map.put(Character.toString(str.charAt(i)), 1);
						}
					}
				}
			} else if (ngram == 2 && str.length() >= 2) {
				for (int i = 0; i < str.length() - 1; i++) {
					if (!Character.toString(str.charAt(i)).equals(".") && !Character.toString(str.charAt(i)).equals(",")
							&& !Character.toString(str.charAt(i)).equals(" ")) {
						String s = Character.toString(str.charAt(i)) + Character.toString(str.charAt(i + 1));
						if (map.containsKey(s)) {
							int a = map.get(s);
							map.put(s, a + 1);
						} else {
							map.put(s, 1);
						}
					}
				}
			} else if (ngram == 3 && str.length() >= 3) {
				for (int i = 0; i < str.length() - 2; i++) {
					if (!Character.toString(str.charAt(i)).equals(".") && !Character.toString(str.charAt(i)).equals(",")
							&& !Character.toString(str.charAt(i)).equals(" ")) {
						String s = Character.toString(str.charAt(i)) + Character.toString(str.charAt(i + 1))
								+ Character.toString(str.charAt(i + 2));
						if (map.containsKey(s)) {
							int a = map.get(s);
							map.put(s, a + 1);
						} else {
							map.put(s, 1);
						}
					}
				}
			}
		}
		Map.Entry<String, Integer> maxEntry = null;
		//maxEntry = map.entrySet().iterator().next();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		String answerString = maxEntry.getKey();
		System.out.println(maxEntry);
		System.out.println(answerString);
		if (ngram == 1) {
			System.out.println("Unigram" + " " + answerString);
		} else if (ngram == 2) {
			System.out.println("Bigram" + " " + answerString);
		} else if (ngram == 3) {
			System.out.println("Trigram" + " " + answerString);
		}
		sc.close();

	}
}
