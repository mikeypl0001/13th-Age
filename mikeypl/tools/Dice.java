package mikeypl.tools;

import java.lang.Double;

public class Dice {
	
	//Want to create an array of x rolls 
	//Expected Value & Standard Deviation of x die of y
	
	private int numOfRolls, dX, length, dXnum;
	private int[] diceArr;
	private double[][] diceArr1;
	
//CONSTRUCTOR
	public Dice ( int rolls, int dX ) {
		
		//SETTING
		this.numOfRolls = rolls;
		this.dX = dX;
		this.diceArr = new int[rolls];
		this.length = diceArr.length;
		
		//GETTING VALUES FOR EACH DICE
		settingRANDDiceRolls ();
	}
	
	public Dice (int dX) {
		
		this(1, dX);
		
	}
//GETTERS	
	public int getNumOfRolls () {
		
		return this.numOfRolls;	
		
	}
	
	public int getdX () {
		
		return this.dX;
		
	}
	
	public int[] getDiceArr () {
		
		return this.diceArr;
		
	}
	
	public int getDiceArr ( int pos ) {
		
		if (pos >= length ) {
			
			error( "You Didn't Roll That Many Dice" );
			return -1;
			
		} else {
		
			return this.diceArr[pos];
		
		}
		
	}
	
	public int getLength () {
		
		return this.length;
		
	}
	
//SETTERS

	public void setDiceVal ( int pos, int val ) {

		//checking there isn't an error
		
		if ( checkValues ( pos, val ) == 0 )
			
			this.diceArr[pos] = val;
		
	}

	public void setDiceRolls ( int[] new_vals ) {
		
		//checking for right length
		int new_length = new_vals.length;
		
		if (new_length != getLength() ) {
			
			error ("You Didn't Roll That Many Die" );
			
		} else {
			
			for (int i = 0; i < new_length; i++ ) {
				
				setDiceVal ( i, new_vals[i] );
				
			}
		}
		
	}
	
	public void settingRANDDiceRolls () {
		
		int[] result = settingRANDDiceRolls ( getNumOfRolls (), getdX() );
		
		setDiceRolls ( result );
		
	}
	
	public static int[] settingRANDDiceRolls (int numOfRolls, int dX ) {
		
		int[] resultingRolls = new int[numOfRolls];
		//int[] returnThis = new int[numOfRolls];
		
		for ( int i = 0; i < numOfRolls; i++ ) {
			
			resultingRolls[i] =(new Double( Math.ceil ( Math.random () * dX ) ) ).intValue();
			
		}
		
		
		return resultingRolls;
		
	}
	


//ERRORS	
	public static void error ( String val ) {
		
		System.out.println ( val );
		
	}
		
	private int checkValues ( int pos, int val ) {
		
		//Checks all array elements are well defined
		
		if ( pos >= getLength() ) {
			
			error ( "You Didn't Roll That Many Die" );
			return -1;
			
		} else if ( pos < 0 ) {
			
			error ( "That Position Doesn't Exist In The Array" );
			return -2;
			
		} else if ( val <= 0 || val > getdX() ) {
			
			error ( "That Number Cannot Be Achieved, Value Will Not Be Changed" );
			return -3;
		
		} else {
			
			return 0;
			
		}
		
	}

	
	

//TO STRING	
	public String toStringDiceRolls() {
		/* For looping "val," from 1st roll to (N-1)th as Final Roll doesn't need  a ,
		So a { is added at the beginning then after the for loop val}
		*/
		
		String answer  = "{";
		
		for (int i=0; i < getNumOfRolls() -1; i++ ) {
			
			answer += this.getDiceArr(i) +", ";
		}
		
		return answer +getDiceArr ( getNumOfRolls () -1 ) +"}"; //final element as java starts at 0
		
	}
	
//PRINTERS
	public void printDiceRolls() {
		
		System.out.println( toStringDiceRolls() );
		
	}
	
	private static void print ( Object anything ) {
		
		System.out.println ( anything );
	}

//CALCULATIONS	

	public int getSumOfRolls () {
		
		//SUM ALL ROLLS FROM 1st Dice to Nth Dice 
		
		int answer = 0;
		
		for ( int i=0; i <getLength (); i++ ) {
			
			answer += getDiceArr(i);
		}
		
		return answer;
	}

	public double getMeanOfRolls () {
		
		//SUM/AMOUNT
		
		return getSumOfRolls() / getNumOfRolls();
		
	}
	
	public double getVarianceOfRolls () {
		
		/*Calculating Variance of the Rolls by: summing the squares of the difference of each dice roll from
		the mean then dividing it by the number of rolls*/
		
		double sumOfSquares = 0;
		double mean = getMeanOfRolls ();
		
		for ( int i=0; i < getLength(); i++ ) {
			
			double difference = getDiceArr(i) - mean;
			sumOfSquares += difference * difference;
			
		}
		
		return sumOfSquares / getNumOfRolls();
		
	}
	
	public int getSmallestValPos () {
		
		//Give you the position of the smallest value rolled
		//So it starts at 0 then changes to i if the value of it at i is lower
		
		int min_val_pos = 0;
		
		for ( int i = 0; i < getLength(); i++ ) {
			
			if ( getDiceArr ( i ) <= getDiceArr ( min_val_pos ) )
				
				min_val_pos = i;
			
		}
		
		return min_val_pos;
		
	}
	
	public double getSDOfRolls () {
		
		//Standard Deviation is sqrt of Variance
		
		return Math.sqrt ( getVarianceOfRolls() );
		
	}
	
