package controller.view;

import controller.view.InfoPrinter;
import imagerecognition.createMineNeuralNet;

import java.util.Scanner;

import org.neuroph.core.data.DataSet;

import controller.Controller;

public class NNviewer extends Interface {
	private Controller c;
	public NNviewer(Controller c){
		this.c=c;
	}
	public void start(){
		Scanner scan=new Scanner(System.in);
	
		String answer="";
		System.out.println("starting");
	
		while(!answer.contentEquals("exit")){
		
		answer=scan.nextLine();
		
		if(answer.contentEquals("start")){
			c.start();
		}
		if(answer.contentEquals("pause")){
			c.pause();
		}
		if(answer.contentEquals("stop")){
			c.stop();
		}
		if(answer.contentEquals("toFile")){
			String filename="temp.nnet";
			c.toFile(filename);
		}
		if(answer.contentEquals("printInfo")){
			//c.printInfo();
		}
		if(answer.contentEquals("monitoring")){
			
			InfoPrinter infoprinter=new InfoPrinter(scan, this);
			Thread info_thread=new Thread(infoprinter);
			info_thread.start();
			
			scan.hasNext();
			info_thread.stop();
			
			System.out.println("monitoring ended");
		}
		
		if(answer.contentEquals("it")){
			System.out.println(c.getItteration()+"  "+c.getError());
		}
		}
		
	}
	public double getError() {
		return c.getError();
	}
	public String getLearningRate() {
		return ""+c.getLearningRate();
	}
	public String getItteration() {
		return ""+c.getItteration();
	}
	public String getErrorChange() {
		return ""+c.getErrorChange();
	}
	public void toFile(String filename) {
		 c.toFile(filename);
		
	}
	}
