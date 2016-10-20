package outputmanager;

import java.io.FileNotFoundException;

public abstract class LogFactory {

	public static LogFactory createLogFactory(final String fileExtension) {
		if (fileExtension.equalsIgnoreCase("txt")) {
			return new TextLogFactory();
		} else if (fileExtension.equalsIgnoreCase("xml")) {
			return new XMLLogFactory();
		}
		return null;
	}

	public abstract LogWriter createLogWriter() throws FileNotFoundException;

}
