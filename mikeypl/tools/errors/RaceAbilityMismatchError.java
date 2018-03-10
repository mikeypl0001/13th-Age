package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class RaceAbilityMismatchError extends RuntimeException {
	
	public RaceAbilityMismatchError() {
		super("Invalid Race - Ability Combination");
	}
	
	public RaceAbilityMismatchError(String msg) {
		super(msg);
	}
	
}