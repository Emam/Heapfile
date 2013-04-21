package heap;

import global.PageId;

import java.io.IOException;

public class test {

	/**
	 * @param args
	 */
	
	static int getNum(byte[] p,int offset){
		return((p[offset]&0xff << 12) + (p[offset+1]&0xff << 8) + (p[offset+2]&0xff << 4) + (p[offset+3]&0xff));  
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			PageId pid = new PageId(5);
			byte p[] = new byte[8];
			pid.writeToByteArray(p, 0);
			System.out.println(getNum(p,0));
			pid.writeToByteArray(p, 4);
			System.out.println(getNum(p,4));
			Tuple t = new Tuple(p,0,8);
			p=t.getTupleByteArray();
			System.out.println(getNum(p,0));
			System.out.println(getNum(p,4));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
