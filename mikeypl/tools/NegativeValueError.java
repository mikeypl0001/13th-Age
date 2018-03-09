package mikeypl.tools;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class NegativeValueError extends RuntimeException {
	
	public NegativeValueError() {
		super("This Value Cannot Be Less Than Zero");
	}
	
	public NegativeValueError(String msg) {
		super(msg);
	}
	
}