package inputparsing;

import java.io.FileNotFoundException;

public class TextInputFactory extends InputFactory {
	
	@Override
	public  InputParser createInputParser(String filePath) throws FileNotFoundException{
		return new TextInputParser(filePath);
	}
	
}