	/*	//MULTIPLE ONES
	Dice(int rolls, int dX, int dXnum){
		this.rolls = getNumOfRolls;
		this.dX = dX;
		this.dXnum = dXnum;
		this.diceArr1 = new double[dXnum][rolls];
		diceRolling1();
	}
	
	public void diceRolling1(){
		for(int i = 0; i < dXnum; i++){
			for(int j=0; j < this.rolls; j++){
				this.diceArr1[i][j] = Math.ceil(Math.random()*dX);
			}
		}
	}
	
	/*public String printRolledDice1(){
		String[] ans = new String[this.dXnum];
		String answer = "";
		for (int j=0; j < this.dXnum; j++){
			String a = "{";
			System.out.println("j = " +j +", a = " +a);
			int rolls = this.rolls -1;
			//Middle Bit
			for (int i=0; i < rolls; i++){
				a += this.diceArr[i] +", ";
				System.out.println("i = " +i +", j = " +j +", a = " +a);
			}
			//End Bit
			ans[j] = " Roll " +j +": " +a +this.diceArr[rolls] +"}";
			answer += ans[j];
		}
		
		return 	answer;	
	}*/
	
	
	public static class Theory {
		
		public static double expectedVal ( int numOfRolls, int dX ) {
			
			//The Expected Value for N dXs is N(0.5dX(dX+1))/dX
			
			return ( numOfRolls * 0.5 * dX * ( dX +1 ) ) / dX;
			
		}

		public static double expectedVal ( int dX ) {
	
			return expectedVal ( 1, dX );
		
		}
	
		public static double variance ( int numOfRolls, int dX ) {
			
			//The Variance of N dXs is N/12 ((dX)^2 - 1)
	
			return ( numOfRolls * ( dX * dX -1 ) ) / 12;
			
		}
	
		public static double variance ( int dX ) {
			
			return variance ( 1, dX );
		
		}
	
		public static double standardDeviation (int numOfRolls, int dX ) {
			
			//Standard Deviation is the sqrt of the variance
			return Math.sqrt ( variance ( numOfRolls, dX ) );
			
		}
	
		public static double standardDeviation ( int dX ) {
			
			return Math.sqrt ( variance ( dX ) );
		
		}
	
		
		public static double expVal_plus_N_SD ( double expVal, int N, double standDev ) {
			
			//For Things Outside Of Dice!
			return expVal + N * standDev;
		}
		
		public static double expVal_plus_N_SD ( int N, int numOfRolls, int dX ) {
			
			return expectedVal ( numOfRolls, dX ) + N * standardDeviation ( numOfRolls, dX );
			
		}
		//one roll case
		public static double expVal_plus_N_SD ( int N, int dX ) {
			
			return expectedVal ( dX ) + N * standardDeviation ( dX );
			
		}
		
	}
	
	public static void main ( String[] args ) {
		
		//CONSTRUCTOR TEST
	
		Dice a = new Dice ( 7, 8 );
		Dice b = new Dice ( 6 );
		Dice c = new Dice ( 3, 4 );
	
		//GETTERS
		print ( "NUM ROLLS" );
		print ( a.getNumOfRolls() );
		print ( b.getNumOfRolls() );
	
		print ( "getdX" );
		print ( a.getdX() );
		print ( b.getdX() );
	
		print( "getDiceArr" );
		for ( int i = 0 ; i < a.getNumOfRolls() ; i++ ) {
			print( a.getDiceArr(i) );
		}
		
		print ( "getLength" );
		print ( a.getLength() );
		print ( b.getLength() );
		
		//SETTERS
		print ( "setDiceVal" );
		a.setDiceVal( 1, 9 );
		a.printDiceRolls ();
		
		print ( "setDiceRolls" );
		int[] roll = { 1, 2, 3, 4, 5, 6, 8 };
		a.setDiceRolls ( roll );
		a.printDiceRolls ();
		
		//Error
		b.setDiceVal ( -1, 2 );
		b.setDiceVal ( 0, -1 );
		b.setDiceVal ( 0, 7 );
		b.setDiceVal ( 7, 4 );
		
		//PRINTERS
		b.printDiceRolls ();
		c.printDiceRolls ();
		
		//CALCULATIONS
		print ( "sum" );
		print ( c.getSumOfRolls() );
		
		print ( "mean" );
		print ( c.getMeanOfRolls() );
		
		print ( "variance" );
		print ( c.getVarianceOfRolls() );
		
		print ( "sd" );
		print ( c.getSDOfRolls() );
		
		print ( "smallest value pos" );
		print ( a.getSmallestValPos() );
		print ( b.getSmallestValPos() );
		print ( c.getSmallestValPos() );
		
		//THEORY
		print ( "exp" );
		print ( Theory.expectedVal ( 3, 4 ) );
		print ( Theory.expectedVal ( 6 ) );
		
		print ( "variance" );
		print ( Theory.variance ( 3, 4 ) );
		print ( Theory.variance ( 6 ) );
		
		print ( "sd" );
		print ( Theory.standardDeviation ( 3, 4 ) );
		print ( Theory.standardDeviation ( 6 ) );
		
		print ( "exp -1 sd" );
		print ( Theory.expVal_plus_N_SD ( -1, 3, 4 ) );
		print ( Theory.expVal_plus_N_SD ( -1, 6 ) );
		print ( Theory.expVal_plus_N_SD ( 3.0, 5, 1.1 ) );
		
		//getSmallestValPos
		for (int i = 0; i < 21; i++) {
			Dice pleaseWork = new Dice(4, 6);
			pleaseWork.printDiceRolls();
			print(pleaseWork.getSmallestValPos());
		}
	}
}