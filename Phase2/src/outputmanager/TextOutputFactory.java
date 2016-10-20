package outputmanager;

import java.io.FileNotFoundException;

public class TextOutputFactory extends OutputFactory {

	public OutputWriter createOutputWriter(String filePath)
			throws FileNotFoundException {
		return new TextOutputWriter(filePath);
	}

}
