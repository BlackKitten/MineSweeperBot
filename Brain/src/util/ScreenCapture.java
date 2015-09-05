package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenCapture {

	public BufferedImage getImage() throws AWTException {
		Robot robot = new Robot();
		Rectangle captureSize = createCaptureRectangle();
		BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
		return bufferedImage;
	}
	
	public void captureToFile(String filename) throws IOException, AWTException
	{
		File file_outputFile = new File(filename);
		ImageIO.write(getImage(), "png", file_outputFile);
	}
	
	protected Rectangle createCaptureRectangle(){
		return new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	}
	
	
}
