package heap;

import global.PageId;
import global.RID;
import global.SystemDefs;

import java.io.IOException;

import bufmgr.BufMgrException;
import bufmgr.BufferPoolExceededException;
import bufmgr.HashOperationException;
import bufmgr.InvalidFrameNumberException;
import bufmgr.PageNotReadException;
import bufmgr.PagePinnedException;
import bufmgr.PageUnpinnedException;
import bufmgr.ReplacerException;

import diskmgr.DiskMgrException;
import diskmgr.FileIOException;
import diskmgr.InvalidPageNumberException;
import diskmgr.InvalidRunSizeException;
import diskmgr.OutOfSpaceException;
import diskmgr.Page;

public class Heapfile {

	String name;
	HFPage header;
	
	public Heapfile(String _name) throws HFException, HFBufMgrException, HFDiskMgrException, IOException{
		name=_name;
		try {
			if(SystemDefs.JavabaseDB.get_file_entry(name)==null){
				SystemDefs.JavabaseDB.openDB(name);
				SystemDefs.JavabaseDB.allocate_page(new PageId());
			}
			Page p=null;
			SystemDefs.JavabaseDB.read_page(new PageId(), p);
			header = new HFPage(p);
		} catch (InvalidPageNumberException e) {
			// TODO Auto-generated catch block
			throw new HFBufMgrException();
			//e.printStackTrace();
		} catch (FileIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiskMgrException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new HFDiskMgrException();
		} catch (OutOfSpaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRunSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRecCnt() throws InvalidSlotNumberException, InvalidTupleSizeException, HFDiskMgrException, HFBufMgrException, IOException{
		return 0;
	}
	
	 public RID insertRecord(byte recPtr[]) throws InvalidSlotNumberException, InvalidTupleSizeException, SpaceNotAvailableException, HFException, HFBufMgrException, HFDiskMgrException, IOException{
		 RID r=header.firstRecord();
		 Tuple t=header.getRecord(r);
		 try {
			
			PageId pid=new PageId(t.getIntFld(1));
			Page p=null;
			SystemDefs.JavabaseBM.pinPage(pid, p, false);
			HFPage hp=new HFPage(p);
			return hp.insertRecord(recPtr);
			
		} catch (FieldNumberOutOfBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReplacerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HashOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PageUnpinnedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFrameNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PageNotReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BufferPoolExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PagePinnedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BufMgrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 
	 public boolean deleteRecord(RID rid) throws InvalidSlotNumberException, InvalidTupleSizeException, HFException, HFBufMgrException, HFDiskMgrException, Exception{
		return false;
	 }
	 
	 public boolean updateRecord(RID rid,
             Tuple newtuple) throws InvalidSlotNumberException, InvalidUpdateException, InvalidTupleSizeException, HFException, HFDiskMgrException, HFBufMgrException, Exception{
		 Page page=null;
		 SystemDefs.JavabaseBM.pinPage(rid.pageNo, page, false);
		 HFPage cur=new HFPage(page);
		 Tuple t=cur.getRecord(rid);
		 if(t==null)return false;
		 t.tupleSet(newtuple.getTupleByteArray(), newtuple.getOffset(), newtuple.getLength());
		 return true;
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
