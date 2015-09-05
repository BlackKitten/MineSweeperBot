package imagerecognition;

import java.io.File;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.DynamicBackPropagation;

public class createMineNeuralNet {
	private MultiLayerPerceptron nn;
	private DataSet dataset;
	public createMineNeuralNet(DataSet dataset){
		this.dataset=dataset;
		dataset.shuffle();
		//nn = new MultiLayerPerceptron(dataset.getInputSize(), 100 , dataset.getOutputSize());
		
		nn = (MultiLayerPerceptron) NeuralNetwork.createFromFile(new File("backup_0,00000.nnet"));
		Neuron[] neur=nn.getOutputNeurons();
		for(int i=0;i<5;i++){
			System.out.println(i);
			neur[i].setLabel(""+i);
		}
		neur[5].setLabel("u");
		neur[6].setLabel("b");
		
		setLearningRule(0.001);
		//createInputLayer();
		//addInputLayerToNetwork();
	}
	public createMineNeuralNet(DataSet[] dataset){
		nn = new MultiLayerPerceptron(dataset[0].getInputSize(), 50, dataset[0].getOutputSize());
		Neuron[] neur=nn.getOutputNeurons();
		for(int i=0;i<5;i++){
			neur[i].setLabel(""+i);
		}
		neur[5].setLabel("u");
		neur[6].setLabel("b");
		
		System.out.println("start_learning");
		int i=0;
		for(DataSet set:dataset){
			this.dataset=set;
			this.dataset.shuffle();
			setLearningRule(0.01);
			nn.learn(this.dataset);
			
			System.out.println(nn.getLearningRule().getPreviousEpochError());
			System.out.println(nn.getLearningRule().getCurrentIteration());
			i++;
			toFile("i"+i+".nnet");
		}
	}
	protected void setLearningRule(double max_error) {
		DynamicBackPropagation rule=new DynamicBackPropagation();
		//double max_error=0.1;
		rule.setMaxIterations(100000);
		rule.setMaxError(0.00001);
		//rule.setMinErrorChange(0.0001);
		nn.setLearningRule(rule);
		
	}
	
	protected MultiLayerPerceptron getNN(){
		return this.nn;
	}
	
	protected double getErrorChange(){
		DynamicBackPropagation rule=(DynamicBackPropagation)nn.getLearningRule();
		return (rule.getTotalNetworkError()-rule.getPreviousEpochError());
	}
	
	public void learn(){
		
		nn.learnInNewThread(this.dataset);
		
		nn.calculate();
	}
	
	public void printInfo(){
		System.out.println("max_error: "+nn.getLearningRule().getMaxError());
		System.out.println("min_error_change: "+nn.getLearningRule().getMinErrorChange());
		System.out.println("learning_rate: "+nn.getLearningRule().getLearningRate());
	}
	public double getError(){
		return nn.getLearningRule().getPreviousEpochError();
	}
	public int getItteration(){
		return nn.getLearningRule().getCurrentIteration();
	}
	
	public double getLearningRate(){
		return nn.getLearningRule().getLearningRate();
	}
	
	public void pause(){
		nn.pauseLearning();
		
	}
	
	public void start(){
		nn.resumeLearning();
	}
	public void toFile(String filename) {
		//nn.stopLearning();
		
		
		nn.save(filename);
		
		
	}
	
	public void stop(){
		nn.stopLearning();
	}
	
	
	
	
}
