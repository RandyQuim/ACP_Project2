/**
 * A class to handle a manually written linked list stack using a LIFO
 * structure. Its abstract data types are used by the java API version of a
 * linked list in a separate class. The two classes differ only in concrete data
 * types.
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 2 File Name: LinkedStack.java
 */
public class LinkedStack {
	/**
	 * The node representing the head of the linked list
	 */
	private Node head;

	/**
	 * Constructs a LinkedStack object with a default value (null) for the head. A
	 * necessary step in a linked list implementation.
	 */
	public LinkedStack() {
		head = null;
	}

	/**
	 * A private class representing a node with String values in the linked list
	 * and references the next node in the list (if one exists) using the 'next'
	 * instance field
	 */
	private class Node {
		private String value;
		private Node next;
	}

	/**
	 * Pushes a String object onto the linked list
	 * 
	 * @param digit an individual digit within a large integer number
	 */
	public void push(String digit) {
		Node tmp = new Node();
		tmp.value = digit;
		tmp.next = head;
		head = tmp;
	}

	/**
	 * Pops the next value off the list/stack or returns null if the list is empty
	 * using a LIFO structure.
	 * 
	 * @return the String value of a digit
	 */
	public String pop() {
		if (head == null) {
			return null;
		} else {
			Node tmp = head;
			head = head.next;
			return tmp.value;
		}
	}

	/**
	 * Creates a linked stack of individual digits that, as a whole, are made up of a
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
