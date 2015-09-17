package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class DataSetHandler {
	protected String results="";
	public void writeToFile(String str_filename) throws IOException{
		writeToFile(results,str_filename);
	}
	
	private void writeToFile(String str_printsel,String filename) throws IOException{
		
		PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename),true)));
		pr.println(str_printsel);
		pr.flush();
		pr.close();
	}
	
}
