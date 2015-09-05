package imagerecognition;

import java.util.Scanner;

import javax.swing.plaf.SliderUI;

import org.neuroph.core.data.DataSet;

public class test {
	
	public static void main(String[] args) throws InterruptedException{
		DataSet ds=DataSet.load("100minedataset01234ub5x5.tset");
		ds.saveAsTxt("blablabla.txt", ",");
		createMineNeuralNet cnn=new createMineNeuralNet(ds);
		cnn.learn();
		
		Scanner scan=new Scanner(System.in);
		
		String answer="";
		System.out.println("starting");
		
		while(!answer.contentEquals("exit")){
			
			answer=scan.nextLine();
			
			if(answer.contentEquals("start")){
				cnn.start();
			}
			if(answer.contentEquals("pause")){
				cnn.pause();
			}
			if(answer.contentEquals("stop")){
				cnn.stop();
			}
			if(answer.contentEquals("toFile")){
				String filename="temp.nnet";
				cnn.toFile(filename);
			}
			if(answer.contentEquals("printInfo")){
				cnn.printInfo();
			}
			if(answer.contentEquals("monitoring")){
				
				InfoPrinter infoprinter=new InfoPrinter(scan, cnn);
				Thread info_thread=new Thread(infoprinter);
				info_thread.start();
				
				scan.hasNext();
				info_thread.stop();
				
				System.out.println("monitoring ended");
			}
			
			if(answer.contentEquals("it")){
				System.out.println(cnn.getItteration()+"  "+cnn.getError());
			}
			
			
		}
		
		cnn.toFile("01234ub10x10.nnet");
	}
}


