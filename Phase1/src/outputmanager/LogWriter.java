package outputmanager;

import datamanager.Taxpayer;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public abstract class LogWriter {
	protected Taxpayer taxpayer;
	private FileOutputStream outputStream = null;
	private PrintWriter outputWriter = null;
	
	public LogWriter(Taxpayer taxpayer) throws FileNotFoundException{
		this.taxpayer = taxpayer;
	}
	
	protected void initializeOutput(String fileName){
		try{
			outputStream = new FileOutputStream(fileName);
		}catch(FileNotFoundException exception){
			System.out.println("Error opening output file.");
			System.exit(0);
		}
		initializeOutputWriter();
	}
	
	private void initializeOutputWriter(){
		outputWriter = new PrintWriter(outputStream);
	}
	
	protected void printLine(String line) {
		outputWriter.println(line);
	}
	
	public abstract void createTaxpayerLog();
	
	protected void close(){
		outputWriter.close();
	}
	
}
