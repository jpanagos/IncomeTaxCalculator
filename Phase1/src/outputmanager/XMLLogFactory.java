package outputmanager;

import java.io.FileNotFoundException;

import datamanager.Taxpayer;

public class XMLLogFactory extends LogFactory {

	@Override
	public LogWriter createLogWriter(Taxpayer taxpayer) throws FileNotFoundException {
		return new XMLLogWriter(taxpayer);
	}

}
