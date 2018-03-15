package mikeypl._13th_Age.characters;

import mikeypl.tools.errors.NegativeValueError;
import mikeypl._13th_Age.interfaces.CharMethods;
import static mikeypl.tools.TextAndDisplay.*;
import static mikeypl.tools.NumberChecks.*;
import mikeypl.tools.errors.*;

class Character implements CharMethods {
	
	//STATS
	private int ac, pd, md, currHP, maxHP;
	
	//CHARACTER NAME, 
	private String name, race;
	//private Race race;
	CharType charType;
		
	//private int meleeAttackMod, meleeHitMod, meleeMiss; /** NOT IMPLEMENTED YET */
	
	//private int diceMelee, diceRanged; /** NOT IMPLEMENTED YET */
	
	//public boolean inFight;
	
	public Character ( String name, String race, CharType charType, int ac, int pd, int md, int hp ) {
		
		this.name = formatText ( name );
		this.race = formatText ( race );
		this.charType = charType;
		
		try {
			this.ac = checkPositive(ac);
			this.pd = checkPositive(pd);
			this.md = checkPositive(md);
			this.maxHP = checkPositive(hp);
		} catch (RuntimeException e) {
			print(e);
		}
		
		this.currHP = maxHP;
		
	}
	
	//SETTERS
	public void setName ( String name ) {
		
		this.name = formatText ( name );
		
	}
	
	public void setRace ( String race ) {
		
		this.race = formatText ( race );
		
	}
	
	public void setCharType ( CharType charType ) {
		
		this.charType = charType;
		
	}
	
	public void setMaxHP (int val) {
		
		try {
			this.maxHP = checkPositive(val);
		} catch (RuntimeException e) {
			print(e);
		}
		
	}
	
	public void setCurrHP(int val) {
		try {
			int diff = checkNegative(val - maxHP);
			this.currHP = checkPositive(val);
			
		} catch (PositiveValueError e) {
			print("currHP > maxHP setting currHP = maxHP");
			this.currHP = maxHP;
		} catch (NegativeValueError f) {
			print(f);
		}
	}
		
		//DEATH & DYING THING sunclass implementation? only on increasing currHP
	
