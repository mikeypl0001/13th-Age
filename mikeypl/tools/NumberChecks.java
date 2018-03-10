package mikeypl.tools;

import mikeypl.tools.errors.NegativeValueError;
import mikeypl.tools.errors.PositiveValueError;

public class NumberChecks {

	public static int checkNegative(int value) throws PositiveValueError {
		
		if ( val > 0) {
			throw new PositiveValueError();
		} else {
			return value;
		}
		
	}
	
	public static int checkPositive(int value) throws NegativeValueError {

		if (value < 0) {
			throw new NegativeValueError();
		} else {
			return value;
		}
		
	}
	
	public static int[] checkPositive(int[] array) throws NegativeValueError {
		
		boolean throwError = false;

		for (int i = 0; i < array.length; i++) {
			try {
				checkPositive(array[i]);
				
			} catch (RuntimeException e) {
				
				throwError = true;
				break;
			}
		}
		
		if (throwError == false) {
			return array;
		} else {
			throw new NegativeValueError(array.length);
		}
	}
	
	public static void main(String[] args) {
		
		try {
			int a = checkPositive(-1);
		} catch (NegativeValueError e) {
			System.out.println(e);
		}
		
		try {
			int[] b = checkPositive(new int[] {5, 2, -1, -3, 4});
		} catch (NegativeValueError e) {
			System.out.println(e);
		}
		
	}
}