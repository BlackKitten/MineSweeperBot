package app;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import util.ImageSplitterException;
import controller.MineSweeperPlayer;
import data.MineHistoryDataSet;
import data.MineHistoryDataSet_v2;

public class ms {

	public static void main(String[] args) throws IOException, ImageSplitterException, AWTException, InterruptedException {
		MineHistoryDataSet mHist = new MineHistoryDataSet_v2();
		MineSweeperPlayer player = new MineSweeperPlayer(mHist);	
		for(int i=0;i<50;i++){
		player.start();
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_P);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_P);
		//Thread.sleep(100);
	}
		mHist.writeToFile("test_v2.txt");

}
}