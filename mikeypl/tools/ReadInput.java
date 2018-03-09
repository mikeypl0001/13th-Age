package mikeypl.tools;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.util.NoSuchElementException;
//import java.io.Console;

public class ReadInput {
	
	private Scanner input;
	private InputStream in;
	
	public ReadInput() {
		
		System.out.println("This only works once :(");
	}
	
	public String readString(String message) {
		
		System.out.println(message);
		String output = "";
			
		this.input = new Scanner(System.in);
		output = this.input.nextLine();
		this.input.close();
			
		return output;
		
	}
	
	public int readInt(String message) {
		
		int output = 0;
		boolean exception = true;
		
		do {
			try {
				
				System.out.println(message);
				input = new Scanner(System.in);
				output = input.nextInt();
				exception = false;
				input.close();
				
			} catch (InputMismatchException e) {
				
				System.out.println("Try Again!");
				
			}
			
		} while (exception);
		
		return output;
	}
	
	
	
	public static void main(String args[]) {
		
		ReadInput a = new ReadInput();
		ReadInput b = new ReadInput();
		System.out.println(a.readString("What is Your Name?"));
		try {
			System.out.println(b.readString("What is Your Dad's Name?"));
		} catch (NoSuchElementException e) {
				System.out.println("Why do you hate me java :(");
		}
		
	}
	
	
}