package com.mfq.n2_1_8;

public class Main {
	public int i = 10;
	
	synchronized public void operateIMainMethod() {
		try {
			i--;
			System.out.println("main pring i=" + i);
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
