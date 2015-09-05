package junit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import util.ImageSplitter;
import util.ImageSplitterException;

public class ImageSplitTest {

	BufferedImage image;
	BufferedImage cropped_image;
	ImageSplitter splitter;
	
	public ImageSplitTest() throws IOException{
		File file = new File("raster1.png"); // I have bear.jpg in my working directory
        FileInputStream fis = new FileInputStream(file);
        image = ImageIO.read(fis); //reading the image file
        splitter = new ImageSplitter();
	}
	@Test
	public void test_cropImage() throws IOException {
		
        
        //cropped_image=splitter.crop(image);
		//ImageIO.write(cropped_image, "png", new File("cropped_image.png"));
		
	}
	@Test
	public void test_getInterBorderWnH() throws ImageSplitterException, IOException{
		//test_cropImage();
		//System.out.println(splitter.getInterBorderWidth(cropped_image));
		//System.out.println(splitter.getInterBorderHeight(cropped_image));
//		int x=0;
//		int bordewidth;
//		while (x<image.getWidth()){
//			bordewidth=splitter.getInterBorderWidth(x, cropped_image);
//			x=x+bordewidth;
//			System.out.println(bordewidth);
//		}
	}
	
	//@Test
	public void test_imageSplit() throws IOException, ImageSplitterException
	{
		//test_cropImage();
		File file = new File("raster1.png"); // I have bear.jpg in my working directory
        FileInputStream fis = new FileInputStream(file);
        image = ImageIO.read(fis); //reading the image file
        splitter = new ImageSplitter();
        
		BufferedImage[] array_image=splitter.split(image);
		int i=0;
		for (BufferedImage image: array_image){
			ImageIO.write(image, "png",new File("img" + i + ".png"));
			i++;
		}
		 
	}
	@Test
	public void test_dimensions() throws IOException, ImageSplitterException{
		File file = new File("raster1.png"); // I have bear.jpg in my working directory
        FileInputStream fis = new FileInputStream(file);
        image = ImageIO.read(fis); //reading the image file
        splitter = new ImageSplitter();
        
		splitter.getDimensions(image);
	}
	
	
	
	

}
