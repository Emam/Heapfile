package heap;

import global.GlobalConst;
import global.PageId;
import global.RID;
import global.SystemDefs;

import java.awt.print.Pageable;
import java.io.IOException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import bufmgr.BufMgrException;
import bufmgr.BufferPoolExceededException;
import bufmgr.HashEntryNotFoundException;
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

	public Heapfile(String _name) throws HFException, HFBufMgrException,
			HFDiskMgrException, IOException {
		name = _name;
		try {
			if (SystemDefs.JavabaseDB.get_file_entry(name) == null) {
				SystemDefs.JavabaseDB.openDB(name);
				SystemDefs.JavabaseDB.allocate_page(new PageId());
			}
			Page p = null;
			SystemDefs.JavabaseDB.read_page(new PageId(), p);
			header = new HFPage(p);
		} catch (InvalidPageNumberException e) {
			// TODO Auto-generated catch block
			throw new HFBufMgrException();
			// e.printStackTrace();
		} catch (FileIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiskMgrException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new HFDiskMgrException();
		} catch (OutOfSpaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRunSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
<<<<<<< HEAD

	public int getRecCnt() throws InvalidSlotNumberException,
			InvalidTupleSizeException, HFDiskMgrException, HFBufMgrException,
			IOException {
		return 0;
	}

	public RID insertRecord(byte recPtr[]) throws InvalidSlotNumberException,
			InvalidTupleSizeException, SpaceNotAvailableException, HFException,
			HFBufMgrException, HFDiskMgrException, IOException {
		try {
			SystemDefs.JavabaseBM.pinPage(header.curPage,
					new Page(header.getpage()), false);
			RID r = header.firstRecord();
			Tuple t = null;
			byte[] slot = null;
			boolean found = false;
			while (!found) {
				for (int i = 0; i < header.getSlotCnt(); i++) {
					t = header.getRecord(r);
					slot = t.returnTupleByteArray();
					if (recPtr.length <= getNum(slot, 4)) {
						found = true;
						break;
					}
					r = header.nextRecord(r);
				}
				if (!found) {
					if(!getNextHeader()){
						slot=new byte[8];
						PageId pid=SystemDefs.JavabaseBM.newPage(new Page(), 1);
						pid.writeToByteArray(slot, 0);
						pid.pid=GlobalConst.MINIBASE_PAGESIZE;
						pid.writeToByteArray(slot, 4);
						Tuple newpage=new Tuple(slot,0,8);
						r=header.insertRecord(newpage.getTupleByteArray());
					}
				}
			}
			PageId pid = new PageId(getNum(slot, 0));
			Page p = null;
=======
	
	public int getRecCnt() throws InvalidSlotNumberException, InvalidTupleSizeException, HFDiskMgrException, HFBufMgrException, IOException{
		
		return 0;
	}
	
	 public RID insertRecord(byte recPtr[]) throws InvalidSlotNumberException, InvalidTupleSizeException, SpaceNotAvailableException, HFException, HFBufMgrException, HFDiskMgrException, IOException{
		 RID r=header.firstRecord();
		 Tuple t=header.getRecord(r);
		 try {
			PageId pid=new PageId(t.getIntFld(1));
			Page p=null;
>>>>>>> c47fa9d188c7588f6da62af6a5bc045ea2cfc870
			SystemDefs.JavabaseBM.pinPage(pid, p, false);
			r = new HFPage(p).insertRecord(recPtr);
			SystemDefs.JavabaseBM.unpinPage(pid, true);
			return r;

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
		} catch (HashEntryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
<<<<<<< HEAD
		return null;
	}

	public boolean deleteRecord(RID rid) throws InvalidSlotNumberException,
			InvalidTupleSizeException, HFException, HFBufMgrException,
			HFDiskMgrException, Exception {
		return false;
	}

	public boolean updateRecord(RID rid, Tuple newtuple)
			throws InvalidSlotNumberException, InvalidUpdateException,
			InvalidTupleSizeException, HFException, HFDiskMgrException,
			HFBufMgrException, Exception {
		Page page = null;
		SystemDefs.JavabaseBM.pinPage(rid.pageNo, page, false);
		HFPage cur = new HFPage(page);
		Tuple t = cur.getRecord(rid);
		if (t == null)
			return false;
		t.tupleSet(newtuple.getTupleByteArray(), newtuple.getOffset(),
				newtuple.getLength());
		return true;
	}

	public Tuple getRecord(RID rid) throws InvalidSlotNumberException,
			InvalidTupleSizeException, HFException, HFDiskMgrException,
			HFBufMgrException, Exception {
		return null;
	}

	public Scan openScan() throws InvalidTupleSizeException, IOException {
		return null;
	}

	public void deleteFile() throws InvalidSlotNumberException,
			FileAlreadyDeletedException, InvalidTupleSizeException,
			HFBufMgrException, HFDiskMgrException, IOException {
	}

	private boolean getNextHeader() throws IOException, InvalidPageNumberException, FileIOException, ReplacerException, HashOperationException, PageUnpinnedException, InvalidFrameNumberException, PageNotReadException, BufferPoolExceededException, PagePinnedException, BufMgrException, HashEntryNotFoundException, DiskMgrException{
		PageId pid=header.getNextPage();
		Page page;
		if(pid==null){
			page=new Page();
			pid=SystemDefs.JavabaseBM.newPage(page, 1);
			header.init(pid, page);
			return false;
		}
		page=null;
		SystemDefs.JavabaseDB.read_page(pid, page);
		SystemDefs.JavabaseBM.pinPage(pid, page, false);
		return true;
	}
	
	private int getNum(byte[] p, int offset) {
		return ((p[offset] & 0xff << 12) + (p[offset + 1] & 0xff << 8)
				+ (p[offset + 2] & 0xff << 4) + (p[offset + 3] & 0xff));
	}

=======
		 return null;
	 }
	 
	 public boolean deleteRecord(RID rid) throws InvalidSlotNumberException, InvalidTupleSizeException, HFException, HFBufMgrException, HFDiskMgrException, Exception{
		 Page page=null;
		 SystemDefs.JavabaseBM.pinPage(rid.pageNo, page, true);
		 HFPage hfp = new HFPage(page);
		 
		 try{
			 hfp.deleteRecord(rid);
			 return true;
		 }
		 catch(InvalidSlotNumberException e){
			 return false;
		 }
	 }
	 
	 public boolean updateRecord(RID rid, Tuple newtuple) throws InvalidSlotNumberException, InvalidUpdateException, InvalidTupleSizeException, HFException, HFDiskMgrException, HFBufMgrException, Exception{
		 
		 Page page=null;
		 SystemDefs.JavabaseBM.pinPage(rid.pageNo, page, false);
		 HFPage cur=new HFPage(page);
		 Tuple t=cur.getRecord(rid);
		 if(t==null)return false;
		 t.tupleSet(newtuple.getTupleByteArray(), newtuple.getOffset(), newtuple.getLength());
		 return true;
	 }
	
	 public Tuple getRecord(RID rid) throws InvalidSlotNumberException, InvalidTupleSizeException, HFException, HFDiskMgrException, HFBufMgrException, Exception{
		Page page=null;
		SystemDefs.JavabaseBM.pinPage(rid.pageNo, page, true);
		HFPage hfp = new HFPage(page);
		return hfp.getRecord(rid);
	 }
	 
	 public Scan openScan() throws InvalidTupleSizeException, IOException{
		return null;
	 }
	 
	 public void deleteFile() throws InvalidSlotNumberException, FileAlreadyDeletedException, InvalidTupleSizeException, HFBufMgrException, HFDiskMgrException, IOException{
		 try {
				
			 SystemDefs.JavabaseDB.delete_file_entry(name);
			
		} catch (FileEntryNotFoundException | FileIOException
				| InvalidPageNumberException | DiskMgrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
>>>>>>> c47fa9d188c7588f6da62af6a5bc045ea2cfc870
}
