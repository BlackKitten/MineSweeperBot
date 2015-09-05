package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MineHistoryDataSet {
	
	private String results="";
	boolean toggle=false;
	
	//write out state
	public MineHistoryDataSet(){
		
	}
	
	public void addToResults(int x,int y,MineField m){
		this.results=this.results+"\n"+x+"\t"+y+"\t"+m.toString();
		toggle=true;
	}
	public void addToResults(int result){
		if(toggle){
		this.results=this.results+result;
		toggle=false;
		}
	}
	
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
