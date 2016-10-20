package inputparsing;

import java.io.FileNotFoundException;

public class XMLInputFactory extends InputFactory {
	
	@Override
	public  InputParser createInputParser(String filePath) throws FileNotFoundException{
		return new XMLInputParser(filePath);
	}
	
}
