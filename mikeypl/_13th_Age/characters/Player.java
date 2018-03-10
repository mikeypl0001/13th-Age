package mikeypl._13th_Age;

import mikeypl.tools.Dice;
import mikeypl._13th_Age.interfaces.*;
//import java.util.ArrayList;

/**
 *  More to do with how this works, remember you can use checkPositive
 */

public class Player extends Character implements LvlUp, FullRest, Abilities {//extends Ally maybe
	
	//Name Race Class
	private String _class;
	
	//Initial Stats
	/**SUBCLASS?**/
	 
	private int lvl = 1;
	
	private int str, strBonus, con, conBonus, intgnce, intgnceBonus;
	private int dex, dexBonus, wis, wisBonus, cha, chaBonus;
	private int initiativeBonus;
	
	
	//Main Figures
	private int currRec, maxRecov = 8, gp;
	
	
	//private ArrayList<RaceAbilities> raceAbilities;
	/**
	//Other Stuff
	private String[][] icons, backgrounds;
	private String[][] _classFeats, talents, powers_spells, magic_items, equipment;
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
	Player( String name, String race, String _class ) {
		//PLACEHOLDER`
		super(name, race, CharType.PLAYER, 0, 0, 0, 0);
		
		this._class = _class;
		this.currRec = maxRecov;
			
		randomiseStats ();
		//super ( name, race, npc, ally, )
		
		//SetlvlMods
		//Initiative
		//Set ACMDPD
		
	}
	
	Player( String name, String race, String _class,
							int str, int con, int dex, int intgnce, int wis, int cha ) {
		//PLACEHOLDER						
		super(name, race, CharType.PLAYER, 0, 0, 0, 0);
		
		this._class = super.formatText ( _class );
		this.str = checkPositive(str);
		this.con = checkPositive(con);
		this.dex = checkPositive(dex);
		this.intgnce = checkPositive(intgnce);
		this.wis = checkPositive(wis);
		this.cha = checkPositive(cha);
		
		this.currRec = maxRecov;
								
	}
							
	Player( String name, String race, String _class,
							int str, int con, int dex, int intgnce, int wis, int cha,
								int recov ) {
									
		this (name, race, _class, str, con, dex, intgnce, wis, cha);
		this.maxRecov = checkPositive(recov);
		this.currRec = maxRecov;
		
	}
	
	
	//SET UP VALUES
	
	public void randomiseStats () {
		//roll 4d6 for each of six abilities then add the best three
		//result = { str val, con val, dex val, intgnce val, wis val, cha val}
		//for loop from0 to 5 of sum of three best die
		
		int [] results = new int[6];
		
		for ( int i = 0; i < results.length; i++ ) {
			
			Dice A = new Dice ( 4 , 6 );
			int min_val_pos = A.getSmallestValPos();
			results[i] = 0;
			
			for ( int j = 0; j < 3; j++ ) {
				
				if ( j != min_val_pos )
					
					results[i] += A.getdiceArr ( i );
					
			}
			
		}
		
		setAbilities ( results );
				
	}
	
	//SETTERS
	
	public void incStr ( int val ) {
		
		this.str += val;
		
	}
	
	public void addStrBonus ( int val ) {
		
		this.strBonus = val;
	}
	
	public void incCon ( int val ) {
		
		this.con += val;
		
	}
	
	public void addConBonus ( int val ) {
		
		this.conBonus = val;
	}
	
	public void incDex ( int val ) {
		
		this.dex += val;
		
	}
	
	public void addDexBonus ( int val ) {
		
		this.dexBonus = val;
		
	}
	
	public void incIntgnce ( int val ) {
		
		this.intgnce += val;
		
	}
	
	public void addIntgnceBonus ( int val ) {
		
		this.intgnceBonus = val;
		
	}
	
	public void incWis ( int val ) {
		
		this.wis += val;
		
	}
	
	public void addWisBonus ( int val ) {
		
		this.wisBonus = val;
	
	}
	
	public void incCha ( int val ) {
		
		this.cha += val;
		
	}
	
	public void addChaBonus ( int val ) {
		
		this.chaBonus = val;
		
	}
	
	public void incGP ( int val ) {
		
		this.gp += val;
		
	}
	
	public void decGP ( int val ) {
		
		this.gp -= val;
		
	}
	
	public void setMaxRecoveries ( int val ) {
		
		int difference = val - maxRecov;
		this.currRec += difference;
		this.maxRecov = val;
		
	}
	
	public void incMaxRecoveries ( int val ) {
		
		this.currRec += val;
		this.maxRecov += val;
		
	}
	
	//HP DONE IN SUPERCLASS
	
	public void decRecoveries () {
		
		if ( currRec > 0 )
			
			--this.currRec;
			
		else
			
			System.out.println( "You have no recoveries left to use" );
		
	}
	
	public void setAbilities ( int[] val ) {
		
		this.str = val[0];
		this.con = val[1];
		this.dex = val[2];
		this.intgnce = val[3];
		this.wis = val[4];
		this.cha = val[5];
		
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
	
	public int getStr () {
		
		return this.str;
		
	}
	
	public int getStrMod () {
		
		return calcAbilityMods ( str );
		
	}
	
	public int getStrBonus () {
		
		return this.strBonus;
		
	}
	
	public int getCon () {
		
		return this.con;
		
	}
	
	public int getConMod () {
		
		return calcAbilityMods ( con );
		
	}
	
	public int getConBonus () {
		
		return this.conBonus;
		
	}
	
	public int getDex () {
		
		return this.dex;
		
	}
	
	public int getDexMod () {
		
		return calcAbilityMods ( dex );
		
	}
	
	public int getDexBonus () {
		
		return this.dexBonus;
		
	}
	
	public int getIntgnce () {
		
		return this.intgnce;
		
	}
	
	public int getIntgnceMod () {
		
		return calcAbilityMods ( intgnce );
		
	}
	
	public int getIntgnceBonus () {
		
		return this.intgnceBonus;
		
	}
	
	
	public int getWis () {
		
		return this.wis;
		
	}

	public int getWisMod () {
		
		return calcAbilityMods ( wis );
		
	}
	
	public int getWisBonus () {
		
		return this.wisBonus;
		
	}
	
	public int getCha () {
		
		return this.cha;
		
	}
	
	public int getChaMod () {
		
		return calcAbilityMods ( cha );
		
	}
	
	public int getChaBonus () {
		
		return this.chaBonus;
		
	}
	
	public int getLvl () {
		
		return this.lvl;
	}
	
	public String get_Class () {
		
		return this._class;
		
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
	
	
	
	
	/* Changed my Dice subclass for 13th age to change the sum method and include modifiers to dice sum*/
	//Add bonus variable to abilities and maybe boolean.
}