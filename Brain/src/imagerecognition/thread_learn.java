package imagerecognition;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.LearningRule;

public class thread_learn implements Runnable {

	private String name;
	private NeuralNetwork<LearningRule>nn;
	
	public thread_learn(String name){
	this.name=name;
	System.out.println("creating "+ this.name);
	}
	
	public void setNN(NeuralNetwork<LearningRule> nn){
		this.nn=nn;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void stop(){
		
	}
	
	public void pause(){
		
	}
	
	public void start(){
		
	}

}
