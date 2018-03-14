package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class UnknownClassError extends RuntimeException {
	
	public UnknownClassError() {
		super("Invalid Class, should be barbarian, bard, cleric, fighter, paladin, ranger, rogue, " + 
		"sorcerer or wizard");
	}
	
	public UnknownClassError(String msg) {
		super(msg);
	}
	
}