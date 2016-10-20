package outputmanager;

import java.io.FileNotFoundException;

public abstract class OutputFactory {

	public static OutputFactory createOutputFactory(String fileExtension) {
		if (fileExtension.equalsIgnoreCase("txt")) {
			return new TextOutputFactory();
		} else if (fileExtension.equalsIgnoreCase("xml")) {
			return new XMLOutputFactory();
		}
		return null;
	}

	public abstract OutputWriter createOutputWriter(String filePath)
			throws FileNotFoundException;

}
