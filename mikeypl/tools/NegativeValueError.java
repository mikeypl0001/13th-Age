package mikeypl.tools;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


class NegativeValueError extends RuntimeException {
	
	public NegativeValueError() {
		super();
	}
	
	public NegativeValueError(String msg) {
		super(msg);
	}
	
}