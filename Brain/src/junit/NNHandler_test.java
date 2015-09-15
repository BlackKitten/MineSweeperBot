package junit;



import org.neuroph.core.data.DataSet;

import controller.NNHandler;

public class NNHandler_test {
	public static void main(String[] args) throws InterruptedException{
		DataSet ds=DataSet.createFromFile("test.txt", 482, 1, "\t", false);
		DataSet[] ds2=ds.sample(100);
		NNHandler handler = new NNHandler("MineSolver3_it1379_backup.nnet");
		handler.loadDataSet(ds2[0]);
		handler.learn();
		boolean finished=false;
		String previous_it = "";
		//String previous_nwErr="";
		while(!finished){
			
			Thread.sleep(10000);
			if(
			!(handler.getInfo()[1].equals(previous_it))
			){
				previous_it=handler.getInfo()[1];
				handler.save();
				System.out.println(handler.getInfo()[1] +": "+handler.getInfo()[0]);
			}
			//if(!(handler.getInfo()[0].equals(previous_nwErr))){
				//previous_nwErr=handler.getInfo()[0];
				
			//}
			
		}
	}
	
}
