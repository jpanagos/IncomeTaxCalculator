package outputmanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public abstract class OutputWriter {

	private FileOutputStream outputStream = null;
	private PrintWriter outputWriter = null;

	protected OutputWriter(String filePath) throws FileNotFoundException {
		initializeOutputStream(filePath);
		initializeOutputWriter();
	}

	protected void initializeOutputStream(String filePath) {
		try {
			outputStream = new FileOutputStream(filePath);
		 } catch (FileNotFoundException exception) {
			System.out.println("Error opening output file.");
			System.exit(0);
		}
	}

	private void initializeOutputWriter() {
		outputWriter = new PrintWriter(outputStream);
	}

	public abstract void write(String key, String value);
	
	public abstract void write(String text);
	
	public void writeEmpty() {
		outputWriter.println();
	}

	protected void printLine(String line) {
		outputWriter.println(line);
	}

	public void close() {
		outputWriter.close();
	}

}
