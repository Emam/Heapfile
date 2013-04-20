package heap;

import global.RID;
import global.SystemDefs;

import java.io.IOException;
import java.util.ArrayList;

import diskmgr.DiskMgrException;
import diskmgr.FileIOException;
import diskmgr.InvalidPageNumberException;
import diskmgr.Page;

public class Heapfile {

	String name;
	ArrayList<Slot> slots;
	
	public Heapfile(String _name) throws HFException, HFBufMgrException, HFDiskMgrException, IOException{
		name=_name;
		try {
			if(SystemDefs.JavabaseDB.get_file_entry(name)==null){
				SystemDefs.JavabaseDB.openDB(name);
			}
		} catch (InvalidPageNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiskMgrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRecCnt() throws InvalidSlotNumberException, InvalidTupleSizeException, HFDiskMgrException, HFBufMgrException, IOException{
		return 0;
	}
	
	 public RID insertRecord(byte recPtr[]) throws InvalidSlotNumberException, InvalidTupleSizeException, SpaceNotAvailableException, HFException, HFBufMgrException, HFDiskMgrException, IOException{
		return null;
	 }
	 
	 public boolean deleteRecord(RID rid) throws InvalidSlotNumberException, InvalidTupleSizeException, HFException, HFBufMgrException, HFDiskMgrException, Exception{
		return false;
	 }
	 
	 public boolean updateRecord(RID rid,
             Tuple newtuple) throws InvalidSlotNumberException, InvalidUpdateException, InvalidTupleSizeException, HFException, HFDiskMgrException, HFBufMgrException, Exception{
				return false;
	 }
	
	 public Tuple getRecord(RID rid) throws InvalidSlotNumberException, InvalidTupleSizeException, HFException, HFDiskMgrException, HFBufMgrException, Exception{
		return null;
	 }
	 
	 public Scan openScan() throws InvalidTupleSizeException, IOException{
		return null;
	 }
	 
	 public void deleteFile() throws InvalidSlotNumberException, FileAlreadyDeletedException, InvalidTupleSizeException, HFBufMgrException, HFDiskMgrException, IOException{
	 }
}
