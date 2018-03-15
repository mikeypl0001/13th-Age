package mikeypl._13th_Age.characters;

import mikeypl.tools.Dice;
import mikeypl._13th_Age.interfaces.*;
import static mikeypl.tools.TextAndDisplay.*;
import static mikeypl.tools.NumberChecks.*;
import static mikeypl.tools.RaceClassEtcWellPosed.*;
import static mikeypl.tools.MoreMath.median;
import mikeypl._13th_Age.stats.RaceAbilities;
import mikeypl._13th_Age.stats.BaseStats;
import mikeypl.tools.errors.*;
//import java.util.ArrayList;

import java.lang.Double;

/**
 *  TEST the Inputs with negative values
 */

public class Player extends Character /*implements LvlUp, FullRest, Abilities*/ {
	
	//Name Race Class
	private String className;
	
	//Initial Stats
	/**SUBCLASS?**/
	 
	private int lvl = 1;
	
	private int[] abilities = new int[6]; //{str, con, dex, int, wis, cha}
	private int[] abilitiesBonus = {0, 0, 0, 0, 0, 0}; //{strBonus, ..., chaBonus}
	//private int initiativeBonus;
	
	//Main Figures
	private int currRec, maxRecov = 8, gp = 0;
	
	
	//private ArrayList<RaceAbilities> raceAbilities;
	/**
	//Other Stuff
	private String[][] icons, backgrounds;
	private String[][] classNameFeats, talents, powers_spells, magic_items, equipment;
	private String[] racial_pow;
	*/
	
	/**
	//diceRolls
	private int diceMelee, diceRanged;
	//Melee
	private int meleeAttackMod, meleeHitMod, meleeMiss = 0;
	//Ranged
	private int  rangedAttackMod, rangedHitMod, RangedMiss = 0; */
	
	/*
		Character ( String name, String race, boolean npc, boolean ally, boolean enemy,
							int ac, int pd, int md, int hp )
	*/
	
	//CONSTRUCTORS
	public Player( String name, String race, String className ) {
		//PLACEHOLDER`
		super(name, isARace(formatText(race)), CharType.PLAYER, 0, 0, 0, 0);
		
		this.className = isAClassName(formatText(className));
		this.currRec = maxRecov;
			
		randomiseStats();//update 50/50 for abilities
		//setInitStats();
		
		
		//SetlvlMods
		//Initiative
		
		
	}
	
	public Player(String name, String race, String className, String ability) {
		
		this(name, race, className);
		RaceAbilities currPlayer = new RaceAbilities(this, isAnAbility(ability));
		
	}
	
	public Player( String name, String race, String className, int[] abilities ) {
		//PLACEHOLDER						
		super(name, race, CharType.PLAYER, 0, 0, 0, 0);
		
		this.className = formatText(className);
		
		try {
			this.abilities = checkPositive(abilities);
		} catch (RuntimeException e) {
			print(e + " Ability Array");
		}
			
		this.currRec = maxRecov;
	
	}
	
	public Player(String name, String race, String className, int[] abilities, String ability) {
		
		this(name, race, className, abilities);
		RaceAbilities currPlayer = new RaceAbilities(this, isAnAbility(ability));
	}
	
	public Player( String name, String race, String className, int[] abilities, int recov ) {
									
		this (name, race, className, abilities);
		
		try {
			this.maxRecov = checkPositive(recov);
		} catch (RuntimeException e) {
			print(e + " Max Recoveries");
		}
		
		this.currRec = maxRecov;
		
	}
	
	public Player(String name, String race, String className, int[] abilities, String ability, int recov) {
		
		this(name, race, className, abilities, recov);
		RaceAbilities currPlayer = new RaceAbilities(this, isAnAbility(ability));
		
	}
	
	
	//SET UP VALUES
	
	protected void randomiseStats () {
		//roll 4d6 for each of six abilities then add the best three
		//result = { str val, con val, dex val, int val, wis val, cha val}
		//for loop from 0 to 5 of sum of three best die
		
		int [] resultingAbilities = new int[6];
		
		for ( int i = 0; i < resultingAbilities.length; i++ ) {
			
			Dice A = new Dice ( 4 , 6 );
			
			int min_val_pos = A.getSmallestValPos();
			
			resultingAbilities[i] = 0;
			
			for ( int j = 0; j < 4; j++ ) {
				if ( j != min_val_pos )
					resultingAbilities[i] += A.getDiceArr(j);
			}
		}
		setAbilities ( resultingAbilities );
				
	}
	
