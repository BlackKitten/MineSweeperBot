package junit;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.io.IOException;

import imagerecognition.WindowAnalyzer;

import org.junit.Before;
import org.junit.Test;

import util.ImageSplitterException;

public class test_WindowAnalyzer {
	
	WindowAnalyzer wAnalyzer;
	
	public test_WindowAnalyzer() throws AWTException, ImageSplitterException {
		wAnalyzer=new WindowAnalyzer();
		
	}
	@Test
	public void test() throws IOException, ImageSplitterException, AWTException {
		this.wAnalyzer.analyzeScreenCapture();
		this.wAnalyzer.printImages();
	}
}
