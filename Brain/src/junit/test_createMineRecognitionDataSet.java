package junit;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.neuroph.imgrec.ImageUtilities;

public class test_createMineRecognitionDataSet {

	@Test
	public void test() throws FileNotFoundException, IOException {
		//String fileName="bomb/053x90.png";
		//String fileName="1/1196x204.png";
		String fileName="0/0224x261.png-0.png";
		BufferedImage o_image=ImageIO.read(new FileInputStream(new File(fileName)));
		BufferedImage n_image=ImageUtilities.resizeImage(o_image, 5, 5);
		ImageIO.write(n_image, "png", new File("example0.png"));
	}

}
