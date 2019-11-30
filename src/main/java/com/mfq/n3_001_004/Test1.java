package com.mfq.n3_001_004;

public class Test1 {
	public static void main(String[] args) {
		String newString = new String("");
		try {
			newString.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
