package wordCount.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
	private BufferedReader buffer = null;
	
	public FileProcessor(File fileIn) throws FileNotFoundException {
		this.buffer = new BufferedReader(new FileReader(fileIn));			
	}
	

	/**
	 * Read one line from the file and return a string. 
	 */
	public synchronized String readOneLineAsString() throws IOException{
		return this.buffer.readLine();
	}
}
