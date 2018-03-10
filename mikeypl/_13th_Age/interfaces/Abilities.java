package mikeypl._13th_Age.interfaces;

public interface Abilities {
	
	
	//Getters
	public int getStr();
	public int getStrMod();
	public int getStrBonus();
	
	public int getCon();
	public int getConMod();
	public int getConBonus();
	
	public int getDex();
	public int getDexMod();
	public int getDexBonus();
	
	public int getIntgnce();
	public int getIntgnceMod();
	public int getIntgnceBonus();
	
	public int getWis();
	public int getWisMod();
	public int getWisBonus();
	
	public int getCha();
	public int getChaMod();
	public int getChaBonus();
	
	public int getInitiativeBous();
	
	
	//Setters
	public void incStr ( int val );
	public void addStrBonus ( int val );
	
	public void incCon ( int val );
	public void addConBonus ( int val );
	
	public void incDex ( int val );
	public void addDexBonus ( int val );

	public void incIntgnce ( int val );
	public void addIntgnceBonus ( int val );
	
	public void incWis ( int val );
	public void addWisBonus ( int val );
	
	public void incCha ( int val );
	public void addChaBonus ( int val );
	
	//randomStats
	public void randomiseStats();
	
	
	//createmod
	public void setStatMods();

}