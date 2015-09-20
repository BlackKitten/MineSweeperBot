package app;

import java.awt.AWTException;
import java.io.IOException;

import util.ImageSplitterException;
import controller.MineSweeperPlayer;
import controller.MineSweeperPlayer_v3;
import data.MineHistoryDataSet_v3;

/**
 * 
 * @author blackkitten
 *
 */
public class Ms_v3 {

	public static void main(String[] args) throws AWTException, ImageSplitterException, IOException, InterruptedException {
		MineHistoryDataSet_v3 mHist=new MineHistoryDataSet_v3();
		MineSweeperPlayer player=new MineSweeperPlayer_v3(mHist);
		Ms_base mb=new Ms_base(player);
		mb.start(1000);
		mHist.writeToFile("test_v3_6.txt");

	}

}
