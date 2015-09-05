package app;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import util.ImageSplitterException;
import controller.MineSweeperPlayer;
import data.MineHistoryDataSet;

public class ms {

	public static void main(String[] args) throws IOException, ImageSplitterException, AWTException, InterruptedException {
		MineHistoryDataSet mHist = new MineHistoryDataSet();
		MineSweeperPlayer player = new MineSweeperPlayer(mHist);	
		for(int i=0;i<100;i++){
		player.start();
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_P);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_P);
		Thread.sleep(100);
	}
		mHist.writeToFile("test.txt");

}
}