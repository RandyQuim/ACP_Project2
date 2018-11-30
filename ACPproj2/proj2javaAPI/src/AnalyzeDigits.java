
/**
 * A class to handle analysis of arbitrarily large integer expressions as
 * Strings. The class adds and subtracts individual digits within the integers
 * by first deciding the appropriate signs given to each number (negative or
 * positive) and the sign of the operator, decides which number is bigger and
 * swaps positions if the number on the bottom is larger, sets carries and
 * borrows, and produces the results.
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 2 File Name: AnalyzeDigits.java
 */
public class AnalyzeDigits {
	/**
	 * Constant representing the amount the current digit requires for a borrow
	 * operation
	 */
	private static final int BORROW = 10;
	/**
	 * Constant representing the space of a single digit for use in skipping and
	 * measuring digits
	 */
	private static final int SINGLE_DIGIT = 1;
	/**
	 * Constant representing the value of a carry (when adding) or a borrow (when
	 * subtracting) to be applied to the next iteration of numbers
	 */
	private static final int CARRY_AND_BORROW = 1;
	/**
	 * Constant for checking/accessing negative and positive signs on numbers
	 */
	private static final int FIRST_CHAR = 0;
	/**
	 * The first of two numbers in an expression
	 */
	private String numOne;
	/**
	 * The second of two numbers in an expression
	 */
	private String numTwo;
	/**
	 * The negative or positive operand sign
	 */
	private String operatorSign;
	/**
	 * Represents/holds the value of the larger number in numerical expressions
	 */
	private String bigger;
	/**
	 * The result of addition and subtraction expressions
	 */
	private String results;
	/**
	 * Identifies whether subtracting digits requires a borrow 
	 */
	private boolean borrow;
	/**
	 * Identifies whether adding digits requires a carry
	 */
	private boolean carry;
	/**
	 * Identifies whether numerical values in expressions need to be swapped (when
	 * the second number is larger)
	 */
	private boolean swap;
	/**
	 * The sign of the first number in an equation (positive or negative)
	 */
	private String firstNumSign;
	/**
	 * The sign of the second number in an equation (positive or negative)
	 */
	private boolean secondNumNegative;

	/**
	 * Constructs an AnalyzeDigits object with default values as instance fields 
	 */
	public AnalyzeDigits() {
		this.numOne = "";
		this.numTwo = "";
		this.operatorSign = "";
		this.results = "";
		this.bigger = "";
		this.firstNumSign = "";
		this.secondNumNegative = false;
		this.borrow = false;
		this.carry = false;
		this.swap = false;
	}

	/**
	 * Determines the signs (negative or positive) of each number (not the operator)
	 * for future calculation and produces a substring of the number without its sign
	 */
	public void decideNegatives() {
		if (getNumTwo().charAt(FIRST_CHAR) == '-') {
			setNumTwo(getNumTwo().substring(SINGLE_DIGIT));
			setSecondNumNegative(true);
		} else {
			setSecondNumNegative(false);
		}
		if (getNumOne().charAt(FIRST_CHAR) == '-') {
			setNumOne(getNumOne().substring(SINGLE_DIGIT));
			setFirstNumSign("-");
		} else {
			setNumOne(getNumOne().substring(SINGLE_DIGIT));
			setFirstNumSign("+");
		}

		pad();
	}

	/**
	 * Tests whether a number swap is necessary.  Sets a flag and calls a swap
	 * method if it is. A number swap is necessary if the bottom number is larger
	 * than the top and the expression requires subtraction.
	 */
	private void testForSwap() {
		if (getFirstNumSign().equals("-") && getOperandSign().equals("-") && isSecondNumNegative()
				&& getBigger().equals(getNumTwo())
				|| getFirstNumSign().equals("-") && !getOperandSign().equals("-") && !isSecondNumNegative()
						&& getBigger().equals(getNumTwo())
				|| !getFirstNumSign().equals("-") && !getOperandSign().equals("-") && isSecondNumNegative()
						&& getBigger().equals(getNumTwo())
				|| getBigger().equals(getNumTwo()) && getFirstNumSign().equals("+") && getOperandSign().equals("-")
						&& !isSecondNumNegative()) {
			setSwap(true);
			swap();
		}
	}

	/**
	 * Sets the boolean condition to the parameter value
	 * 
	 * @param swap the truth value of a number swap
	 */
	public void setSwap(boolean swap) {
		this.swap = swap;
	}

	/**
	 * Returns the truth value of a number swap
	 * 
	 * @return
	 */
	public boolean isSwap() {
		return swap;
	}

