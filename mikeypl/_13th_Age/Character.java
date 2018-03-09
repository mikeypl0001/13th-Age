package mikeypl._13th_Age;

import mikeypl.tools.NegativeValueError;

class Character implements CharMethods {
	
	/**
	 *  What a method which check hp is not 0 and goes to dying rules
	 *  under currentHP increase
	 *  
	 *  Setter and other numbers just get if <0 and throw an error.
	 *  
	 *  Maybe a method which throws an NegativeValueError then another which catches it somehow?
	 */
	
	//STATS
	private int ac, pd, md, curr_hp, max_hp;
	
	//CHARACTER NAME
	private String name, race;
	//private Race race;
	boolean npc, ally, enemy; //ally and enemy are npc
		
	private int meleeAttackMod, meleeHitMod, meleeMiss; /** NOT IMPLEMENTED YET */
	
	private int diceMelee, diceRanged; /** NOT IMPLEMENTED YET */
	
	public boolean inFight;
	
	public Character ( String name, String race, boolean npc, boolean ally, boolean enemy,
							int ac, int pd, int md, int hp ) {
		
		this.name = formatText ( name );
		this.race = formatText ( race );
		this.npc = npc;
		this.ally = ally;
		this.enemy = enemy;
		this.ac = ac;
		this.pd = pd;
		this.md = md;
		this.max_hp = hp;
		this.curr_hp = max_hp;
		
	}
	
	//SETTERS
	public void setName ( String name ) {
		
		this.name = formatText ( name );
		
	}
	
	public void setRace ( String race ) {
		
		this.race = formatText ( race );
		
	}
	
	public void setIsNPC ( boolean npc ) {
		
		this.npc = npc;
		
	}
	
	public void setIsAlly ( boolean ally ) {
		
		this.ally = ally;
		
	}
	
	public void setIsEnemy ( boolean enemy ) {
		
		this.enemy = enemy;
		
	}
	
	public void setMaxHP (int val) {
		
		//int hp = checkPositive_MxHP(val);
		//if
		this.max_hp = val;
		
	}
	
	public void setCurrHP ( int val ) {
		
		if (val > max_hp)
			System.out.printf("Invalid Current HP, you enter %d when the max health is %d \n", val, max_hp);
		else
			this.curr_hp = val;
		
	}
	
	public void setACMDPDHP ( int ac, int pd, int md, int hp ) {
		
		this.ac = ac;
		this.pd = pd;
		this.md = md;
		this.max_hp = hp;
		
	}
	
	public void changeAC ( int ac ) {
		
		this.ac = ac;
		
	}
	
	public void changePD ( int pd ) {
		
		this.pd = pd;
		
	}
	
	public void changeMD ( int md ) {
		
		this.md = md;
		
	}
	
	public void changeMaxHP ( int hp ) {
		
		this.max_hp = hp;
				
	}
	
	public void incCurrHP (int val) {
		
		if (val + curr_hp > max_hp) {
			
			System.out.printf("Invalid Current HP, you enter %d which gives " + 
				"a total Current HP of %d when the max health is %d \n",
					val, val + curr_hp, max_hp);
			
		} else {
		
			this.curr_hp += val;
			
		}
	}
	
	public void incMaxHP ( int val ) {
		
		this.max_hp += val;
		this.curr_hp += val;
	}
	
