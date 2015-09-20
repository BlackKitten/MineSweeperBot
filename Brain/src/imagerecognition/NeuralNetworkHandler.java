package imagerecognition;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageUtilities;

public class NeuralNetworkHandler {

	private NeuralNetwork nn;
	public NeuralNetworkHandler(String filename) {
		this.nn=NeuralNetwork.createFromFile(new File(filename));
	}
	
	public int analyze(BufferedImage image){
		nn.setInput(new FractionRgbData(ImageUtilities.resizeImage(image, 5, 5)).getFlattenedRgbValues());
		nn.calculate();
		double[] output = nn.getOutput();
		int key=-1;
		double value=0;
		for(int i=0;i<output.length;i++){
			if(output[i]>value){
				if(i<10){
					key=i;
				}else{
					if(i==10){
						key=-1;
					}
					//if(i==6){
					//	key=9;
					//}
				}
				value=output[i];
			}
		}
		return key;
		
	}

}