	/**
	 * Swaps the numbers if the bottom number is larger
	 * than the top and the expression requires subtraction
	 */
	public void swap() {
		String tmp1 = "";
		String tmp2 = "";
		tmp1 = getNumOne();
		tmp2 = getNumTwo();
		setNumOne(tmp2);
		setNumTwo(tmp1);
	}

	/**
	 * Swaps the numbers back to their original position to display the original
	 * expression in proper/original form
	 */
	public void reSwap() {
		if (isSwap()) {
			setSwap(false);
			swap();
		}
	}

	/**
	 * Pads the larger number with one zero and the smaller with enough zeroes to
	 * match the larger. For use in calculations.
	 */
	private void pad() {
		String numOne = getNumOne();
		String numTwo = getNumTwo();
		if (getNumOne().length() > getNumTwo().length()) {
			numOne = "0" + numOne;
			while (numOne.length() > numTwo.length()) {
				numTwo = "0" + numTwo;
			}
			setBigger(numOne);
			setNumOne(numOne);
			setNumTwo(numTwo);
		} else {
			numTwo = "0" + numTwo;
			while (numTwo.length() > numOne.length()) {
				numOne = "0" + numOne;
			}
			setBigger(numTwo);
			setNumOne(numOne);
			setNumTwo(numTwo);
		}

		testForSwap();
	}

	/**
	 * Returns true if the expression requires subtraction and false otherwise
	 * 
	 * @return the boolean value of subtraction
	 */
	public boolean analyzeSigns() {
		if (getFirstNumSign().equals("-") && !getOperandSign().equals("-") && !isSecondNumNegative()
				&& getBigger().equals(getNumTwo())
				|| !getFirstNumSign().equals("-") && getOperandSign().equals("-") && !isSecondNumNegative()
				|| !getFirstNumSign().equals("-") && !getOperandSign().equals("-") && isSecondNumNegative()
				|| getFirstNumSign().equals("-") && getOperandSign().equals("-") && isSecondNumNegative()
						&& getBigger().equals(getNumTwo())
				|| getFirstNumSign().equals("-") && getOperandSign().equals("-") && isSecondNumNegative()
						&& getBigger().equals(getNumOne())
				|| getFirstNumSign().equals("-") && getOperandSign().equals("+") && !isSecondNumNegative()
						&& getBigger().equals(getNumOne())
				|| isSwap()) {
			return true;
		}
		return false;
	}

	/**
	 * Performs addition and sets a carry condition if a carry is required for the
	 * next iteration. Turns the numbers back into a String after calculations and
	 * returns the value of the addition.
	 * 
	 * @param digit1 the first digit to add
	 * @param digit2 the second digit to add
	 * @return the result of the addition calculation
	 */
	public String add(int digit1, int digit2) {
		String result = "";
		if (isCarry()) {
			digit1 = digit1 + CARRY_AND_BORROW;
		}
		result = String.valueOf(digit1 + digit2);
		if (result.length() > SINGLE_DIGIT) {
			result = String.valueOf(result.charAt(SINGLE_DIGIT));
			setCarry(true);
		} else {
			setCarry(false);
		}

		return result;
	}

	/**
	 * Performs subtraction and sets a borrow condition if a borrow is required from
	 * the digits in the next iteration and adds a borrow value (10) to the current
	 * digit. Turns the numbers back into a String after calculations and returns
	 * the value of the subtraction (the result).
	 * 
	 * @param digit1 the first digit to subtract
	 * @param digit2 the second digit to subtract
	 * @return the result of the subtraction operation
	 */
	public String subtract(int digit1, int digit2) {
		String result = "";

		if (isBorrow()) {
			digit1 = digit1 - CARRY_AND_BORROW;
		}
		if (digit1 < digit2) {
			digit1 = BORROW + digit1;
			setBorrow(true);
		} else {
			setBorrow(false);
		}

		result = String.valueOf(digit1 - digit2);
		return result;
	}

	/**
	 * Concatenates the resulting digits after subtraction or addition into a String
	 * to display to the console
	 * 
	 * @param stack3 the result stack
	 */
	public void concatenate(LinkedStack stack3) {
		for (int i = 0; i < getNumOne().length(); i++) {
			String digit = stack3.pop();
			results = results + digit;
		}

	}

	/**
	 * Returns the concatenated result of the expressions with a negative or a
	 * positive sign, depending on the form of the operation
	 * 
	 * @return the resulting number (negative or positive) 
	 */
	public String getResult() {
		return resultingSign() + results;
	}

