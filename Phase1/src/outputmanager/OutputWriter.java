package outputmanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import datamanager.Taxpayer;

public abstract class OutputWriter {
	
	protected Taxpayer taxpayer;
	private FileOutputStream outputStream = null;
	private PrintWriter outputWriter = null;
	
	protected OutputWriter(Taxpayer taxpayer, String filePath) throws FileNotFoundException{
		initializeOutputStream(filePath);
		initializeOutputWriter();
		this.taxpayer = taxpayer;
	}
	
	protected void initializeOutputStream(String filePath){
		try{
			outputStream = new FileOutputStream(filePath);
		}catch(FileNotFoundException e){
			System.out.println("Error opening output file.");
			System.exit(0);
		}
	}
	
	private void initializeOutputWriter(){
		outputWriter = new PrintWriter(outputStream);
	}
	
	public abstract void printFile();
	
	protected void printLine(String line) {
		outputWriter.println(line);
	}
	
	protected void close(){
		outputWriter.close();
	}
}
