package mikeypl.tools.errors;

import java.lang.RuntimeException;

//	This class is used to throw an error if the value of something is negative


public class UnknownRaceError extends RuntimeException {
	
	public UnknownRaceError() {
		super("Invalid Race, should be human, dwarf, dark_elf, high_elf, wood_elf, gnome, half_elf, half_orc or halfling");
	}
	
	public UnknownRaceError(String msg) {
		super(msg);
	}
	
}