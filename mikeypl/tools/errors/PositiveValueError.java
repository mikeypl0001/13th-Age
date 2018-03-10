package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class PositiveValueError extends RuntimeException {
	
	public PositiveValueError() {
		super("This Value Cannot Be Greater Than Zero");
	}
	
	public PositiveValueError(String msg) {
		
		super(msg);
		
	}
		
}