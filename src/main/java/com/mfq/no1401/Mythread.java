package com.mfq.no1401;

public class Mythread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("run=" + this.isAlive());
	}

}
