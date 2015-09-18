package controller;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.neuroph.core.NeuralNetwork;

import util.ImageSplitterException;
import data.MineField;
import data.MineFieldField_v2;
import data.MineHistoryDataSet;

public class MineSweeperPlayer_v2 extends MineSweeperPlayer {

	public MineSweeperPlayer_v2(MineHistoryDataSet mHist) throws AWTException,
			ImageSplitterException, IOException {
		super(mHist);
	}

	protected NeuralNetwork createMineSolverNN() {
		return NeuralNetwork.createFromFile(new File("backup_0,08777.nnet"));
	}

	protected double getProb4XY(int x, int y, MineField m) {
		MineFieldField_v2 mField = (MineFieldField_v2) m.getField(x, y);
		double prob = 0;
		for (MineFieldField_v2.NeigBourField nField : mField.getNeighbors()) {
			prob = prob + getProbN(nField.field);
		}
		return prob;

	}

	private double getProbN(MineFieldField_v2 mField) {
		if (mField == null) {
			return 0;
		} else {
			double[] dbl_input = { mField.getValue(),
					mField.getNumberOfUnknownNeighbors() };
			nn.setInput(dbl_input);
			nn.calculate();
			return nn.getOutput()[0];
		}
	}
}
