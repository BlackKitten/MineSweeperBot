package controller;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;

public class NNHandler_MineSolverV3 extends NNHandler_MineSolverV2 {

	@Override
	protected MultiLayerPerceptron createNN() {
		MultiLayerPerceptron nn= new MultiLayerPerceptron(25,100,100,1);
		DynamicBackPropagation rule=new DynamicBackPropagation();
		rule.setMaxIterations(10000);
		rule.setMaxError(0.01);
		nn.setLearningRule(rule);
		return nn;
	}

}
