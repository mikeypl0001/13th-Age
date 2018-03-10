package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class UnknownAbilityError extends RuntimeException {
	
	public UnknownAbilityError() {
		super("Invalid Ability, shold be str, con, dex, int, wis or cha");
	}
	
	public UnknownAbilityError(String msg) {
		super(msg);
	}
	
}