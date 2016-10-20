package outputmanager;

import java.io.FileNotFoundException;

import datamanager.Taxpayer;

public class XMLOutputFactory extends OutputFactory {

	@Override
	public OutputWriter createOutputWriter(Taxpayer taxpayer, String filePath) 
			throws FileNotFoundException {
		return new XMLOutputWriter(taxpayer, filePath);
	}

}
