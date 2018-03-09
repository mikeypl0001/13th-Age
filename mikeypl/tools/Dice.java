package mikeypl.tools;

public class Dice {
	
	//Want to create an array of x rolls 
	//Expected Value & Standard Deviation of x die of y
	
	private int num_rolls, dX, length, dXnum;
	private double[] diceArr;
	private double[][] diceArr1;
	
//CONSTRUCTOR
	public Dice ( int rolls, int dX ) {
		
		//SETTING
		this.num_rolls = rolls;
		this.dX = dX;
		this.diceArr = new double[rolls];
		this.length = diceArr.length;
		
		//GETTING VALUES FOR EACH DICE
		settingRANDDiceRolls ();
	}
	
	public Dice (int dX) {
		
		this(1, dX);
		
	}
//GETTERS	
	public int getNum_rolls () {
		
		return this.num_rolls;	
		
	}
	
	public int getdX () {
		
		return this.dX;
		
	}
	
	public double[] getdiceArr () {
		
		return this.diceArr;
		
	}
	
	public double getdiceArr ( int pos ) {
		
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

	public void setDiceVal ( int pos, double val ) {

		//checking there isn't an error
		
		if ( checkValues ( pos, val ) == 0 )
			
			this.diceArr[pos] = val;
		
	}

	public void setDiceRolls ( double[] new_vals ) {
		
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
		
		double [] result = settingRANDDiceRolls ( getNum_rolls (), getdX() );
		
		setDiceRolls ( result );
		
	}
	
	public static double [] settingRANDDiceRolls (int num_rolls, int dX ) {
		
		double[] resulting_rolls = new double[num_rolls];
		
		for ( int i = 0; i < num_rolls; i++ ) {
			
			resulting_rolls[i] = Math.ceil ( Math.random () * dX );
			
		}
		
		return resulting_rolls;
		
	}
	


//ERRORS	
	public static void error ( String val ) {
		
		System.out.println ( val );
		
	}
		
	private int checkValues ( int pos, double val ) {
		
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
	public String toString_DiceRolls() {
		/* For looping "val," from 1st roll to (N-1)th as Final Roll doesn't need  a ,
		So a { is added at the beginning then after the for loop val}
		*/
		
		String answer  = "{";
		
		for (int i=0; i < getNum_rolls() -1; i++ ) {
			
			answer += this.getdiceArr(i) +", ";
		}
		
		return answer +getdiceArr ( getNum_rolls () -1 ) +"}"; //final element as java starts at 0
		
	}
	
//PRINTERS
	public void printDiceRolls() {
		
		System.out.println( toString_DiceRolls() );
		
	}
	
	private static void print ( Object anything ) {
		
		System.out.println ( anything );
	}

//CALCULATIONS	

	public double getSumOfRolls () {
		
		//SUM ALL ROLLS FROM 1st Dice to Nth Dice 
		
		double answer = 0;
		
		for ( int i=0; i <getLength (); i++ ) {
			
			answer += getdiceArr(i);
		}
		
		return answer;
	}

	public double getMeanOfRolls () {
		
		//SUM/AMOUNT
		
		return getSumOfRolls() / getNum_rolls();
		
	}
	
	public double getVarianceOfRolls () {
		
		/*Calculating Variance of the Rolls by: summing the squares of the difference of each dice roll from
		the mean then dividing it by the number of rolls*/
		
		double sumOfSquares = 0;
		double mean = getMeanOfRolls ();
		
		for ( int i=0; i < getLength(); i++ ) {
			
			double difference = getdiceArr(i) - mean;
			sumOfSquares += difference * difference;
			
		}
		
		return sumOfSquares / getNum_rolls();
		
	}
	
	public int getSmallestValPos () {
		
		//Give you the position of the smallest value rolled
		//So it starts at 0 then changes to i if the value of it at i is lower
		
		int min_val_pos = 0;
		
		for ( int i = 0; i < getLength(); i++ ) {
			
			if ( getdiceArr ( i ) <= getdiceArr ( min_val_pos ) )
				
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
		this.rolls = getNum_rolls;
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
		
		public static double expectedVal ( int num_rolls, int dX ) {
			
			//The Expected Value for N dXs is N(0.5dX(dX+1))/dX
			
			return ( num_rolls * 0.5 * dX * ( dX +1 ) ) / dX;
			
		}

		public static double expectedVal ( int dX ) {
	
			return expectedVal ( 1, dX );
		
		}
	
		public static double variance ( int num_rolls, int dX ) {
			
			//The Variance of N dXs is N/12 ((dX)^2 - 1)
	
			return ( num_rolls * ( dX * dX -1 ) ) / 12;
			
		}
	
		public static double variance ( int dX ) {
			
			return variance ( 1, dX );
		
		}
	
		public static double standardDeviation (int num_rolls, int dX ) {
			
			//Standard Deviation is the sqrt of the variance
			return Math.sqrt ( variance ( num_rolls, dX ) );
			
		}
	
		public static double standardDeviation ( int dX ) {
			
			return Math.sqrt ( variance ( dX ) );
		
		}
	
		
		public static double expVal_plus_N_SD ( double expVal, int N, double standDev ) {
			
			//For Things Outside Of Dice!
			return expVal + N * standDev;
		}
		
		public static double expVal_plus_N_SD ( int N, int num_rolls, int dX ) {
			
			return expectedVal ( num_rolls, dX ) + N * standardDeviation ( num_rolls, dX );
			
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
		print ( a.getNum_rolls() );
		print ( b.getNum_rolls() );
	
		print ( "getdX" );
		print ( a.getdX() );
		print ( b.getdX() );
	
		print( "getdiceArr" );
		for ( int i = 0 ; i < a.getNum_rolls() ; i++ ) {
			print( a.getdiceArr(i) );
		}
		
		print ( "getLength" );
		print ( a.getLength() );
		print ( b.getLength() );
		
		//SETTERS
		print ( "setDiceVal" );
		a.setDiceVal( 1, 9 );
		a.printDiceRolls ();
		
		print ( "setDiceRolls" );
		double[] roll = { 1, 2, 3, 4, 5, 6, 8 };
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
	}
}