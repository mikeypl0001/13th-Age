package mikeypl.tools;

import static java.lang.Math.*;
import java.util.Arrays;
import java.lang.Double;

public class MoreMath {
	
	public static double median(int[] numbers) {
		//Sorting the numbers then finding the middle one which is the middle element if odd
		//or the sum of the two middles halved if even
		int numberOfNumbers = numbers.length;
		int medianIndex; // will be floor(length/2) for odd
		//for even it will be floor(length/2), floor(length/2) - 1 indices halved
		
		Arrays.sort(numbers);
		medianIndex = (new Double (floor(numberOfNumbers / 2.0))).intValue();
		
		if (numberOfNumbers % 2 == 1) {
			return numbers[medianIndex];
		} else {
			return (numbers[medianIndex] + numbers[medianIndex - 1]) / 2.0;
		}
		
	}
		
	public static void main(String[] args) {
		
		int[] a = {3, 1, 4, 4};
		System.out.println(median(a));
	}
}