package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class UnknownArmourError extends RuntimeException {
	
	public UnknownArmourError() {
		super("Invalid Armour, should be none, light, or heavy");
	}
	
	public UnknownArmourError(String msg) {
		super(msg);
	}
	
}