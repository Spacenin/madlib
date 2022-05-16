package madlib;

public class Driver {

	public static void main(String[] args) {
		boolean looper = true;
		
		System.out.println("--Welcome to the Java Madlibs Machine!--");
		
		//Continues program until 4 is input
		while (looper) {
			System.out.println("1. Input string to Madlib");
			System.out.println("2. Input from file");
			System.out.println("3. See format");
			System.out.println("4. Quit");
			
			int userChoice = Helpers.getChoice();
			
			switch (userChoice) {
				//Command line option
				case 1:
					Menu.fromCL();
					
					break;
				//File option
				case 2:
					Menu.fromFile();
					
					break;
				//Print format guidelines option
				case 3:
					Menu.format();
					
					break;
				//Quit option
				case 4:
					looper = false;
					break;
			}
		}
		
		System.out.println();
		System.out.println("Bye bye till next time :)");
	}

}
