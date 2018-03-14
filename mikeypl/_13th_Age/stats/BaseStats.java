package mikeypl._13th_Age.stats;

import mikeypl._13th_Age.characters.Player;
import static mikeypl.tools.TextAndDisplay.*;
import static mikeypl.tools.RaceClassEtcWellPosed.*;
import mikeypl.tools.errors.*;

public class BaseStats {
	
	private String className;
	private String armourType;
	private boolean hasShield;
	private int attackPenalty;
	private int recoverydX;
	
	//background points, recoveryDie
	
	public BaseStats(String className) {
		try {
			this.className = isAClassName(className);
		} catch (UnknownClassError e) {
			System.out.println(e);
		}
	}
	
	public BaseStats(Player player) {
		this(player.getClassName());
	}
	
	public int setHP() throws BadArguementError {
		
		switch(className) {
			case "barbarian": case "bard": case "cleric": case "ranger":
				return 7;
			case "fighter": case "paladin":
				return 8;
			case "rogue": case "sorcerer": case "wizard":
				return 6;
			default:
				throw new BadArguementError();
		}
	}
	
	public int setAC(String armourType) {
		return setAC(armourType, false);
	}
	
	public int setAC(String armourType, boolean hasShield) throws BadArguementError {
		
		try {
			this.armourType = isAnArmour(formatText(armourType));
			this.hasShield = hasShield;
			int acWithNoArmour;
			
			if (className == "rogue") {
				acWithNoArmour = 11;
			} else {
				acWithNoArmour = 10;
			}
			int acWithArmour = 0;
			
			switch(armourType) {
				
				case "none":
					acWithArmour = acWithNoArmour;
					break;
				
				case "light":
					switch(className) {
						case "sorcerer": case "wizard":
							acWithArmour = 10;
							break;
						case "barbarian": case "bard": case "cleric": case "paladin": case "rogue":
							acWithArmour = 12;
							break;
						case "fighter":
							acWithArmour = 13;
							break;
						case "ranger":
							acWithArmour = 14;
							break;
						default:
							throw new BadArguementError();
					}
					break;
				
				case "heavy":
					switch(className) {
						case "sorcerer": case "wizard":
							acWithArmour = 11;
							break;
						case "barbarian": case "bard": case "rogue":
							acWithArmour = 13;
							break;
						case "cleric":
							acWithArmour = 14;
							break;
						case "fighter": case "ranger":
							acWithArmour = 15;
							break;
						case "paladin":
							acWithArmour = 16;
							break;
					}
					break;
					
				default:
					throw new BadArguementError();
					
				}
			
			int acWithShield = hasShield ? (acWithArmour + 1) : acWithArmour;
			
			return acWithShield;
			
		} catch (UnknownArmourError e) {
			System.out.println(e);
			return -1;
		}
				
	}
	
	
	public int setPD() throws BadArguementError {
		
		switch(className) {
			case "bard": case "fighter": case "paladin": case "wizard":
				return 10;
			case "barbarian": case "cleric": case "ranger": case "sorcerer":
				return 11;
			case "rogue":
				return 12;
			default:
				throw new BadArguementError();
		}
		
	}
	
	public int setMD() throws BadArguementError {
		
		switch (className) {
			case "barbarian": case "fighter": case "ranger": case "rogue": case "sorcerer":
				return 10;
			case "bard": case "cleric": 
				return 11;
			case "paladin": case "wizard":
				return 12;
			default:
				throw new BadArguementError();
		}
	}
	
	public int setAttackPenalty() throws BadArguementError {
		
		int armourPenalty;
		int shieldPenalty;
		
		if (armourType == "none" || armourType == "light") {
			armourPenalty = 0;
			
		} else {
		
			if (className == "cleric" || className == "fighter" || className == "paladin") {
				armourPenalty = 0;
			} else {
				armourPenalty = -2;
			}
		}
		
		if (hasShield == false) {
			
			shieldPenalty = 0;
			
		} else {
			
			switch(className) {
				
				case "barbarian": case "cleric": case "fighter": case "paladin":
					shieldPenalty = 0;
					break;
					
				case "bard":
					shieldPenalty = -1;
					break;
					
				case "ranger": case "rogue": case "sorcerer": case "wizard":
					shieldPenalty = -2;
					break;
				default:
					throw new BadArguementError();
				
			}
		}
		
		this.attackPenalty = armourPenalty + shieldPenalty;
		
		return attackPenalty;
		
	}
	
	public int getBaseAttackPenalty() {
		return this.attackPenalty;
	}
	
	public int[] changeArmour(String newArmourType) {
		
		try {
			this.armourType = isAnArmour(newArmourType);
			int ac = setAC(armourType, this.hasShield);
			int attackPenalty = setAttackPenalty();
			
			int[] acAndAttackPenalty = {ac, attackPenalty};
			
			return acAndAttackPenalty;
		
		} catch (UnknownArmourError e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int[] changeHasShield(boolean hasShield) {
		
		this.hasShield = hasShield;
		int ac = setAC(this.armourType, hasShield);
		int attackPenalty = setAttackPenalty();
		
		int[] acAndAttackPenalty = {ac, attackPenalty};
		
		return acAndAttackPenalty;
		
	}
	
	public int setRecoverydX() throws BadArguementError {

		switch(className) {
			case "sorcerer": case "wizard":
				recoverydX = 6;
				break;
				
			case "bard": case "cleric": case "ranger": case "rogue":
				recoverydX = 8;
				break;
				
			case "barbarian": case "fighter": case "paladin":
				recoverydX = 10;
				break;
				
			default:
				throw new BadArguementError();
		}
		
		return recoverydX;
	}
	
	
	
}
