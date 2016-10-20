package inputparsing;

import java.io.FileNotFoundException;

public abstract class InputFactory {

	public static InputFactory createInputFactory(final String fileExtension) {
		if (fileExtension.equalsIgnoreCase("txt")) {
			return new TextInputFactory();
		} else if (fileExtension.equalsIgnoreCase("xml")) {
			return new XMLInputFactory();
		}
		return null;
	}

	public abstract InputParser createInputParser(String filePath)
			throws FileNotFoundException;

}
