package heap;

import global.RID;
import heap.Tuple;


import java.io.IOException;

public class Scan {

	public Scan(Heapfile hf) throws InvalidTupleSizeException, IOException{
		
	}
	
	public Tuple getNext(RID rid) throws InvalidTupleSizeException, IOException{
		return null;
	}
	
	public boolean position(RID rid) throws InvalidTupleSizeException, IOException{
		return false;
	}
	
	public void closescan(){
		
	}
}
