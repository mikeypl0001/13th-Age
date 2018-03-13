package mikeypl._13th_Age.stats;

import mikeypl._13th_Age.characters.Player;
import static mikeypl.tools.TextAndDisplay.*;

class BaseStats {
	
	private String className;
	private String armourType;
	private boolean hasShield;
	private int attackPenalty;
	private int recoverydX;
	
	//background points, recoveryDie
	
	public BaseStats(Player player) {
		this.className = player.getClassName();
	}
	
	public int setHP() {
		
		switch(className) {
			case "barbarian": case "bard": case "cleric": case "ranger":
				return 7;
			case "fighter": case "paladin":
				return 8;
			case "rogue": case "sorcerer": case "wizard":
				return 6;
		}//default throw error
	}
	
	public int setAC(String armourType) {
		setAC(armourType, false);
	}
	
	public int setAC(String armourType, boolean hasShield) { //NEED to Create THING WITH ATTACK PENALTY
		
		this.armourType = formatText(armourType); //CHECK it is one of the following...
		this.hasShield = hasShield;
		int acWithNoArmour;
		
		if (className = "rogue") { //WANT TO CHECK FOR NOT A CLASS
			acWithNoArmour = 11;
		} else {
			acWithNoArmour = 10;
		}
		int acWithArmour;
		
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
						break;
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
				break;//default throw error
				
			}
		
		int acWithShield = hasShield ? (acWithArmour + 1) : acWithArmour;
		
		return acWithShield;
				
	}
	
	public int setPD() {
		
		switch(className) {
			case "bard": case "fighter": case "paladin": case "wizard":
				return 10;
			case "barbarian": case "cleric": case "ranger": case "sorcerer":
				return 11;
			case "rogue":
				return 12;
			default:
				//throw
				return -1;
		}// default throw error
		
	}
	
	public int setMD() {
		
		switch (className) {
			case "barbarian": case "fighter": case "ranger": case "rogue": case "sorcerer":
				return 10;
			case "bard": case "cleric": 
				return 11;
			case "paladin": case "wizard":
				return 12;
			default:
				//throw
				return -1;
		}
	}
	
	public int setAttackPenalty() {
		
		int armourPenalty;
		int shieldPenalty;
		
		if (armourType == "none" || armourType == "light") {
			armourPenalty = 0;
			
		} else {//error should have been dealt with
		
			if (className == "cleric" || className == "fighter" || className = "paladin") {
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
					//throw Error
					shieldPenalty = 0;
					break;
				
			}
		}
		
		this.attackPenalty = armourPenalty + shieldPenalty;
		
		return attackPenalty;
		
	}
	
	public int getBaseAttackPenalty() {
		return this.attackPenalty;
	}
	
	public int[] changeArmour(String newArmourType) {
		
		this.armourType = newArmourType;
		int ac = setAC(armourType, this.hasShield);
		int attackPenalty = setAttackPenalty();
		
		int[] acAndAttackPenalty = {ac, attackPenalty};
		
		return acAndAttackPenalty;
	}
	
	public int[] changeHasShield(boolean hasShield) {
		this.hasShield = hasShield;
		int ac = setAC(this.armourType, hasShield);
		int attackPenalty = setAttackPenalty();
		
		int[] acAndAttackPenalty = {ac, attackPenalty};
		
		return acAndAttackPenalty;
		
	}
	
	public int setRecoverydX() {
		//6 - sorcerer, wizard, 8 - bard, cleric, ranger, rogue, 10 - barbarian, fighter, paladin
		switch(className) {
			case: "sorcerer": case "wizard":
				recoverydX = 6;
				break;
				
			case "bard": case "cleric": case "ranger": case "rogue":
				recoverydX = 8;
				break;
				
			case "barbarian": case "fighter": case "paladin":
				recoverydX = 10;
				break;
				
			default:
				//throw error
				recoverydX = -1;
				break;
		}
		
		return recoverydX;
	}
	
}
