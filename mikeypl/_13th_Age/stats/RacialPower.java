package mikeypl._13th_Age.stats;

import java.util.ArrayList;
import mikeypl._13th_Age.characters.Player;
import mikeypl.tools.errors.*;
import static mikeypl.tools.RaceClassEtcWellPosed.*;

public class RacialPower {
	
	private final String[][] RACIALPOWER = {
		{"Quick to Fight", 
			"At the start of each battle, roll initiative twice, choose the preferred result"},
		{"That's Your Best Shot?",
			"Once per battle as a free action after you have been hit by an enemy attack, you can heal" +
			 "using a recovery. If the escalation die is less than 2, you get half the usual healing from" +
			 "the recovery. Unlike other recoveries, you have to roll it not taking any averages"},
		{"Lethal",
			"Once per battle, reroll a melee attack and use the roll you prefer as the result"},
		{"Cruel",
			 "Once per battle, deal ongoing damage to a target you hit with a natural even attack roll as" +
			 "a free action. The ongoing damage equals 5 times your level. Normal save ends the damage"},
		{"Highblood Teleport",
			 "Once per battle as a move action, place yourself in a nearby location you can see"},
		{"Elven Grace",
			 "At the start of each of your turns, roll a die to see if you get an extra standard action." + " If your roll is equal to or lower than the escalation die, you get an extra action that " +
			 "turn. At the start of battle, you roll a d6. Each time you successfully gain an extra " +
			 "action, the size of the die you roll increases by one step on the following progression: " +
			 "(d4) d6, d8, d10, d12, d20. If you get an extra action after rolling a d20, you can't get " +
			 "any more extra actions that battle."},
		{"Small",
			"+2 AC bonus against opportunity attacks"},
		{"Confounding",
			 "Once per battle, when you roll a natural 16+ with an attack, you can also daze the target" +
			 " until the end of your next turn"},
		{"Minor Illusions",
			 "As a standard action, at-will, you can create a strong smell or a sound nearby. " +
			 "Nearby creatures that fail a normal save notice the smell or sound. Creates that make the " +
			 "save may notice it but recognise it as not exactly real."},
		{"Surprising",
			 "Once per battle, subtract one from the natural result of one of your own d20 rolls"},
		{"Small",
			"+2 AC bonus against opportunity attacks"},
		{"Evasive",
			 "Once per battle, force an enemy that hits you with an attack to reroll the attack with a " + "-2 penalty"}
	};
	
	private String race;
	private ArrayList<String> name; //of RacialPower
	private ArrayList<String> description; // of RacialPower
	
	
	//private Feat elfFeat = new Feat("elf", )???
	//private Feat champFeat;
	private boolean hasElfFeat = false;
	private boolean hasChampFeat = false;


	public RacialPower(Player player) {
		this(player.getRace());
	}
	
	public RacialPower(String race) throws BadArguementError {
		
		this.race = isARace(race);
		name = new ArrayList<String>();
		description = new ArrayList<String>();
		
		switch (race) {
			case "human":
				this.name.add(RACIALPOWER[0][0]);
				this.description.add(RACIALPOWER[0][1]);
				break;
			case "dwarf":
				this.name.add(RACIALPOWER[1][0]);
				this.description.add(RACIALPOWER[1][1]);
				break;
			case "half_orc":
				this.name.add(RACIALPOWER[2][0]);
				this.description.add(RACIALPOWER[2][1]);
				break;
			case "dark_elf":
				this.name.add(RACIALPOWER[3][0]);
				this.description.add(RACIALPOWER[3][1]);
				break;
			case "high_elf":
				this.name.add(RACIALPOWER[4][0]);
				this.description.add(RACIALPOWER[4][1]);
				break;
			case "wood_elf":
				this.name.add(RACIALPOWER[5][0]);
				this.description.add(RACIALPOWER[5][1]);
				break;
			case "gnome":
				this.name.add(RACIALPOWER[6][0]);
				this.name.add(RACIALPOWER[7][0]);
				this.name.add(RACIALPOWER[8][0]);
				this.description.add(RACIALPOWER[6][1]);
				this.description.add(RACIALPOWER[7][1]);
				this.description.add(RACIALPOWER[8][1]);
				break;
			case "half_elf":
				this.name.add(RACIALPOWER[9][0]);
				this.description.add(RACIALPOWER[9][1]);
				break;
			case "halfling":
				this.name.add(RACIALPOWER[10][0]);
				this.name.add(RACIALPOWER[11][0]);
				this.description.add(RACIALPOWER[10][1]);
				this.description.add(RACIALPOWER[11][1]);
				break;
			default:
				throw new BadArguementError();
			
		}
		
	}
	
	public String getRace() {
		return this.race;
	}
	
	public ArrayList<String> getName() {
		return this.name;
	}
	
	public ArrayList<String> getDescription() {
		return this.description;
	}
	
	/*public Feat getChampFeat() {
		return this.champFeat;
	}
	
	public getElfFeat() {
		return this.elfFeat;
	}
	*/
	
	public void gotChampFeat(boolean hasChampFeat) {
		this.hasChampFeat = hasChampFeat;
	}
	
	public void gotElfFeat(boolean hasElfFeat) throws RaceFeatMismatchError {
		
		if ((race == "wood_elf" || race == "high_elf" || race == "dark_elf") && hasElfFeat) {
			this.hasElfFeat = true;
		} else if (hasElfFeat == false){
			this.hasElfFeat = false;
		} else {
			throw new RaceFeatMismatchError();
		}
		
	}
	
	public String[][] getAllRacialPowers() {
		return this.RACIALPOWER;
	}
	
	public boolean hasChampFeat() {
		return this.hasChampFeat;
	}
	
	public boolean hasElfFeat() {
		return this.hasElfFeat;
	}
	
	public static void main(String[] args) {
		Player geoff = new Player("geoff", "wood_elf", "ranger", "dex");
		RacialPower rpGeoff = new RacialPower(geoff);
		System.out.println(rpGeoff.getRace());
		System.out.println(rpGeoff.getName());
		System.out.println(rpGeoff.getDescription());
		System.out.println(rpGeoff.hasChampFeat());
		System.out.println(rpGeoff.hasElfFeat());
		rpGeoff.gotChampFeat(true);
		rpGeoff.gotElfFeat(true);
		System.out.println(rpGeoff.hasChampFeat());
		System.out.println(rpGeoff.hasElfFeat());
		
	}
	
	
	//Class For Champion feat for each of these!
	//Elves all have adventurer Feat
}