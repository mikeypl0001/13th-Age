package mikeypl.tools;

public class TextAndDisplay {
	
	
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
		
	
	
	public static void print(Object input) {
		
		System.out.println(input);
	}
	

}