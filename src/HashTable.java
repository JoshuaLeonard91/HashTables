/**
 * <b>COP 3530: Project 5 – Hash Tables </b>
 * <p>
 * This class handles inserting, deleting, and printing for the hash table. <br>
 * <b>Methods:<br>
 * </b>
 * 
 * <pre>
 * {@linkplain #HashTable(int) HashTable} : Constructor for the Hash Table class file. 
 * {@linkplain #insert(String, long, long) insert}    : Method for finding the hash value and calling on the insert method from the Linked List java file. 
 * {@linkplain #delete(String) delete}    : Method for deleting a country object from the hash table, works alongside the Linked List deletion method. 
 * {@linkplain #find(String) find}      : This method works along with the find method in the Linked List class file.  
 * {@linkplain #hashFunc(int) hashFunc}  : This method is used to find the hash value and takes in the key from the sum of chars in unicode.
 * {@linkplain #key(String) key}       : This method is used to find the sum of the string, adding all the chars unicode value and is used by the hashFunc method. 
 * {@linkplain #getPrime(int) getPrime}  : This method is used to get the prime number and and calls upon the isPrime method.
 * {@linkplain #isPrime(int) isPrime}   : This method checks whether a number is prime or not.
 * {@linkplain #display() display}   : This method is used to display hash table in a clean format, it is called on by the find method in the project 5 class file.
 * {@linkplain #printEmptyAndCollidedCell() printEmptyAndCollidedCell} : This method is used to print out the number of empty cells and the number of collisions, that is a index with more than one country.
 * </pre>
 * 
 * @author Joshua Leonard
 * @version 12/1/2022
 */
public class HashTable {
	private LinkedList[] hashArray; // array holds hash table
	public int arraySize;
	public int cnt;

	/**
	 * The constructor for the class file, it takes in the initial amount of
	 * countries, times it by 2 then finds the first prime number, which will give
	 * us the actual size of the array. The method will then take the array size to
	 * create the array of linked list, and then fill the array with the linked
	 * list.
	 * 
	 * @param size amount of countries to be inserted in the hash table
	 */
	public HashTable(int size) // constructor
	{
		arraySize = getPrime(size * 2);
		hashArray = new LinkedList[arraySize]; // create array
		for (int j = 0; j < arraySize; j++) // fill array
			hashArray[j] = new LinkedList(); // with lists
	}

	/**
	 * <b>This method calculates the hash value with the key and hash function
	 * methods.</b>
	 * 
	 * <pre>
	 * It then uses the hash value to find the index to insert into, and calls on
	 * the insert method in the linked list class file.
	 * </pre>
	 * 
	 * @param country    name
	 * @param population population
	 * @param cases      covid cases
	 */
	public void insert(String country, long population, long cases) {
		int hashVal = hashFunc(key(country));
		hashArray[hashVal].insert(country, population, cases);
	}

	/**
	 * <b>This method calculates the hash value with the key and hash function
	 * methods.</b>
	 * 
	 * <pre>
	 * It then uses the hash value to find the index in which the
	 * country to be deleted is in, and calls on the delete method method in the
	 * linked list class file.
	 * </pre>
	 * 
	 * @param country the country name to be deleted
	 */
	public void delete(String country) {
		int hashVal = hashFunc(key(country));
		if (hashArray[hashVal].isEmpty()) {
			System.out.println("Could not find the country with given name.\n");
			return;
		}
		hashArray[hashVal].delete(country);
	}

	/**
	 * <b>This method calculates the hash value with the key and hash function
	 * methods.</b>
	 * 
	 * <pre>
	 * It then uses the hash value to find the index in which the country to be
	 * found is in, and calls on the find method method in the linked list class
	 * file. If found it will return the index, otherwise it will return -1.
	 * </pre>
	 * 
	 * @param key
	 * @return val
	 */
	public int find(String key) {
		int hashVal = hashFunc(key(key));
		if (hashArray[hashVal].isEmpty()) {
			return -1;
		}
		int val = hashArray[hashVal].find(key, hashVal);
		return val;
	}

	/**
	 * This method is the hash function, which will take the array size (293) and
	 * mod the key, the key is all the characters unicode value in the string
	 * summed.
	 * 
	 * <pre>
	 * Examples:
	 * 
	 * Iraq = 397 mod 293 = index 104
	 * Bulgaria = 807 mod 293 = index 221
	 * Kenya = 504 mod 293 = index  211
	 * United Kingdom = 1362 mod 293 = index 190
	 * Angola = 594 mod 293 = index 8
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public int hashFunc(int key) {
		return key % arraySize; // hash function
	}

	/**
	 * This method takes in a string, the country name, and splits it into an
	 * array(can do this with just a string and charAt), and adds up each characters
	 * unicode value to sum and returns it.
	 * 
	 * @param name
	 * @return
	 */
	public int key(String name) {
		char[] c = name.toCharArray();
		int key = 0;
		for (int i = 0; i < c.length; i++) {
			key += (int) c[i];
		}
		return key;
	}

	/**
	 * This method will return the 1st prime that is greater than the value passed
	 * in.
	 * 
	 * @param min array size
	 * @return the first prime number
	 */
	public int getPrime(int min) {
		for (int j = min + 1; true; j++) {
			if (isPrime(j)) {
				return j;
			}
		}
	}

	/**
	 * This method checks if the number passed in is prime or not.
	 * 
	 * @param n
	 * @return true or false
	 */
	public boolean isPrime(int n) {
		for (int j = 2; (j * j <= n); j++) {
			if (n % j == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * The display method will print out the index and the countries data, it will
	 * also use different spacings depending on j's value in the for loop to keep it
	 * clean and formatted.
	 */
	public void display() {
		for (int j = 0; j < arraySize; j++) {
			if (j < 10) {
				System.out.print(j + ".   ");
			} else if (j > 9 && j < 100) {
				System.out.print(j + ".  ");
			} else {
				System.out.print(j + ". ");
			}
			if (hashArray[j].first != null) {
				hashArray[j].displayList();
			} else {
				System.out.println("empty");
			}
		}
		System.out.println("");
	}

	/**
	 * The printEmptyAndCollidedCell will loop through the array of lists, and
	 * increment empty, if the index/cell was empty, or increment collision if the
	 * index has more than 1 county.
	 */
	public void printEmptyAndCollidedCell() {
		int empty = 0;
		int collided = 0;
		for (int i = 0; i < arraySize; i++) {
			if (hashArray[i].first == null) {
				empty++;
			}
			if (hashArray[i].first != null && hashArray[i].getNextNode(hashArray[i].first) != null) {
				collided++;
			}
		}
		System.out.println("There are " + empty + " empty cells and " + collided + " collisions in the hash table.\n");
	}
}
