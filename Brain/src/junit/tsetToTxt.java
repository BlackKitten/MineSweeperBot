package junit;

import org.junit.internal.runners.model.EachTestNotifier;
import org.neuroph.core.data.DataSet;

public class tsetToTxt {

	public static void main(String[] args) {
		//DataSet dataset = new DataSet(300,4);
		DataSet dataset=DataSet.load("20minedataset01234ub5x5.tset");
		for (String columnName:dataset.getColumnNames()){
			System.out.println(columnName);
		}
		dataset.saveAsTxt("20minedataset01234ub5x5.txt", ",");

	}

}
