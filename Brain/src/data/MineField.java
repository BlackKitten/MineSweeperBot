package data;

import imagerecognition.NeuralNetworkHandler;

public class MineField {
	private MineFieldField[][]  mineFields ;
	private NeuralNetworkHandler nn;
	
	public MineField(NeuralNetworkHandler nn){
		this.nn=nn;
		mineFields=new MineFieldField[30][16];
		for(int i=0;i<30;i++){
			for(int j=0;j<16;j++){
				mineFields[i][j]=new MineFieldField(nn,i,j);
				//mineFields[i][j].x=i;
				//mineFields[i][j].y=j;
			}
		}
	}
	

	public MineFieldField getField(int x,int y){
		return mineFields[x][y];
	}
	
	public MineFieldField[][] getFields(){
		return this.mineFields;
	}
	
	public String toString(){
		
		String str_printsel="";
		for(int i=0;i<mineFields.length;i++){
			//str_printsel=str_printsel+"\t";
			for(int j=mineFields[i].length-1;j>=0;j--){
				str_printsel=str_printsel+mineFields[i][j].getValue()+"\t";
			}
		}
		return str_printsel;		
	}
	
	public double[] toDouble(){
		double[] mArray = new double[mineFields.length*mineFields[0].length];
		for(int i=0;i<mineFields.length;i++){
			//str_printsel=str_printsel+"\t";
			for(int j=mineFields[i].length-1;j>=0;j--){
				mArray[i*mineFields[0].length+j]=mineFields[i][j].getValue();
			}
		}
		return mArray;
	}
}
