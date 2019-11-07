package com.mfq.n1_11_2;

public class MyThread extends Thread{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 500000; i++) {
			System.out.println("i = " + (i + 1));
		}
		
	}

}
