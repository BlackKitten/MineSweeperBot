package app;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import util.ImageSplitterException;
import controller.MineSweeperPlayer;
import controller.MineSweeperPlayer_v2;
import data.MineHistoryDataSet;
import data.MineHistoryDataSet_v2;

public class Ms {

	public static void main(String[] args) throws IOException, ImageSplitterException, AWTException, InterruptedException {
		MineHistoryDataSet mHist = new MineHistoryDataSet();
		MineSweeperPlayer player = new MineSweeperPlayer(mHist);			
		Ms_base mb=new Ms_base(player);
		mb.start(1);
		
	
		mHist.writeToFile("MineSolver_v1.txt");

}
}