package mikeypl._13th_Age.stats;

import mikeypl.tools.errors.*;
import static mikeypl.tools.TextAndDisplay.*;
import static mikeypl.tools.RaceClassEtcWellPosed.*;
import mikeypl._13th_Age.characters.Player;
import mikeypl._13th_Age.stats.Ability;

import java.util.ArrayList;

/**
 *  Add stuff to do with feats?
 */
 
 //Humans get 2 feats at lvl 1

public class RaceAbilities {
	
	private String race, ability;
		
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
		
		int[] temp = abilityRaceStartBonus();
		player.addAbilities(temp);
		
	}
	
		
	public int[] abilityRaceStartBonus() throws RaceAbilityMismatchError {
	
		int[] abilityAdjust = {0, 0, 0, 0, 0, 0};
		int abilityIndex = 6;
		
		// Checks to see if the ability belongs to that race then sends out that ability else
				
		if (checkMatchesRace()) {
			
			abilityIndex = Ability.getIndex(ability);
			abilityAdjust[abilityIndex] = 2;
			return abilityAdjust;
			
		} else {
			
			int[] abilityIndexOptions = getRaceAbilityBonus(race);
			ArrayList<String> abilityWordOptions = new ArrayList<String>(); 
			
			for (int i : abilityIndexOptions) {
				abilityWordOptions.add(Ability.getShortName(i));
			}
			
			print("It should be " + abilityWordOptions);
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
	
	public static int[] getRaceAbilityBonus(String race) throws BadArguementError {
		race = isARace(race);
		//str 0, con 1, dex 2, int 3, wis 4, cha 5
		
		switch(race) {
			case "human":
				return new int[] {0, 1, 2, 3, 4, 5};
			case "dwarf":
				return new int[] {1, 4};
			case "dark_elf":
				return new int[] {2, 5};
			case "high_elf":
				return new int[] {3, 5};
			case "wood_elf":
				return new int[] {2, 4};
			case "gnome":
				return new int[] {2, 3};
			case "half_elf":
				return new int[] {1, 5};
			case "half_orc":
				return new int[] {0, 2};
			case "halfling":
				return new int[] {1, 2};
			default:
				throw new BadArguementError();
		} 
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
