package mikeypl._13th_Age.stats;

import static mikeypl.tools.RaceClassEtcWellPosed.*;
import static mikeypl.tools.NumberChecks.*;
import mikeypl.tools.errors.*;

public enum Ability {
	
	STR (0, "str", "Strength"),
	CON (1, "con", "Constitution"),
	DEX (2, "dex", "Dexterity"),
	INT (3, "int", "Intelligence"),
	WIS (4, "wis", "Wisdom"),
	CHA (5, "cha", "Charisma");
	
	private final int index;
	private final String shortName;
	private final String longName;
	
	Ability(int val, String shortName, String longName) {
		this.index = val;
		this.shortName = shortName;
		this.longName = longName;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getLongName() {
		return longName;
	}
	
	public static int getIndex(String shortName) {
		shortName = isAnAbility(shortName);
		int index = -1;
		
		for (Ability a : Ability.values()) {
			if (a.getShortName() == shortName) {
				index = a.getIndex();
				break;
			}
		}
		
		index = checkPositive(index);
		return index;
	}
	
	public static String getShortName(int index) {
		
		String shortName = "";
		
		try {
			index = checkPositive(index);
			int diff = checkNegative(index - 5); //stops entering a number too big
						
			for (Ability a : Ability.values()) {
				if (a.getIndex() == index) {
					shortName = a.getShortName();
					break;
				}
			}
			
			return shortName;
			
		} catch (RuntimeException e) {
			
			System.out.println(e + "Index Values must be between 0 & 5");
			return isAnAbility(shortName);
		}
	}
	
	public static void main(String[] args) {
		Ability ab = Ability.STR;
		System.out.println(ab.getIndex());
		System.out.println(ab.getLongName());
		
		System.out.println(Ability.getIndex("wis"));
		System.out.println(Ability.getShortName(4));
	}
	
}