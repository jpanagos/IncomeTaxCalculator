package outputmanager;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public abstract class LogWriter {

	private FileOutputStream outputStream = null;
	private PrintWriter outputWriter = null;

	public LogWriter() throws FileNotFoundException {
	}

	public abstract void initialize(int afm);

	protected void initializeOutput(final String fileName) {
		try {
			outputStream = new FileOutputStream(fileName);
		} catch (FileNotFoundException exception) {
			System.out.println("Error opening output file.");
			System.exit(0);
		}
		initializeOutputWriter();
	}

	private void initializeOutputWriter() {
		outputWriter = new PrintWriter(outputStream);
	}

	public abstract void write(String key, String value);

	public void printLine(String line) {
		outputWriter.println(line);
	}

	public void close() {
		outputWriter.close();
	}

}
