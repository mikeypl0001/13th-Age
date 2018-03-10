package mikeypl._13th_Age.characters;

import mikeypl.tools.errors.NegativeValueError;
import mikeypl._13th_Age.interfaces.CharMethods;
import static mikeypl.tools.TextAndDisplay.*;

class Character implements CharMethods {
	
	//STATS
	private int ac, pd, md, curr_hp, max_hp;
	
	//CHARACTER NAME, 
	private String name, race;
	//private Race race;
	CharType charType;
		
	private int meleeAttackMod, meleeHitMod, meleeMiss; /** NOT IMPLEMENTED YET */
	
	private int diceMelee, diceRanged; /** NOT IMPLEMENTED YET */
	
	public boolean inFight;
	
	public Character ( String name, String race, CharType charType, int ac, int pd, int md, int hp ) {
		
		this.name = formatText ( name );
		this.race = formatText ( race );
		this.charType = charType;
		this.ac = checkPositive(ac);
		this.pd = checkPositive(pd);
		this.md = checkPositive(md);
		this.max_hp = checkPositive(hp);
		this.curr_hp = max_hp;
		
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
		
		this.max_hp = checkPositive(val);
		
	}
	
	public void setCurrHP ( int val ) {
		
		if (val > max_hp)
			System.out.printf("Invalid Current HP, you enter %d when the max health is %d \n", val, max_hp);
		else
			this.curr_hp = checkPositive(val);
		
	}
	
	public void setACMDPDHP ( int ac, int pd, int md, int hp ) {
		
		this.ac = checkPositive(ac);
		this.pd = checkPositive(pd);
		this.md = checkPositive(md);
		this.max_hp = checkPositive(hp);
		
	}
	
	public void changeAC ( int ac ) {
		
		this.ac = checkPositive(ac);
		
	}
	
	public void changePD ( int pd ) {
		
		this.pd = checkPositive(pd);
		
	}
	
	public void changeMD ( int md ) {
		
		this.md = checkPositive(md);
		
	}
	
	public void changeMaxHP ( int hp ) {
		
		this.max_hp = checkPositive(hp);
				
	}
	
	public void incCurrHP (int val) {
		
		if (val + curr_hp > max_hp) {
			
			System.out.printf("Invalid Current HP, you enter %d which gives " + 
				"a total Current HP of %d when the max health is %d \n",
					val, val + curr_hp, max_hp);
			
		} else {
		
			this.curr_hp += val;
			
		}
		
		if (this.curr_hp <= 0) {
			System.out.println("YOU ARE DEAD");
			//DEAD & DYING METHOD
		}
	}
	
	public void incMaxHP ( int val ) throws NegativeValueError {
		
		if (val >= 0) {
			
			this.max_hp += val;
			this.curr_hp += val;
		
		} else {
			
			throw new NegativeValueError("YOU ARE MEANT TO BE INCREASING HEALTH NOT DECREASING!");
			
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
		
		return this.curr_hp;
	}
	
	public int getMaxHP () {
		
		return this.max_hp;
		
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
//Check if Anything is postive,
	//PROTECTED?
	protected static int checkPositive(int value) throws NegativeValueError {
		
		if (value < 0) {
			throw new NegativeValueError();
		} else {
			return value;
		}
	}
	

	public void randomiseStats () { } //Subclass Implementation

		
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
		gary.changeMaxHP(100);
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
			twoBits.changeMaxHP(1);
		//INCREASERS
		twoBits.incCurrHP(-4);
		print("CURR HP: " + twoBits.getCurrHP());
		//twoBits.incMaxHP(-59);
		
	}
		
}
