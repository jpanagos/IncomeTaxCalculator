package outputmanager;

import java.io.FileNotFoundException;

public class XMLOutputFactory extends OutputFactory {

	public OutputWriter createOutputWriter(String filePath)
			throws FileNotFoundException {
		return new XMLOutputWriter(filePath);
	}

}