	/**
	 * Returns the final sign of the result
	 * 
	 * @return the final sign of the result
	 */
	private String resultingSign() {
		String finalSign = " ";
		if (getFirstNumSign().equals("-") && getOperandSign().equals("+") && isSecondNumNegative()
				|| getFirstNumSign().equals("-") && getOperandSign().equals("-") && !isSecondNumNegative()
				|| getBigger().equals(getNumOne()) && getFirstNumSign().equals("-") && getOperandSign().equals("+")
						&& !isSecondNumNegative()
				|| getBigger().equals(getNumOne()) && getFirstNumSign().equals("-") && getOperandSign().equals("-")
						&& isSecondNumNegative()
				|| getBigger().equals(getNumTwo()) && getFirstNumSign().equals("+") && getOperandSign().equals("+")
						&& isSecondNumNegative()
				|| getBigger().equals(getNumTwo()) && getFirstNumSign().equals("+") && getOperandSign().equals("-")
						&& !isSecondNumNegative()) {
			finalSign = "-";
		} else
			finalSign = "+";

		return finalSign;
	}

	/**
	 * Returns the sign (negative or positive) of the second number
	 * 
	 * @return the sign of the second number
	 */
	public String reformatSign() {
		String sign = "";
		if (isSecondNumNegative()) {
			sign = "-";
		} else
			sign = "+";
		return sign;
	}

	/**
	 * Sets the numerical results of an expression to the parameter value
	 * 
	 * @param results the results of an expression
	 */
	public void setResults(String results) {
		this.results = results;
	}

	/**
	 * Returns the bigger number in the expression
	 * 
	 * @return the bigger number in the expression
	 */
	private String getBigger() {
		return bigger;
	}

	/**
	 * Sets the bigger number in the expression to the parameter value
	 * 
	 * @param bigger the bigger number in the expression
	 */
	private void setBigger(String bigger) {
		this.bigger = bigger;
	}

	/**
	 * Returns the first number in the expression
	 * 
	 * @return the first number in the expression
	 */
	public String getNumOne() {
		return numOne;
	}

	/**
	 * Sets the first number in the expression to the parameter value
	 * 
	 * @param numOne the first number in the expression
	 */
	public void setNumOne(String numOne) {
		this.numOne = numOne;
	}

	/**
	 * Returns the second number in the expression
	 * 
	 * @return the second number in the expression 
	 */
	public String getNumTwo() {
		return numTwo;
	}

	/**
	 * Sets the second number in the expression to the parameter value
	 * 
	 * @param numTwo the second number in the expression
	 */
	public void setNumTwo(String numTwo) {
		this.numTwo = numTwo;
	}

	/**
	 * Sets the operand sign to the parameter value
	 * 
	 * @param operandSign the operand sign
	 */
	public void setOperandSign(String operandSign) {
		this.operatorSign = operandSign;
	}

	/**
	 * Returns the operand sign
	 * 
	 * @return the operand sign
	 */
	public String getOperandSign() {
		return operatorSign;
	}

	/**
	 * Returns the sign of the first number
	 * 
	 * @return the sign of the first number
	 */
	public String getFirstNumSign() {
		return firstNumSign;
	}

	/**
	 * Sets the sign of the first number to the parameter value
	 * 
	 * @param firstNumSign the sign of the first number
	 */
	public void setFirstNumSign(String firstNumSign) {
		this.firstNumSign = firstNumSign;
	}

	/**
	 * Returns the condition of the second number's sign on whether it is negative.
	 * Used to test conditions in various ways.
	 * 
	 * @return the boolean condition on whether the second number's sign is negative
	 */
	public boolean isSecondNumNegative() {
		return secondNumNegative;
	}

	/**
	 * Sets the boolean condition on whether the second number's sign is negative to
	 * the parameter value
	 * 
	 * @param negative the boolean condition on whether the second number's sign is
	 * negative
	 */
	private void setSecondNumNegative(boolean negative) {
		this.secondNumNegative = negative;
	}

	/**
	 * Returns the boolean condition on whether an addition operation requires a
	 * carry
	 * 
	 * @return the boolean condition on whether an addition operation requires a
	 * carry
	 */
	private boolean isCarry() {
		return carry;
	}

	/**
	 * Returns the boolean condition on whether a subtraction operation requires a
	 * borrow
	 * 
	 * @return the boolean condition on whether a subtraction requires a borrow
	 */
	private boolean isBorrow() {
		return borrow;
	}

	/**
	 * Sets the boolean condition on whether an addition operation requires a
	 * carry to the parameter value
	 * 
	 * @param carry boolean condition on whether an addition operation requires a
	 * carry
	 */
	private void setCarry(boolean carry) {
		this.carry = carry;
	}

	/**
	 * Sets the boolean condition on whether a subtraction operation requires a
	 * borrow to the parameter value
	 * 
	 * @param borrow boolean condition on whether a subtraction operation requires a
	 * borrow
	 */
	private void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}

}
