package labproblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab1 {
	public static int sum = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		List<Integer> list = new ArrayList<>();

		for (int i = line.length() - 1; i >= 0; i--) {
			list.add(Integer.parseInt(Character.toString(line.charAt(i))));
		}
		if (list.size() < 20) {
			for (int i = 0; i < list.size(); i++) {
				if (i % 2 != 0) {
					int num = list.get(i) * 2;
					int sumOfnum = findSumofNumber(num);
					sum = sum + sumOfnum;
				} else {
					sum = sum + list.get(i);
				}

			}
			// System.out.println(sum);
			if (sum % 10 == 0) {
				System.out.println("VALID");
			} else {
				/*
				 * int rightmost = list.get(0); int rem = sum % 10; int numb = rightmost - rem;
				 * System.out.println("INVALID"+" "+numb);
				 */

				sum = sum - list.get(0);
				// System.out.println(sum);
				int newSum = sum * 9;
				int rem1 = newSum % 10;
				System.out.println("INVALID" + " " + rem1);
			}
			sc.close();
		}
	}

	private static int findSumofNumber(int num) {
		int rem = num % 10;
		int a = (num - rem) / 10;
		int answer = rem + a;
		return answer;
	}
}
