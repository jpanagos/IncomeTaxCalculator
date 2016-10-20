package outputmanager;

import java.io.FileNotFoundException;

public class XMLLogWriter extends LogWriter {

	public XMLLogWriter() throws FileNotFoundException {
		super();
	}

	public void initialize(int afm) {
		String fileName = afm + "_LOG.xml";
    	super.initializeOutput(fileName);
	}

	public void write(String key, String value) {
		key = key.replaceAll("\\s", "");
		String line = "<" + key + "> " + value + " </" + key + ">";
		super.printLine(line);
	}

}
