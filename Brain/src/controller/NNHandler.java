package controller;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;

public class NNHandler {
	private NeuralNetwork nn;
	private DataSet ds;
	public NNHandler(){
		this.nn=createNN();
	}
	
	private NeuralNetwork createNN(){
		return new MultiLayerPerceptron(482,200,100,1);
	}
	
	public void loadDataSet(DataSet d){
		this.ds=d;
	}
	
	public void learn(){
		this.ds.shuffle();
		DynamicBackPropagation rule=new DynamicBackPropagation();
		rule.setMaxIterations(1000);
		rule.setMaxError(0.01);
		nn.setLearningRule(rule);
		nn.learnInNewThread(this.ds);
		
	}
	
	public void toFile(String filename){
		nn.save(filename);
	}
	public double calculateProb(int x,int y,double[] field){
		nn.setInput(inputVector);
		nn.calculate();	
	}
}
