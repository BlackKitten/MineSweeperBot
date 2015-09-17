package data;

public class MineHistoryDataSet_v2 extends MineHistoryDataSet{
	
	protected MineFieldField_v2 lastfield;
	boolean toggle=false;
	
	public void addToResults(int x,int y,MineField m){
		lastfield=(MineFieldField_v2) m.getField(x, y);
		toggle=true;
	}
	
	public void addToResults(int result){
		if(toggle && !(lastfield==null)){
			//try{
		for(MineFieldField_v2.NeigBourField nField:lastfield.getNeighbors()){
			if(!(nField.field==null)){
			this.results=this.results+"\n"+nField.field.getValue()+"\t"+ nField.field.getNumberOfUnknownNeighbors()+"\t"+result;
			}
			}
			//}catch (NullPointerException e){
				
			//}
		toggle=false;
		}
	}
}
