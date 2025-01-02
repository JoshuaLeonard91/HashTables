
/*
 * Author: Joshua Leonard
 * Course: COP3530
 * Project #: 5
 * Title: Hash Tables
 * Due Date: 12/10/2022
 *
 * This project demonstrates the use of:
 * 1. Taking a string, adding all the values to get the sum (unicode) getting the hash value (using mod 293).
 * 2. Using a double-ended singly linked list in an array (293 indexes).
 * 3. Filling the hash table following the appropriate hash function.
 * 4. Dealing with collision in the hash table using Linked List (double ended singly linked list).
 * 5. Searching, finding and deleting objects from the hash table.
 */
import java.io.IOException;
import java.util.Scanner;

/**
 * <b>COP 3530: Project 5 – Hash Tables</b>
 * <p>
 * This is the main class file that handles the display menu, reading in the
 * user input for the file creating the hash table, and printing formatted
 * strings with country data.
 * 
 * @author Joshua Leonard
 * @version 11/28/2022
 */
public class Project5 {
	public static HashTable hTable;
	public static int nbrCountries;
	private static FileHandler fileHandler = new FileHandler();
	private static Scanner scnr = new Scanner(System.in);
	private static String fileName;

	/**
	 * <b>!Entry Point!</b>
	 * <p>
	 * This is the main function.
	 * <p>
	 * It prints out the the Project #, Who the instructor is, who the student is,
	 * students N#:, and the name of the project.
	 * <p>
	 * The program will loop until a correct file name is given, it will create a
	 * new hash table of linked lists(starting off as null).
	 * <p>
	 * If the file was successfully read it will insert the country objects into the
	 * binary search tree and print out how many countries were added into the tree
	 * and display the menu for the project as indicated in the directions.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner file = new Scanner(System.in);
		System.out.println(
				"COP3530 Project 5\nInstructor: Professor Liu\nStudent: Joshua Leonard\nN#: N01476308\nHash Tables\n");
		boolean flag = false;
		while (!flag) {
			try {
				System.out.print("Enter the file name: ");
				fileName = file.nextLine();
				System.out.println();
				nbrCountries = fileHandler.setSize(fileName);
				System.out.println("\nThere were " + nbrCountries + " country records read into the hash table.\n");
				hTable = new HashTable(nbrCountries);
				fileHandler.readCountry(fileName);
				displayMenu();
				flag = true;
			} catch (IOException e) {
				System.out.println("File not found: " + e.getMessage());
			}
		} // end while loop
		file.close();
	}// end main

	/**
	 * This method is the display menu, it will loop until the user selects 6 to
	 * quit the program. Each input will call on another method or methods to get
	 * the correct data.
	 * 
	 */
	public static void displayMenu() {
		boolean flag = true;
		boolean inputFlag = false;

		do {
			String input = selection(inputFlag);
			inputFlag = false;
			switch (input) {

			case "1":
				displayTable();
				break;
			case "2":
				deletetion();
				break;
			case "3":
				insertNew();
				System.out.println();
				break;
			case "4":
				findCountry();
				break;
			case "5":
				hTable.printEmptyAndCollidedCell();
				break;
			case "6":
				System.out.println("Programing Exiting");
				flag = false;
			}
		} while (flag);// end do-while
		scnr.close();
	}// end displayMenu

	/**
	 * This method is for the user selection, if the input does not match the
	 * required input it will let the user know it was an invalid input and ask
	 * again. If conditions are passed it will return the the users input back to
	 * the display menu.
	 * 
	 * <pre>
	 * 1: Print hash table
	 * 2: Delete a country of a given name
	 * 3: Insert a country of its name, population, and COVID cases
	 * 4: Search and print a country and its case rate for a given name
	 * 5: Print numbers of empty cells and collided cells
	 * 6: Quit Program
	 * </pre>
	 * 
	 * @param inputFlag
	 * @return input
	 */
	public static String selection(boolean inputFlag) {
		System.out.println("Please make a selection: ");
		System.out.println("1) Print hash table");
		System.out.println("2) Delete a country of a given name");
		System.out.println("3) Insert a country of its name, population, and COVID cases");
		System.out.println("4) Search and print a country and its case rate for a given name");
		System.out.println("5) Print numbers of empty cells and collided cells");
		System.out.println("6) Quit Program");

		String input = "";
		while (!inputFlag) {
			input = scnr.nextLine();
			if (input.matches("1|2|3|4|5|6")) {
				inputFlag = true;
			} else {
				System.out.println("Invalid Choice! Please enter 1-6:");
			} // end if/else
		} // end while
		return input;
	}

	/**
	 * This method will search the linked list at the proper position in the hash
	 * table and print out whether it was found, and if found will print the
	 * country, its index and the case rates.
	 * 
	 * <pre>
	 * This method calls on 2 other find methods in the hashTable class file, which
	 * calls another find method in the LinkedList Java class file.
	 * 
	 * #Please see other methods in which is called#
	 */
	public static void findCountry() {
		System.out.print("Enter country name: ");
		String searchName = scnr.nextLine();
		if (hTable.find(searchName) == -1) {
			System.out.println("Could not find the country with given name.\n");
		}
		scnr = new Scanner(System.in);
	}

	/**
	 * This method prompts the user for 3 inputs, the country name, population and
	 * covid cases and inserts it into the hash table.
	 * 
	 * <pre>
	 * This method calls on the insert table in the hash table class file, which
	 * calls on the insert method in the linked list java class file.
	 * </pre>
	 * 
	 * #Please see other methods in which is called#
	 */
	public static void insertNew() {
		System.out.print("Enter country name: ");
		String nName = scnr.nextLine();
		Long nPop = (long) 0;
		Long nCases = (long) 0;
		try {
			System.out.print("Enter country population: ");
			nPop = scnr.nextLong();
			System.out.print("Enter country COVID cases: ");
			nCases = scnr.nextLong();
		} catch (Exception e) {
			System.out.println("Invalid input, enter a integer number.");
		}
		hTable.insert(nName, nPop, nCases);
		scnr = new Scanner(System.in);
	}

	/**
	 * This method prompts the user for an input, the country name which is to be
	 * deleted.
	 * 
	 * <pre>
	 * This method then calls on the delete method in the hash table java class
	 * file, which calls on the linked list java class file.
	 * 
	 * #Please see other methods in which is called#
	 */
	public static void deletetion() {
		System.out.print("Enter country name: ");
		String dc = scnr.nextLine();
		hTable.delete(dc);
		scnr = new Scanner(System.in);
	}

	/**
	 * This method prints out the initial setup for the table to be displayed.
	 * 
	 * <pre>
	 * Will call on the display print method in the hash table java class file, if
	 * there are collisions within a hash value it will call on the linked list
	 * display method to print out the countries in the list.
	 */
	public static void displayTable() {
		String name = "Name";
		String gdppc = "Case Rate";
		System.out.printf("      %-30s %s\n", name, gdppc);
		System.out.println("--------------------------------------------------");
		hTable.display();
	}

}
