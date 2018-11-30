import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to handle file input of arbitrarily large integer numbers and
 * expressions
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 2 File Name: File.java
 */
public class File {
	/**
	 * The file to store and read large integer expressions
	 */
	private Scanner file;

	/**
	 * Constructs a File object that is not associated with a file
	 */
	public File() {
		this.file = null;
	}

	/**
	 * Opens the file. Closes the file if an attempt is made to open an already open
	 * file
	 * 
	 * @param fileName the name of the file
	 */
	public void open(String fileName) {
		try {
			if (file != null) {
				file.close();
			}

			file = new Scanner(new FileInputStream("addsAndSubtracts.txt"));
			file.useDelimiter(" |\n|\r");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the file only if it is already open
	 */
	public void close() {
		if (file != null) {
			file.close();
		}

		file = null;
	}

	/**
	 * Reads large integer data and expressions written to file (input) and returns
	 * true if there is no more data to read, false otherwise
	 */
	public boolean read(AnalyzeDigits digits) {
		String digit = file.next();
		digits.setNumOne(digit);
		String operandSign = file.next();
		digits.setOperandSign(operandSign);
		digit = file.next();
		digits.setNumTwo(digit);
		if (file.hasNext()) {
			file.nextLine();
			return false;
		}

		return true;
	}

}