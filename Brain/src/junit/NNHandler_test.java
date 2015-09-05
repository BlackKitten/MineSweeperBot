package junit;



import org.neuroph.core.data.DataSet;

import controller.NNHandler;

public class NNHandler_test {
	public static void main(String[] args) throws InterruptedException{
		DataSet ds=DataSet.createFromFile("test.txt", 482, 1, "\t", false);
		NNHandler handler = new NNHandler();
		handler.loadDataSet(ds);
		handler.learn();
		boolean finished=false;
		while(!finished){
			Thread.sleep(10000);
			System.out.println(handler.getInfo());
			handler.save();
		}
	}
	
}