	private void setInitStats(String armour, boolean hasShield) { //Attack Penalt
		BaseStats playerBaseStats = new BaseStats(this);
		//hp
		int hpWithoutLvlMod = playerBaseStats.setHP() + this.abilities[1]; //con
		int hp = hpWithoutLvlMod * getLvlMod(lvl);
		
		//ac, withoutMod + median(con, dex, wis) + lvl
		int acWithoutMod = playerBaseStats.setAC(armour, hasShield);
		int acStatsMedian = (new Double(median(new int[] {getCon(), getDex(), getWis()}))).intValue();
		int ac = acWithoutMod + acStatsMedian + lvl;
		
		//pd, withoutMod + median(str, con, dex) + lvl
		int pdWithoutMod = playerBaseStats.setPD();
		int pdStatsMedian = (new Double (median(new int[] {getStr(), getCon(), getDex()}))).intValue();
		int pd = pdWithoutMod + pdStatsMedian + lvl;
		
		//md, withoutMod + median(int, wis, cha) + lvl
		int mdWithoutMod = playerBaseStats.setMD();
		int mdStatsMedian = (new Double (median(new int[] {getInt(), getWis(), getCha()}))).intValue();
		int md = mdWithoutMod + mdStatsMedian + lvl;
		
		this.setACMDPDHP(ac, md, pd, hp);
		
		
	}
	
	//SETTERS
	