	//Ally to Enemy
	/** Implement in subclass calling super adding extras*/
	public void allyBecomesEnemy () {
		
		this.ally = !ally;
		this.enemy = !enemy;
		
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
	
	public boolean isNPC () {
		
		return this.npc;
		
	}
	
	public boolean isEnemy () {
		
		return this.enemy;
		
	}
	
	public boolean isAlly () {
		
		return this.ally;
		
	}
//Check if HP is postive,

	public void inAFight() {
		
		this.inFight = true;
		
	}
	
	public outOfFight() {
		
		this.inFight = false;
		
	}
	
	public static void checkPositive(int val) {
		
		if (val > 0) {
			
			return val;
			
		} else if (	inFight && val <= 0) {
			
			return 0;
			
		} else {
			
			return -1;
			
		}
		
	}
//FMethods for text formatter
	private static boolean noSpaces ( String input ) {
			
			if ( input.contains ( " " ) == false )
				
				return true;
				
			else
				
				return false;
				
	}
	
	private static boolean has__ ( String input ) {
		
		if (input.contains ( "__" ) )
			
			return true;
			
		else
			
			return false;
			
	}
	
	private static int getFirstSpacePos ( String input ) {
		
		return input.indexOf ( " " );
		
	}
	
	private static int getLastSpacePos (String input ) {
		
		return input.lastIndexOf ( " " );
	}
	
	private static int getLastCharPos ( String input ) {
		
		return input.length() - 1;
	}
	
	public static String formatText ( String input ) {
				
		//All lower case
		String lowercase = input.toLowerCase ();
		//System.out.println ( lowercase );
		
		//Checking for spaces at start of the word
		
		if ( noSpaces ( lowercase ) ) {
			
			//no spaces then we are done
			return lowercase;
			
		} else {
			
			//Removes any spaces at the start of the word
			
			while ( getFirstSpacePos ( lowercase ) == 0 ) {
								
				String temp = lowercase.replaceFirst ( " ", "" );
				
				//updating the variables
				lowercase = temp;
							
			}
						
			//Checking for any spaces now 
			
			if ( noSpaces ( lowercase ) ) {
				
				//No spaces then we are done
				
				return lowercase;
				
			} else {
					
				//Removing any spaces at the end of the word
				while ( getLastSpacePos ( lowercase ) == getLastCharPos ( lowercase ) ) {
						
					String temp2 = lowercase.substring ( 0, getLastSpacePos( lowercase ) - 1 );
										
					//updating the variables
					lowercase = temp2;
					
				}
				
				//Checking again for any spaces, if so they are now in the middle
				
				if ( noSpaces ( lowercase ) ) {
					
					//No Spaces, we are done
					
					return lowercase;
					
				} else {
					
					//turning " " into "_"
					
					while ( noSpaces ( lowercase ) == false ) {
				
						String temp3 = lowercase.replaceFirst ( " ", "_" );
					
						//updating the variables
						lowercase = temp3;
							
					}
					
					//Checking for "__" and replacing "__" with "_"
					
					if ( has__ ( lowercase ) ) {
					
						while ( has__ ( lowercase ) ) {
						
							String intermediate2 = lowercase.replaceFirst ( "__", "_" );
						
							//updating the variables
							lowercase = intermediate2;
												
						}
										
					}
					return lowercase;
					
				}
						
			}
				
	
		}	
		
	}
	
	protected static void print(Object input) {
		
		System.out.println(input);
	}
	
	public void randomiseStats () { } //Subclass Implementation

		
	public static void main ( String[] args ) {
		
		Character gary = new Character("Gar-ou Ray", "Wood Elf", false, true, false, 17, 14, 11, 30);
		
		print(gary.getName());
		print(gary.getRace());
		print("ac: " + gary.getAC());
		print("pd: " + gary.getPD());
		print("md: " + gary.getMD());
		print("currHP: " + gary.getCurrHP());
		print("MaxHP: " + gary.getMaxHP());
		print("NPC: " + gary.isNPC());
		print("Enemy: " + gary.isEnemy());
		print("Ally: " + gary.isAlly());
		
		print("Change name to gary");
		gary.setName("   Gary    ");
		print(gary.getName());
		
		print("Change Race to Human");
		gary.setRace("Human");
		print(gary.getRace());
		
		print("Become a NPC");
		gary.setIsNPC(true);
		print(gary.isNPC());
		
		print("Become Enemy");
		gary.allyBecomesEnemy();
		print("Ally: " + gary.isAlly());
		print("Enemy: " + gary.isEnemy());
		
		print("HP to 50");
		gary.setMaxHP(50);
		print(gary.getMaxHP());
		
		print("Current HP to 60");
		gary.setCurrHP(60);
		print(gary.getCurrHP());
		
		print("Current HP to 40");
		gary.setCurrHP(40);
		print(gary.getCurrHP());
		
		print("SET AC MD PD HP 1 1 1 1");
		gary.setACMDPDHP(1, 1, 1, 1);
		System.out.printf("AC %d MD %d PD %d HP %d \r", gary.getAC(), gary.getMD(), 
							gary.getPD(), gary.getMaxHP());
		
		print("Change AC 10");
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
		System.out.printf("Max HP: %d Curr HP %d", gary.getMaxHP(),
				gary.getCurrHP());
	
	}
		
}
