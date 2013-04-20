package heap;

public class Slot {

	int id,offset,length;
	public Slot(int _id,int _offset,int _length){
		id=_id;
		offset=_offset;
		length=_length;
	}
	
	public boolean equals(Object o){
		return (((Slot)o).id==id);
	}
}