	public void addAbilities(int[] array) {
		
		try {
			
			if (array.length > abilities.length) {
				throw new ArrayIndexOutOfBoundsException();
			}
			
			for (int i = 0; i < 6; i++) {
				this.abilities[i] += array[i];
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			print("Array Mismatch, there should be only 6 Indexs");
		}		
	}
	
	public void incStr ( int val ) {
		
		try {
			int diff = checkPositive(abilities[0] + val);
			this.abilities[0] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
			
	}
	
	public void addStrBonus ( int val ) {
		
		try {
			int diff = checkPositive(abilitiesBonus[0] + val);
			this.abilitiesBonus[0] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
	}
	
	public void incCon ( int val ) {
		
		try {
			int diff = checkPositive(abilities[1] + val);
			this.abilities[1] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void addConBonus ( int val ) {
		
		try {
			int diff = checkPositive(abilitiesBonus[1] + val);
			this.abilitiesBonus[1] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
	
	}
	
	public void incDex ( int val ) {
		
		try {
			int diff = checkPositive(abilities[2] + val);
			this.abilities[2] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void addDexBonus ( int val ) {
		
		try {
			int diff = checkPositive(abilitiesBonus[2] + val);
			this.abilitiesBonus[2] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void incInt ( int val ) {
		
		try {
			int diff = checkPositive(abilities[3] + val);
			this.abilities[3] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void addIntBonus ( int val ) {
		
		try {
			int diff = checkPositive(abilitiesBonus[3] + val);
			this.abilitiesBonus[3] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void incWis ( int val ) {
		
		try {
			int diff = checkPositive(abilities[4] + val);
			this.abilities[4] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void addWisBonus ( int val ) {
		
		try {
			int diff = checkPositive(abilitiesBonus[4] + val);
			this.abilitiesBonus[4] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
	
	}
	
	public void incCha ( int val ) {
		
		try {
			int diff = checkPositive(abilities[5] + val);
			this.abilities[5] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void addChaBonus ( int val ) {
		
		try {
			int diff = checkPositive(abilitiesBonus[5] + val);
			this.abilitiesBonus[5] += val;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void incGP ( int val ) {
		
		try {
			this.gp += checkPositive(val);
		} catch (NegativeValueError e) {
			print("Don't increase GP by a negative amount");
		}
				
	}
	
	public void decGP ( int val ) {
		
		try {
			int diff = checkPositive(this.gp + checkNegative(-val));
			this.gp -= val;
		} catch(PositiveValueError e) {
			print("You Cannot Decrease Your GP by a Negative Amount");
		} catch (NegativeValueError f) {
			print("You Cannot Buy That Item, you do not have enough GP");
		}
		
	}
	
	public void setMaxRecoveries ( int val ) {
		
		try {
			int difference = checkPositive(val - maxRecov);
			this.currRec += difference;
			this.maxRecov = val;
		} catch (NegativeValueError e) {
			print("val needs to be more than maxRecov");
		}
		
	}
	
	public void incMaxRecoveries ( int val ) {
		
		try {
			this.currRec += checkPositive(val);
			this.maxRecov += checkPositive(val);
		} catch (NegativeValueError e) {
			print("You are meant to be Increasing Your Recoveries NOT decreasing!");
		}
					
	}
	
	//HP DONE IN SUPERCLASS
	
	public void decRecoveries () {
		
		if ( currRec > 0 )
			
			--this.currRec;
			
		else
			
			System.out.println( "You have no recoveries left to use" );
		
	}
	
	public void setAbilities ( int[] val ) {
		
		this.abilities[0] = val[0];
		this.abilities[1] = val[1];
		this.abilities[2] = val[2];
		this.abilities[3] = val[3];
		this.abilities[4] = val[4];
		this.abilities[5] = val[5];
		
	}
	
	
//FULL HEAL UP	
	public void renewNumRec () {
		
		this.currRec = getMaxRecoveries ();
		
	}
	
	public void renewHP () {
		
		super.setCurrHP ( super.getMaxHP() );
	}
	
	
	//public void setLvlMod () {
		
		//this.lvlMod = getLvlMod ( this. lvl );
		/** Get this relating to the stuff it does*/
	//}
	
	/*public void incLvl () {
		
		++this.lvl;
		setAbilityMods ();
		setAbilityModLvl ();
		setLvlMod ();
		//HP AC MD...
		
	}*/
	
	//GETTERS
	
	public int[]  getAbilities() {
		return this.abilities;
	}
	
	public int getStr () {
		
		return this.abilities[0];
		
	}
	
	public int getStrMod () {
		
		return calcAbilityMods ( abilities[0] );
		
	}
	
	public int getStrBonus () {
		
		return this.abilitiesBonus[0];
		
	}
	
	public int getCon () {
		
		return this.abilities[1];
		
	}
	
	public int getConMod () {
		
		return calcAbilityMods ( abilities[1] );
		
	}
	
	public int getConBonus () {
		
		return this.abilitiesBonus[1];
		
	}
	
	public int getDex () {
		
		return this.abilities[2];
		
	}
	
	public int getDexMod () {
		
		return calcAbilityMods ( abilities[2] );
		
	}
	
	public int getDexBonus () {
		
		return this.abilitiesBonus[2];
		
	}
	
	public int getInt () {
		
		return this.abilities[3];
		
	}
	
	public int getIntMod () {
		
		return calcAbilityMods ( abilities[3] );
		
	}
	
	public int getIntBonus () {
		
		return this.abilitiesBonus[3];
		
	}
	
	public int getWis () {
		
		return this.abilities[4];
		
	}

	public int getWisMod () {
		
		return calcAbilityMods ( abilities[4] );
		
	}
	
	public int getWisBonus () {
		
		return this.abilitiesBonus[4];
		
	}
	
	public int getCha () {
		
		return this.abilities[5];
		
	}
	
	public int getChaMod () {
		
		return calcAbilityMods ( abilities[5] );
		
	}
	
	public int getChaBonus () {
		
		return this.abilitiesBonus[5];
		
	}
	
	public int getLvl () {
		
		return this.lvl;
	}
	
	public String getClassName() {
		
		return this.className;
		
	}
	
	public int getGP () {
		
		return this.gp;
		
	}
	
	public int getNumOfRecoveries () {
		
		return this.currRec;
		
	}
	
	public int getMaxRecoveries () {
		
		return this.maxRecov;
		
	}
		
	public static int getLvlMod ( int lvl ) {
		
		if ( lvl <= 4 ) {
			
			return lvl + 2;
			
		} else if ( lvl <= 7 ) {
			
			return lvl * 2 - 2;
			
		} else {
			
			return lvl * 4 - 16;
		}
		
	}
	
	private static int calcAbilityMods ( int abilityVal ) {
		
		
		Double A = Math.floor ( abilityVal / 2 - 5 );
		return A.intValue();
		
	}
	
	public static void main(String[] args) {
		
		Player gary = new Player("Gar-ou Ray", "wood_elf", "ranger");
		printArray(gary.getAbilities());
		//GETTERS
		
		print(gary.getStr());
		print(gary.getCon());
		print(gary.getDex());
		print(gary.getInt());
		print(gary.getWis());
		print(gary.getCha());
		
		print("Getting MODs of Abilities");
		print(gary.getStrMod());
		print(gary.getConMod());
		print(gary.getDexMod());
		print(gary.getIntMod());
		print(gary.getWisMod());
		print(gary.getChaMod());
		
		print("Getting Mod Bonuses");
		gary.addStrBonus(1);
		gary.addConBonus(2);
		gary.addDexBonus(-3);
		gary.addIntBonus(4);
		gary.addWisBonus(5);
		gary.addChaBonus(3);
		print(gary.getStrBonus());
		print(gary.getConBonus());
		print(gary.getDexBonus());
		print(gary.getIntBonus());
		print(gary.getWisBonus());
		print(gary.getChaBonus());
		
		//Level
		print("Level");
		print(gary.getLvl());
		
		//Class
		print(gary.getClassName());
		print(gary.getRace());
		//GP
		print(gary.getGP());
		//Recoveries
		print(gary.getNumOfRecoveries());
		print(gary.getMaxRecoveries());
		//LVL MOD
		print("Level Mod at Lvl 5");
		print(getLvlMod(5));
		
		//SETTERS
		gary.addAbilities(new int[]{2,2,2,2,2,2,});
		printArray(gary.getAbilities());
		
		//incAbilities
		print("Increasing Abilities");
		gary.incStr(-1);//What if this goes negative throw Negative thingy...
		gary.incCon(1);
		gary.incDex(2);
		gary.incInt(3);
		gary.incWis(4);
		gary.incCha(5);
		printArray(gary.getAbilities());
		//GP
		gary.incGP(50);
		gary.decGP(3);
		print("GP: " + gary.getGP());
		
		//ERRORS
		Player bob = new Player("Bob", "Gnome", "Sorceror", new int[] {-1, 2, 2, 2, 2, 2});
		bob.addConBonus(-4);
	}
	
		
	
	/* Changed my Dice subclass for 13th age to change the sum method and include modifiers to dice sum*/
	
	//Add bonus variable to abilities and maybe boolean.
	
	
}