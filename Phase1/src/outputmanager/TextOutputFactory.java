package outputmanager;

import java.io.FileNotFoundException;

import datamanager.Taxpayer;

public class TextOutputFactory extends OutputFactory {

	@Override
	public OutputWriter createOutputWriter(Taxpayer taxpayer, String filePath) 
			throws FileNotFoundException {
		return new TextOutputWriter(taxpayer, filePath);
	}

}
