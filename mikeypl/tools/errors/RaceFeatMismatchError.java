package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class RaceFeatMismatchError extends RuntimeException {
	
	public RaceFeatMismatchError() {
		super("Invalid Race - Feat Combination");
	}
	
	public RaceFeatMismatchError(String msg) {
		super(msg);
	}
	
}