package imagerecognition;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import data.MineField;
import data.MineFieldField;
import data.MineField_v2;
import util.ImageSplitter;
import util.ImageSplitterException;
import util.ScreenCapture;
import util.ScreenCapture0_0_951_594;

public class WindowAnalyzer {

	Integer[][][] int3_dimensions;
	MineField mineField;
	ImageSplitter splitter;

	public WindowAnalyzer(NeuralNetworkHandler nn) throws AWTException, ImageSplitterException {
		BufferedImage image = captureScreen();
		splitter = new ImageSplitter();
		this.int3_dimensions = splitter.getDimensions(image);
		mineField = new MineField_v2(nn);
		for (int x = 0; x < int3_dimensions.length; x++) {
			for (int y = 0; y < int3_dimensions[x].length; y++) {
				mineField.getField(x, y).x = int3_dimensions[x][y][0];
				mineField.getField(x, y).y = int3_dimensions[x][y][1];
				mineField.getField(x, y).widht = int3_dimensions[x][y][2];
				mineField.getField(x, y).height = int3_dimensions[x][y][3];
			}
		}
	}

	protected BufferedImage captureScreen() throws AWTException {
		ScreenCapture capturer = new ScreenCapture0_0_951_594();
		return capturer.getImage();
	}

	public void analyzeScreenCapture() throws IOException,
			ImageSplitterException, AWTException {
		analyzeScreenCapture(captureScreen());
	}

	protected void analyzeScreenCapture(BufferedImage screencapture)
			throws IOException, ImageSplitterException {

		for (MineFieldField[] array_field : mineField.getFields()) {
			for (MineFieldField field : array_field) {
				field.setImage(splitter.getImage(screencapture, field.x,
						field.y, field.widht, field.height));
				field.analyzeImage();
			}
		}
	}

	public void printImages() throws IOException {
		for (MineFieldField[] array_field : mineField.getFields()) {
			for (MineFieldField field : array_field) {
				ImageIO.write(field.getImage(), "png", new File(field.x + "x"
						+ field.y));

			}
		}

	}

	
	public void updateMineField() throws IOException, ImageSplitterException, AWTException{
		analyzeScreenCapture(captureScreen());
	}
	
	public MineField getMineField(){
		return this.mineField;
	}
}
