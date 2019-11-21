package com.mfq.n2_1_8;

public class MyThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Sub sub = new Sub();
		sub.operateISubMethod();
	}

}
