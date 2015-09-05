package imagerecognition;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.LearningRule;

import data.MineField;
import util.ImageSplitterException;



public class MineSweeperRecog {

	private NeuralNetworkHandler nn;
	private WindowAnalyzer wa;
	
	public MineSweeperRecog(String filename) throws AWTException, ImageSplitterException, IOException{
		this.nn=loadNN(filename);
		this.wa=loadWa(nn);
		this.wa.updateMineField();
	}
	protected NeuralNetworkHandler loadNN(String filename){
		return new NeuralNetworkHandler(filename);
	}
	
	protected WindowAnalyzer loadWa(NeuralNetworkHandler nn) throws AWTException, ImageSplitterException{
		WindowAnalyzer analyzer=new WindowAnalyzer(nn);
		return analyzer;
	}
	
	public MineField getField() throws IOException, ImageSplitterException, AWTException{
		return wa.getMineField();
	}
	
	public void updateMineField() throws IOException, ImageSplitterException, AWTException{
		this.wa.updateMineField();
	}
}
