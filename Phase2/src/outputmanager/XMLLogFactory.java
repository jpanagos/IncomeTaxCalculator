package outputmanager;

import java.io.FileNotFoundException;

public class XMLLogFactory extends LogFactory {

	public LogWriter createLogWriter() throws FileNotFoundException {
		return new XMLLogWriter();
	}

}
