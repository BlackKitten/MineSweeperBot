package junit;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.io.IOException;

import imagerecognition.MineSweeperRecog;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.ImageSplitterException;

public class test_MineSweeperRecog {

	private MineSweeperRecog mRecog; 
	@Before
	public void init() throws AWTException, ImageSplitterException, IOException{
		this.mRecog=new MineSweeperRecog("backup_0,00000.nnet");
	}
	@Test
	public void test() throws IOException, ImageSplitterException, AWTException {
		mRecog.updateMineField();
		System.out.println(mRecog.getField().toString());
		System.out.println("\n\n\n\n\n\n\n\n\n");
	}
	@Test
	public void test_after10seconds() throws InterruptedException, IOException, ImageSplitterException, AWTException{
		Thread.sleep(10000);
		mRecog.updateMineField();
		System.out.println(mRecog.getField().toString());
	}
}
