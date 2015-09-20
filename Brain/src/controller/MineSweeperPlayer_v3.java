package controller;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.neuroph.core.NeuralNetwork;

import util.ImageSplitterException;
import data.MineField;
import data.MineHistoryDataSet;

public class MineSweeperPlayer_v3 extends MineSweeperPlayer {

	public MineSweeperPlayer_v3(MineHistoryDataSet mHist) throws AWTException,
			ImageSplitterException, IOException {
		super(mHist);
		// TODO Auto-generated constructor stub
	}
	
	protected NeuralNetwork createMineSolverNN(){
		return NeuralNetwork.createFromFile(new File("backup_0,10839.nnet"));
	}
	
	protected double getProb4XY(int x,int y,MineField m){
		double[] line=new double[25];
		int counter=0;
		for(int i=x-2;i<=x+2;i++){
			for(int j=y-2;j<=y+2;j++){
				int value=-1;
				try{
					value=m.getField(i, j).getValue();
				}catch(IndexOutOfBoundsException e){
					//field is outside of minefield, value=-1(unknown);
				}
				line[counter]=value;
				counter++;
			}
		}
		nn.setInput(line);
		nn.calculate();
		return nn.getOutput()[0];
	}

}
