package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MineHistoryDataSet extends DataSetHandler {
	
	
	boolean toggle=false;
	
	//write out state
	public MineHistoryDataSet(){
		super();
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
	

}
