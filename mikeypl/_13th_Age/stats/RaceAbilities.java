package mikeypl._13th_Age.stats;

import mikeypl.tools.errors.*;
import static mikeypl.tools.TextAndDisplay.*;
import mikeypl._13th_Age.characters.Player;

/**
 *  Add stuff to do with feats?
 */
 
 //Humans get 2 feats at lvl 1

public class RaceAbilities {
	
	private String race, ability;
	private static final String[] ABILITY = {"str", "con", "dex", "int", "wis", "cha"};
	private static final String[] RACE = {"human", "dwarf", "dark_elf", "high_elf", "wood_elf",
											"gnome", "half_elf", "half_orc", "halfling"};
	
	private static final String[][] RACIALPOWER = 
		{"human" {
			""
		},
		}
	
	public RaceAbilities(String race, String ability) {
		try {
			this.race = isARace(formatText(race));
			this.ability = isAnAbility(formatText(ability));
		} catch (RuntimeException e) {
			System.out.println(e);
		}		
	}
	
	public RaceAbilities(Player player, String ability) {
		
		this(player.getRace(), ability);
		
		int[] abilityArray = player.getAbilities();
		int[] temp = abilityRaceStartBonus();
		player.addAbilities(temp);
		
	}
	
	private static String isAnAbility(String ability) throws UnknownAbilityError {
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
	
	private static String isARace(String race) throws UnknownRaceError {
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
	
	
	public int[] abilityRaceStartBonus() throws RaceAbilityMismatchError {
	
		int[] abilityAdjust = {0, 0, 0, 0, 0, 0};
		int abilityIndex = 6;
		
		// Checks to see if the ability belongs to that race then sends out that ability else
				
		if (checkMatchesRace()) {
			switch (ability) {
			
			case("str"):
				abilityIndex = 0;
				break;
			
			case("con"):
				abilityIndex = 1;
				break;
				
			case("dex"):
				abilityIndex = 2;
				break;
				
			case("int"):
				abilityIndex = 3;
				break;
				
			case("wis"):
				abilityIndex = 4;
				break;
			
			case("cha"):
				abilityIndex = 5;
				break;
					
			}
			abilityAdjust[abilityIndex] = 2;
			return abilityAdjust;
			
		} else {
			throw new RaceAbilityMismatchError();
		}

	}
	
	private boolean checkMatchesRace() { 
		
		
		boolean result = false;
		
		switch(race) {
						
			case("human"):
				result = true;
				break;
						
			case("dwarf"):
				if (ability == "con" || ability == "wis") {
					result =  true;
				}
				break;
				
			case("dark_elf"):
				if (ability == "dex" || ability == "cha") {
					result = true;
				} 
				break;
				
			case("high_elf"):
				if (ability == "int" || ability == "cha") {
					result = true;
				}
				break;
				
			case("wood_elf"):
				if (ability == "dex" || ability == "wis") {
					result = true;
				}
				break;
						
			case("gnome"):
				if (ability == "dex" || ability == "int") {
					result = true;
				}
				break;
				
			case("half_elf"):
				if (ability == "con" || ability == "cha") {
					result = true;
				} 
				break;
				
			case("half_orc"):
				if (ability == "str" || ability == "dex") {
					result = true;
				} 
				break;
				
			case("halfling"):
				if (ability == "con" || ability == "dex") {
					result = true;
				}
				break;				
		}
		return result;
			
	}
	
	
	
	public static void main(String[] args) {
		//System.out.println(formatText("DEX"));
		RaceAbilities gary = new RaceAbilities("wood_elf", "DEX");
		RaceAbilities gary2 = new RaceAbilities("wooD_elf", "str");
		RaceAbilities gary3 = new RaceAbilities("wood_elf", "str");
		try {
			int[] gary3Ability = gary3.abilityRaceStartBonus();
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		RaceAbilities urekTar = new RaceAbilities("half_orc", "dex");
		
		int[] stuff = urekTar.abilityRaceStartBonus();
		printArray(stuff);
	}
}