package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class BadArguementError extends RuntimeException {
	
	public BadArguementError() {
		super("You should not see this error, if you do your code has missed another error");
	}
	
	public BadArguementError(String msg) {
		super(msg);
	}
	
}