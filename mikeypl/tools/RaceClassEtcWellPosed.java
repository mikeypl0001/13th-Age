package mikeypl.tools;

import mikeypl.tools.errors.*;

public class RaceClassEtcWellPosed {
	
	private static final String[] CLASS = {"barbarian", "bard", "cleric", "fighter", "paladin",
											"ranger", "rogue", "sorcerer", "wizard"};
	private static final String[] ARMOUR = {"none", "light", "heavy"};
	private static final String[] ABILITY = {"str", "con", "dex", "int", "wis", "cha"};
	private static final String[] RACE = {"human", "dwarf", "dark_elf", "high_elf", "wood_elf",
											"gnome", "half_elf", "half_orc", "halfling"};
											
	public static String isAnAbility(String ability) throws UnknownAbilityError {
		//Checks it's an Ability in the list
		
		boolean withinABILITY = false;
		
		for (int i = 0; i < ABILITY.length; i++) {
			if (ability == ABILITY[i]) {
				withinABILITY = true;
				break;
			}
		}
		
		if (withinABILITY) {
			return ability;
		} else {
			throw new UnknownAbilityError();
		}
	}
	
	public static String isARace(String race) throws UnknownRaceError {
		//Checks it's a Race in the list
		
		boolean withinRACE = false;
		
		for (int i = 0; i < RACE.length; i++) {
			if (race == RACE[i]) {
				withinRACE = true;
				break;
			}
		}
		
		if (withinRACE) {
			return race;
		} else {
			throw new UnknownRaceError();
		}
	}
	
	public static String isAnArmour(String armourType) throws UnknownArmourError {
		
		boolean throwError = true;
		
		for (int i = 0; i < ARMOUR.length; i++) {
			
			if (armourType == ARMOUR[i]) {
				throwError = false;
				break;
			}
		}
		
		if (throwError) {
			throw new UnknownArmourError();
		} else {
			return armourType;
		}
		
	}
	
	public static String isAClassName(String className) throws UnknownClassError {
		
		boolean throwError = true;
		
		for (int i = 0; i < CLASS.length; i++) {
			
			if (className == CLASS[i]) {
				throwError = false;
				break;
			}
		}
		
		if (throwError) {
			throw new UnknownClassError();
		} else {
			return className;
		}
	}
	
}