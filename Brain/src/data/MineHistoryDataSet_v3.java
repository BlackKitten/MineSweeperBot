package data;

public class MineHistoryDataSet_v3 extends MineHistoryDataSet {
	boolean toggle=false;
	public MineHistoryDataSet_v3(){
		super();
	}
	
	public void addToResults(int x,int y,MineField m){
		String line="";
		for(int i=x-2;i<=x+2;i++){
			for(int j=y-2;j<=y+2;j++){
				int value=-1;
				try{
					value=m.getField(i, j).getValue();
				}catch(IndexOutOfBoundsException e){
					//field is outside of minefield, value=-1(unknown);
				}
				line=line+value+"\t";
			}
		}
		results=results+"\n"+line;
		toggle=true;
	}
	
	public void addToResults(int result){
		if(toggle){
		this.results=this.results+result+"\t";
		toggle=false;
		}
	}
}
