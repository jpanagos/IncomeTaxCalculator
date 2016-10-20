package outputmanager;

import java.io.FileNotFoundException;

public class TextOutputWriter extends OutputWriter {
	
	public TextOutputWriter(String filePath) throws FileNotFoundException {
		super(filePath);
	}

	public void write(String key, String value) {
		String line = key + ": " + value;
		super.printLine(line);
	}
	
	public void write(String text) {
		super.printLine(text + ": ");
	}

}
