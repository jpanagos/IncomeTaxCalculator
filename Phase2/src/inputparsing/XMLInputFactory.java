package inputparsing;

import java.io.FileNotFoundException;

public class XMLInputFactory extends InputFactory {

	public  InputParser createInputParser(final String filePath)
			throws FileNotFoundException {
		return new XMLInputParser(filePath);
	}

}
