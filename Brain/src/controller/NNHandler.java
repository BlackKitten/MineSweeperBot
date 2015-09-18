package controller;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;

public class NNHandler extends NNHandler_base{

	public NNHandler(){
		this.nn=createNN();
	}
	
	public NNHandler(String filename){
		this.nn=(MultiLayerPerceptron)MultiLayerPerceptron.createFromFile(filename);
	}
	
	protected MultiLayerPerceptron createNN(){
		MultiLayerPerceptron nn= new MultiLayerPerceptron(482,100,50,1);
		DynamicBackPropagation rule=new DynamicBackPropagation();
		rule.setMaxIterations(10000);
		rule.setMaxError(0.01);
		nn.setLearningRule(rule);
		return nn;
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

	public String[] getInfo() {
		String s1=""+nn.getLearningRule().getPreviousEpochError();
		String s2=""+nn.getLearningRule().getCurrentIteration();
		String[] s_all={s1,s2};
		return s_all;
	}
	public Thread.State getState(){
		return nn.getLearningThread().getState();
	}
	public void toFile(){
		this.save();
	}
	
	public void save(){
		nn.save("MineSolver3_it"+nn.getLearningRule().getCurrentIteration()+".nnet");
	}
}


