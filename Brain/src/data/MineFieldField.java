package data;

import imagerecognition.NeuralNetworkHandler;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MineFieldField implements NeighborObserver, Observable {
	public int x;
	public int y;
	public int widht;
	public int height;
	public int idx;
	public int idy;

	private BufferedImage image;
	private NeuralNetworkHandler nn;
	public static int minesLeft = 99;
	public static int fieldsLeft = 480;
	/**
	 * value = 0,1,2,3,4,5,6,7,8,-1
	 */
	private int value = -1;
	private double mineProbability = -1;

	public MineFieldField(NeuralNetworkHandler nn,int idx,int idy) {
		this.nn = nn;
		this.observers=new ArrayList<NeighborObserver>();
		this.idx=idx;
		this.idy=idy;
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setValue(int value) throws Exception {
		if (value >= -1 && value <= 8) {
			this.value = value;
		} else {
			throw new Exception("value must be between -1 and 8");
		}
	}

	public void analyzeImage() {
		this.value = this.nn.analyze(image);
	}

	//TODO: values 
	public int getValue() {
		return this.value;		
	}

	public double getProb() {
		if (this.value == -1) {
			if (mineProbability < 0) {
				return minesLeft / fieldsLeft;
			} else {
				return mineProbability;
			}
		} else {
			return -1;
		}

	}
	private ArrayList<NeighborObserver> observers;

	@Override
	public void addObservers(NeighborObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProb() {
		// TODO Auto-generated method stub
		
	}
}
