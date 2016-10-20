package outputmanager;

import java.io.FileNotFoundException;

public class TextLogWriter extends LogWriter {
	
    public TextLogWriter() throws FileNotFoundException {
    	super();
    }

    public void initialize(int afm) {
    	String fileName = afm + "_LOG.txt";
    	super.initializeOutput(fileName);
    }

	public void write(String key, String value) {
		String line = key + ": " + value;
		super.printLine(line);
	}

}
