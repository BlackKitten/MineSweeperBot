package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.sf.image4j.util.ImageUtil;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageRecognitionHelper;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.neuroph.imgrec.ImageSampler;
import org.neuroph.imgrec.ImageUtilities;
import org.neuroph.imgrec.image.Dimension;
import org.neuroph.util.plugins.PluginBase;

public class organise {
	public static void main(String[] args) {
		 
		 
		    // load trained neural network saved with Neuroph Studio (specify some existing neural network file here)
		    NeuralNetwork nnet = NeuralNetwork.createFromFile("MineRecognizer.nnet"); // load trained neural network saved with Neuroph Studio
		    // get the image recognition plugin from neural network
		    
		    //nnet.addPlugin(new ImageRecognitionPlugin(new Dimension(5, 5)));
		    //PluginBase base = nnet.getPlugin(ImageRecognitionPlugin.class);
		    //ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin)base; // get the image recognition plugin from neural network
		    
		    String[] array_string_folder={"0","1","2","3","4","unclicked","bomb"};
		    
		    for(String string_folder:array_string_folder){
		    File directory = new File(string_folder);
		    
		    double match=0;
		    double total=0;
		    for(File file:directory.listFiles()){
			    try {
			         // image recognition is done here (specify some existing image file)
			    	nnet.setInput(new FractionRgbData(ImageUtilities.resizeImage(ImageIO.read(file), 5, 5)).getFlattenedRgbValues());
			    	nnet.calculate();
			    	double[] output = nnet.getOutput(); //imageRecognition.recognizeImage(file);
			        
			        int fkey=-1;
			        double value=0;
			        for(int i=0;i<output.length;i++){
			        	
			        	if(output[i]>value){
			        		fkey=i;
			        		value=output[i];
			        		
			        		//file.renameTo(new File(key+file.getName()));
			        		
			        		
			        	}
			        	
			        }
			        System.out.println(string_folder+" "+fkey+": "+value+"   "+file.getName());
			        total++;
			        if(string_folder.equals(fkey) || (string_folder.equals("bomb") && fkey==5) || (string_folder.equals("unclicked") && fkey==6)){
			        	match++;
			        }
			 
			    } catch(IOException ioe) {
			        ioe.printStackTrace();
			    }
		 }
		    double result=match/total;
		    System.out.println("match: "+result);
		
}
	 }
}
