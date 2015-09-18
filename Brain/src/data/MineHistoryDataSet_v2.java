package data;

public class MineHistoryDataSet_v2 extends MineHistoryDataSet{
	
	//protected MineFieldField_v2 lastfield;
	protected String[] lastStrings=new String[8];
	boolean toggle=false;
	
	public void addToResults(int x,int y,MineField m){
		//System.out.println("\n"+x+" "+y);
		MineFieldField_v2 lastfield=(MineFieldField_v2) m.getField(x, y);
		int i=0;
		for(MineFieldField_v2.NeigBourField nField:lastfield.getNeighbors()){
			try{
			this.lastStrings[i]=nField.field.getValue()+"\t"+ nField.field.getNumberOfUnknownNeighbors()+"\t";
			}catch(NullPointerException e){}
			//System.out.println(this.lastStrings[i]);
			i++;
		}
		toggle=true;
		//temp
		
	}
	
	public void addToResults(int result){
		if(toggle){
			for(int i=0;i<8;i++){
				if(!(this.lastStrings[i]==null)){
				this.results=this.results+"\n"+this.lastStrings[i]+result;
				}
			}
		toggle=false;
		}
	}
}
