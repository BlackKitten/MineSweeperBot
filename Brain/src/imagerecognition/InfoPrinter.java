package imagerecognition;

import java.util.Scanner;

public class InfoPrinter implements Runnable{
	private Scanner scan;
	private createMineNeuralNet cnn;
	public InfoPrinter(Scanner scan,createMineNeuralNet cnn){
		this.scan=scan;
		this.cnn=cnn;
	}
	
	@Override
	public void run() {
		double error=1;
		while(true){
			
		String E=""+cnn.getError();
		String lr=""+cnn.getLearningRate();
		System.out.println("iteration: "+cnn.getItteration()+"\t"+"error: "+ E + "\t Learning rate: "+lr + "change: "+cnn.getErrorChange()) ;
		
		if(cnn.getError()<error){
			error=cnn.getError();
			String str_error=String.format("%.5f", error);
			String filename="backup_"+ str_error+".nnet";
			cnn.toFile(filename);
			System.out.println(error+": saved to file:"+filename);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

}
