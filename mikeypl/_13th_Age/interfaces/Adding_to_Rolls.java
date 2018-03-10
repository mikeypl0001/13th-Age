package mikeypl._13th_Age.interfaces;

public interface Adding_to_Rolls {
	
	public int getDexModLvl ( Player p );
	
	public static int getInitiative ( int dexModLvl, int initiativeBonus );
	
}