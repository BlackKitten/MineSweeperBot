package data;

import imagerecognition.NeuralNetworkHandler;

public class MineField_v2 extends MineField {

	public MineField_v2(NeuralNetworkHandler nn) {
		super(nn);
		for(MineFieldField[] row_mField:this.getFields()){
			for(MineFieldField mField:row_mField){
				MineFieldField_v2 v2_mField=(MineFieldField_v2)mField;
				v2_mField.setNeigBors(this);
			}
		}
	}

}
