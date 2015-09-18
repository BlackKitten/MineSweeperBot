package data;

import imagerecognition.NeuralNetworkHandler;

//new alg:
//ida,valueB,valuesPer -> b-prob-a
//=>SUM
public class MineFieldField_v2 extends MineFieldField {

	
	private NeigBourField[] neigBourField_neigbors;
			
	public MineFieldField_v2(NeuralNetworkHandler nn, int idx, int idy) {
		super(nn, idx, idy);
		
		
	}
	
	public NeigBourField[] getNeighbors(){
		return this.neigBourField_neigbors;
	}
	
	//Needs to be manually called after init of all fields
	public void setNeigBors(MineField m){
		//TODO: check 4 values beyond border;
		int[] arr_intx={-1,0,1,1,1,0,-1,-1};
		int[] arr_inty={-1,-1,-1,0,1,1,1,0};
		neigBourField_neigbors=new NeigBourField[8];
		for(int i=0;i<8;i++){
			neigBourField_neigbors[i]=new NeigBourField();
			try{
				neigBourField_neigbors[i].field=(MineFieldField_v2) m.getField(this.idx+arr_intx[i], this.idy+arr_inty[i]); //not set
			}catch(ArrayIndexOutOfBoundsException f){
				
			}
		}	
	}
	
	public int getNumberOfUnknownNeighbors(){
		int sum=0;
		for(NeigBourField nField:neigBourField_neigbors){
			if(!(nField.field==null) && nField.field.getValue()==-1){
				sum++;
			}
		}
		return sum;
	}
	/**
	 * 
	 * @param field
	 * @return
	 * @throws NullPointerException
	 */
	public double getMineProbOfField(MineFieldField field){
		for(NeigBourField nfield: neigBourField_neigbors){
			if(nfield.field.equals(field)){
				return nfield.prob;
			}
		}
		throw new NullPointerException("Field not found among neibors");
	}

	public class NeigBourField{
		//field=null if this neigbor does not exist 4 outerobject.
		public MineFieldField_v2 field;
		//probability neigborField containing mines, according to the outerObject,
		//-1 : invalid square (eg outside of minefield border) or not set
		//0-1:probablitity of subjectsquare containing a mine according to this field.
		double prob=-1;
	}
}
