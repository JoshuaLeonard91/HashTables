
/**
 * <b>COP 3530: Project 5 – Hash Tables </b>
 * <p>
 * This class handles inserting,deleting,finding and printing in the Linked List
 * within each cell of the hash table.
 * 
 * <pre>
 * {@linkplain #LinkedList() LinkedList}    : The constructor for the linked list. 
 * {@linkplain #find(String, int) find}          : This method handles finding the country, if found will print out data and return the index, if not returns -1. 
 * {@linkplain #insert(String,long,long) insert}        : The insert method handles inserting country objects into the list at the given cell.
 * {@linkplain #delete(String) delete}        : The delete method handles deleting country objects into the list at the given cell.
 * {@linkplain #isEmpty() isEmpty}       : A method to check whether the list is empty.
 * {@linkplain #getNextNode(Node) getNextNode}   : A method to return the next node of the current node.
 * </pre>
 * 
 * @author Josh
 * @version 12/9/2022
 */
public class LinkedList {
	public Node first; // ref to first list item
	public Node last;

	/**
	 * The constructor for the class, sets the first node to null.
	 */
	public LinkedList() // constructor
	{
		first = null;
		last = null;
	}

	/**
	 * <b>The insert method in the Linked List class file works with the find method
	 * from the hash table java file, that works with the project5 class file.</b>
	 * 
	 * <pre>
	 * This method is does most of the work while the previous calls got the correct
	 * cell to find the data. It returns -1 if not found or prints out the data and
	 * returns the index it was found in.
	 * </pre>
	 * 
	 * @param name    string name
	 * @param hashVal index
	 * @return if found will return the hash val
	 */
	public int find(String name, int hashVal) {
		Node current = first;
		while (current != null && !(current.name.equals(name))) {
			if (current.nextNode == null) {
				return -1;
			} else {
				current = current.nextNode;
			}
		}
		System.out.printf("%s is found at index %d with case rate of %.3f\n\n", current.name, hashVal,
				(double) current.cases / current.population * 100000);
		return hashVal;
	}

	/**
	 * <b>The insert method in the Linked List class file works with the find method
	 * from the hash table java file, that works with the project5 class file.</b>
	 *
	 * <pre>
	 * This method inserts the new node at the end of the list.
	 * </pre>
	 *
	 * @param name
	 * @param population
	 * @param cases
	 */
	public void insert(String name, long population, long cases) {
		Node theNode = new Node(name, population, cases);
		if (isEmpty()) {
			first = theNode;
		} else {
			last.nextNode = theNode;
		}
		last = theNode;
	}

	/**
	 * <b>The delete method in the Linked List class file works with the find method
	 * from the hash table java file, that works with the project5 class file.</b>
	 * 
	 * <pre>
	 * This method deletes the node from the list, it iterates through each node 
	 * until the node is found then it will delete the. If the node is not found 
	 * it will let the user know it was not found. If found it will print out 
	 * the country was was deleted.
	 * </pre>
	 * 
	 * @param name
	 * @return Node
	 */
	public Node delete(String name) {
		Node current = first;
		Node previous = first;
		while (!(current.name.equals(name))) {
			if (current.nextNode == null) {
				System.out.println("Could not find the country with given name.\n");
				return null;
			} else {
				previous = current;
				current = current.nextNode;
			}
		}
		if (current == first) {
			first = first.nextNode;
		} else if (current == last) {
			previous.nextNode = null;
			last = previous;
		} else {
			previous.nextNode = current.nextNode;
		}
		System.out.println("\nThe country with the name " + name + " was deleted from the hash table.\n");
		return current;
	}

	/**
	 * This method checks whether the list at the current cell/index is empty.
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return (first == null);
	}

	/**
	 * Method that returns the next node of the node that is pass in.
	 * 
	 * @param current
	 * @return node
	 */
	public Node getNextNode(Node current) {
		return current.nextNode;
	}

	/**
	 * A helper method for display list, used to initiate the recursive method.
	 */
	public void displayList() {
		first.printNode();
		displayList(first.nextNode);
	}

	/**
	 * This method recursively prints out the lists data until it reaches the base
	 * case.
	 * 
	 * @param first
	 * @return the next node
	 */
	public Node displayList(Node first) {
		Node current = first;
		if (current == null) {// base case
			return null;
		} else { // recursive case
			System.out.print("     ");
			current.printNode();
		}
		return displayList(current.nextNode);
	}

	/**
	 * <b>COP 3530: Project 5 – Hash Tables</b>
	 * <p>
	 * The Node class is used to store data for the binary search tree, as well as
	 * the leftChild reference and rightChild reference.
	 * 
	 * <pre>
	 * name: Country name
	 * population: Country population
	 * cases: Covid Cases
	 * nextNode: the pointer to next node.
	 * </pre>
	 * 
	 * <pre>
	 * <b>Methods:</b>
	 * {@linkplain #Node(String, long,long) Node}: creates a node with this data
	 * {@linkplain #printNode() printData}: prints the nodes name and gdp per capita that is formatted
	 * </pre>
	 * 
	 * @author Joshua Leonard
	 * @version 12/2/2022
	 *
	 */
	private class Node {
		String name;
		long population;
		long cases;
		Node nextNode;

		/**
		 * Constructor to create a Node with the data for the country name, countries
		 * population, and countries cases.
		 *
		 * @param name
		 * @param population
		 * @param cases
		 */
		public Node(String name, long population, long cases) {
			this.name = name;
			this.population = population;
			this.cases = cases;
		}

		/**
		 * This method is used to print out each nodes data that is formatted to work
		 * with the print data method in the project5 class file.
		 */
		public void printNode() {
			System.out.printf("%-30s %-20.3f\n", name, (double) cases / population * 100000);
		}
	}
}
