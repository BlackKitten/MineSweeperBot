package imagerecognition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageUtilities;

public class createMineRecognitionDataSet {
	
	private static ArrayList<FractionRgbData> a0;
	private static ArrayList<FractionRgbData> a1;
	private static ArrayList<FractionRgbData> a2;
	private static ArrayList<FractionRgbData> a3;
	private static ArrayList<FractionRgbData> a4;
	private static ArrayList<FractionRgbData> au;
	private static ArrayList<FractionRgbData> ab;
	private static int inputSize;
	private static int outputSize=11;
	
	public static void main(String[] args) throws IOException {
		
		a0=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("0"), a0);
		a1=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("1"), a1);
		a2=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("2"), a2);
		a3=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("3"), a3);
		a4=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("4"), a4);
		au=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("unclicked"), au);
		ab=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("bomb"), ab);
		
		
		//for each file in directory 
		
			//create fractionrfgbData + addToArrayList
				
			//put label 
			
			//create dataset from fractionrgbdata
		//for(int i=0;i<100;i++){
			createDataSet(100);
		//}
		
	}
		
		public static void createDataSet(int x) throws IOException{
		ArrayList<FractionRgbData> a0=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("0"), a0);
		ArrayList<FractionRgbData> a1=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("1"), a1);
		ArrayList<FractionRgbData> a2=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("2"), a2);
		ArrayList<FractionRgbData> a3=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("3"), a3);
		ArrayList<FractionRgbData> a4=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("4"), a4);
		ArrayList<FractionRgbData> au=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("unclicked"), au);
		ArrayList<FractionRgbData> ab=new ArrayList<FractionRgbData>();
		addRgbDataFromFolderToArrayList(new File("bomb"), ab);
		
		DataSet dataset=new DataSet(inputSize, outputSize);
		addRow(dataset,0,a0,x);
		addRow(dataset,1,a1,x);
		addRow(dataset,2,a2,x);
		addRow(dataset,3,a3,x);
		addRow(dataset,4,a4,x);
		addRow(dataset,5,au,x);
		addRow(dataset,6,ab,x);
		
		
		//dataset.saveAsTxt("dataset.txt", ",");
		dataset.save(x+"minedataset01234ub5x5.tset");
		dataset.saveAsTxt(x+"minedataset01234ub5x5.txt", ",");
		System.out.println(inputSize);
	}
	public static void addRow(DataSet dataset,int pos,ArrayList<FractionRgbData> list,int sublistSize){
		double[]output= new double[outputSize];
		for(int i=0;i<outputSize;i++){
			if(i==pos){
			output[i]=1;
			}else{
				output[i]=0;
			}
		}
		List<FractionRgbData> sublist;
		try{
			sublist=list.subList(0, sublistSize);
		}catch(IndexOutOfBoundsException e){
			sublist=list;		
		}
		for(FractionRgbData rgb_dat:sublist){
			dataset.addRow(rgb_dat.getFlattenedRgbValues(), output);
		}
	}
	
	
	public static void addRgbDataFromFolderToArrayList(File folder,ArrayList<FractionRgbData> aL) throws IOException{
		for (File file: folder.listFiles()){
			FileInputStream fis;
//			try {
				fis = new FileInputStream(file);
				BufferedImage image=ImageIO.read(fis);
				image=ImageUtilities.resizeImage(image,5,5);
				FractionRgbData rgb_dat=new FractionRgbData(image);
				inputSize=rgb_dat.getFlattenedRgbValues().length;
//			try {
				aL.add(rgb_dat);
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	

}
