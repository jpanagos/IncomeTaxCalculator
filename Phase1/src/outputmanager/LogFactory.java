package outputmanager;

import java.io.FileNotFoundException;

import datamanager.Taxpayer;

public abstract class LogFactory {
	
	public static LogFactory createLogFactory(String fileExtension){
		if (fileExtension.equalsIgnoreCase("txt")){
			return new TextLogFactory();
		}else if (fileExtension.equalsIgnoreCase("xml")){
			return new XMLLogFactory();
		}
		return null;
	}
	
	public abstract LogWriter createLogWriter(Taxpayer taxpayer) 
			throws FileNotFoundException;
	
}
