package controller;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;

public class NNHandler_MineSolverV2 extends NNHandler_base {

	public NNHandler_MineSolverV2(String string) {
		super(string);
	}

	public NNHandler_MineSolverV2() {
		super();
	}

	@Override
	protected MultiLayerPerceptron createNN() {
		
		MultiLayerPerceptron nn= new MultiLayerPerceptron(2,20,20,1);
		DynamicBackPropagation rule=new DynamicBackPropagation();
		rule.setMaxIterations(10000);
		rule.setMaxError(0.01);
		nn.setLearningRule(rule);
		return nn;
	}
	
	public String[] getInfo() {
		String s1=""+nn.getLearningRule().getPreviousEpochError();
		String s2=""+nn.getLearningRule().getCurrentIteration();
		String[] s_all={s1,s2};
		return s_all;
	}
	
	public double getLearningRate() {
		return nn.getLearningRule().getLearningRate();
	}
	public double getErrorChange() {
		return nn.getLearningRule().getTotalNetworkError()-nn.getLearningRule().getPreviousEpochError();
	}
	public double getItteration() {
		return nn.getLearningRule().getCurrentIteration();
	}
	public double getError() {
		return nn.getLearningRule().getPreviousEpochError();
	}

	

}
