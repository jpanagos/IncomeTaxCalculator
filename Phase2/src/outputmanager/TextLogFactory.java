package outputmanager;

import java.io.FileNotFoundException;

public class TextLogFactory extends LogFactory {

	public LogWriter createLogWriter() throws FileNotFoundException {
		return new TextLogWriter();
	}

}
