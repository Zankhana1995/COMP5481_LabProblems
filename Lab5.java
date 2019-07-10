
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab5 {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine().trim();
		String letter = sc.nextLine().trim();

		if (str.equals(letter)) {
			System.out.println("Shorest window===> " + letter);
		} else {
			List<String> newList = new ArrayList<>();
			for (int i = 0; i < str.length(); i++) {
				for (int j = i + 1; j <= str.length(); j++) {
					String substring = str.substring(i, j);
					int count = 0;
					if (substring.length() >= letter.length()) {
						StringBuffer sb = new StringBuffer();
						sb.append(substring);
						for (int k = 0; k < letter.length(); k++) {
							if (sb.toString().contains(Character.toString(letter.charAt(k)))) {
								int index = sb.indexOf(Character.toString(letter.charAt(k)));
								sb.deleteCharAt(index);
								count++;
							}
						}
						if (count == letter.length()) {
							newList.add(substring);
						}
					}
				}
			}
			String shortest = newList.get(0);
			for (String string : newList) {
				if (string.length() < shortest.length() && string.length() >= letter.length()) {

					shortest = string;
				}
			}
			System.out.print(shortest);
		}
		sc.close();
	}

	public static String charRemoveAt(String str, int p) {
		return str.substring(0, p) + str.substring(p + 1);
	}
}