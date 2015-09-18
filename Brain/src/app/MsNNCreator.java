package app;

import org.neuroph.core.data.DataSet;

import controller.Controller;

public class MsNNCreator {

	public static void main(String[] args) {
		Controller troller=new Controller(DataSet.createFromFile("test_v2_2.txt", 2, 1, "\t",false));
		troller.startView();

	}

}
