package imagerecognition;

import org.neuroph.core.data.DataSet;

public class test2 {
	public static void main(String[] args) throws InterruptedException{
		DataSet[] datasets=new DataSet[99];
		for(int i=1;i<=99;i++){
			datasets[i-1]=DataSet.load(i+"minedataset01234ub5x5.tset");
		}
		//datasets[9]=DataSet.load("minedataset01234ub10x10.tset");
		createMineNeuralNet cnn=new createMineNeuralNet(datasets);
		cnn.toFile("testset.nnet");
	}
}
