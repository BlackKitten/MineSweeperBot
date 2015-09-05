package junit;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.Test;

import util.ScreenCapture;
import util.ScreenCapture0_0_951_594;

public class ScreenCaptureTest {

	@Test
	public void test() throws IOException, AWTException {
		//tester
		ScreenCapture tester = new ScreenCapture0_0_951_594();
		tester.captureToFile("raster1.png");
		//cropping of file
		
	}
	

	

}
