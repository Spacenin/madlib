package madlib;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class Menu {
	
	/***
	 * Scanner object for Menu class
	 */
	private static Scanner sc = new Scanner(System.in);
	
	/***
	 * Write Madlib from command line and user input
	 * @param None
	 * @return None
	 */
	public static void fromCL() {
		//Get user entered string
		System.out.println();
		System.out.println("Enter the passage you'd like to Madib");
		System.out.print("--> ");
		
		String userString = sc.nextLine();
		String stringArrayd[] = userString.split(" ");
		stringArrayd = Helpers.replaceWords(stringArrayd);
		
		//Print out each word
		System.out.println();
		System.out.println("<--Madlib'd version-->");
		
		for (String each : stringArrayd) {
			System.out.print(each + " ");
		}
		
		System.out.println();
		System.out.println("<-------------------->");
		System.out.println();
	}
	
	/***
	 * Write Madlib from file
	 * @param None
	 * @return None
	 */
	public static void fromFile() {
		//Get filename and read from file
		System.out.println();
		System.out.println("Enter the name of the file you'd like to Madlib");
		System.out.print("--> ");
		
		String fileName = sc.nextLine();
		ArrayList<String> userArray = new ArrayList<String>();
		
		//Hold user until file is found
		Path filepath = Paths.get(fileName);
		while (Files.notExists(filepath)) {
			System.out.println("File not found! Please enter again: ");
			System.out.print("--> ");
			fileName = sc.nextLine();
			
			filepath = Paths.get(fileName);
		}
		
		File file = filepath.toFile();
		
		//Create reader and read each line, splitting and putting into arrayList
		try (BufferedReader read = new BufferedReader(new FileReader(file))) {
			String line;
			
			while ((line = read.readLine()) != null) {
				String lineArray[] = line.split(" ");
				
				for (String each : lineArray) {
					userArray.add(each);
				}
			}
		} catch (IOException exc) {
			System.out.println(exc);
		}
		
		String arrayNormald[] = userArray.toArray(new String[userArray.size()]);
		arrayNormald = Helpers.replaceWords(arrayNormald);
		
		//Print out each word
		System.out.println();
		System.out.println("<--Madlib'd version-->");
		
		for (String each : arrayNormald) {
			System.out.print(each + " ");
		}
		
		System.out.println();
		System.out.println("<-------------------->");
		System.out.println();
	}
	
	/***
	 * Prints format guidelines to command line
	 * @param None
	 * @return None
	 */
	public static void format() {
		//This just prints the format screen
		System.out.println();
		System.out.println("<--------------------------------------------------------------------------------->");
		System.out.println("Whenever entering a string, please enter as follows:");
		System.out.println("'Hello, my name is <noun> and I am <adj>. I enjoy <verb>.'");
		System.out.println("The type of word should be encased in <> with either noun, adj, or verb.");
		System.out.println("If it does not follow that exact input formatting, program will not fill correctly.");
		System.out.println("<--------------------------------------------------------------------------------->");
		System.out.println();
		
		//:p
	}
	
}
