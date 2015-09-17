package controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;

import net.sf.javaml.utils.ArrayUtils;

import org.neuroph.core.NeuralNetwork;

import data.MineField;
import data.MineFieldField;
import data.MineHistoryDataSet;
import util.ImageSplitterException;
import imagerecognition.MineSweeperRecog;

public class MineSweeperPlayer {

	private MineSweeperRecog mRecog;
	private MineHistoryDataSet mHist;
	private NeuralNetwork nn;
	public MineSweeperPlayer(MineHistoryDataSet mHist) throws AWTException, ImageSplitterException, IOException{
		this.mRecog=new MineSweeperRecog("backup_0,00000.nnet");
		this.mHist=mHist;
		this.nn=NeuralNetwork.createFromFile(new File("MineSolver3_it17.nnet"));
	}
	public void start() throws IOException, ImageSplitterException, AWTException, InterruptedException{
		try
		{
			while(true){	
			read();
			analyze();						
			}
		}catch(GameOverException e){
			this.mHist.addToResults(0);
		System.out.println("gameOver");
	}
	}
	
	public void read() throws IOException, ImageSplitterException, AWTException{
		this.mRecog.updateMineField();
	}
	
	public void analyze() throws IOException, ImageSplitterException, AWTException, InterruptedException, GameOverException{
		MineField field=this.mRecog.getField();
	
		MineFieldField mf=getLowestProbMineField(field);
		this.mHist.addToResults(1); //add succes to previous move
		this.mHist.addToResults(mf.idx,mf.idy,field); //add current move
		action(mf.x+mf.widht/2,mf.y+mf.height/2);	
		action(mf.x+mf.widht/2,mf.y+mf.height/2);	
		
		//this.mHist.addToResults(mf.x, mf.y, field, );
	}
	protected MineFieldField getLowestProbMineField(MineField m) throws GameOverException{
		MineFieldField lowestProb=null;
		double double_lowestProb=1;
		for (MineFieldField[] mFieldA : m.getFields()){
			for (MineFieldField mField : mFieldA){
				//prob -1 -> field is already clicked
				if(mField.getValue()==-1){
				if(lowestProb!=null){
					//if(mField.getProb()<lowestProb.getProb()){
					if(getProb4XY(mField.idx, mField.idy, m) < double_lowestProb){
						lowestProb=mField;
					}
				}else{
					lowestProb=mField;
					}
				}else{
					if(mField.getValue()==9){
						throw new GameOverException();
					}
				}
			}
		}
		return lowestProb;
	}
	protected double getProb4XY(int x,int y,MineField m){				
		double[] dbl_input1={(double)x,(double)y};
		double[] dbl_input = new double[dbl_input1.length+m.toDouble().length];
		System.arraycopy(dbl_input1, 0, dbl_input, 0, dbl_input1.length);
		System.arraycopy(m.toDouble(), 0, dbl_input, dbl_input1.length, m.toDouble().length);
		nn.setInput(dbl_input);
		nn.calculate();
		return nn.getOutput()[0];		
	}
	
	public void action(int x,int y) throws AWTException, InterruptedException{
		Robot robot = new Robot();
		robot.mouseMove(x, y);
		//Thread.sleep(100);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		//Thread.sleep(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);	
	}
}
