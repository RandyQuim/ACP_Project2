import java.io.IOException;

/**
 * A class to test a digit analysis class using expressions bigger than the
 * integer type can hold to add and subtract. It is done as strings through use
 * of several linked list stacks. This test class is implemented to use both
 * java's linkedList class as well as a linked list class created manually.
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 2 File Name: StackTester.java
 */
public class StackTester {

	public static void main(String[] args) throws IOException {
		File file = new File();
		AnalyzeDigits digits = new AnalyzeDigits();
		LinkedStack stack1 = new LinkedStack();
		LinkedStack stack2 = new LinkedStack();
		LinkedStack stack3 = new LinkedStack();
		LinkedStack stack = new LinkedStack();
		file.open("addsAndSubtracts.txt");
		boolean endOfFile = false;
		while (!endOfFile) {
			endOfFile = file.read(digits);
			digits.decideNegatives();
			stack1.createStacks(stack1, digits.getNumOne());
			stack2.createStacks(stack2, digits.getNumTwo());
			stack.buildResultStack(stack1, stack2, stack3, digits);
			digits.concatenate(stack3);
			String sign = digits.reformatSign();
			digits.reSwap();
			System.out.println(" " + digits.getFirstNumSign() + digits.getNumOne() + "\n" + digits.getOperandSign() + sign
					+ digits.getNumTwo());
			System.out.println("_______________________________");
			System.out.println(" " + digits.getResult() + "\n");
			digits.setResults("");
		}
		file.close();
	}

}