	public void setACMDPDHP ( int ac, int pd, int md, int hp ) {
		
		try {
			this.ac = checkPositive(ac);
			this.pd = checkPositive(pd);
			this.md = checkPositive(md);
			this.maxHP = checkPositive(hp);
			this.currHP = maxHP;
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void changeAC ( int ac ) {
		
		try {
			this.ac = checkPositive(ac);
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void changePD ( int pd ) {
		
		try {
			this.pd = checkPositive(pd);
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
	
	public void changeMD ( int md ) {
		
		try {
			this.md = checkPositive(md);
		} catch (NegativeValueError e) {
			print(e);
		}
		
	}
			
	public void incCurrHP (int val) {
		
		try {
			int difference = checkNegative((val + currHP) - maxHP);
			this.currHP += val;
		} catch (RuntimeException e) {
			print("HP increase cannot be more than your current maxHP, setting currHP to maxHP");
			this.currHP = maxHP;
		}
		//DEATH & DYING
	}
	
	public void incMaxHP ( int val ) throws NegativeValueError {
		
		try {
			val = checkPositive(val);
			this.maxHP += val;
			this.currHP += val;
		} catch (NegativeValueError e) {
			print("This is not increasing your maxHP");
		}
		
	}
	
	//Ally to Enemy
	/** Implement in subclass calling super adding extras*/
	public void allyBecomesEnemy () {
		
		this.charType = (charType == CharType.ALLY) ? CharType.ENEMY : charType; 
	}
	
	//GETTERS
	
	public String getName () {
		
		return this.name;
		
	}
	
	public String getRace () {
		
		return this.race;
		
	}
	
	public int getAC () {
		
		return this.ac;
		
	}
	
	public int getPD () {
		
		return this.pd;
		
	}
	
	public int getMD () {
		
		return this.md;
		
	}
	
	public int getCurrHP () {
		
		return this.currHP;
	}
	
	public int getMaxHP () {
		
		return this.maxHP;
		
	}
	
	public CharType getCharType() {
		
		return this.charType;
		
	}
	
	public boolean isNPC () {
		
		return (charType != CharType.PLAYER);
	}
	
	public boolean isEnemy () {
		
		return (charType == CharType.ENEMY);
		
	}
	
	public boolean isAlly () {
		
		return (charType == CharType.ALLY);
		
	}

	protected void randomiseStats () { } //Subclass Implementation

		
	public static void main ( String[] args ) {
		
		Character gary = new Character("Gar-ou Ray", "Wood Elf", CharType.PLAYER, 17, 14, 11, 30);
		//TEST GETTERS
		print(gary.getName());
		print(gary.getRace());
		print("ac: " + gary.getAC());
		print("pd: " + gary.getPD());
		print("md: " + gary.getMD());
		print("currHP: " + gary.getCurrHP());
		print("MaxHP: " + gary.getMaxHP());
		print("CharTpye: " + gary.getCharType());
		print("NPC: " + gary.isNPC());
		print("Enemy: " + gary.isEnemy());
		print("Ally: " + gary.isAlly());
		//TEST WORD FORMATTER
		print("Change name to gary");
		gary.setName("   Gary    ");
		print(gary.getName());
		
		print("Change Race to Human");
		gary.setRace("Human");
		print(gary.getRace());
		//TEST setNPC
		print("Become a NPC");
		gary.setCharType(CharType.ALLY);
		print(gary.isNPC());
		//TEST ALLY BECOMES ENEMY
		print("Become Enemy");
		gary.allyBecomesEnemy();
		print("Ally: " + gary.isAlly());
		print("Enemy: " + gary.isEnemy());
		//TEST setMaxHP
		print("HP to 50");
		gary.setMaxHP(50);
		print(gary.getMaxHP());
		//TEST setCurrHP
		print("Current HP to 60");
		gary.setCurrHP(60);
		print(gary.getCurrHP());
		//TEST setCurrHP
		print("Current HP to 40");
		gary.setCurrHP(40);
		print(gary.getCurrHP());
		//TEST setACMDPDHP
		print("SET AC MD PD HP 1 1 1 1");
		gary.setACMDPDHP(1, 1, 1, 1);
		System.out.printf("AC %d MD %d PD %d HP %d \n", gary.getAC(), gary.getMD(), 
							gary.getPD(), gary.getMaxHP());
		
		System.out.println("Change AC 10");
		gary.changeAC(10);
		print(gary.getAC());
		
		print("Change MD 10");
		gary.changeMD(10);
		print(gary.getMD());
		
		print("Change PD");
		gary.changePD(10);
		print(gary.getPD());
		
		print("Change Max Health 100");
		gary.setMaxHP(100);
		print(gary.getMaxHP());

		print("Decrease Curr HP by 10");
		gary.incCurrHP(-10);
		print(gary.getCurrHP());
		
		print("Increase Max HP by 10");
		gary.incMaxHP(10);
		System.out.printf("Max HP: %d Curr HP %d \n", gary.getMaxHP(),
				gary.getCurrHP());
	
	
		//TESTING NEGATIVE
		Character twoBits = new Character("Two Bits", "gnome", CharType.ALLY, 0, 1, 2, 3);
		
			//TEST CURR > MAX
			twoBits.setCurrHP(30);
			print("CURR HP: " + twoBits.getCurrHP() + " MAX HP: " + twoBits.getMaxHP());
		
			//SETTERS are good
			//CHANGERS
			twoBits.changeAC(1);
			twoBits.changePD(1);
			twoBits.changeMD(1);
			twoBits.setMaxHP(1);
		//INCREASERS
		twoBits.incCurrHP(-4);
		print("CURR HP: " + twoBits.getCurrHP());
		//twoBits.incMaxHP(-59);
		
	}
		
}
