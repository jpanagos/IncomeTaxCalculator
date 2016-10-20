package outputmanager;

import java.io.FileNotFoundException;

public class XMLOutputWriter extends OutputWriter {

	public XMLOutputWriter(String filePath) throws FileNotFoundException {
		super(filePath);
	}

	public void write(String key, String value) {
		key = key.replaceAll("\\s", "");
		String line = "<" + key + "> " + value + " </" + key + ">";
		super.printLine(line);
	}

	public void write(String text) {
		super.printLine("<" + text.trim() + ">");
	}

}
