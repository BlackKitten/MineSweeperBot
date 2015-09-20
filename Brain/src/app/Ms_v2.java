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
/**
 * 
 * @author blackkitten
 *	uses number on neigbouring squares and their amount of unknown surrounding squares to predict probability of mine.
 */

public class Ms_v2 {

	public static void main(String[] args) throws IOException, ImageSplitterException, AWTException, InterruptedException {
		MineHistoryDataSet mHist = new MineHistoryDataSet_v2();
		MineSweeperPlayer player = new MineSweeperPlayer_v2(mHist);	
		Ms_base mb=new Ms_base(player);
		
		mb.start(1);
		mHist.writeToFile("test_v2_2.txt");

}
}
