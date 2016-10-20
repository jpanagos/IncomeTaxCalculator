package inputparsing;

import java.io.FileNotFoundException;

public class TextInputFactory extends InputFactory {

	public InputParser createInputParser(final String filePath)
			throws FileNotFoundException {
		return new TextInputParser(filePath);
	}

}
