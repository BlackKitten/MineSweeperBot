package controller;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;

public class NNHandler {
	private MultiLayerPerceptron nn;
	private DataSet ds;
	public NNHandler(){
		this.nn=createNN();
	}
	
	public NNHandler(String filename){
		this.nn=(MultiLayerPerceptron)MultiLayerPerceptron.createFromFile(filename);
	}
	
	private MultiLayerPerceptron createNN(){
		MultiLayerPerceptron nn= new MultiLayerPerceptron(482,200,100,1);
		DynamicBackPropagation rule=new DynamicBackPropagation();
		rule.setMaxIterations(1000);
		rule.setMaxError(0.1);
		nn.setLearningRule(rule);
		return nn;
	}
	
	public void loadDataSet(DataSet d){
		this.ds=d;
	}
	
	public void learn(){
		this.ds.shuffle();	
		nn.learnInNewThread(this.ds);
		nn.calculate();
	}
	
	public void toFile(String filename){
		nn.save(filename);
	}
	public double calculateProb(int x,int y,double[] field){
		double[] dbl_input1={(double)x,(double)y};
		double[] dbl_input = new double[dbl_input1.length+field.length];
		System.arraycopy(dbl_input1, 0, dbl_input, 0, dbl_input1.length);
		System.arraycopy(field, 0, dbl_input, dbl_input1.length, field.length);
		nn.setInput(dbl_input);
		nn.calculate();	
		return nn.getOutput()[0];
	}

	public String getInfo() {
		return ""+nn.getLearningRule().getPreviousEpochError()+"\n"+nn.getLearningRule().getCurrentIteration();
	}
	public Thread.State getState(){
		return nn.getLearningThread().getState();
	}
	
	public void save(){
		nn.save("MineSolver3_it"+nn.getLearningRule().getCurrentIteration()+".nnet");
	}
}
