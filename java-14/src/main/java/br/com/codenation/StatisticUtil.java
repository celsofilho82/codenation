package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		int result = 0;
		for(int element : elements) {
			sum += element;
		}
		result = sum / elements.length;
		return result;
	}

	public static int mode(int[] elements) {
		int result = 0;
		int aux = 0;
		
		for(int i = 0; i < elements.length; i++) {
			int count = 0;
			for(int j = 0; j < elements.length; j++) {
				if(elements[i] == elements[j]) {
					count ++;
				}
			}
			if(count > aux) {
				result = elements[i];
				aux = count;
			}
		}
		
		return result;
	}

	public static int median(int[] elements) {
		
		Arrays.sort(elements);
		int result = 0;
		int aux = 0;
		int lengthArray = elements.length;
		
		if((lengthArray % 2) == 1) {
			aux = lengthArray / 2;
			result = elements[aux];
		}else {
			aux = lengthArray / 2;
			result = (elements[aux] + elements[aux-1]) / 2;
		}
		
		return result;
	}
}