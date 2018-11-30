import java.util.LinkedList;

/**
 * A class to take advantage of the abstract data types of a manually written
 * linked list class. This class uses the java.util.LinkedList API to implement
 * the stack.  It differs only in its concrete implementation.
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 2 File Name: LinkedStack.java
 */
public class LinkedStack {
	/**
	 * The LinkedList to manipulate stacks
	 */
	private LinkedList<String> list;
	
	/**
	 * Creates a LinkedStack object and instantiates a new LinkedList object for
	 * stack manipulation
	 */
	public LinkedStack() {
		this.list = new LinkedList<String>();
	}
	
	/**
	 * Pushes a String object onto the linked list
	 * 
	 * @param digit an individual digit within a large integer number
	 */
	public void push(String digit) {
		list.addFirst(digit);
	}
	
	/**
	 * Pops the next value off the list/stack or returns null if the list is empty
	 * using a LIFO structure.  Simply calls the java LinkedList method pop. 
	 * 
	 * @return the String value of a digit
	 */
	public String pop() {
		return list.pop();
    }
	
	/**
	 * Creates a linked stack of individual digits that, as a whole, are made up a
	 * large integer number.
	 * 
	 * @param stack the stack to be created
	 * @param numOne the number to be broken up into digits on the stack
	 */
	public void createStacks(LinkedStack stack, String numOne) {
		for (int i = 0; i < numOne.length(); i++) {
			String temp = String.valueOf(numOne.charAt(i));
			stack.push(temp);
		}
	}
	
	/**
	 * Builds a result stack by adding or subtracting the numbers from two
	 * individual stacks of strings by changing their type to integers and placing
	 * their result - as a String - in a third stack.
	 * 
	 * @param stack1 the stack representing the first big integer
	 * @param stack2 the stack representing the second big integer
	 * @param stack3 the stack to hold the result of adding or subtracting digits from
	 * the first and second stack
	 * @param digits the AnalyzeDigits class object to perform sign analysis and
	 * addition and subtraction
	 */
	public void buildResultStack(LinkedStack stack1, LinkedStack stack2, LinkedStack stack3, AnalyzeDigits digits) {
		for (int i = 0; i < digits.getNumOne().length(); i++) {
			String result = "";
			int digit1 = Integer.parseInt(stack1.pop());
			int digit2 = Integer.parseInt(stack2.pop());
			if (digits.analyzeSigns()) {
				result = digits.subtract(digit1, digit2);
			} else
				result = digits.add(digit1, digit2);
			stack3.push(result);
		}
	}

}