package app;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import util.ImageSplitterException;
import controller.MineSweeperPlayer;

public class Ms_base {
	private MineSweeperPlayer mp;
	public Ms_base(MineSweeperPlayer mp){
		this.mp=mp;
	}
	
	public void start(int times) throws IOException, ImageSplitterException, AWTException, InterruptedException{
		for(int i=0;i<times;i++){
			mp.start();
			if(i<times-1){
			Thread.sleep(100);
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_P);
			}
			
		}
	}
}
