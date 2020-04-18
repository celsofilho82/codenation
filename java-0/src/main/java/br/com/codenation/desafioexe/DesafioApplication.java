package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		int num1 = 1;
		int num2 = 0;

		final ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(num2);
		list.add(num1);
		
		while (num1 <= 350) {
			num1 = num1 + num2;
			num2 = num1 - num2;
			list.add(num1);
		}

		return list;
	}

	public static Boolean isFibonacci(final Integer a) {
		final List<Integer> list = fibonacci();
		return list.contains(a);
	}

}