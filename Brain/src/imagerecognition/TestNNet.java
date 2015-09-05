package imagerecognition;

import junit.ImageSplitTest;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.LearningRule;

public class TestNNet {
	
	public static void main (String[] args){
		NeuralNetwork<LearningRule>nn =NeuralNetwork.createFromFile("NewNeuralNetwork11.nnet");
		nn.save("test");

	}
}
