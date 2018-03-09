package mikeypl._13th_Age;

import mikeypl.tools.Dice;

class Dice_13 extends Dice {
	
	private int plus;
	
	public Dice_13( int num_rolls, int dX ) {
		
		super ( num_rolls, dX );
		
		beginnning ();
		printDiceRolls ();
		display_sum ();
		
	}
	
	public Dice_13 ( int num_rolls, int dX, int plus ) {
		
		super ( num_rolls, dX );
		this.plus = plus;
		
	}
	
	public void beginnning () {
		
		System.out.println ( super.getNum_rolls () +" d" +super.getdX () +":" );
		
	}
	
	
	public void display_sum () {
		
		System.out.println ( "Which sum to " +super.getSumOfRolls() );
		
	}
	
	public static void rollingSave ( int saveVal ) {
		
		Dice A = new Dice(20);
		
		double valRolled = A.getdiceArr ( 0 );
		System.out.println ( "You rolled " +A.toString_DiceRolls () );
		
		
		if ( valRolled < saveVal )
			
			System.out.println("You Failed a Save :(");
			
		else
			
			System.out.println("You Passed A Save :)");
			
	}
	
	public double getSumOfRolls () {
		
		//SUM ALL ROLLS FROM 1st Dice to Nth Dice plus the
		
		double answer = plus;
		
		for ( int i=0; i <getLength (); i++ ) {
			
			answer += getdiceArr(i);
		}
		
		return answer;
	
	}
	
	public static void main ( String[] args ) {
		
		Dice_13 a = new Dice_13(5,10);
		Dice_13 b = new Dice_13(4, 8);
		rollingSave(11);
		
	}
}