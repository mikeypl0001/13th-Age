package mikeypl.tools;

class Dice_13 extends Dice {
	
	private int plus;
	private int sumOfRolls;
	
	public Dice_13( int numOfRolls, int dX, boolean wantText ) {
		
		super ( numOfRolls, dX );
		
		if (wantText) {
			beginnning ();
			printDiceRolls ();
			display_sum ();
		}
		
	}
	
	public Dice_13(int numOfRolls, int dX) {
		this (numOfRolls, dX, true);
	}
	
	public Dice_13 ( int numOfRolls, int dX, int plus ) {
		
		super ( numOfRolls, dX );
		this.plus = plus;
		this.sumOfRolls = this.getSumOfRolls() + plus;
		
	}
	
	public void beginnning () {
		
		System.out.println ( super.getNumOfRolls () +" d" +super.getdX () +":" );
		
	}
	
	
	public void display_sum () {
		
		System.out.println ( "Which sum to " +super.getSumOfRolls() );
		
	}
	
	public static void rollingSave ( int saveVal ) { //Return boolean?
		
		Dice A = new Dice(20);
		
		int valRolled = A.getDiceArr ( 0 );
		System.out.println ( "You rolled " +A.toStringDiceRolls () );
		
		
		if ( valRolled < saveVal )
			
			System.out.println("You Failed a Save :(");
			
		else
			
			System.out.println("You Passed A Save :)");
			
	}
	
	public int getSumOfRolls () {
		
		//SUM ALL ROLLS FROM 1st Dice to Nth Dice plus the
		
		int answer = plus;
		
		for ( int i=0; i <getLength (); i++ ) {
			
			answer += getDiceArr(i);
		}
		
		return answer;
	
	}
	
	public static void main ( String[] args ) {
		
		Dice_13 a = new Dice_13(5,10);
		Dice_13 b = new Dice_13(4, 8);
		rollingSave(11);
		
	}
}